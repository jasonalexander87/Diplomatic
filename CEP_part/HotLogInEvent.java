public class HotLogInEvent {
    private int sessionID;
    private boolean hotlogin;

    public HotLogInEvent(int sessionID, boolean hotlogin) {
        this.sessionID = sessionID;
        this.hotlogin = hotlogin;
    }

    public int getsessionID() {
        return sessionID;
    }
    
    public boolean gethotlogin() {
        return hotlogin;
    }
}
