package com.microservice.edu.form.comments;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论表主表
 */
@Data
public class CommentsRoot implements Serializable{

    //评论主键id
    public String question_id;

    //评论唯一标识
    public String lesson_id;

    //评论的资源id。标记这条评论是属于哪个资源的。资源可以是文章、视频、资源
    public String chapter_no;

    //评论者id
    public String owner_id;

    //评论内容
    public String content;

    //创建时间
    public Date createTime;

    //子评论
    public List<CommentsReply> listCommentsReply;


    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getChapter_no() {
        return chapter_no;
    }

    public void setChapter_no(String chapter_no) {
        this.chapter_no = chapter_no;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<CommentsReply> getListCommentsReply() {
        return listCommentsReply;
    }

    public void setListCommentsReply(List<CommentsReply> listCommentsReply) {
        this.listCommentsReply = listCommentsReply;
    }
}

