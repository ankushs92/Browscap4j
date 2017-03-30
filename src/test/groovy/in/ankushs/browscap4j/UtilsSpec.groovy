package in.ankushs.browscap4j
import  in.ankushs.browscap4j.utils.PreConditions
import in.ankushs.browscap4j.utils.Strings
/**
 * Created by Ankush on 04/09/16.
 */
class UtilsSpec extends BaseSpec{

    def "Pass null . IllegalArgumentException to be thrown"(){
        when :
        PreConditions.checkNull(null,"null object")

        then:
        thrown(IllegalArgumentException)
    }

    def "Pass null String. IllegalArgumentException to be thrown"(){
        when :
        PreConditions.checkEmptyString(null,"null object")

        then:
        thrown(IllegalArgumentException)

    }

    def "Pass empty String. IllegalArgumentException to be thrown"(){
        when :
        PreConditions.checkEmptyString(" ","null object")

        then:
        thrown(IllegalArgumentException)

    }

    def "Pass expression that evaluates to false.IllegalArgumentException to be thrown"(){
        when :
        PreConditions.checkExpression(1==2,"null object")

        then:
        thrown(IllegalArgumentException)

    }

    def "Pass null String.returns false"(){
        given :
        def str = null
        when :
        def bool = Strings.hasText(str)
        then:
        bool == false
    }

    def "Pass empty String.returns false"(){
        given :
        def str = " "
        when :
        def bool = Strings.hasText(str)
        then:
        bool == false
    }

}
