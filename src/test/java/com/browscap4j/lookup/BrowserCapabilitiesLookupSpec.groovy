package com.browscap4j.lookup

import com.browscap4j.domain.Browscap

class BrowserCapabilitiesLookupSpec extends BaseSpec{

	final Browscap browscap =  new Browscap(new File("/Users/Ankush/Downloads/browscap/browscap.csv"))


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
		browserCapabilities.deviceBrandName==''
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
	
	def "Test WinVista,Microsoft Corporation,Windows Desktop,IE"(){
		given:
		def ua="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Acoo Browser; GTB5; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; InfoPath.1; .NET CLR 3.5.30729; .NET CLR 3.0.30618)"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		   browserCapabilities.browser=='IE'
		   browserCapabilities.deviceBrandName==''
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
		  browserCapabilities.deviceBrandName==''
		 browserCapabilities.deviceCodeName==''
		  browserCapabilities.deviceName==''
		   browserCapabilities.deviceType==''
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform==''
		   browserCapabilities.platformMaker==''
		}

	def "Test WinXP,Microsoft Corporation,Windows Desktop,Chrome"(){
		given:
		def ua="Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.25 (KHTML, like Gecko) Chrome/12.0.704.0 Safari/534.25"
			
		when:
		def browserCapabilities = browscap.lookup(ua)
	   
	   then:
		   browserCapabilities.browser=='Chrome'
		   browserCapabilities.deviceBrandName==''
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
		   browserCapabilities.deviceBrandName==''
		   browserCapabilities.deviceCodeName=='Windows Desktop'
		   browserCapabilities.deviceName=='Windows Desktop'
		   browserCapabilities.deviceType=='Desktop'
		   browserCapabilities.isMobile==false
		   browserCapabilities.isTablet==false
		   browserCapabilities.platform=='Win2000'
		   browserCapabilities.platformMaker=='Microsoft Corporation'
		}

}
