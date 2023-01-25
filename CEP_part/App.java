import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompileException;
import com.espertech.esper.compiler.client.EPCompiler;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.EPDeployException;
import com.espertech.esper.runtime.client.EPDeployment;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPRuntimeProvider;
import com.espertech.esper.runtime.client.EPStatement;

public class App {

  public static void insertEvent(String log, EPRuntime runtime) {

    String[] event = log.split("/");

    switch (event[0]) {

      case "InitEvent":
          runtime.getEventService().sendEventBean(new InitEvent(Integer.parseInt(event[1]), Integer.parseInt(event[2]), Integer.parseInt(event[3]), Integer.parseInt(event[4])), "InitEvent");
          break;
      case "TerminationEvent":
          runtime.getEventService().sendEventBean(new TerminationEvent(Integer.parseInt(event[1]), Integer.parseInt(event[2])),"TerminationEvent");
          break;
      case "BytesReceiveEvent":
          runtime.getEventService().sendEventBean(new BytesReceiveEvent(Integer.parseInt(event[1]), Integer.parseInt(event[2])), "BytesReceiveEvent");        
          break;
      case "BytesSendEvent":
          runtime.getEventService().sendEventBean(new BytesSendEvent(Integer.parseInt(event[1]), Integer.parseInt(event[2])), "BytesSendEvent"); 
          break;
      case "DiffHostRateEvent":
          runtime.getEventService().sendEventBean(new DiffHostRateEvent(Integer.parseInt(event[1]), Integer.parseInt(event[2]), Integer.parseInt(event[3])), "DiffHostRateEvent");
          break;
      case "FailedLogInEvent":
          runtime.getEventService().sendEventBean(new FailedLogInEvent(Integer.parseInt(event[1])), "FailedLogInEvent"); 
          break;
      case "HotLogInEvent":
          runtime.getEventService().sendEventBean(new HotLogInEvent(Integer.parseInt(event[1]), Boolean.parseBoolean(event[2])), "HotLogInEvent");
          break;
      case "LandEvent":
          runtime.getEventService().sendEventBean(new LandEvent(Integer.parseInt(event[1]), Boolean.parseBoolean(event[2])), "LandEvent");
          break;
      case "SrvCountEvent":
          runtime.getEventService().sendEventBean(new SrvCountEvent(Integer.parseInt(event[1]), Integer.parseInt(event[2])), "SrvCountEvent");
          break;
      case "WrongFragEvent":
          runtime.getEventService().sendEventBean(new WrongFragEvent(Integer.parseInt(event[1])), "WrongFragEvent");       
          break;                  
    }
  }

 
  public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        PrintWriter writer = null;
        BufferedReader reader = null;

        Socket socketReceiveLog = null;
        Socket socketSend2Model = null;

