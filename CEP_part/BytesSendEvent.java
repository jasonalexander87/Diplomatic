public class BytesSendEvent {
    private int sessionID;
    private int bytessent;

    public BytesSendEvent(int sessionID, int bytessent) {
        this.sessionID = sessionID;
        this.bytessent = bytessent;
    }

    public int getsessionID() {
        return sessionID;
    }

    public int getbytessent() {
        return bytessent;
    }
    
}
