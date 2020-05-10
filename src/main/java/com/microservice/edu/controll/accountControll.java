package com.microservice.edu.controll;


import com.microservice.edu.form.UploadForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Decoder;
import web.SessionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
public class accountControll {


	@RequestMapping(value = "/account", method = RequestMethod.GET)
	@Transactional(readOnly = true)
    public String account(HttpServletRequest request){
		System.out.println(SessionContext.getUserName(request));
        //提示具体用户名称登录成功
        return "/account";
    }

	//获取当前用户信息
	private UserDetails getUserDetails(){
		String username = null;
		//当前认证通过的用户身份
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//用户身份
		Object principal = authentication.getPrincipal();
		if(principal == null){
			username = "匿名";
		}
		if(principal instanceof UserDetails){
			UserDetails userDetails = (UserDetails) principal;
			return userDetails;
		}
		return null;
	}

	@Value("${userProfilePath}")
	private String userProfilePath;


	@RequestMapping(path = "/video/upload", method = RequestMethod.POST)
	String upload(Model model, UploadForm uploadForm) {
		Path path = Paths.get(userProfilePath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (NoSuchFileException ex) {
				System.err.println(ex);
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}

		String filename = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		Path uploadfile = Paths
				.get(userProfilePath + filename + ".png");

		String imageString = uploadForm.getImageCode().replace("url(\"","");
		imageString=imageString.replace("\")","");
		imageString=imageString.replace(" ","+");

		imageString = imageString.replace("data:image/jpeg;base64,", "");
		imageString = imageString.replace("data:image/png;base64,", "");


		writeImage(imageString,uploadfile);


		return "redirect:/sample";
	}

	private void writeImage(String imageString,Path userProfilePath){

		if (imageString == null) {
			return;
		}

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			//Base64デコード
			byte[] b = decoder.decodeBuffer(imageString);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			File file = new File(userProfilePath.toString());
			OutputStream out = new FileOutputStream(file);
			out.write(b);
			out.flush();
			out.close();

			File file2 = new File(userProfilePath.toString());
			if (file2.exists()) {
				file2.setExecutable(true, false);
				file2.setReadable(true, false);
				file2.setWritable(true, false);
			}

			return ;
		} catch (Exception e) {
			return ;
		}
	}
}
