import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Team {
    private String teamName;
    private final ArrayList<Pitcher> pitchers = new ArrayList<>();

    public Team(String name) {
        setTeamName(name);
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<Pitcher> getPitchers() {
        return pitchers;
    }

    public Pitcher addPitcher(Pitcher p) {
        for(Pitcher pitcher : pitchers) {
            if(p.equals(pitcher)) {
                return pitcher;
            }
        }
        pitchers.add(p);
        return p;
    }

    private String[] pitchNames() {
        HashSet<String> pitches = new HashSet<>();
        for(Pitcher p : pitchers) {
            pitches.addAll(Arrays.asList(p.pitchNames()));
        }
        return pitches.toArray(new String[0]);
    }

    private int pitchCount(String... pitchName) {
        int count = 0;
        for(Pitcher p : pitchers) {
            count += p.pitchCount(pitchName);
        }
        return count;
    }

    public int strikes(String... pitchName) {
        int count = 0;
        for(Pitcher p : pitchers) {
            count += p.strikes(pitchName);
        }
        return count;
    }

    public int balls(String... pitchName) {
        int count = 0;
        for(Pitcher p : pitchers) {
            count += p.balls(pitchName);
        }
        return count;
    }

    public float velocity(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitcher pitcher : pitchers) {
            for(Pitch p : pitcher.getPitches()) {
                if(p.getPitch().equals(pitchName)) {
                    float velo = p.getRelSpeed();
                    if(mode == Pitcher.AVG) {
                        sum += velo;
                        count++;
                    } else if(mode == Pitcher.MIN && (velo < min || min == 0)) {
                        min = velo;
                    } else if(mode == Pitcher.MAX && (velo > max || max == 0)) {
                        max = velo;
                    }
                }
            }
        }
        if(mode == Pitcher.MIN) {
            return min;
        } else if(mode == Pitcher.MAX) {
            return max;
        }
        return sum / count;
    }

    public float spinRate(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitcher pitcher : pitchers) {
            for(Pitch p : pitcher.getPitches()) {
                if(p.getPitch().equals(pitchName)) {
                    float spinRate = p.getSpinRate();
                    if(mode == Pitcher.AVG) {
                        sum += spinRate;
                        count++;
                    } else if(mode == Pitcher.MIN && (spinRate < min || min == 0)) {
                        min = spinRate;
                    } else if(mode == Pitcher.MAX && (spinRate > max || max == 0)) {
                        max = spinRate;
                    }
                }
            }
        }
        if(mode == Pitcher.MIN) {
            return min;
        } else if(mode == Pitcher.MAX) {
            return max;
        }
        return sum / count;
    }

    public float vertBreak(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitcher pitcher : pitchers) {
            for(Pitch p : pitcher.getPitches()) {
                if(p.getPitch().equals(pitchName)) {
                    float verticalBreak = p.getVerticalBreak();
                    if(mode == Pitcher.AVG) {
                        sum += verticalBreak;
                        count++;
                    } else if(mode == Pitcher.MIN && (verticalBreak < min || min == 0)) {
                        min = verticalBreak;
                    } else if(mode == Pitcher.MAX && (verticalBreak > max || max == 0)) {
                        max = verticalBreak;
                    }
                }
            }
        }
        if(mode == Pitcher.MIN) {
            return min;
        } else if(mode == Pitcher.MAX) {
            return max;
        }
        return sum / count;
    }

    public float horzBreak(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitcher pitcher : pitchers) {
            for(Pitch p : pitcher.getPitches()) {
                if(p.getPitch().equals(pitchName)) {
                    float horizontalBreak = p.getHorizontalBreak();
                    if(mode == Pitcher.AVG) {
                        sum += horizontalBreak;
                        count++;
                    } else if(mode == Pitcher.MIN && (horizontalBreak < min || min == 0)) {
                        min = horizontalBreak;
                    } else if(mode == Pitcher.MAX && (horizontalBreak > max || max == 0)) {
                        max = horizontalBreak;
                    }
                }
            }
        }
        if(mode == Pitcher.MIN) {
            return min;
        } else if(mode == Pitcher.MAX) {
            return max;
        }
        return sum / count;
    }

    public float extension(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitcher pitcher : pitchers) {
            for(Pitch p : pitcher.getPitches()) {
                if(p.getPitch().equals(pitchName)) {
                    float ext = p.getExtension();
                    if(mode == Pitcher.AVG) {
                        sum += ext;
                        count++;
                    } else if(mode == Pitcher.MIN && (ext < min || min == 0)) {
                        min = ext;
                    } else if(mode == Pitcher.MAX && (ext > max || max == 0)) {
                        max = ext;
                    }
                }
            }
        }
        if(mode == Pitcher.MIN) {
            return min;
        } else if(mode == Pitcher.MAX) {
            return max;
        }
        return sum / count;
    }

    public float relHeight(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitcher pitcher : pitchers) {
            for(Pitch p : pitcher.getPitches()) {
                if(p.getPitch().equals(pitchName)) {
                    float relHeight = p.getRelHeight();
                    if(mode == Pitcher.AVG) {
                        sum += relHeight;
                        count++;
                    } else if(mode == Pitcher.MIN && (relHeight < min || min == 0)) {
                        min = relHeight;
                    } else if(mode == Pitcher.MAX && (relHeight > max || max == 0)) {
                        max = relHeight;
                    }
                }
            }
        }
        if(mode == Pitcher.MIN) {
            return min;
        } else if(mode == Pitcher.MAX) {
            return max;
        }
        return sum / count;
    }

    public int strikeouts(String... pitchName) {
        int sum = 0;
        for(Pitcher p : pitchers) {
            sum += p.strikeouts(pitchName);
        }
        return sum;
    }

    public int walks(String... pitchName) {
        int sum = 0;
        for(Pitcher p : pitchers) {
            sum += p.walks(pitchName);
        }
        return sum;
    }

    public int groundBalls(String... pitchName) {
        int sum = 0;
        for(Pitcher p : pitchers) {
            sum += p.groundBalls(pitchName);
        }
        return sum;
    }

    public int lineDrives(String... pitchName) {
        int sum = 0;
        for(Pitcher p : pitchers) {
            sum += p.lineDrives(pitchName);
        }
        return sum;
    }

    public int flyBalls(String... pitchName) {
        int sum = 0;
        for(Pitcher p : pitchers) {
            sum += p.flyBalls(pitchName);
        }
        return sum;
    }

    public int popUps(String... pitchName) {
        int sum = 0;
        for(Pitcher p : pitchers) {
            sum += p.popUps(pitchName);
        }
        return sum;
    }

    public String buildCSV() {
        String[] pitchNames = pitchNames();
        StringBuilder csv = new StringBuilder();
        csv.append(String.format("Pitches,Total (%d),Strikes (%d),Balls (%d),Strikeouts (%d),Walks (%d)",
                pitchCount(), strikes(), balls(), strikeouts(), walks())).append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%d,%d,%d,%d,%d", pn, pitchCount(pn), strikes(pn), balls(pn),
                    strikeouts(pn), walks(pn))).append("\n");
        }
        csv.append("\n").append(String.format("Contact,Groundballs (%d),Line drives (%d),Flyballs (%d),Popups (%d)",
                groundBalls(), lineDrives(), flyBalls(), popUps())).append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%d,%d,%d,%d", pn, groundBalls(pn), lineDrives(pn), flyBalls(pn), popUps(pn))).append("\n");
        }
        csv.append("\n").append("Velocity,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, velocity(pn, 0), velocity(pn, 1), velocity(pn, 2))).append("\n");
        }
        csv.append("\n").append("Spin Rate,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, spinRate(pn, 0), spinRate(pn, 1), spinRate(pn, 2))).append("\n");
        }
        csv.append("\n").append("Vertical Break,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, vertBreak(pn, 0), vertBreak(pn, 1), vertBreak(pn, 2))).append("\n");
        }
        csv.append("\n").append("Horizontal Break,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, horzBreak(pn, 0), horzBreak(pn, 1), horzBreak(pn, 2))).append("\n");
        }
        csv.append("\n").append("Extension,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, extension(pn, 0), extension(pn, 1), extension(pn, 2))).append("\n");
        }
        csv.append("\n").append("Release Height,Min,Avg,Max").append("\n");
        for(String pn : pitchNames) {
            csv.append(String.format("%s,%.2f,%.2f,%.2f", pn, relHeight(pn, 0), relHeight(pn, 1), relHeight(pn, 2))).append("\n");
        }
        return csv.toString();
    }
}