public class TerminationEvent {
    private int sessionID;
    private int timestamp;

    public TerminationEvent(int sessionID, int timestamp) {
        this.sessionID = sessionID;
        this.timestamp = timestamp; 
    }

    public int getsessionID() {
        return sessionID;
    }

    public int gettimestamp() {
        return timestamp;
    }
    
}
