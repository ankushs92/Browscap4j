# Browscap4j

![travis!](https://travis-ci.org/ankushs92/Browscap4j.svg?branch=master "travis")
[![Coverage Status](https://coveralls.io/repos/github/ankushs92/Browscap4j/badge.svg?branch=master)](https://coveralls.io/github/ankushs92/Browscap4j?branch=master)

A simple to use Java library for the [Browscap project](http://browscap.org/).Uses the `browscap.csv` file , which can be found [here](http://browscap.org/stream?q=BrowsCapCSV)

**Note: This library and all of the unit tests have been updated for the latest Browscap csv file (version 6017), as on 26th Sep,2016**

**Get it 

With maven :

```xml

<dependency>
	<groupId>in.ankushs</groupId>
	<artifactId>browscap4j</artifactId>
	<version>1.4.4</version>
</dependency>

```

Or gradle:

```groovy

compile('in.ankushs:browscap4j:1.4.4')

```

The Javadocs for the latest release can be found [here](http://www.javadoc.io/doc/in.ankushs/browscap4j/1.4.4)

#Instructions
**Note : Browscap4j uses Java 8** .
In order to get Browser capabilities, you need to first provide Browscap4j with the path of the csv file, like so :

```java
File csvFile = new File(PATH_TO_BROWSCAP_CSV);
Browscap browscap = new Browscap(csvFile);
```
The default processing mechanism is serial . If you want to enable parallel processing, initiate like so :

```java
File csvFile = new File(PATH_TO_BROWSCAP_CSV);
boolean enableParallel = true;
Browscap browscap = new Browscap(csvFile,enableParallel);
```

Browscap4j uses Java 8 parallel streams are used for parallel processing.

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

Some extra conveneance methods :

```java
boolean isDesktop = browserCapabilities.isDesktop();
boolean isWindows = browserCapabilities.isWindows();
boolean isiOS = browserCapabilities.isiOS();
boolean isAndroid = browserCapabilities.isAndroid();
boolean isBot = browserCapabilities.isBot(); //Bot or crawler
```

**Important** 
Browscap4j does not cache the results that it returns.The developer using Browscap4j is adviced to use their own Caching solution.

#Integrating with Spring Boot
I have written a detailed blog post on how to use browscap4j with Spring Boot. You can read the tutorial [here](http://ankushs92.github.io/libraries/2016/05/12/browscap4j.html) and get the Spring-Boot-browscap4j app [here](https://github.com/ankushs92/Spring-Boot-browscap4j) .

#Performance
Performance testing was done on Macbook Pro,8 GM Ram , i5 2nd gen with SSD. The performance is decent. I'm currently on a much faster solution. The best case for resolving a user agent is about 20 ms ,the worst being 130-140 ms.
However, most Strings are resolved around the 70-100ms mark.

#Testing
This part is a bit tricky. With the guys at Browscap updating the csv file every now and then ,it is impossible for the tests written for a particular version of the file to be valid for the next version.
The tests have been updated for the latest Browscap csv file as on 26th Sep,2016.
 
Run the code and match the results by going [here](http://browscap.org/ua-lookup) .

#Supported By

![yourkit!](https://www.yourkit.com/images/yklogo.png "yourkit")


YourKit supports open source projects with its full-featured Java Profiler.
YourKit, LLC is the creator of [YourKit Java Profiler](https://www.yourkit.com/java/profiler/index.jsp) and [YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/index.jsp),innovative and intelligent tools for profiling Java and .NET applications.

