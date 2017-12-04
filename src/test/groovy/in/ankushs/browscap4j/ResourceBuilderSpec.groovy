package in.ankushs.browscap4j

import in.ankushs.browscap4j.service.ResourceBuilder

/**
 * Created by Ankush on 04/09/16.
 */
class ResourceBuilderSpec extends BaseSpec{

    def "Pass a file that doesn't exist. IOException to be thrown"(){
        given :
            def fileThatDoesntExist = new File ("/arbitrary")
        when:
            def r = new ResourceBuilder(fileThatDoesntExist).getRegexNamePatternsMap()
        then:
            thrown(RuntimeException)
    }
    def "Pass a file that doesn't exist. IOException to be thrown1"(){
        given :
        def fileThatDoesntExist = new File ("/arbitrary")
        when:
        def r = new ResourceBuilder(fileThatDoesntExist).getNamePatternsToBrowserCapabilitiesMap()
        then:
        thrown(RuntimeException)
    }


}
