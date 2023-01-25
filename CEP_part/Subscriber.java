import java.io.PrintWriter;

public class Subscriber {
    public PrintWriter testobj;

    public Subscriber(PrintWriter testobj){
        this.testobj = testobj;

    }

    public void update(int session, int BSEsum,int BREsum, boolean L, boolean HL, Long FLC, Long WFC, Long SUC, Long SRVCC, Long DHRC, int TETS, int INETS){

        System.out.println("SUBSCRIBER");

        String sessionID = Integer.toString(session);
        String bytesSent = Integer.toString(BSEsum);
        String bytesReceived = Integer.toString(BREsum);
        String land = String.valueOf(L);
        String hotLogin = String.valueOf(HL);
        String faileLogin = Long.toString(FLC);
        String wrongFrag = Long.toString(WFC);
        String suAtemts = Long.toString(SUC);
        String srvCount = Long.toString(SRVCC);
        String diffHost = Long.toString(DHRC);
        String timestampEnd = Integer.toString(TETS);
        String timestampInit = Integer.toString(INETS);
        
        String data = sessionID + "/" + bytesSent + "/" + bytesReceived + "/" + land + "/" + hotLogin + "/" + faileLogin + "/" + wrongFrag + "/" + suAtemts + srvCount + "/" + diffHost + "/" + timestampEnd + "/" + timestampInit + "\n";

        testobj.write(data);
        

        
    }
    
}
