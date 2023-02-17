public class Pitch {
    private String pitch, contact;
    private boolean isStrike, isStrikeout, isWalk;
    private float relSpeed, relHeight, spinRate, ext, vert, horz;

    public Pitch(String pitchType, boolean isStrike, boolean isStrikeout, boolean isWalk, String contact, float relSpeed, float spinRate, float extension, float relHeight, float vertBreak, float horzBreak) {
        setPitch(pitchType);
        setStrike(isStrike);
        setStrikeout(isStrikeout);
        setWalk(isWalk);
        setContact(contact);
        setRelSpeed(relSpeed);
        setSpinRate(spinRate);
        setExtension(extension);
        setRelHeight(relHeight);
        setVerticalBreak(vertBreak);
        setHorizontalBreak(horzBreak);
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public float getRelSpeed() {
        return relSpeed;
    }

    public void setRelSpeed(float relSpeed) {
        this.relSpeed = relSpeed;
    }

    public float getSpinRate() {
        return spinRate;
    }

    public void setSpinRate(float spinRate) {
        this.spinRate = spinRate;
    }

    public float getExtension() {
        return ext;
    }

    public void setExtension(float extension) {
        ext = extension;
    }

    public float getRelHeight() {
        return relHeight;
    }

    public void setRelHeight(float relHeight) {
        this.relHeight = relHeight;
    }

    public float getVerticalBreak() {
        return vert;
    }

    public void setVerticalBreak(float vBreak) {
        vert = vBreak;
    }

    public float getHorizontalBreak() {
        return horz;
    }

    public void setHorizontalBreak(float hBreak) {
        horz = hBreak;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isStrikeout() {
        return isStrikeout;
    }

    public void setStrikeout(boolean strikeout) {
        isStrikeout = strikeout;
    }

    public boolean isWalk() {
        return isWalk;
    }

    public void setWalk(boolean walk) {
        isWalk = walk;
    }
}