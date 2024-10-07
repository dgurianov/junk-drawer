package gud.fun.junkdrawer.configuration;

public class Endpoints {

    //Set up
    public static final String API = "/api";
    public static final String CONTENT = "/content";
    public static final String DATA = "/data";
    public static final String VERSION = "/v1";

    //Create content endpoint
    public static final String CREATE_CONTENT = API + VERSION + CONTENT;

    //Data endpoints
    public static final String CITY = API + VERSION + DATA + "/city";
    public static final String PHONE_NUMBER = API + VERSION + DATA + "/phone-number";
    public static final String PERSON = API + VERSION + DATA + "/person";

    public static String getUrls(){
        return String.join("\n",
                CREATE_CONTENT,
                CITY,
                PERSON
        );
    }

}
