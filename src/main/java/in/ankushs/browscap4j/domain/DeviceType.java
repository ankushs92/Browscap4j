package in.ankushs.browscap4j.domain;

import in.ankushs.browscap4j.utils.Strings;
import lombok.Getter;

/**
 * Created by ankushsharma on 05/12/17.
 */
@Getter
public enum DeviceType {

    MOBILE_PHONE("Mobile Phone"),
    MOBILE_DEVICE("Mobile Device"),
    TABLET("Tablet"),
    DESKTOP("Desktop"),
    TV_DEVICE("TV Device"),
    CONSOLE("Console"),
    FONE_PAD("FonePad"),
    EBOOK_READER("Ebook Reader"),
    CAR_ENTERTAINMENT_SYSTEM("Car Entertainment System"),
    UNKNOWN("unknown");

    private final String code;

    DeviceType(final String code){
        this.code = code;
    }

    public static DeviceType from(final String code){
        DeviceType result = UNKNOWN;
        if(!Strings.hasText(code)){
            result = UNKNOWN;
        }
        else{
            for(final DeviceType deviceType : values()){
                if(deviceType.getCode().equals(code)){
                    result = deviceType;
                    break;
                }
            }
        }
        return result;
    }

}
