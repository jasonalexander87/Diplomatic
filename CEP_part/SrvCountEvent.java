public class SrvCountEvent {
    private int sessionID;
    private int port;

    public SrvCountEvent(int sessionID, int port) {
        this.sessionID = sessionID;
        this.port = port;
    }

    public int getsessionID() {
        return sessionID;
    }

    public int getport() {
        return port;
    }
    
}
