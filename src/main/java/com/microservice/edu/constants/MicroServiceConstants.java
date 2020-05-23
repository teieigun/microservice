package com.microservice.edu.constants;

public final class MicroServiceConstants {

	//省番号
	public static final String CODE_SHENG ="01";

	//用户状态 0：为认证状态的用户
	public static final int USER_STATUS_NOPASS =0;

	//用户状态 1：通过认证状态的用户
	public static final int USER_STATUS_PASSING =1;

	//用户状态 2：通过认证状态的用户
	public static final int USER_STATUS_PASSED =2;

	//套餐ID从 90000 到 99999
	public static final int COURSE_ID_FROM =90000;

	//套餐ID从 90000 到 99999
	public static final int COURSE_ID_TO =99999;

	//开发环境
	public static final String IMAGE_PATH="file:///C:\\img\\profile\\";

	//真实环境
	//public static final String IMAGE_PATH="file:/var/www/html/img/";

	//开发环境
	public static final String UPLOAD_PATH="file:///C:\\upload\\";

	//真实环境
	//public static final String UPLOAD_PATH="file:/var/www/html/upload/";

}
