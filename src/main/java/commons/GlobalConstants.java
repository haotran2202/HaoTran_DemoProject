

package commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalConstants {
    private static GlobalConstants globalConstants;

    private GlobalConstants() {

    }

    public static synchronized GlobalConstants getGlobalConstants() {
        if (globalConstants == null) {
            globalConstants = new GlobalConstants();
        }
        return globalConstants;
    }

    //Wait Infor
    private final long longTimeout = 30;
    private final long shortTimeout = 5;

    //System Infor
    private final String projectPath = System.getProperty("user.dir");
    private final String osName = System.getProperty("os.name");

    //App Infor User
    private final String liveUserUrl = "http://live.techpanda.org/";

    private final String userName = "test2211@gmail.com";
    private final String password = "123456";

    //App Infor Admin
    private final String liveAdminUrl = "http://live.techpanda.org/index.php/backendlogin";

    private final String adminUserName = "user01";
    private final String adminPassword = "guru99.com";

    //Download/ Upload
    private final String UPLOAD_PATH = projectPath + "/uploadFiles/";
    private final String DOWNLOAD_PATH = projectPath + "downloadFiles/";


}