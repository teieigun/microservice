package com.microservice.edu.mapper;

import com.microservice.edu.form.comments.CommentsRoot;
import com.microservice.edu.form.comments.CommentsReply;
import com.microservice.edu.form.comments.Liked;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentsMapper {

    /**
     * 获取该文章或资源下的所有评论
     * @param ownerId 文章或资源id
     * @return
     */
    @Select("select * from comments_root where owner_id = #{ownerId}")
    List<CommentsRoot> findByOwnerId(String ownerId);

    /**
     * 用问题ID获取问题内容
     * @param questionId 问题ID
     * @return
     */
    @Select("SELECT t1.question_id,t1.lesson_id,t1.chapter_no,t1.owner_id,t2.profile_image,t1.content,t1.create_time from comments_root t1 LEFT JOIN user_base_info t2 ON t1.owner_id = t2.email WHERE question_id = #{questionId}")
    List<CommentsRoot> findByOwnerPk(String questionId);

    /**
     * 获取该文章或资源下的所有评论
     * @param lessonId 文章或资源id
     * @param chapterno 文章或资源id
     * @return
     */
    @Select("SELECT t1.question_id,t1.lesson_id,t1.chapter_no,t1.owner_id,t2.profile_image,t1.content,t1.create_time from comments_root t1 LEFT JOIN user_base_info t2 ON t1.owner_id = t2.email WHERE lesson_id =#{lessonId} AND chapter_no=#{chapterno}")
    List<CommentsRoot> findByLessonChapter(int lessonId,int chapterno );

    /**
     * 添加子评论或回复评论
     * @param commentsReply
     * @return
     */
    @Insert("insert into comments_reply (id,comment_id,from_id,from_name,from_avatar,to_id,to_name,to_avatar,like_num,content,create_time) " +
            "values(#{id},#{commentId},#{fromId},#{fromName},#{fromAvatar},#{toId},#{toName},#{toAvatar},#{likeNum},#{content},#{createTime})")
    boolean addSonComments(CommentsReply commentsReply);

    /**
     * 添加父评论
     * @param commentsRoot
     * @return
     */
    @Insert("insert into comments_root (question_id,lesson_id,chapter_no,owner_id,content,create_time) " +
            "values(#{question_id},#{lesson_id},#{chapter_no},#{owner_id},#{content},#{createTime})")
    boolean addRootComments(CommentsRoot commentsRoot);

    /**
     * 点赞
     * @param liked
     * @return
     */
    @Insert("insert into liked (obj_id,user_id,like_status) values(#{objId},#{userId},#{likeStatus})")
    boolean addLiked(Liked liked);

    /**
     * 点赞或取消点赞
     * @param liked
     * @return
     */
    @Insert("update liked set like_status = #{likeStatus} where user_id = #{userId} and obj_id = #{objId}")
    boolean updateLiked(Liked liked);

    /**
     * 查询单个用户的所有点赞信息
     * @param userId
     * @return
     */
    @Select("select * from liked where user_id = #{userId}")
    List<Liked> getListLiked(String userId);

    /**
     * 检测用户是否点赞了
     * @param liked
     * @return
     */
    @Select("select * from liked where user_id = #{userId} and obj_id = #{objId} ")
    Liked checkedLike(Liked liked);

    /**
     * 更新父表的点赞数
     * @param liked
     * @return
     */
    @Update("update comments_root set like_num = like_num + #{likeStatus} where comment_id = #{objId} and like_num + #{likeStatus} >= 0")
    boolean updateRootLikeNum(Liked liked);

    /**
     * 更新子评论点赞数
     * @param liked
     * @return
     */
    @Update("update comments_reply set like_num = like_num + #{likeStatus} where comment_id = #{objId} and like_num + #{likeStatus} >= 0")
    boolean updateReplyLikeNum(Liked liked);
}
