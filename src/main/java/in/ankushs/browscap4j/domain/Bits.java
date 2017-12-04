package in.ankushs.browscap4j.domain;

import in.ankushs.browscap4j.utils.Strings;
import lombok.Getter;

/**
 * Created by ankushsharma on 05/12/17.
 */
@Getter
public enum Bits {

    UNKNOWN("Unknown","Unknown"),
    BIT_0("0", "0 Bits"),
    BIT_8("8", "8 Bits"),
    BIT_16("16", "16 Bits"),
    BIT_32("32", "32 Bits"),
    BIT_64("64", "64 Bits");

    private final String code;
    private final String description;

    Bits(final String code, final String description){
        this.code = code;
        this.description = description;
    }

    public static Bits from(final String code){
        Bits result = UNKNOWN;
        if(!Strings.hasText(code)){
            result = UNKNOWN;
        }
        else{
            for(final Bits bits : values()){
                if(bits.getCode().equals(code)){
                    result = bits;
                    break;
                }
            }
        }
        return result;
    }
}
