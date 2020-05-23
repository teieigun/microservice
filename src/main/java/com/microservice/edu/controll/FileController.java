package com.microservice.edu.controll;

import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.BigSmallDocumentsPojo;
import com.microservice.edu.pojo.LessonTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;
import com.microservice.edu.service.FileService;
import com.microservice.edu.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@Controller
public class FileController {


    @Autowired
    FileService fileService;

    @RequestMapping(value = "/admin/upload", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('9')")//拥有9级权限才可以访问
    public String upload(Model model) {

        //保存目录取得
        try {

            List<BigCategoryTblPojo> resultList = fileService.getBigCtgCode();
            model.addAttribute("resultList", resultList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/fileupload";
    }

    @RequestMapping(value = "/admin/smallCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @PreAuthorize("hasAuthority('9')")//拥有9级权限才可以访问
    public String[] smallCode(Model model, String bigCode) {

        List<SmallCategoryTblPojo> resultList = null;

        //保存目录取得
        Map<String, String> testmap = null;

        String[] test = {"001/java", "002/oracle", "003/html"};

        List<String> list = new ArrayList<String>();
        try {
            resultList = fileService.getSmallCtgCode(bigCode);

            list.add("0/--请选择IT技术小分类--");
            for (SmallCategoryTblPojo obj : resultList) {
                list.add(obj.bigCtgCode + obj.smallCtgCode + "/" + obj.smallCtgName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] array = list.toArray(new String[list.size()]);
        return array;
    }

    @RequestMapping(value = "/admin/lessonPath", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @PreAuthorize("hasAuthority('9')")//拥有9级权限才可以访问
    public String[] bigSmallCode(Model model, String bigSmallCode) {

        List<LessonTblPojo> resultList = null;

        //保存目录取得
        Map<String, String> testmap = null;

        String[] test = {"001/java", "002/oracle", "003/html"};

        List<String> list = new ArrayList<String>();
        try {
            resultList = fileService.getLessonListByCtg(bigSmallCode.substring(0, 3), bigSmallCode.substring(3, 7));

            for (LessonTblPojo obj : resultList) {
                list.add(obj.lessonName + "/" + obj.uploadPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] array = list.toArray(new String[list.size()]);
        return array;
    }

    @RequestMapping(value = "/admin/doUpload", method = {RequestMethod.POST})
    @PreAuthorize("hasAuthority('9')")//拥有9级权限才可以访问
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("path") String path, String smallCtg) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = path;
            System.out.println(filePath);

            File dest = new File(filePath + fileName);

            BigSmallDocumentsPojo pojo = new BigSmallDocumentsPojo();
            pojo.documentUrl = path.replace("\\","/");
            pojo.bigCtgCode = smallCtg.substring(0, 3);
            pojo.smallCtgCode = smallCtg.substring(3, 7);
            pojo.documentName = fileName;

            file.transferTo(dest);
            int rs = fileService.saveDocumentInfo(pojo);
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "上传失败！";
    }


    @RequestMapping(value = "/video/download", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional(readOnly = true)
    @ResponseBody
    public List<BigSmallDocumentsPojo> download(Model model,String lessonId) {

        List<BigSmallDocumentsPojo> resultList = null;
        //保存目录取得
        try {

            resultList = fileService.getDownloadFile(lessonId);
            model.addAttribute("resultList", resultList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }


    @RequestMapping(value = "/video/dlimage", method = RequestMethod.GET)
    public void Download1(HttpServletResponse res, String filePath, String fileName) {

        res.setContentType("application/octet-stream");
        try {
            res.setHeader("Content-Disposition", "attachment; filename=" +
                    new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/video/dldocx", method = RequestMethod.GET)
    public void Download2(HttpServletResponse res, String filePath, String fileName) {

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/video/dlexe", method = RequestMethod.GET)
    public void Download3(HttpServletResponse res, String filePath, String fileName) {

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/video/dlzip", method = RequestMethod.GET)
    public void Download4(HttpServletResponse res, String filePath, String fileName) {

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