        try {
        socketReceiveLog  = new Socket("localhost",5555);
        socketSend2Model = new Socket("localhost", 6666);
        writer = new PrintWriter(socketSend2Model.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socketReceiveLog.getInputStream()));
        
        } catch(Exception e) { System.out.println("ERROR CONECTING TO SOCKET");}

        Configuration configuration = new Configuration();
        EPCompiler compiler = EPCompilerProvider.getCompiler();

        configuration.getCommon().addEventType(LogIn.class);
        configuration.getCommon().addEventType(LogOut.class);
        configuration.getCommon().addEventType(One.class);
        configuration.getCommon().addEventType(BytesReceiveEvent.class);
        configuration.getCommon().addEventType(BytesSendEvent.class);
        configuration.getCommon().addEventType(DiffHostRateEvent.class);
        configuration.getCommon().addEventType(FailedLogInEvent.class);
        configuration.getCommon().addEventType(HotLogInEvent.class);
        configuration.getCommon().addEventType(InitEvent.class);
        configuration.getCommon().addEventType(LandEvent.class);
        configuration.getCommon().addEventType(SrvCountEvent.class);
        configuration.getCommon().addEventType(SuEvent.class);
        configuration.getCommon().addEventType(TerminationEvent.class);
        configuration.getCommon().addEventType(WrongFragEvent.class);
        configuration.getCompiler().getByteCode().setAllowSubscriber(true);


        EPRuntime runtime1 = EPRuntimeProvider.getDefaultRuntime(configuration);

        CompilerArguments arguments = new CompilerArguments(configuration);

        String from0 = "InitEvent(sessionID = context.init.sessionID)#keepall as IE, ";
        String select0 = "IE.sessionID as session, ";
        String from1 = "BytesSendEvent(sessionID = context.init.sessionID)#keepall as BSEE, ";
        String select1 = "coalesce(sum(coalesce(BSEE.bytessent,0)),0) as BSEsum, ";
        String from2 = "BytesReceiveEvent(sessionID = context.init.sessionID)#keepall as BREE, ";
        String select2 = "coalesce(sum(coalesce(BREE.bytesreceived,0)),0) as BREsum, ";
        String from3 = "LandEvent(sessionID = context.init.sessionID)#keepall as LE, ";
        String select3 = "LE.land as L, ";
        String from4 = "HotLogInEvent(sessionID = context.init.sessionID)#keepall as HLE, ";
        String select4 = "HLE.hotlogin as HL, ";
        String from5 = "FailedLogInEvent(sessionID = context.init.sessionID)#keepall as FLIE, ";
        String select5 = "coalesce(count(FLIE.*),0) as FLC, ";
        String from6 = "WrongFragEvent(sessionID = context.init.sessionID)#keepall as WFE, ";
        String select6 = "coalesce(count(WFE.*),0) as WFC, ";
        String from7 = "SuEvent(sessionID = context.init.sessionID)#keepall as SUE, ";
        String select7 = "coalesce(count(SUE.*),0) as SUC, ";
        String from8 = "SrvCountEvent(port = context.init.port)#time(2 sec) as SRVCE, ";
        String select8 = "coalesce(count(SRVCE.*),0) as SRVCC, ";
        String from9 = "DiffHostRateEvent(port = context.init.port and IP != context.init.IP)#time(2 sec) as DHRE, ";
        String select9 = "coalesce(count(DHRE.*),0) as DHRC, ";
        String from10 = "TerminationEvent(sessionID = context.init.sessionID)#keepall as TE ";
        String select10 = "TE.timestamp as TETS, ";
        String select11 = "context.init.timestamp as INETS ";
        //String from11 = "InitEvent(sessionID = context.init.sessionID)#keepall as INE ";
        //String select12 = "INE.sessionID as SID ";

        String createctx = "@public create context ids partition by sessionID from InitEvent as init terminated by TerminationEvent(sessionID=init.sessionID); ";
        String query_select = "@public @name('mystatement2') context ids select ";
        String query_select_full = createctx + query_select + select0 + select1 + select2 + select3 + select4 + select5 + select6 + select7 + select8 + select9 + select10 + select11;
        String query_full_from = query_select_full + "from " + from0 + from1 + from2 + from3 + from4 + from5 + from6 + from7 + from8 + from9 + from10;
        String output = "output last when terminated";
        String query = query_full_from + output;
        //System.out.println(query);
        
// CONTEXT CREATION STATEMENT
      EPCompiled epCompiled2;
      //String test = "@public create context testone partition by id from LogIn as init terminated by LogOut(id=init.id); @public @name('mystatement') context testone select event1.id as id, count(event2.*) as counter, coalesce(sum(coalesce(event2.counter,0)),0) as sumation from LogIn(id = context.init.id)#keepall as event1, One(id = context.init.id)#keepall as event2 output last when terminated ";
      //String test3 = "@public create context testone partition by id from LogIn as init terminated by LogOut(id=init.id); " + "@public @name('mystatement') context testone select event1.id as id, count(event2.*) as counter, coalesce(sum(coalesce(event2.counter,0)),0) as sumation from LogIn(id = context.init.id)#keepall as event1, One(id = context.init.id)#keepall as event2 output last when terminated ";
      try {
        epCompiled2 = compiler.compile(query, arguments);

        System.out.println("Success 2");
      }
       catch (EPCompileException ex) {
       // handle exception here
       System.out.println("ERROR");
       throw new RuntimeException(ex);
}


      
      EPDeployment deployment;

      try {
        deployment = runtime1.getDeploymentService().deploy(epCompiled2);
        System.out.println("DEPLOYMENT SUCESS");
       }
       catch (EPDeployException ex) {
        // handle exception here
        throw new RuntimeException(ex);
       }

       EPStatement statement1 =
       runtime1.getDeploymentService().getStatement(deployment.getDeploymentId(), "mystatement2");

       statement1.addListener( (newData, oldData, statement, runtime) -> {
        //int id = (int) newData[0].get("id");
        //System.out.println(String.format("ID: %d", id));
        System.out.println("LISTENER");
       });

       Subscriber sub = new Subscriber(writer);
       statement1.setSubscriber(sub);
        
       String message = null;
       while(true) {

        message = reader.readLine();
        insertEvent(message,runtime1);

       }



    }
}
