

package commons;

public class GlobalConstants {
    //Wait Infor
    public static final long LONG_TIMEOUT = 30;
    public static final long SHORT_TIMEOUT = 5;

    //System Infor
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    //App Infor User
    public static final String LIVE_USER_URL = "http://live.techpanda.org/";

    public static final String USERNAME = "test2211@gmail.com";
    public static final String PASSWORD = "123456";

    //App Infor Admin
    public static final String LIVE_ADMIN_URL = "http://live.techpanda.org/index.php/backendlogin";

    public static final String ADMIN_USERNAME = "user01";
    public static final String ADMIN_PASSWORD = "guru99.com";

    //Download/ Upload
    public static final String UPLOAD_PATH = PROJECT_PATH + "/uploadFiles/";
    public static final String DOWNLOAD_PATH = PROJECT_PATH + "downloadFiles/";


}