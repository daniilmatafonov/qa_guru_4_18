package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${env}.properties")
public interface IConfig extends Config {

    @Key("browser")
    String browser();

    @Key("remote_url")
    String remoteUrl();

    @Key("video_storage")
    String videoStorage();

}
