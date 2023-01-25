public class BytesReceiveEvent {
    private int sessionID;
    private int bytesreceived;

    public BytesReceiveEvent(int sessionID, int bytesreceived) {
        this.sessionID = sessionID;
        this.bytesreceived = bytesreceived;
    }

    public int getsessionID() {
        return sessionID;
    }

    public int getbytesreceived() {
        return bytesreceived;
    }
    
}
