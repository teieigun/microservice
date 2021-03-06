package com.microservice.edu.form.comments;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 评论表子表
 */
@Data
public class CommentsReply implements Serializable {

    public CommentsRoot commentsRoot;

    //问题ID
    public String question_id;

    //回答ID
    public String comment_id;

    //回答者
    public String anwser_id;

    //评论内容
    public String content;

    //获得点赞的数量
    public Integer like_num;

    //创建时间
    public Date create_time;

    //提问者头像
    public String profile_image;

    //发布者
    public String mb_name;

    //发布时间
    public int days;


    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getAnwser_id() {
        return anwser_id;
    }

    public void setAnwser_id(String anwser_id) {
        this.anwser_id = anwser_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getLike_num() {
        return like_num;
    }

    public void setLike_num(Integer like_num) {
        this.like_num = like_num;
    }

    public CommentsRoot getCommentsRoot() {
        return commentsRoot;
    }

    public void setCommentsRoot(CommentsRoot commentsRoot) {
        this.commentsRoot = commentsRoot;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getMb_name() {
        return mb_name;
    }

    public void setMb_name(String mb_name) {
        this.mb_name = mb_name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

