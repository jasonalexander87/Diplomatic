public class FailedLogInEvent {
    private int sessionID;
    
    public FailedLogInEvent(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getsessionID() {
        return sessionID;
    }
    
}
