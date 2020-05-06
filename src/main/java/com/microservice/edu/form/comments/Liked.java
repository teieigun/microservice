package com.microservice.edu.form.comments;

import lombok.Data;

import java.io.Serializable;

@Data
public class Liked  implements Serializable {
    public Integer id; //主键

    public String objId;//对应对象的id

    public String userId;//点赞用户的id

    public Integer likeStatus;//点赞状态，对应的1标识已赞，0标识取消赞

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
    }
}
