import java.util.ArrayList;

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

    public String[] pitchNames() {
        ArrayList<String> pitchNames = new ArrayList<>();
        for(Pitch p : pitches) {
            if(!pitchNames.contains(p.getPitch())) {
                pitchNames.add(p.getPitch());
            }
        }
        String[] ans = new String[pitchNames.size()];
        for(int i = 0; i < ans.length; i++) {
            ans[i] = pitchNames.get(i);
        }
        return ans;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public int strikes(String pitchName) {
        int count = 0;
        for(Pitch p : pitches) {
            count += p.getPitch().equals(pitchName) && p.isStrike() ? 1 : 0;
        }
        return count;
    }

    public int strikes() {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.isStrike() ? 1 : 0;
        }
        return sum;
    }

    public int balls(String pitchName) {
        int count = 0;
        for(Pitch p : pitches) {
            count += p.getPitch().equals(pitchName) && !p.isStrike() ? 1 : 0;
        }
        return count;
    }

    public int balls() {
        return pitchCount() - strikes();
    }

    public int pitchCount(String pitchName) {
        int count = 0;
        for(Pitch p : pitches) {
            count += p.getPitch().equals(pitchName) ? 1 : 0;
        }
        return count;
    }

    public int pitchCount() {
        return pitches.size();
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

    public int strikeouts(String pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getPitch().equals(pitchName) && p.isStrikeout() ? 1 : 0;
        }
        return sum;
    }

    public int strikeouts() {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.isStrikeout() ? 1 : 0;
        }
        return sum;
    }

    public int walks(String pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getPitch().equals(pitchName) && p.isWalk() ? 1 : 0;
        }
        return sum;
    }

    public int walks() {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.isWalk() ? 1 : 0;
        }
        return sum;
    }

    public int groundBalls(String pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getPitch().equals(pitchName) && p.getContact().equals("GroundBall") ? 1 : 0;
        }
        return sum;
    }
    public int groundBalls() {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getContact().equals("GroundBall") ? 1 : 0;
        }
        return sum;
    }

    public int lineDrives(String pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getPitch().equals(pitchName) && p.getContact().equals("LineDrive") ? 1 : 0;
        }
        return sum;
    }

    public int lineDrives() {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getContact().equals("LineDrive") ? 1 : 0;
        }
        return sum;
    }

    public int flyBalls(String pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getPitch().equals(pitchName) && p.getContact().equals("FlyBall") ? 1 : 0;
        }
        return sum;
    }

    public int flyBalls() {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getContact().equals("FlyBall") ? 1 : 0;
        }
        return sum;
    }

    public int popUps(String pitchName) {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getPitch().equals(pitchName) && p.getContact().equals("Popup") ? 1 : 0;
        }
        return sum;
    }

    public int popUps() {
        int sum = 0;
        for(Pitch p : pitches) {
            sum += p.getContact().equals("Popup") ? 1 : 0;
        }
        return sum;
    }

    public boolean equals(Pitcher pitcher) {
        return name.equals(pitcher.getName()) && teamName.equals(pitcher.getTeamName());
    }
}