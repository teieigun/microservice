package com.microservice.edu.service;

import java.util.List;

import com.microservice.edu.form.comments.CommentsReply;
import com.microservice.edu.form.comments.CommentsRoot;
import com.microservice.edu.form.comments.Liked;

public interface CommentService {

    /**
     * 获取该文章或资源下的所有评论
     * @param ownerId 文章或资源id
     * @return
     */
    List<CommentsRoot> findByOwnerIdService(String ownerId);

    /**
     * 用问题ID取得问题内容
     * @param questionId 问题ID
     * @return
     */
    List<CommentsRoot> findByPkService(String questionId);

    /**
     * 获取该文章或资源下的所有评论
     * @param lessonId 文章或资源id
     * @param chapterNo 文章或资源id
     * @return
     */
    List<CommentsRoot> findByLessonChapter(int lessonId,int chapterNo);

    List<CommentsRoot> findByLessonChapterPage(int lessonId,int chapterNo,int offset,int row);



    List<CommentsReply> findByQuestionId(String questionId);



    /**
     * 添加父评论
     * @param commentsRoot
     * @return
     */
    boolean addRootCommentsService(CommentsRoot commentsRoot);

    /**
     * 添加子评论或回复评论
     * @param commentsReply
     * @return
     */
    boolean addSonCommentsService(CommentsReply commentsReply);

    /**
     * 点赞
     * @param liked
     * @return
     */
    boolean addLikedService(Liked liked);

    /**
     * 查询单个用户的所有点赞信息
     * @param userId
     * @return
     */
    List<Liked> getListLikedService(String userId);

    /**
     * 修改点赞
     * @param liked
     * @return
     */
    boolean updateLikedService(Liked liked);

    /**
     * 检测用户是否点赞了
     * @param liked
     * @return
     */
    Liked checkedLikeService(Liked liked);

    /**
     * 更新父表的点赞数
     * @param liked
     * @return
     */
    boolean updateRootLikeNumService(Liked liked);

    /**
     * 更新子评论点赞数
     * @param liked
     * @return
     */
    boolean updateReplyLikeNumService(Liked liked);
}
