package com.microservice.edu.config;

import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfiguration  
{  
	@Bean
	public TomcatServletWebServerFactory tomcatCustomizer() {
	  TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
	  factory.addConnectorCustomizers((connector -> {
	    connector.addUpgradeProtocol(new Http2Protocol());
	    connector.setMaxPostSize(10000000);
	  }));
	  return factory;
	}

}
