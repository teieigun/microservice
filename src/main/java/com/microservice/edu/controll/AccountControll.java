package com.microservice.edu.controll;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.microservice.edu.constants.MicroServiceConstants;
import com.microservice.edu.form.ChangePwdForm;
import com.microservice.edu.form.PassForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.dao.UserDao;
import com.microservice.edu.form.UploadForm;
import com.microservice.edu.pojo.UserPojo;
import com.microservice.edu.service.ProfileService;

import com.microservice.edu.web.SessionContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AccountControll {

    @Autowired
    ProfileService profileService;

    @Value("${userProfilePath}")
    private String userProfilePath;

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String account(Model model, String ValCode, HttpServletRequest request) {
        System.out.println(SessionContext.getUserName(request));
        UserPojo userPojo = null;
        try {
            userPojo = userDao.findbyPk(SessionContext.getUserName(request));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ChangePwdForm form = (ChangePwdForm)model.asMap().get("cpwd");
        if(form==null){
            form = new ChangePwdForm();
        }

        String errorMsg = (String)model.asMap().get(MicroServiceConstants.ERROR_MESSAGE);
        String executeMsg = (String)model.asMap().get(MicroServiceConstants.EXECUTE_MESSAGE);

        model.addAttribute("errorMsg", errorMsg);
        model.addAttribute("executeMsg", executeMsg);

        model.addAttribute("cpwd", form);
        model.addAttribute("username", SessionContext.getUserName(request));
        model.addAttribute("filename", userProfilePath);
        model.addAttribute("ValCode", userPojo.getValidateCode());
        model.addAttribute("profileImage", SessionContext.getAttribute(request, "profileImage"));
        //提示具体用户名称登录成功
        return "/account";
    }

    //获取当前用户信息
    private UserDetails getUserDetails() {
        String username = null;
        //当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "匿名";
        }
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails;
        }
        return null;
    }

    @RequestMapping(path = "/video/upload", method = RequestMethod.POST)
    String upload(Model model, UploadForm uploadForm, HttpServletRequest request) {
        UserPojo userPojo = null;
        try {
            userPojo = userDao.findbyPk(SessionContext.getUserName(request));
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

            String imageName = userPojo.getValidateCode() + ".png";

            profileService.imgUpload(uploadForm, userProfilePath + imageName);

            userDao.updateUserProfileImage(SessionContext.getUserName(request), imageName);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/account";
    }

    /**
     *  变更密码
     * **/
    @RequestMapping(path = "/video/cpwd", method = RequestMethod.POST)
    @Transactional
    String cpwd(Model model, @ModelAttribute("form") @Valid ChangePwdForm chpwdForm, BindingResult result, RedirectAttributes attributes) {
        UserPojo userPojo = null;
        try {

            String rs ="redirect:/account";

            int rows = profileService.updatePasswd(chpwdForm);

            if (result.hasErrors()) {
                attributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "cpwd", result);
                attributes.addFlashAttribute("cpwd", chpwdForm);
                return "redirect:/account";
            }

            if(rows<1){
                attributes.addFlashAttribute(MicroServiceConstants.ERROR_MESSAGE, "密码更新失败");
                return "redirect:/account";
            }

            attributes.addFlashAttribute(MicroServiceConstants.EXECUTE_MESSAGE, "密码更新成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/account";
    }
}
