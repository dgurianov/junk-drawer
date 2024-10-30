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
    public static final String TRANSACTION = API + VERSION + DATA + "/transaction";

    //Stream endpoints
    public static final String STREAM_CITY = API + VERSION + STREAM + "/city";
    public static final String STREAM_COUNTRY = API + VERSION + STREAM + "/country";
    public static final String STREAM_PHONE_NUMBER = API + VERSION + STREAM + "/phone-number";
    public static final String STREAM_TRANSACTION = API + VERSION + STREAM + "/transaction";
}
