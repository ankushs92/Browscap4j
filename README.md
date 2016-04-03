# Browscap4j
A simple to use Java library for the [Browscap project](http://browscap.org/).Uses the `browscap.csv` file , which can be found [here](http://browscap.org/stream?q=BrowsCapCSV) 

#Instructions

In order to get Browser capabilities, you need to first provide Browscap4j with the path of the csv file, like so :

```java
File csvFile = new File(PATH_TO_BROWSCAP_CSV);
Browscap browscap = new Browscap(csvFile);
```

Once the data is loaded from the file into memory , any subsequent invocation of the above code **would not** load the data again. 

Next,just fetch the data for a User agent String ,like so :

```java
String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36";

BrowserCapabilities browserCapabilities = browscap.lookup(userAgent);
String browser = browserCapabilities.getBrowser();
String deviceBrandName = browserCapabilities.getDeviceBrandName();
String deviceCodeName = browserCapabilities.getDeviceCodeName();
String deviceName = browserCapabilities.getDeviceName();
String deviceType = browserCapabilities.getDeviceType();
String platform = browserCapabilities.getPlatform();
String platformMaker = browserCapabilities.getPlatformMaker();
boolean isMobile = browserCapabilities.isMobile();
boolean isTablet = browserCapabilities.isTablet();
```

You are probably wondering why there are only 9 fields ,when there are around 45 or more in the csv file. Well , the simple answer is that the csv file contains way too much data for a user agent, most of which the author thinks is not needed for most projects.Add to that the headache of parsing so many fields. 
However , if anyone wants certain fields included , just say so and it will be done!.

**Important** 
Browscap4j does not cache the results that it returns.The developer using Browscap4j is adviced to use their own Caching solution.

**Performance**
Performance testing was done on Macbook Pro,8 GM Ram , i5 2nd gen with SSD.
Performance is pretty damn decent! The best case for resolving a user agent is about 200 ms ,the worst being 500 ms.

**Testing**

