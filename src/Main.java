import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main {
    private static final ArrayList<Pitcher> pitchers = new ArrayList<>();

    public static void main(String[] args) {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Open Trackman .csv file");
        jfc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".csv") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return ".csv or directory";
            }
        });
        jfc.setMultiSelectionEnabled(true);
        int val = jfc.showOpenDialog(null);
        if(val == JFileChooser.OPEN_DIALOG) {
            File[] files = jfc.getSelectedFiles();
            for(File file : files) {
                String pathName = file.getAbsolutePath();
                try {
                    FileInputStream fis = new FileInputStream(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line = br.readLine();
                    int count = 0;
                    while(line != null) {
                        if(count++ > 0) parsePitch(line);
                        line = br.readLine();
                    }
                    exportData(pathName.substring(0, pathName.length() - file.getName().length()), file.getName());
                } catch(Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        System.exit(0);
    }

    private static void parsePitch(String line) {
        String[] data = line.split(",");
        Pitcher p = new Pitcher(data[9], data[6].substring(1, data[6].length() - 1) + " " + data[5].substring(1));
        for(Pitcher pitcher : pitchers) {
            if(p.equals(pitcher)) {
                p = pitcher;
                break;
            }
        }
        data = new String[]{data[21].equals("Undefined") ? data[22] : data[21], data[23], data[24], data[25], data[30], data[33], data[38], data[36], data[40], data[41]};
        String pitchType = data[0];
        boolean isStrike = data[1].startsWith("Strike") || data[1].equals("FoulBall") || data[1].equals("InPlay");
        boolean isStrikeout = data[2].equals("Strikeout");
        boolean isWalk = data[2].equals("Walk");
        String contact = data[3];
        float relSpeed = data[4].isEmpty() ? 0 : Float.parseFloat(data[4]);
        float spinRate = data[5].isEmpty() ? 0 : Float.parseFloat(data[5]);
        float extension = data[6].isEmpty() ? 0 : Float.parseFloat(data[6]);
        float relHeight = data[7].isEmpty() ? 0 : Float.parseFloat(data[7]);
        float vertBreak = data[8].isEmpty() ? 0 : Float.parseFloat(data[8]);
        float horzBreak = data[9].isEmpty() ? 0 : Float.parseFloat(data[9]);
        p.addPitch(new Pitch(pitchType, isStrike, isStrikeout, isWalk, contact, relSpeed, spinRate, extension, relHeight, vertBreak, horzBreak));
        if(!pitchers.contains(p)) {
            pitchers.add(p);
        }
    }

    private static void exportData(String pathName, String fileName) {
        String year = fileName.substring(0, 4);
        String month = fileName.substring(4, 6);
        String day = fileName.substring(6, 8);
        for(Pitcher p : pitchers) {
            File folder = new File(String.format("%s\\%s Pitch Report %s-%s-%s", pathName, p.getTeamName(), month, day, year));
            if(folder.mkdir()) System.out.println("New directory: " + folder.getAbsolutePath());
            String csv = buildCSV(p);
            try {
                File file = new File("%s\\%s-%s-%s-%s.csv".formatted(folder.getAbsolutePath(), p.getName(), month, day, year));
                if(file.createNewFile()) System.out.println("New file created: " + file.getName());
                FileWriter outputFile = new FileWriter(file);
                outputFile.write(csv);
                outputFile.close();
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        try {
            Desktop.getDesktop().open(new File(pathName));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static String buildCSV(Pitcher p) {
        String[] pitchNames = p.pitchNames();
        StringBuilder csv = new StringBuilder();
        csv.append(String.format("Pitches,Total (%d),Strikes (%d),Balls (%d),Strikeouts (%d),Walks (%d)",
                p.pitchCount(), p.strikes(), p.balls(), p.strikeouts(), p.walks())).append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%d,%d,%d,%d,%d", pn, p.pitchCount(pn), p.strikes(pn), p.balls(pn),
                    p.strikeouts(pn), p.walks(pn))).append("\n");
        }
        csv.append("\n").append(String.format("Contact,Groundballs (%d),Line drives (%d),Flyballs (%d),Popups (%d)",
                p.groundBalls(), p.lineDrives(), p.flyBalls(), p.popUps())).append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%d,%d,%d,%d", pn, p.groundBalls(pn), p.lineDrives(pn), p.flyBalls(pn), p.popUps(pn))).append("\n");
        }
        csv.append("\n").append("Velocity,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, p.velocity(pn, 0), p.velocity(pn, 1), p.velocity(pn, 2))).append("\n");
        }
        csv.append("\n").append("Spin Rate,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, p.spinRate(pn, 0), p.spinRate(pn, 1), p.spinRate(pn, 2))).append("\n");
        }
        csv.append("\n").append("Vertical Break,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, p.vertBreak(pn, 0), p.vertBreak(pn, 1), p.vertBreak(pn, 2))).append("\n");
        }
        csv.append("\n").append("Horizontal Break,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, p.horzBreak(pn, 0), p.horzBreak(pn, 1), p.horzBreak(pn, 2))).append("\n");
        }
        csv.append("\n").append("Extension,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, p.extension(pn, 0), p.extension(pn, 1), p.extension(pn, 2))).append("\n");
        }
        csv.append("\n").append("Release Height,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, p.relHeight(pn, 0), p.relHeight(pn, 1), p.relHeight(pn, 2))).append("\n");
        }
        return csv.toString();
    }
}