package environmentConfig;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${envOwner}.properties"})
public interface Environment extends Config {

    @Key("app.url")
    String appUrl();

    @Key("app.username")
    String appUsername();

    @Key("app.password")
    String appPassword();

}
