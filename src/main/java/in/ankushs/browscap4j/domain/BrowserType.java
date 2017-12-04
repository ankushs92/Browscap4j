package in.ankushs.browscap4j.domain;

import in.ankushs.browscap4j.utils.Strings;
import lombok.Getter;

/**
 * Created by ankushsharma on 05/12/17.
 */
@Getter
public enum BrowserType {

    UNKNOWN("Unknown"),
    BROWSER("Browser"),
    APPLICATION("Application"),
    BOT_CRAWLER("Bot/Crawler"),
    USERAGENT_ANONYMIZER("Useragent Anonymizer"),
    OFFLINE_BROWSER("Offline Browser"),
    MULTIMEDIA_PLAYER("Multimedia Player"),
    LIBRARY("Library"),
    FEED_READER("Feed Reader"),
    EMAIL_CLIENT("Email Client");

    private final String code;

    BrowserType(final String code){
        this.code = code;
    }

    public static BrowserType from(final String code){
        BrowserType result = UNKNOWN;
        if(!Strings.hasText(code)){
            result = UNKNOWN;
        }
        else{
            for(final BrowserType browserType : values()){
                if(browserType.getCode().equals(code)){
                    result = browserType;
                    break;
                }
            }
        }
        return result;
    }
}

