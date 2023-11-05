/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jodo.model;

import java.util.List;

/**
 *
 * @author Abhishek
 */
public class UrlsettingApiResponse {

    private String resdatetime;
    private int revisionno;
    private int status;
    private String statusdesc;
    private List<UrlSetting> urlSetting;

    public UrlsettingApiResponse(String resdatetime, int revisionno, int status, String statusdesc, List<UrlSetting> urlSetting) {
        this.resdatetime = resdatetime;
        this.revisionno = revisionno;
        this.status = status;
        this.statusdesc = statusdesc;
        this.urlSetting = urlSetting;
    }

    public UrlsettingApiResponse() {
    }

    public String getResdatetime() {
        return resdatetime;
    }

    public void setResdatetime(String resdatetime) {
        this.resdatetime = resdatetime;
    }

    public int getRevisionno() {
        return revisionno;
    }

    public void setRevisionno(int revisionno) {
        this.revisionno = revisionno;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public List<UrlSetting> getUrlSetting() {
        return urlSetting;
    }

    public void setUrlSetting(List<UrlSetting> urlSetting) {
        this.urlSetting = urlSetting;
    }

    @Override
    public String toString() {
        return "UrlsettingApiResponse{" + "resdatetime=" + resdatetime + ", revisionno=" + revisionno + ", status=" + status + ", statusdesc=" + statusdesc + ", urlSetting=" + urlSetting + '}';
    }
    
}