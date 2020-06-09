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

	//执行成功
	public static final String EXECUTE_MESSAGE = "executeMessage";
	//执行失败
	public static final String ERROR_MESSAGE = "errorMessage";

	//匿名用户
	public static final String NO_NAME_USER = "microservice@microserviceedu.com";

	//登录状态
	public static final String LOGIN_STATUS = "loginStatus";
	//已登录状态
	public static final String LOGIN_STATUS_ON = "1";
	//非登录状态
	public static final String LOGIN_STATUS_OFF = "0";



	//--------------------------------环境切换------------------------------------/
	//开发环境
	//public static final String IMAGE_PATH="file:///C:\\microservice\\profile\\";

	//开发环境
	//public static final String UPLOAD_PATH="file:///C:\\upload\\";

	//开发环境
	//public static final String HTTP_URL ="http://localhost:8080";


	//真实环境
	public static final String IMAGE_PATH="file:/var/www/html/img/";
	//真实环境
	public static final String HTTP_URL ="http://www.microserviceedu.com";
	//真实环境
	public static final String UPLOAD_PATH="file:/var/www/html/upload/";

	//---------------------------------------------------------------------------/
}
