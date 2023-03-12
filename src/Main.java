import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.util.ArrayList;

public class Main {
    private static final ArrayList<Team> teams = new ArrayList<>();

    public static void main(String[] args) {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Raw Trackman .csv File(s)");
        jfc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".csv") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return ".csv";
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
                    while (line != null) {
                        if (count++ > 0 && !line.isBlank()) parsePitch(line);
                        line = br.readLine();
                    }
                    exportData(pathName.substring(0, pathName.length() - file.getName().length()), file.getName());
                } catch (Exception e) {
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
        boolean teamFound = false;
        for(Team t : teams) {
            if(p.getTeamName().equals(t.getTeamName())) {
                teamFound = true;
                p = t.addPitcher(p);
                break;
            }
        }
        if(!teamFound) {
            Team t = new Team(p.getTeamName());
            t.addPitcher(p);
            teams.add(t);
        }
        data = new String[]{data[21].equals("Undefined") ? data[22] : data[21], data[23], data[24], data[25], data[30],
                data[33], data[38], data[36], data[40], data[41]};
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
    }

    private static void exportData(String pathName, String fileName) {
        String year = fileName.substring(0, 4);
        String month = fileName.substring(4, 6);
        String day = fileName.substring(6, 8);
        for(Team t : teams) {
            File folder = new File(String.format("%s\\%s Pitch Report %s-%s-%s", pathName, t.getTeamName(), month, day, year));
            if(folder.mkdir()) System.out.println("New directory: " + folder.getAbsolutePath());
            for(Pitcher p : t.getPitchers()) {
                try {
                    File file = new File("%s\\%s-%s-%s-%s.csv".formatted(folder.getAbsolutePath(), p.getName(), month, day, year));
                    if(file.createNewFile()) System.out.println("New file created: " + file.getName());
                    FileWriter outputFile = new FileWriter(file);
                    outputFile.write(p.buildCSV());
                    outputFile.close();
                } catch(Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
            try {
                File file = new File("%s\\%s TOTALS-%s-%s-%s.csv".formatted(folder.getAbsolutePath(), t.getTeamName(), month, day, year));
                if(file.createNewFile()) System.out.println("New file created: " + file.getName());
                FileWriter outputFile = new FileWriter(file);
                outputFile.write(t.buildCSV());
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
}