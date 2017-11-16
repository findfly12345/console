package com.allcheer.bpos.form;

/**
 * Created by fireWorks on 2016/3/10.
 */
public class ChannelFileUploadForm {

    private String uploadFile;

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile == null ? null : uploadFile.trim();
    }

    public String getUploadFile() {
        return uploadFile;
    }
}
