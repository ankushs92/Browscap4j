package in.ankushs.browscap4j.domain;

import in.ankushs.browscap4j.utils.Strings;
import lombok.Getter;

/**
 * Created by ankushsharma on 05/12/17.
 */
@Getter
public enum DevicePointingMethod {

    UNKNOWN("unknown"),
    MOBILE_PHONE("mouse"),
    MOBILE_DEVICE("touchscreen"),
    TABLET("joystick"),
    DESKTOP("stylus"),
    TV_DEVICE("clickwheel"),
    CONSOLE("trackpad"),
    FONE_PAD("trackball");

    private final String code;

    DevicePointingMethod(final String code){
        this.code = code;
    }


    public static DevicePointingMethod from(final String code){
        DevicePointingMethod result = UNKNOWN;
        if(!Strings.hasText(code)){
            result = UNKNOWN;
        }
        else{
            for(final DevicePointingMethod method : values()){
                if(method.getCode().equals(code)){
                    result = method;
                    break;
                }
            }
        }
        return result;
    }
}
