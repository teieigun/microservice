package com.microservice.edu.form.comments;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表子表
 */
@Data
public class CommentsReply implements Serializable{

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
}

