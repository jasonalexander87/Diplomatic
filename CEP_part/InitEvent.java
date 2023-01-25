public class InitEvent {
    private int sessionID;
    private int port;
    private int timestamp;
    private int IP;

    public InitEvent(int sessionID, int port, int timestamp, int IP) {
        this.sessionID = sessionID;
        this.port = port;
        this.timestamp = timestamp;
        this.IP = IP;
    
    }

    public int getsessionID() {
        return sessionID;
    }

    public int getport(){
        return port;
    }
    
    public int gettimestamp() {
        return timestamp;
    }

    public int getIP(){
        return IP;
    }
}
