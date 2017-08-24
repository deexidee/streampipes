import org.streampipes.pe.sources.samples.config.AkerVariables;
import org.streampipes.pe.sources.samples.util.Utils;

/**
 * Created by robin on 10.10.14.
 *
 * Test class for testing the connection
 */
public class ServerTest {
    public static void  main(String[] args) throws Exception
    {
        //long[] variables = {AkerVariables.GearLubeOilTemperature.tagNumber()};
        long[] variables = {AkerVariables.DrillingRPM.tagNumber()};
        String cont = Utils.performRequest(variables, "drillinrpm", "2013-11-20T11:01:00+0100", "2013-11-21T14:15:00+0100");
        System.out.println("Got as answer: \n" + cont);

        //Get request for checking current playbacks
       /* String uri = "https://account.nissatech.com/EventPlayer/api/playback/";
        System.out.println("Currently running playbacks: \n" + Request.Get(uri).execute().returnContent().asString());*/
    }
}