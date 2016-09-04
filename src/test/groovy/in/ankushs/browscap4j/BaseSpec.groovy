package in.ankushs.browscap4j

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import spock.lang.Specification

class BaseSpec extends Specification{
    private static final String URL = "http://browscap.org/stream?q=BrowsCapCSV";
    private static final Logger log = LoggerFactory.getLogger(BaseSpec.class)

    private static File downloadBrowscapFile(){
        log.info("Downloading browscap.csv from $URL")
        def fileLocation =  new BaseSpec().getClass().getClassLoader().getResource("browscap.csv").getFile()
        def file = new File(fileLocation)
        println file.getAbsolutePath()
        def out = new BufferedOutputStream(new FileOutputStream(file))
        out << new URL(URL).openStream()
        out.close()
        log.info("Downloading finished")
        file
    }
}
