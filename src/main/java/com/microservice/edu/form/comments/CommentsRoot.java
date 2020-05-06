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
    public Integer id;

    //评论唯一标识
    public String commentId;

    //评论的资源id。标记这条评论是属于哪个资源的。资源可以是文章、视频、资源
    public String ownerId;

    //评论类型。1文章，2视频评论，3资源评论
    public Integer type;

    //评论者id
    public String fromId;

    //评论者名字
    public String fromName;

    //评论者头像
    public String fromAvatar;

    //获得点赞的数量
    public Integer likeNum;

    //评论内容
    public String content;

    //创建时间
    public Date createTime;

    //子评论
    public List<CommentsReply> listCommentsReply;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public List<CommentsReply> getListCommentsReply() {
        return listCommentsReply;
    }

    public void setListCommentsReply(List<CommentsReply> listCommentsReply) {
        this.listCommentsReply = listCommentsReply;
    }
}

