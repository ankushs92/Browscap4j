# Browscap4j
A simple to use Java library for the [Browscap project](http://browscap.org/).Uses the `browscap.csv` file , which can be found [here](http://browscap.org/stream?q=BrowsCapCSV) 

**Note: This library and all of the unit tests have been updated for the latest Browscap csv file , as on 25th April,2016**

#Get 

With maven :

```xml

<dependency>
	<groupId>in.ankushs</groupId>
	<artifactId>browscap4j</artifactId>
	<version>1.3</version>
</dependency>

```

Or gradle:

```groovy

compile('in.ankushs:browscap4j:1.3')

```

The Javadocs for the latest release can be found [here](http://www.javadoc.io/doc/in.ankushs/browscap4j/1.3)

#Instructions
**Note : Browscap4j uses Java8** .
In order to get Browser capabilities, you need to first provide Browscap4j with the path of the csv file, like so :

```java
File csvFile = new File(PATH_TO_BROWSCAP_CSV);
Browscap browscap = new Browscap(csvFile);
```

Once the data is loaded from the file into memory , any subsequent invocation of the above code **would not** re-load the data . 

Next,just fetch the data for a User agent String ,like so :

```java
String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25";

BrowserCapabilities browserCapabilities = browscap.lookup(userAgent); 
String browser = browserCapabilities.getBrowser();
String deviceBrandName = browserCapabilities.getDeviceBrandName(); 
String deviceCodeName = browserCapabilities.getDeviceCodeName();
String deviceName = browserCapabilities.getDeviceName();
String deviceType = browserCapabilities.getDeviceType();
String platform = browserCapabilities.getPlatform();
String platformMaker = browserCapabilities.getPlatformMaker();
String platformVersion = browserCapabilities.getPlatformVersion();
boolean isMobile = browserCapabilities.isMobile();
boolean isTablet = browserCapabilities.isTablet();

System.out.println("Browser ::: " + browser);
System.out.println("deviceBrandName ::: " + deviceBrandName);
System.out.println("deviceCodeName ::: " + deviceCodeName);
System.out.println("deviceName ::: " + deviceName);
System.out.println("deviceType ::: " + deviceType);
System.out.println("platform ::: " + platform);
System.out.println("platformMaker ::: " + platformMaker);
System.out.println("platformVersion ::: " + platformVersion);
System.out.println("isMobile ::: " + isMobile);
System.out.println("isTablet ::: " + isTablet);
```

This prints : 
```
Browser ::: Safari
deviceBrandName ::: Apple
deviceCodeName ::: iPhone
deviceName ::: iPhone
deviceType ::: Mobile Phone
platform ::: iOS
platformMaker ::: Apple Inc
platformVersion ::: 6.0
isMobile ::: true
isTablet ::: false
```
You are probably wondering why there are only 10 fields ,when there are around 45 or more in the csv file. Well , the simple answer is that the csv file contains way too much data for a user agent, most of which the author thinks is not needed for most projects.Add to that the headache of parsing so many fields. 
However , if anyone wants certain fields included , just say so and it will be done!.

**Important** 
Browscap4j does not cache the results that it returns.The developer using Browscap4j is adviced to use their own Caching solution.

#Performance
Performance testing was done on Macbook Pro,8 GM Ram , i5 2nd gen with SSD.
Performance is pretty damn decent!Once you warm up the JVM for some time, the best case for resolving a user agent is about 20 ms ,the worst being 130-140 ms.
However,most Strings are resolved around the 70-100ms mark.
That is a considerable performance improvement ,considering that the same program written using the data structures in Python takes ~ 7-10 sec (best case) to resolve a user agent String.

#Testing
This part is a bit tricky. With the guys at Browscap updating the csv file every now and then ,it is impossible for the tests written for a particular version of the file to be valid for the next version.
The tests have been updated for the latest Browscap csv file as on 25th April 2016.
 
Run the code and match the results by going [here](http://browscap.org/ua-lookup) .

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/ankushs92/browscap4j/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/ankushs92/browscap4j/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

