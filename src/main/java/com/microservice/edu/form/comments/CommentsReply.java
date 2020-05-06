package com.microservice.edu.form.comments;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表子表
 */
@Data
public class CommentsReply implements Serializable{

    //父评论的主键id
    public String id;

    //该条评论的唯一标识
    public String commentId;

    //评论者id
    public String fromId;

    //评论者名字
    public String fromName;

    //评论者头像
    public String fromAvatar;

    //被评论者id
    public String toId;

    //被评论者名字
    public String toName;

    //被评论者头像
    public String toAvatar;

    //获得点赞的数量
    public Integer likeNum;

    //评论内容
    public String content;

    //创建时间
    public Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAvatar() {
        return toAvatar;
    }

    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
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
}

