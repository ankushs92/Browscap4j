package in.ankushs.browscap4j.service.xml;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import in.ankushs.browscap4j.service.ParsingService;

public class XmlParsingServiceTest {

        @Test
        public void test() {
            File file = new File(getClass().getClassLoader().getResource("browscap.test.xml").getFile());
            ParsingService service = XmlParsingService.getInstance(file);
            Long expectedVersion = 6021L;
            LocalDateTime expectedRelease = LocalDateTime.of(2017, 1, 31, 15, 21, 58);
            Assert.assertEquals(expectedVersion, service.getVersion());
            Assert.assertEquals(expectedRelease, service.getReleaseDate());
            Assert.assertEquals(3, service.getNamePatterns().size());
            Assert.assertEquals(3, service.getNamePatternsToBrowserCapabilitiesMap().size());
        }

}
