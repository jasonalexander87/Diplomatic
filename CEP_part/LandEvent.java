public class LandEvent {
    private int sessionID;
    private boolean land;

    public LandEvent(int sessionID, boolean land) {
        this.sessionID = sessionID;
        this.land = land;
    }

    public int getsessionID() {
        return sessionID;
    }

    public boolean getland() {
        return land;
    }
    
}
