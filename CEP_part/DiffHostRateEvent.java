public class DiffHostRateEvent {
    private int sessionID;
    private int port;
    private int IP;

    public DiffHostRateEvent(int sessionID, int port, int IP) {
        this.sessionID = sessionID;
        this.port = port;
        this.IP = IP;

    }

    public int getsessionID() {
        return sessionID;
    }

    public int getport() {
        return port;
    }

    public int getIP() {
        return IP;
    }
    
}
