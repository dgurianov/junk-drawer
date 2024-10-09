package gud.fun.junkdrawer.configuration;

public class Endpoints {

    //Set up
    public static final String API = "/api";
    public static final String CONTENT = "/content";
    public static final String STREAM = "/stream";
    public static final String DATA = "/data";
    public static final String VERSION = "/v1";

    //Manage content endpoint
    public static final String CREATE_CONTENT = API + VERSION + CONTENT;

    //Data endpoints
    public static final String CITY = API + VERSION + DATA + "/city";
    public static final String COUNTRY = API + VERSION + DATA + "/country";
    public static final String PHONE_NUMBER = API + VERSION + DATA + "/phone-number";
    public static final String CUSTOMER = API + VERSION + DATA + "/customer";

    //Stream endpoints
}
