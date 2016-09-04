package in.ankushs.browscap4j

import in.ankushs.browscap4j.domain.Browscap

/**
 * Created by Ankush on 04/09/16.
 */
class BrowscapSpec  extends BaseSpec{

    def " Pass a null or empty file to Browscap.Throw IllegalArgumentException"(){
        when:
            new Browscap(null)
        then:
            thrown (IllegalArgumentException)
    }

    def " Pass an file that doesn't exist to Browscap.Throw IllegalArgumentException"(){
        when:
             new Browscap(new File("/arbitrary/location"))
        then:
        thrown (IllegalArgumentException)

    }
}
