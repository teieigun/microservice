package com.microservice.edu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "slack.api")
public class SlackApiConfiguration {

    private String fastApi;

    private String htmApi;

    private String phaseFlg;

    private String contextPathForJs;

	private String contextPath="";

	private String imagePath="";

	private String userProfilePath="";


	public String getUserProfilePath() {
		return userProfilePath;
	}
	public void setUserProfilePath(String userProfilePath) {
		this.userProfilePath = userProfilePath;
	}
	public String getFastApi() {
		return fastApi;
	}

	public void setFastApi(String fastApi) {
		this.fastApi = fastApi;
	}

	public String getHtmApi() {
		return htmApi;
	}

	public void setHtmApi(String htmApi) {
		this.htmApi = htmApi;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getPhaseFlg() {
		return phaseFlg;
	}

	public void setPhaseFlg(String phaseFlg) {
		this.phaseFlg = phaseFlg;
	}

	public String getContextPathForJs() {
		return contextPathForJs;
	}

	public void setContextPathForJs(String contextPathForJs) {
		this.contextPathForJs = contextPathForJs;
	}


}