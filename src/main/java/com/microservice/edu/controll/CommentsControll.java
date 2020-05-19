package com.microservice.edu.controll;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microservice.edu.form.comments.CommentsReply;
import com.microservice.edu.form.comments.CommentsRoot;
import com.microservice.edu.form.comments.Liked;
import com.microservice.edu.form.comments.ResultDT;
import com.microservice.edu.service.CommentService;
import com.microservice.edu.service.WatchVideoService;
import com.microservice.edu.util.LogUtil;
import com.microservice.edu.util.ResultDTUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import web.SessionContext;

/**
 * 点赞问题控制器
 */
@Controller
@Slf4j
public class CommentsControll {
    @Autowired
    CommentService commentService;

    @Autowired
    WatchVideoService watchVideoService;

    private static int ROW =5;

    /**
     * 提问题 和 回答问题
     * @param commentsRoot
     * @return
     */
    @RequestMapping(value = "/video/question", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public String addRootComments(CommentsRoot commentsRoot,HttpServletRequest request) {


        String url = "redirect:/video/watch?lessonId=" + commentsRoot.lesson_id+"&chapterNo=" + commentsRoot.chapter_no+"&tagFlg=3&questionId=0";

        System.out.println("-------------:"+commentsRoot.getQuestion_id());
        //提问
        LogUtil.info("1" + commentsRoot.toString());
        if (commentsRoot.getContent().length() != 0 && commentsRoot.getQuestion_id().isEmpty()) {
            commentsRoot.setQuestion_id(UUID.randomUUID().toString().replaceAll("-", ""));//设置问题唯一标识
            commentsRoot.setCreate_time(new Date());//设置添加问题时间
            commentsRoot.setOwner_id(SessionContext.getUserName(request));

            LogUtil.info("2" + commentsRoot);
            boolean b = commentService.addRootCommentsService(commentsRoot); //调用service方法来完成问题的存储
            LogUtil.info("3" + commentsRoot.toString());
        // 回答
        }else{
            CommentsReply commentsReply = new CommentsReply();

            commentsReply.setQuestion_id(commentsRoot.question_id);
            commentsReply.setComment_id(UUID.randomUUID().toString().replaceAll("-", ""));
            commentsReply.setCreate_time(new Date());//设置添加问题时间
            commentsReply.setAnwser_id(SessionContext.getUserName(request));
            commentsReply.setContent(commentsRoot.getContent());
            boolean b = commentService.addSonCommentsService(commentsReply); //调用service方法来完成问题的存储
            url = "redirect:/video/watch?lessonId=" + commentsRoot.lesson_id+"&chapterNo=" + commentsRoot.chapter_no+"&tagFlg=3&questionId="+commentsRoot.question_id;
        }
        //问题内容为空 返回错误信息
        return url;
    }



    /**
     * 根据资源ID来回去该资源的所有问题
     * @param request
     * @return
     */

    @RequestMapping(value = "/video/showQuestion", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    @ResponseBody
    public List<CommentsRoot> showQuestion(int lessonId,int chapterNo, HttpServletRequest request) {

        LogUtil.info(lessonId+"/"+chapterNo);
        LogUtil.info(SessionContext.getUserName(request));


        System.out.println("LessonId:"+lessonId);

        //查询所有问题
        List<CommentsRoot> byOwnerIdService = commentService.findByLessonChapter(lessonId,chapterNo);
        LogUtil.info(byOwnerIdService.toString());

        //model.addAttribute("ListCommentsRoot", byOwnerIdService);


        return byOwnerIdService;
    }

    @RequestMapping(value = "/video/showQuestionPage", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    @ResponseBody
    public List<CommentsRoot> showQuestionFt(int lessonId,int chapterNo,int offset, HttpServletRequest request) {

        LogUtil.info(lessonId+"/"+chapterNo);
        LogUtil.info(SessionContext.getUserName(request));


        System.out.println("LessonId:"+lessonId);

        //查询所有问题
        List<CommentsRoot> byOwnerIdService = commentService.findByLessonChapterPage(lessonId,chapterNo,(offset-1)*ROW,ROW);
        LogUtil.info(byOwnerIdService.toString());

        //model.addAttribute("ListCommentsRoot", byOwnerIdService);


        return byOwnerIdService;
    }


    @RequestMapping(value = "/video/showAnwser", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    @ResponseBody
    public List<CommentsReply> showAnwser(String questionId, HttpServletRequest request) {

        LogUtil.info("questionId:"+questionId);
        LogUtil.info(SessionContext.getUserName(request));

        //查询所有问题
        List<CommentsRoot> byPkService = commentService.findByPkService(questionId);

        List<CommentsReply> commentsReply = commentService.findByQuestionId(questionId);

        if(commentsReply!=null && commentsReply.size()>0) {
        	if(byPkService!=null && byPkService.size()>0) {
        		commentsReply.get(0).commentsRoot=byPkService.get(0);
        	}
        }else {
        	commentsReply = new ArrayList<CommentsReply>();
        	CommentsReply commentsReply2 = new CommentsReply();
        	commentsReply2.commentsRoot=byPkService.get(0);
        	commentsReply.add(commentsReply2);
        }

        LogUtil.info(byPkService.toString());

        //model.addAttribute("ListCommentsRoot", byOwnerIdService);
        return commentsReply;
    }


    /**
     * 点赞模块，已完善
     * @param liked
     * @return
     */
    @RequestMapping(value = "/video/isLike", method = RequestMethod.POST)
    @Transactional(readOnly = true)
    public ResultDT isLike(Liked liked,String commType) {
        LogUtil.info("isLike"+liked.toString());
       boolean b;
        Liked checked = commentService.checkedLikeService(liked);
        if(checked == null){
            liked.setLikeStatus(1);
            b = commentService.addLikedService(liked);
        }else {
            if(checked.getLikeStatus() == 0)
                liked.setLikeStatus(1);
            else
                liked.setLikeStatus(0);
            b = commentService.updateLikedService(liked);
        }
        if(b) {
            //更新问题的点赞次数
            if(liked.getLikeStatus() == 0)
                liked.setLikeStatus(-1);
            if(commType.equals("root"))
                commentService.updateRootLikeNumService(liked);
            else
                commentService.updateReplyLikeNumService(liked);
            return ResultDTUtils.success(liked);
        }
        else
            return ResultDTUtils.error(ResultDTUtils.SUBMIT_ERROR,"SubmitError");
    }

    @RequestMapping(value = "/video/getListLikeByUserId", method = RequestMethod.POST)
    @Transactional(readOnly = true)
    public ResultDT getListLikeByUserId(String userId){
        //查询所有点赞信息
        List<Liked> listLikedService = commentService.getListLikedService(userId);
        LogUtil.info(listLikedService.toString());
        return ResultDTUtils.success(listLikedService);
    }

    @ResponseBody
    @RequestMapping(value = "/video/uploadImage", method = RequestMethod.GET)
    public Map upload(MultipartFile file, HttpServletRequest request){

        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "D:\\mycode\\LayUiTest\\src\\main\\resources\\static\\images\\" + dateStr+"\\"+uuid+"." + prefix;


                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images/"+ dateStr+"/"+uuid+"." + prefix);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }
}
