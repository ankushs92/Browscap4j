# Browscap4j

![travis!](https://travis-ci.org/ankushs92/Browscap4j.svg?branch=master "travis")


A simple to use Java library for the [Browscap project](http://browscap.org/).Uses the `browscap.csv` file , which can be found [here](http://browscap.org/stream?q=BrowsCapCSV)

**GET**

With maven :

```xml

<dependency>
    <groupId>in.ankushs</groupId>
    <artifactId>browscap4j</artifactId>
    <version>2.0-PRE</version>
</dependency>

```

Or gradle:

```groovy

compile group: 'in.ankushs', name: 'browscap4j', version: '2.0-PRE'

```

The Javadocs for the latest release can be found [here](http://www.javadoc.io/doc/in.ankushs/browscap4j/1.4.4)

**USAGE**

**Note : Browscap4j uses Java 8** .

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

Some extra conveneance methods :

```java
boolean isDesktop = browserCapabilities.isDesktop();
boolean isWindows = browserCapabilities.isWindows();
boolean isiOS = browserCapabilities.isiOS();
boolean isAndroid = browserCapabilities.isAndroid();
boolean isBot = browserCapabilities.isBot(); // Or crawler
```

**Performance**

For version '2.0-PRE', the parsing and searching of Ua strings is resolved within 1 ms.
For the stable `1.4.4` version, expect result to be returned within 70ms average case.


**Supported By**

![yourkit!](https://www.yourkit.com/images/yklogo.png "yourkit")


YourKit supports open source projects with its full-featured Java Profiler.
YourKit, LLC is the creator of [YourKit Java Profiler](https://www.yourkit.com/java/profiler/index.jsp) and [YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/index.jsp),innovative and intelligent tools for profiling Java and .NET applications.

