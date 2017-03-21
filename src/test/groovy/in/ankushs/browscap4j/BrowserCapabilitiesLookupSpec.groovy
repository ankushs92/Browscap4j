package in.ankushs.browscap4j

import in.ankushs.browscap4j.domain.Browscap
import org.slf4j.Logger
import org.slf4j.LoggerFactory;;

class BrowserCapabilitiesLookupSpec extends BaseSpec{

	private static final String URL = "http://browscap.org/stream?q=BrowsCapCSV";
	private static final Logger log = LoggerFactory.getLogger(BaseSpec.class)

	private File downloadBrowscapFile(){
		def fileLocation =  BaseSpec.class.getClassLoader()
				.getResource("browscap.csv")
				.getFile()
		def file = new File(fileLocation)

		if(file.size() == 0 ){
			log.info("Downloading browscap.csv from $URL")

			def out = new BufferedOutputStream(new FileOutputStream(file))
			out << new URL(URL).openStream()
			out.close()
			log.info("Downloading finished")
		}
		file
	}

	Browscap browscap
	def setup(){
		browscap =  new Browscap( downloadBrowscapFile())
	}



//	final Browscap browscap = new Browscap(new File("/Users/Ankush/Downloads/browscap.csv"))
	// Format: Test platform,platformMaker,deviceName,browser
	def "Test MacOSX ,AppleInc , Macintosh,Chrome"(){
		given:
		def ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36"

		when:
		def browserCapabilities = browscap.lookup(ua)
		
		
		then:
		browserCapabilities.browser=='Chrome'
		browserCapabilities.browserType=='Browser'
		browserCapabilities.browserMajorVersion=='46'
		browserCapabilities.deviceBrandName=='Apple'
		browserCapabilities.deviceCodeName=='Macintosh'
		browserCapabilities.deviceName=='Macintosh'
		browserCapabilities.deviceType=='Desktop'
		browserCapabilities.isMobile==false
		browserCapabilities.isTablet==false
		browserCapabilities.isBot()==false
		browserCapabilities.isWindows()==false
		browserCapabilities.isAndroid()==false
		browserCapabilities.isDesktop() == true
		browserCapabilities.isiOS()==false

		browserCapabilities.platform=='MacOSX'
		browserCapabilities.platformMaker=='Apple Inc'
		browserCapabilities.platformVersion=='10.10'
	}


