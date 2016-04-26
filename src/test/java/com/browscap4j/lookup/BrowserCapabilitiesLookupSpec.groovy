package com.browscap4j.lookup

import in.ankushs.browscap4j.domain.Browscap;; 

class BrowserCapabilitiesLookupSpec extends BaseSpec{

	final String PATH_TO_BROWSCAP_FILE="/Users/Ankush/Downloads/browscap/new/browscap.csv"
	final Browscap browscap =  new Browscap(new File(PATH_TO_BROWSCAP_FILE))

	// Format: Test platform,platformMaker,deviceName,browser
	def "Test MacOSX ,AppleInc , Macintosh,Chrome"(){
		given:
		def ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Chrome'
		browserCapabilities.deviceBrandName=='Apple'
		browserCapabilities.deviceCodeName=='Macintosh'
		browserCapabilities.deviceName=='Macintosh'
		browserCapabilities.deviceType=='Desktop'
		browserCapabilities.isMobile==false
		browserCapabilities.isTablet==false
		browserCapabilities.platform=='MacOSX'
		browserCapabilities.platformMaker=='Apple Inc'
	}


	def "Test Android,Google Inc,Kindle Fire HDX 8.9,Silk"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; en-us; KFAPWI Build/JDQ39) AppleWebKit/535.19 (KHTML, like Gecko) Silk/3.13 Safari/535.19 Silk-Accelerated=true"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Silk'
		browserCapabilities.deviceBrandName=='Amazon'
		browserCapabilities.deviceCodeName=='KFAPWI'
		browserCapabilities.deviceName=='Kindle Fire HDX 8.9'
		browserCapabilities.deviceType=='Tablet'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==true
		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'
	}

	def "Test iOS,Apple Inc,iPad,Safari"(){
		given:
		def ua="Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Safari'
		browserCapabilities.deviceBrandName=='Apple'
		browserCapabilities.deviceCodeName=='iPad'
		browserCapabilities.deviceName=='iPad'
		browserCapabilities.deviceType=='Tablet'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==true
		browserCapabilities.platform=='iOS'
		browserCapabilities.platformMaker=='Apple Inc'
	}

	def "Test iOS,Apple Inc,iPhone,Safari"(){
		given:
		def ua="Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53"
		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Safari'
		browserCapabilities.deviceBrandName=='Apple'
		browserCapabilities.deviceCodeName=='iPhone'
		browserCapabilities.deviceName=='iPhone'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.platform=='iOS'
		browserCapabilities.platformMaker=='Apple Inc'
	}

	def "Test Android,Google Inc,Galaxy S4,Chrome"(){
		given:
		def ua="Mozilla/5.0 (Linux; Android 4.2.2; GT-I9505 Build/JDQ39) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.59 Mobile Safari/537.36"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Chrome'
		browserCapabilities.deviceBrandName=='Samsung'
		browserCapabilities.deviceCodeName=='GT-I9505'
		browserCapabilities.deviceName=='Galaxy S4'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'
	}

	def "Test Android,Google Inc,Galaxy Note II,Android"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; Android 4.1; en-us; GT-N7100 Build/JRO03C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Android'
		browserCapabilities.deviceBrandName=='Samsung'
		browserCapabilities.deviceCodeName=='GT-N7100'
		browserCapabilities.deviceName=='Galaxy Note II'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'
	}

	def "Test MeeGo,Linux Foundation,N9,Nokia Browser"(){
		given:
		def ua="Mozilla/5.0 (MeeGo; NokiaN9) AppleWebKit/534.13 (KHTML, like Gecko) NokiaBrowser/8.5.0 Mobile Safari/534.13"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Nokia Browser'
		browserCapabilities.deviceBrandName=='Nokia'
		browserCapabilities.deviceCodeName=='N9'
		browserCapabilities.deviceName=='N9'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.platform=='MeeGo'
		browserCapabilities.platformMaker=='Linux Foundation'
	}

	def "Test WinPhone8,Microsoft Corporation,Lumia 520,IEMobile"(){
		given:
		def ua="Mozilla/5.0 (compatible; MSIE 10.0; Windows Phone 8.0; Trident/6.0; IEMobile/10.0; ARM; Touch; NOKIA; Lumia 520)"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='IEMobile'
		browserCapabilities.deviceBrandName=='Nokia'
		browserCapabilities.deviceCodeName=='Lumia 520'
		browserCapabilities.deviceName=='Lumia 520'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.platform=='WinPhone8'
		browserCapabilities.platformMaker=='Microsoft Corporation'
	}

	def "Test Android,Google Inc,general Mobile Phone,Android WebView"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; Android 4.4.2; en-us; LGMS323 Build/KOT49I.MS32310c) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.1599.103 Mobile Safari/537.36"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Android WebView'
		browserCapabilities.deviceBrandName=='Unknown'
		browserCapabilities.deviceCodeName=='general Mobile Phone'
		browserCapabilities.deviceName=='general Mobile Phone'
		browserCapabilities.deviceType=='Mobile Phone'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==false
		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'
	}

	def "Test Android,Google Inc,Nexus 10,Chrome"(){
		given:
		def ua="Mozilla/5.0 (Linux; Android 4.3; Nexus 10 Build/JSS15Q) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2307.2 Safari/537.36"

		when:
		def browserCapabilities = browscap.lookup(ua)

		then:
		browserCapabilities.browser=='Chrome'
		browserCapabilities.deviceBrandName=='Google'
		browserCapabilities.deviceCodeName=='Nexus 10'
		browserCapabilities.deviceName=='Nexus 10'
		browserCapabilities.deviceType=='Tablet'
		browserCapabilities.isMobile==true
		browserCapabilities.isTablet==true
		browserCapabilities.platform=='Android'
		browserCapabilities.platformMaker=='Google Inc'
	}
	
	def "Test WinVista,Microsoft Corporation,Windows Desktop,Acoo Browser"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Acoo Browser; GTB5; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; InfoPath.1; .NET CLR 3.5.30729; .NET CLR 3.0.30618)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		   browserCapabilities.browser=='Acoo Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='WinVista'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		}

	def "Test Unknown,Unknown,Unknown,Unknown"(){
		given:
		def ua="Mozilla/5.0 (X11; U; Linux amd64) Iron/21.0.1200.0 Chrome/21.0.1200.0 Safari/537.1"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		  browserCapabilities.browser=='Default Browser'
		  browserCapabilities.deviceBrandName=='Unknown'
		 browserCapabilities.deviceCodeName=='Unknown'
		  browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
		}

	def "Test WinXP,Microsoft Corporation,Windows Desktop,Chrome"(){
		given:
		def ua="Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.25 (KHTML, like Gecko) Chrome/12.0.704.0 Safari/534.25"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		   browserCapabilities.browser=='Chrome'
		   browserCapabilities.deviceBrandName=='Unknown'
		    browserCapabilities.deviceCodeName=='Windows Desktop'
		  browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='WinXP'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		}
	
	def "Test Win2000,Microsoft Corporation,Windows Desktop,IE"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 6.0; America Online Browser 1.1; Windows NT 5.0)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		   browserCapabilities.browser=='IE'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Win2000'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		}
	
	// Format: Test platform,platformMaker,deviceName,browser
	
	def "Test unknown,unknown,general Desktop,w3m "(){
		given:
		def ua="w3m/0.1.9"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='w3m'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Desktop'
		   browserCapabilities.deviceName=='general Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'

	}
	
	def "Unknown,Unknown ,Unknown ,Default Browser"(){
		given:
		def ua="Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.1pre) Gecko/20090629 Vonkeror/1.0"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Default Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
	}
	
	def "MacOSX,Apple Inc,Macintosh,TenFourFox"(){
		given:
		def ua="Mozilla/5.0 (Macintosh; PPC Mac OS X 10.5; rv:10.0.2) Gecko/20120216 Firefox/10.0.2 TenFourFox/7450"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='TenFourFox'
		   browserCapabilities.deviceBrandName=='Apple'
		   browserCapabilities.deviceCodeName=='Macintosh'
		   browserCapabilities.deviceName=='Macintosh'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='MacOSX'
		   browserCapabilities.platformMaker=='Apple Inc'
	}
	
	def "WinVista,Microsoft Corporation,Windows Desktop,IE"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; TencentTraveler 4.0; Trident/4.0; SLCC1; Media Center PC 5.0; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30618)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='IE'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='WinVista'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
	}
	
	def "WinXP,Microsoft Corporation,Windows Desktop,SeaMonkey"(){
		given:
		def ua="Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.6) Gecko/20070809 Sylera/3.0.18 SeaMonkey/1.1.4"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='SeaMonkey'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='WinXP'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
	}
	
	def "Win7,Microsoft Corporation,Windows Desktop,Firefox"(){
		given:
		def ua="Mozilla/5.0 (Windows; U; Windows NT 6.1; x64; fr; rv:1.9.1.1) Gecko/20090722 Firefox/3.5.1 Orca/1.2 build 2"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Firefox'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Win7'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
	}
	
	def "Win98,Microsoft Corporation,Windows Desktop,Opera"(){
		given:
		def ua="Opera/4.02 (Windows 98; U) [en]"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Opera'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Win98'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		
	}	
	
	def "Linux,Linux Foundation,Linux Desktop,Netscape"(){
		given:
		def ua="Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.0.2) Gecko/20021120 Netscape/7.01"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Netscape'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Linux Desktop'
		   browserCapabilities.deviceName=='Linux Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Linux'
		   browserCapabilities.platformMaker=='Linux Foundation'
	}
	
	def "WinVista,Microsoft Corporation,Windows Desktop,Maxthon"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; MyIE2; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Maxthon'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='WinVista'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
	}
	
	def "Linux,Linux Foundation,general Desktop,Lynx"(){
		given:
		def ua="Lynx/2.8.6dev.11 libwww-FM/2.14"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Lynx'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Desktop'
		   browserCapabilities.deviceName=='general Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Linux'
		   browserCapabilities.platformMaker=='Linux Foundation'
	}
	def "Unknown,Unknown,Unknown,General Crawlers"(){
		given:
		def ua="HotJava/1.1.2 FCS"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='General Crawlers'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
	}
	
	def "Unknown,Unknown,Unknown,IE Offline Browser"(){
		given:
		def ua=" Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0; YComp 5.0.2.6; MSIECrawler)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='IE Offline Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
	}
	
	def "Amiga OS,Commodore International,Amiga,Voyager"(){
		given:
		def ua="AmigaVoyager/3.2 (AmigaOS/MC680x0)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Voyager'
		   browserCapabilities.deviceBrandName=='Commodore'
		   browserCapabilities.deviceCodeName=='Amiga'
		   browserCapabilities.deviceName=='Amiga'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Amiga OS'
		   browserCapabilities.platformMaker=='Commodore International'
	}
	
	def"Unknown,Unknown,Unknown,Google Bot"(){
		given:
		def ua="Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Google Bot'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
	}
	
	def "Unknown,Unknown,general Desktop,YaCy Bot"(){
		given:
		def ua="yacybot (x86 Windows XP 5.1; java 1.6.0_12; Europe/de) http://yacy.net/bot.html"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='YaCy Bot'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Desktop'
		   browserCapabilities.deviceName=='general Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
	}
	def "Unknown,Unknown,Unknown,Yahoo! Slurp"(){
		given:
		def ua="Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Yahoo! Slurp'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='Unknown'
		   browserCapabilities.deviceName=='Unknown'
		   browserCapabilities.deviceType=='Unknown'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
	}
	
	
	def "Android,Google Inc,general Mobile Phone,Miui Browser"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; Android 4.3; en-us; HM 1SW Build/JLS36C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 XiaoMi/MiuiBrowser/1.0"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Miui Browser'
		   browserCapabilities.deviceBrandName=='Unknown'
		   browserCapabilities.deviceCodeName=='general Mobile Phone'
		   browserCapabilities.deviceName=='general Mobile Phone'
		   browserCapabilities.deviceType=='Mobile Phone'
		   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Android'
		   browserCapabilities.platformMaker=='Google Inc'
	}
	
	def "Android,Google Inc,C6903,Chrome"(){
		
		given:
		def ua="Mozilla/5.0 (Linux; Android 4.3; C6903 Build/14.2.A.1.136) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.59 Mobile Safari/537.36"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Chrome'
		   browserCapabilities.deviceBrandName=='Sony'
		   browserCapabilities.deviceCodeName=='C6903'
		   browserCapabilities.deviceName=='Xperia Z1'
		   browserCapabilities.deviceType=='Mobile Phone'
		   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Android'
		   browserCapabilities.platformMaker=='Google Inc'
		
	}
	
	def "Android,Google Inc,B8000-H,Android"(){
		given:
		def ua="Mozilla/5.0 (Linux; U; Android 4.2.2; de-de; Lenovo B8000-H/JDQ39) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.2.2 Mobile Safari/534.30"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Android'
		   browserCapabilities.deviceBrandName=='Lenovo'
		   browserCapabilities.deviceCodeName=='B8000-H'
		   browserCapabilities.deviceName=='Yoga Tablet 10 3G'
		   browserCapabilities.deviceType=='Tablet'
		   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==true
		   browserCapabilities.platform=='Android'
		   browserCapabilities.platformMaker=='Google Inc'
	}
	
	def "Unknown,Unknown,general Mobile Device,Nokia"(){
		given:
		def ua="NokiaN97/21.1.107 (SymbianOS/9.4; Series60/5.0 Mozilla/5.0; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebkit/525 (KHTML, like Gecko) BrowserNG/7.1.4"
			
		when:
		def browserCapabilities = browscap.lookup(ua)

	   then:
		   browserCapabilities.browser=='Nokia'
		   browserCapabilities.deviceBrandName=='Nokia'
		   browserCapabilities.deviceCodeName=='general Mobile Device'
		   browserCapabilities.deviceName=='general Mobile Device'
		   browserCapabilities.deviceType=='Mobile Phone'
		   browserCapabilities.isMobile==true
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Unknown'
		   browserCapabilities.platformMaker=='Unknown'
	}
}
