import java.util.ArrayList;
import java.util.HashSet;

public class Pitcher {
    private final String name, teamName;
    private final ArrayList<Pitch> pitches = new ArrayList<>();
    public static final int MIN = 0;
    public static final int AVG = 1;
    public static final int MAX = 2;

    public Pitcher(String teamName, String pitcherName) {
        name = pitcherName;
        this.teamName = teamName;
    }

    public void addPitch(Pitch p) {
        pitches.add(p);
    }

    public ArrayList<Pitch> getPitches() {
        return pitches;
    }

    public String[] pitchNames() {
        HashSet<String> pitchNames = new HashSet<>();
        for(Pitch p : pitches) {
            pitchNames.add(p.getPitch());
        }
        return pitchNames.toArray(new String[0]);
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public int strikes(String... pitchName) {
        int count = 0;
        for(Pitch p : pitches) {
            if(pitchName.length > 0) {
                count += p.getPitch().equals(pitchName[0]) && p.isStrike() ? 1 : 0;
            } else {
                count += p.isStrike() ? 1 : 0;
            }
        }
        return count;
    }

    public int balls(String... pitchName) {
        int count = 0;
        for(Pitch p : pitches) {
            if(pitchName.length > 0) {
                count += p.getPitch().equals(pitchName[0]) && !p.isStrike() ? 1 : 0;
            } else {
                count += p.isStrike() ? 0 : 1;
            }
        }
        return count;
    }

    public int pitchCount(String... pitchName) {
        if(pitchName.length == 0) return pitches.size();
        int count = 0;
        for(Pitch p : pitches) {
            count += p.getPitch().equals(pitchName[0]) ? 1 : 0;
        }
        return count;
    }

    public float velocity(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitch p : pitches) {
            if(p.getPitch().equals(pitchName)) {
                float velo = p.getRelSpeed();
                if(mode == AVG) {
                    sum += velo;
                    count++;
                } else if(mode == MIN && (velo < min || min == 0)) {
                    min = velo;
                } else if(mode == MAX && (velo > max || max == 0)) {
                    max = velo;
                }
            }
        }
        if(mode == MIN) {
            return min;
        } else if(mode == MAX) {
            return max;
        }
        return sum / count;
    }

    public float spinRate(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitch p : pitches) {
            if(p.getPitch().equals(pitchName)) {
                float spinRate = p.getSpinRate();
                if(mode == AVG) {
                    sum += spinRate;
                    count++;
                } else if(mode == MIN && (spinRate < min || min == 0)) {
                    min = spinRate;
                } else if(mode == MAX && (spinRate > max || max == 0)) {
                    max = spinRate;
                }
            }
        }
        if(mode == MIN) {
            return min;
        } else if(mode == MAX) {
            return max;
        }
        return sum / count;
    }

    public float vertBreak(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitch p : pitches) {
            if(p.getPitch().equals(pitchName)) {
                float vertBreak = p.getVerticalBreak();
                if(mode == AVG) {
                    sum += vertBreak;
                    count++;
                } else if(mode == MIN && (vertBreak < min || min == 0)) {
                    min = vertBreak;
                } else if(mode == MAX && (vertBreak > max || max == 0)) {
                    max = vertBreak;
                }
            }
        }
        if(mode == MIN) {
            return min;
        } else if(mode == MAX) {
            return max;
        }
        return sum / count;
    }

    public float horzBreak(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitch p : pitches) {
            if(p.getPitch().equals(pitchName)) {
                float horzBreak = p.getHorizontalBreak();
                if(mode == AVG) {
                    sum += horzBreak;
                    count++;
                } else if(mode == MIN && (horzBreak < min || min == 0)) {
                    min = horzBreak;
                } else if(mode == MAX && (horzBreak > max || max == 0)) {
                    max = horzBreak;
                }
            }
        }
        if(mode == MIN) {
            return min;
        } else if(mode == MAX) {
            return max;
        }
        return sum / count;
    }

    public float extension(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitch p : pitches) {
            if(p.getPitch().equals(pitchName)) {
                float ext = p.getExtension();
                if(mode == AVG) {
                    sum += ext;
                    count++;
                } else if(mode == MIN && (ext < min || min == 0)) {
                    min = ext;
                } else if(mode == MAX && (ext > max || max == 0)) {
                    max = ext;
                }
            }
        }
        if(mode == MIN) {
            return min;
        } else if(mode == MAX) {
            return max;
        }
        return sum / count;
    }

    public float relHeight(String pitchName, int mode) {
        float sum = 0, min = 0, max = 0;
        int count = 0;
        for(Pitch p : pitches) {
            if(p.getPitch().equals(pitchName)) {
                float relH = p.getRelHeight();
                if(mode == AVG) {
                    sum += relH;
                    count++;
                } else if(mode == MIN && (relH < min || min == 0)) {
                    min = relH;
                } else if(mode == MAX && (relH > max || max == 0)) {
                    max = relH;
                }
            }
        }
        if(mode == MIN) {
            return min;
        } else if(mode == MAX) {
            return max;
        }
        return sum / count;
    }

    public int strikeouts(String... pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            if(pitchName.length > 0) {
                sum += p.getPitch().equals(pitchName[0]) && p.isStrikeout() ? 1 : 0;
            } else {
                sum += p.isStrikeout() ? 1 : 0;
            }
        }
        return sum;
    }

    public int walks(String... pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            if(pitchName.length > 0) {
                sum += p.getPitch().equals(pitchName[0]) && p.isWalk() ? 1 : 0;
            } else {
                sum += p.isWalk() ? 1 : 0;
            }
        }
        return sum;
    }

    public int groundBalls(String... pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            if(pitchName.length > 0) {
                sum += p.getPitch().equals(pitchName[0]) && p.getContact().equals("GroundBall") ? 1 : 0;
            } else {
                sum += p.getContact().equals("GroundBall") ? 1 : 0;
            }
        }
        return sum;
    }

    public int lineDrives(String... pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            if(pitchName.length > 0) {
                sum += p.getPitch().equals(pitchName[0]) && p.getContact().equals("LineDrive") ? 1 : 0;
            } else {
                sum += p.getContact().equals("LineDrive") ? 1 : 0;
            }
        }
        return sum;
    }

    public int flyBalls(String... pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            if(pitchName.length > 0) {
                sum += p.getPitch().equals(pitchName[0]) && p.getContact().equals("FlyBall") ? 1 : 0;
            } else {
                sum += p.getContact().equals("FlyBall") ? 1 : 0;
            }
        }
        return sum;
    }

    public int popUps(String... pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            if(pitchName.length > 0) {
                sum += p.getPitch().equals(pitchName[0]) && p.getContact().equals("Popup") ? 1 : 0;
            } else {
                sum += p.getContact().equals("Popup") ? 1 : 0;
            }
        }
        return sum;
    }

    public boolean equals(Pitcher pitcher) {
        return name.equals(pitcher.getName()) && teamName.equals(pitcher.getTeamName());
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