	def "Test Android,Google Inc,Kindle Fire HDX 8.9,Silk"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; en-us; KFAPWI Build/JDQ39) AppleWebKit/535.19 (KHTML, like Gecko) Silk/3.13 Safari/535.19 Silk-Accelerated=true"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Silk'
		browserCapabilities.browserType=='Browser'
		browserCapabilities.browserMajorVersion=='3'
		browserCapabilities.deviceBrandName=='Amazon'
		browserCapabilities.deviceCodeName=='KFAPWI'
		browserCapabilities.deviceName=='Kindle Fire HDX 8.9'
		browserCapabilities.deviceType=='Tablet'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==true
		browserCapabilities.isBot()==false
		browserCapabilities.isDesktop() == false

		browserCapabilities.isWindows()==false
		browserCapabilities.isAndroid()==true
		browserCapabilities.isiOS()==false

		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'	
	    browserCapabilities.platformVersion=='Unknown'

	}

	def "Test iOS,Apple Inc,iPad,Safari"(){
		given:
		def ua="Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Safari'
		browserCapabilities.browserType=='Browser'

		browserCapabilities.deviceBrandName=='Apple'
		browserCapabilities.deviceCodeName=='iPad'
		browserCapabilities.deviceName=='iPad'
		browserCapabilities.deviceType=='Tablet'
		browserCapabilities.isMobile==true
		browserCapabilities.isDesktop() == false

		browserCapabilities.isTablet==true
		browserCapabilities.isBot()==false
		browserCapabilities.isWindows()==false
		browserCapabilities.isAndroid()==false
		browserCapabilities.isiOS()==true

		browserCapabilities.platform=='iOS'
		browserCapabilities.platformMaker=='Apple Inc'
	   browserCapabilities.platformVersion=='7.0'

	}

	def "Test iOS,Apple Inc,iPhone,Safari"(){
		given:
		def ua="Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53"
		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Safari'
		browserCapabilities.browserType=='Browser'
		browserCapabilities.deviceBrandName=='Apple'
		browserCapabilities.deviceCodeName=='iPhone'
		browserCapabilities.deviceName=='iPhone'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.isDesktop() == false

		browserCapabilities.isBot()==false
		browserCapabilities.isWindows()==false
		browserCapabilities.isAndroid()==false
		browserCapabilities.isiOS()==true

		browserCapabilities.platform=='iOS'
		browserCapabilities.platformMaker=='Apple Inc'
	   browserCapabilities.platformVersion=='7.0'
	}

	def "Test Android,Google Inc,Galaxy S4,Chrome"(){
		given:
		def ua="Mozilla/5.0 (Linux; Android 4.2.2; GT-I9505 Build/JDQ39) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.59 Mobile Safari/537.36"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Chrome'
		browserCapabilities.browserType=='Browser'
		browserCapabilities.deviceBrandName=='Samsung'
		browserCapabilities.deviceCodeName=='GT-I9505'
		browserCapabilities.deviceName=='Galaxy S4'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.isBot()==false
		browserCapabilities.isDesktop() == false

		browserCapabilities.isWindows()==false
		browserCapabilities.isAndroid()==true
		browserCapabilities.isiOS()==false

		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'
		browserCapabilities.platformVersion=='4.2'
	}

	def "Test Android,Google Inc,Galaxy Note II,Android"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; Android 4.1; en-us; GT-N7100 Build/JRO03C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Android'
		browserCapabilities.browserType=='Browser'
		browserCapabilities.deviceBrandName=='Samsung'
		browserCapabilities.deviceCodeName=='GT-N7100'
		browserCapabilities.deviceName=='Galaxy Note II'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isDesktop() == false

		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.isBot()==false
		browserCapabilities.isWindows()==false
		browserCapabilities.isAndroid()==true
		browserCapabilities.isiOS()==false

		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'
		browserCapabilities.platformVersion=='4.1'		
	}

	def "Test MeeGo,Linux Foundation,N9,Nokia Browser"(){
		given:
		def ua="Mozilla/5.0 (MeeGo; NokiaN9) AppleWebKit/534.13 (KHTML, like Gecko) NokiaBrowser/8.5.0 Mobile Safari/534.13"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Nokia Browser'
		browserCapabilities.browserType=='Browser'
		browserCapabilities.deviceBrandName=='Nokia'
		browserCapabilities.deviceCodeName=='N9'
		browserCapabilities.deviceName=='N9'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.isDesktop() == false

		browserCapabilities.isBot()==false
		browserCapabilities.isWindows()==false
		browserCapabilities.isAndroid()==false
		browserCapabilities.isiOS()==false

		browserCapabilities.platform=='MeeGo'
		browserCapabilities.platformMaker=='Linux Foundation'
		browserCapabilities.platformVersion=='Unknown'
		
	}

	def "Test WinPhone8,Microsoft Corporation,Lumia 520,IEMobile"(){
		given:
		def ua="Mozilla/5.0 (compatible; MSIE 10.0; Windows Phone 8.0; Trident/6.0; IEMobile/10.0; ARM; Touch; NOKIA; Lumia 520)"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='IEMobile'
		browserCapabilities.browserType=='Browser'
		browserCapabilities.deviceBrandName=='Nokia'
		browserCapabilities.deviceCodeName=='Lumia 520'
		browserCapabilities.deviceName=='Lumia 520'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.isDesktop() == false

		browserCapabilities.isBot()==false
		browserCapabilities.isWindows()==true
		browserCapabilities.isAndroid()==false
		browserCapabilities.isiOS()==false

		browserCapabilities.platform=='WinPhone8'
		browserCapabilities.platformMaker=='Microsoft Corporation'
		browserCapabilities.platformVersion=='8.0'
		
	}

	def "Test Android,Google Inc,general Mobile Phone,Android WebView"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; Android 4.4.2; en-us; LGMS323 Build/KOT49I.MS32310c) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.1599.103 Mobile Safari/537.36"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Android WebView'
		browserCapabilities.browserType=='Browser'
		browserCapabilities.deviceBrandName=='Unknown'
		browserCapabilities.deviceCodeName=='general Mobile Phone'
		browserCapabilities.deviceName=='general Mobile Phone'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.isDesktop() == false

		browserCapabilities.isBot()==false
		browserCapabilities.isWindows()==false
		browserCapabilities.isAndroid()==true
		browserCapabilities.isiOS()==false

		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'
		browserCapabilities.platformVersion=='4.4'
	}

	def "Test Android,Google Inc,Nexus 10,Chrome"(){
		given:
		def ua="Mozilla/5.0 (Linux; Android 4.3; Nexus 10 Build/JSS15Q) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2307.2 Safari/537.36"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Chrome'
		browserCapabilities.browserType=='Browser'
		browserCapabilities.deviceBrandName=='Google'
		browserCapabilities.deviceCodeName=='Nexus 10'
		browserCapabilities.deviceName=='Nexus 10'
		browserCapabilities.deviceType=='Tablet'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==true
		browserCapabilities.isBot()==false
		browserCapabilities.isDesktop() == false

		browserCapabilities.isWindows()==false
		browserCapabilities.isAndroid()==true
		browserCapabilities.isiOS()==false

		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'
		browserCapabilities.platformVersion=='4.3'
		
	}
	
	def "Test WinVista,Microsoft Corporation,Windows Desktop,Acoo Browser"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Acoo Browser; GTB5; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; InfoPath.1; .NET CLR 3.5.30729; .NET CLR 3.0.30618)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		   browserCapabilities.browser=='Acoo Browser'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='WinVista'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='6.0'
		   
		}

	def "Test Unknown,Unknown,Unknown,Unknown"(){
		given:
		def ua="Mozilla/5.0 (X11; U; Linux amd64) Iron/21.0.1200.0 Chrome/21.0.1200.0 Safari/537.1"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		  browserCapabilities.browser=='Default Browser'
	   		browserCapabilities.browserType=='Unknown'
		  browserCapabilities.deviceBrandName=='Unknown'
		 browserCapabilities.deviceCodeName=='Unknown'
		  browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isBot()==false
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isWindows()==false
	   browserCapabilities.isAndroid()==false
	   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
		   
		}

	def "Test WinXP,Microsoft Corporation,Windows Desktop,Chrome"(){
		given:
		def ua="Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.25 (KHTML, like Gecko) Chrome/12.0.704.0 Safari/534.25"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		   browserCapabilities.browser=='Chrome'
	   browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		    browserCapabilities.deviceCodeName=='Windows Desktop'
		  browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isTablet==false

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='WinXP'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='5.1'
		   
		}
	
	def "Test Win2000,Microsoft Corporation,Windows Desktop,IE"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 6.0; America Online Browser 1.1; Windows NT 5.0)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		   browserCapabilities.browser=='IE'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true


	   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Win2000'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='5.0'
		   
		}
	
	// Format: Test platform,platformMaker,deviceName,browser
	
	def "Test unknown,unknown,general Desktop,w3m "(){
		given:
		def ua="w3m/0.1.9"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='w3m'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Desktop'
		   browserCapabilities.deviceName=='general Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	def "Firefox,Windows Desktop ,Desktop ,WinXP"(){
		given:
		def ua="Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.1pre) Gecko/20090629 Vonkeror/1.0"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Firefox'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='WinXP'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='5.1'
		   
	}
	
	def "MacOSX,Apple Inc,Macintosh,TenFourFox"(){
		given:
		def ua="Mozilla/5.0 (Macintosh; PPC Mac OS X 10.5; rv:10.0.2) Gecko/20120216 Firefox/10.0.2 TenFourFox/7450"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='TenFourFox'
	  	 browserCapabilities.browserType=='Browser'
		 browserCapabilities.browserMajorVersion=='10'

	   browserCapabilities.deviceBrandName=='Apple'
		   browserCapabilities.deviceCodeName=='Macintosh'
		   browserCapabilities.deviceName=='Macintosh'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='MacOSX'
		   browserCapabilities.platformMaker=='Apple Inc'
		   browserCapabilities.platformVersion=='10.5'
		   
	}
	
	def "WinVista,Microsoft Corporation,Windows Desktop,IE"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; TencentTraveler 4.0; Trident/4.0; SLCC1; Media Center PC 5.0; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30618)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='IE'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.browserMajorVersion=='8'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false


	   browserCapabilities.platform=='WinVista'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='6.0'
		   
	}
	
	def "WinXP,Microsoft Corporation,Windows Desktop,SeaMonkey"(){
		given:
		def ua="Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.6) Gecko/20070809 Sylera/3.0.18 SeaMonkey/1.1.4"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='SeaMonkey'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='WinXP'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='5.1'
		   
	}
	
	def "Win7,Microsoft Corporation,Windows Desktop,Firefox"(){
		given:
		def ua="Mozilla/5.0 (Windows; U; Windows NT 6.1; x64; fr; rv:1.9.1.1) Gecko/20090722 Firefox/3.5.1 Orca/1.2 build 2"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Firefox'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Win7'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='6.1'
		   
	}
	
	def "Win98,Microsoft Corporation,Windows Desktop,Opera"(){
		given:
		def ua="Opera/4.02 (Windows 98; U) [en]"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Opera'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.browserMajorVersion=='4'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Win98'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='98'
		   
	}	
	
	def "Linux,Linux Foundation,Linux Desktop,Netscape"(){
		given:
		def ua="Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.0.2) Gecko/20021120 Netscape/7.01"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Netscape'
		   browserCapabilities.browserMajorVersion=='7'
	      browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Linux Desktop'
		   browserCapabilities.deviceName=='Linux Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false

	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Linux'
		   browserCapabilities.platformMaker=='Linux Foundation'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	def "WinVista,Microsoft Corporation,Windows Desktop,Maxthon"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; MyIE2; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Maxthon'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.browserMajorVersion=='2'

		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
	   browserCapabilities.isDesktop() == true


	   browserCapabilities.isTablet==false

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='WinVista'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='6.0'
		   
	}
	
	def "Linux,Linux Foundation,general Desktop,Lynx"(){
		given:
		def ua="Lynx/2.8.6dev.11 libwww-FM/2.14"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Lynx'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Desktop'
		   browserCapabilities.deviceName=='general Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Linux'
		   browserCapabilities.platformMaker=='Linux Foundation'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	def "Unknown,Unknown,Unknown,General Crawlers"(){
		given:
		def ua="HotJava/1.1.2 FCS"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='General Crawlers'
	   		browserCapabilities.browserType=='Bot/Crawler'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isBot()==true
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	def "Win2000,Microsoft Corporation,Windows Desktop,IE"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0; YComp 5.0.2.6; MSIECrawler)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='IE'
	   browserCapabilities.browserType=='Browser'
		   browserCapabilities.browserMajorVersion=='5'

	   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isTablet==false

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Win2000'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='5.0'
		   
	}
	
	def "Amiga OS,Commodore International,Amiga,Voyager"(){
		given:
		def ua="AmigaVoyager/3.2 (AmigaOS/MC680x0)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Voyager'
	   browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Commodore'
		   browserCapabilities.deviceCodeName=='Amiga'
		   browserCapabilities.deviceName=='Amiga'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Amiga OS'
		   browserCapabilities.platformMaker=='Commodore International'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	def"Unknown,Unknown,Unknown,Google Bot"(){
		given:
		def ua="Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Google Bot'
	   browserCapabilities.browserType=='Bot/Crawler'

	   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isBot()==true
	   browserCapabilities.isWindows()==false
	   browserCapabilities.isAndroid()==false
	   browserCapabilities.isiOS()==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	def "Unknown,Unknown,general Desktop,YaCy Bot"(){
		given:
		def ua="yacybot (x86 Windows XP 5.1; java 1.6.0_12; Europe/de) http://yacy.net/bot.html"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='YaCy Bot'
	  		 browserCapabilities.browserType=='Bot/Crawler'
		   browserCapabilities.browserMajorVersion=='0'

	   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Desktop'
		   browserCapabilities.deviceName=='general Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true


	   browserCapabilities.isBot()==true
	   browserCapabilities.isWindows()==false
	   browserCapabilities.isAndroid()==false
	   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	
	def "Unknown,Unknown,Unknown,Yahoo! Slurp"(){
		given:
		def ua="Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Yahoo! Slurp'
	   browserCapabilities.browserType=='Bot/Crawler'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == false

		   browserCapabilities.isBot()==true
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	
	def "Android,Google Inc,general Mobile Phone,Miui Browser"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; Android 4.3; en-us; HM 1SW Build/JLS36C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 XiaoMi/MiuiBrowser/1.0"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Miui Browser'
		   browserCapabilities.browserType=='Browser'

	   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Mobile Phone'
		   browserCapabilities.deviceName=='general Mobile Phone'
		   browserCapabilities.deviceType=='Mobile Phone'
		   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == false

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==true
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Android'
		   browserCapabilities.platformMaker=='Google Inc'
		   browserCapabilities.platformVersion=='4.3'
		   
	}
	
	def "Android,Google Inc,C6903,Chrome"(){
		
		given:
		def ua="Mozilla/5.0 (Linux; Android 4.3; C6903 Build/14.2.A.1.136) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.59 Mobile Safari/537.36"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Chrome'
		   browserCapabilities.browserType=='Browser'

	   browserCapabilities.deviceBrandName=='Sony'
		   browserCapabilities.deviceCodeName=='C6903'
		   browserCapabilities.deviceName=='Xperia Z1'
		   browserCapabilities.deviceType=='Mobile Phone'
		   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == false

		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==true
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Android'
		   browserCapabilities.platformMaker=='Google Inc'
		   browserCapabilities.platformVersion=='4.3'
		   
	}
	
	def "Android,Google Inc,B8000-H,Android"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; Android 4.2.2; de-de; Lenovo B8000-H/JDQ39) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.2.2 Mobile Safari/534.30"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Android'
	  		 browserCapabilities.browserType=='Browser'

	   browserCapabilities.deviceBrandName=='Lenovo'
		   browserCapabilities.deviceCodeName=='B8000-H'
		   browserCapabilities.deviceName=='Yoga Tablet 10 3G'
		   browserCapabilities.deviceType=='Tablet'
		   browserCapabilities.isMobile==true
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isTablet==true
	   browserCapabilities.isBot()==false
	   browserCapabilities.isWindows()==false
	   browserCapabilities.isAndroid()==true
	   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Android'
		   browserCapabilities.platformMaker=='Google Inc'
		   browserCapabilities.platformVersion=='4.2'
		   
	}
	
	def "iOS,Apple Inc,iPhone,Safari"(){
		given:
		def ua="Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Safari'
	 	  browserCapabilities.browserType=='Browser'

	 	  browserCapabilities.deviceBrandName=='Apple'
		   browserCapabilities.deviceCodeName=='iPhone'
		   browserCapabilities.deviceName=='iPhone'
		   browserCapabilities.deviceType=='Mobile Phone'
		   browserCapabilities.isMobile==true
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isTablet==false
		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==true

	   browserCapabilities.platform=='iOS'
		   browserCapabilities.platformMaker=='Apple Inc'
		   browserCapabilities.platformVersion=='6.0'
		   
	}
	
	def "Win7,Microsoft Corporation,Windows Desktop,360 Secure Browser"(){
		given:
		def ua="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1 QIHU 360SE"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='360 Secure Browser'
	  		 browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isTablet==false
		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Win7'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='6.1'
		   
	}
	
	def "Unknown,Unknown,3DS,NetFront NX"(){
		given:
		def ua="Mozilla/5.0 (Nintendo 3DS; U; ; en) Version/1.7552.EU"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='NetFront NX'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Nintendo'
		   browserCapabilities.deviceCodeName=='3DS'
		   browserCapabilities.deviceName=='3DS'
		   browserCapabilities.deviceType=='Console'
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==false
		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   		browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	def "Syllable,Syllable Project,general Desktop,ABrowse"(){
		given:
		def ua="Mozilla/5.0 (compatible; ABrowse 0.4; Syllable)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='ABrowse'
	  	 browserCapabilities.browserType=='Browser'

	   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Desktop'
		   browserCapabilities.deviceName=='general Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Syllable'
		   browserCapabilities.platformMaker=='Syllable Project'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	def "Unknown,Unknown,general Desktop,Amaya"(){
		given:
		def ua="amaya/11.1 libwww/5.4.0"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Amaya'
		   browserCapabilities.deviceBrandName=='Unknown'
	   		browserCapabilities.browserType=='Application'
		   browserCapabilities.deviceCodeName=='general Desktop'
		   browserCapabilities.deviceName=='general Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	
	def "WinXP,Microsoft Corporation,Windows Desktop,Avant"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; JyxoToolbar1.0; Embedded Web Browser from: http://bsalsa.com/; Avant Browser; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 1.1.4322)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Avant'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isTablet==false
		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='WinXP'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='5.1'
		   
	}

	def "WinNT,Microsoft Corporation,Windows Desktop,Beonex Communicator"(){
		given:
		def ua="Mozilla/5.0 (Windows; U; WinNT; en; rv:1.0.2) Gecko/20030311 Beonex/0.8.2-stable"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Beonex Communicator'
	   		browserCapabilities.browserType=='Application'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.isBot()==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isWindows()==true
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.platform=='WinNT'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	def "Linux Smartphone OS,Linux Foundation,general Mobile Phone,Bolt"(){
		given:
		def ua="Mozilla/5.0 (X11; 78; CentOS; US-en) AppleWebKit/527+ (KHTML, like Gecko) Bolt/0.862 Version/3.0 Safari/523.15"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Bolt'
	   		browserCapabilities.browserType=='Unknown'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Mobile Phone'
		   browserCapabilities.deviceName=='general Mobile Phone'
		   browserCapabilities.deviceType=='Mobile Phone'
		   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false
	   browserCapabilities.platform=='Linux Smartphone OS'
		   browserCapabilities.platformMaker=='Linux Foundation'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	//8
	def "Linux Smartphone OS,Linux Foundation,general Mobile Phone,BrowseX"(){
		given:
		def ua="Mozilla/4.61 [en] (X11; U; ) - BrowseX (2.0.0 Windows)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='BrowseX'
	 	  browserCapabilities.browserType=='Bot/Crawler'
	 	  browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isBot()==true
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
		   
	}
	
	//8
	def "MacOSX,Apple Inc,Macintosh,Camino"(){
		given:
		def ua="Mozilla/5.0 (Macintosh; PPC Mac OS X 10.5; rv:2.0.1) Gecko/20100101 Firefox/4.0.1 Camino/2.2.1"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Camino'
	   		browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Apple'
		   browserCapabilities.deviceCodeName=='Macintosh'
		   browserCapabilities.deviceName=='Macintosh'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
	   		browserCapabilities.isiOS()==false
	   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='MacOSX'
		   browserCapabilities.platformMaker=='Apple Inc'
		   browserCapabilities.platformVersion=='10.5'
		   
	}
	
	
	//8
	def "MacPPC,Apple Inc,Macintosh,Classilla"(){
		given:
		def ua="Mozilla/5.0 (Macintosh; U; PPC; en-US; mimic; rv:9.3.0) Clecko/20120101 Classilla/CFM"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Classilla'
	  	 browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Apple'
		   browserCapabilities.deviceCodeName=='Macintosh'
		   browserCapabilities.deviceName=='Macintosh'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isBot()==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isWindows()==false
		   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false

	   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='MacPPC'
		   browserCapabilities.platformMaker=='Apple Inc'
		   browserCapabilities.platformVersion=='Unknown'
	}
	
	def "Unknown,Unknown,Unknown,YOURLS"(){
		given:
		def ua="YOURLS v1.5.1 +http://yourls.org/ (running on http://drms.be)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='YOURLS'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.browserType=='Bot/Crawler'

	   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isBot()==true
		   browserCapabilities.isWindows()==false
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false
	   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
	}
	
	def "Unknown,Unknown,Unknown,Yahoo Link Preview"(){
		given:
		def ua="Mozilla/5.0 (compatible; Yahoo Link Preview; https://help.yahoo.com/kb/mail/yahoo-link-preview-SLN23615.html)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Yahoo Link Preview'
		   browserCapabilities.deviceBrandName=='Unknown'
	   	   browserCapabilities.browserType=='Bot/Crawler'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isBot()==true
			browserCapabilities.isWindows()==false
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isAndroid()==false
		   browserCapabilities.isiOS()==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
	}
	
	def "Unknown,Unknown,general Mobile Device,Nokia"(){
		given:
		def ua="Nokia SyncML HTTP Client"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Nokia'
	   	browserCapabilities.browserType=='Browser'
		   browserCapabilities.deviceBrandName=='Nokia'
		   browserCapabilities.deviceCodeName=='general Mobile Device'
		   browserCapabilities.deviceName=='general Mobile Device'
		   browserCapabilities.deviceType=='Mobile Phone'
		   browserCapabilities.isMobile==true
	   		browserCapabilities.isAndroid()==false
		   browserCapabilities.isAndroid()==false
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isiOS()==false
		   browserCapabilities.isBot()==false
		   browserCapabilities.isWindows()==false

	  	 browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		   browserCapabilities.platformVersion=='Unknown'
	}
	
	def "Linux,Linux Foundation,Linux Desktop,gvfs"(){
		given:
		def ua="gvfs/0.2.3"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='gvfs'
		   browserCapabilities.deviceBrandName=='Unknown'
	   browserCapabilities.browserType=='Tool'
		   browserCapabilities.deviceCodeName=='Linux Desktop'
		   browserCapabilities.deviceName=='Linux Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
	   		browserCapabilities.isiOS()==false
	   browserCapabilities.isWindows()==false
	   browserCapabilities.isDesktop() == true

	   browserCapabilities.isAndroid()==false
	   	browserCapabilities.isBot()==false
		   browserCapabilities.platform=='Linux'
		   browserCapabilities.platformMaker=='Linux Foundation'
		   browserCapabilities.platformVersion=='Unknown'
	}
	
	def "iOS,Apple Inc,general Mobile Device,Reeder"(){
		given:
		def ua="Reeder/1.0.1 CFNetwork/467.12 Darwin/10.3.1"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Reeder'
	   		browserCapabilities.browserType=='Application'
		   browserCapabilities.deviceBrandName=='Apple'
		   browserCapabilities.deviceCodeName=='general Mobile Device'
		   browserCapabilities.deviceName=='general Mobile Device'
		   browserCapabilities.deviceType=='Mobile Device'
		   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==false
	   		browserCapabilities.isAndroid()==false
		   browserCapabilities.isWindows()==false
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isBot()==false
		   browserCapabilities.isiOS() == true
		   browserCapabilities.platform=='iOS'
		   browserCapabilities.platformMaker=='Apple Inc'
		   browserCapabilities.platformVersion=='3.2'
	}
	
	def "iOS,Apple Inc,iPad,Facebook App"(){
		given:
		def ua="Mozilla/5.0 (iPad; CPU OS 6_0_1 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Mobile/10A523 [FBAN/FBIOS;FBAV/6.0.1;FBBV/180945;FBDV/iPad2,1;FBMD/iPad;FBSN/iPhone OS;FBSV/6.0.1;FBSS/1; FBCR/;FBID/tablet;FBLC/en_US;FBOP/1]"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Facebook App'
	   		browserCapabilities.browserType=='Application'
		   browserCapabilities.deviceBrandName=='Apple'
		   browserCapabilities.deviceCodeName=='iPad'
		   browserCapabilities.deviceName=='iPad'
		   browserCapabilities.deviceType=='Tablet'
		   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==true
	   		browserCapabilities.isBot()==false
	   browserCapabilities.isDesktop() == false

	   browserCapabilities.isAndroid() ==false
		   browserCapabilities.isWindows()==false
		   browserCapabilities.isiOS()==true
		   browserCapabilities.platform=='iOS'
		   browserCapabilities.platformMaker=='Apple Inc'
		   browserCapabilities.platformVersion=='6.0'
	}
}
