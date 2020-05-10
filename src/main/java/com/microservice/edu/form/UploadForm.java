package com.microservice.edu.form;

import org.springframework.web.multipart.MultipartFile;
public class UploadForm {
    private MultipartFile file;

    private String imageCode;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }
}
