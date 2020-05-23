package com.microservice.edu.pojo;

public class LessonTblPojo {

    public String lessonId;
    public String lessonName;
    public String bigCtgCode;
    public String bitCtgName;
    public String smallCtgCode;
    public String lessonRole;
    public String lessonImg;
    public String uploadPath;
    public String del;
    public String lessonType;
    public String videoFlg;


    public String getLessonType() { return lessonType; }

    public void setLessonType(String lessonType) { this.lessonType = lessonType; }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getBigCtgCode() {
        return bigCtgCode;
    }

    public void setBigCtgCode(String bigCtgCode) {
        this.bigCtgCode = bigCtgCode;
    }

    public String getSmallCtgCode() {
        return smallCtgCode;
    }

    public void setSmallCtgCode(String smallCtgCode) {
        this.smallCtgCode = smallCtgCode;
    }

    public String getLessonRole() {
        return lessonRole;
    }

    public void setLessonRole(String lessonRole) {
        this.lessonRole = lessonRole;
    }

    public String getLessonImg() {
        return lessonImg;
    }

    public void setLessonImg(String lessonImg) {
        this.lessonImg = lessonImg;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getBitCtgName() {
        return bitCtgName;
    }

    public void setBitCtgName(String bitCtgName) {
        this.bitCtgName = bitCtgName;
    }

    public String getVideoFlg() { return videoFlg; }

    public void setVideoFlg(String videoFlg) { this.videoFlg = videoFlg; }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
