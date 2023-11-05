/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jodo.model;

/**
 *
 * @author Abhishek
 */

public class UrlSetting {
    private int functionid;
    private int addPermission;
    private int viewpermission;
    private int editpermission;
    private int deletepermission;

    public int getFunctionid() {
        return functionid;
    }

    public void setFunctionid(int functionid) {
        this.functionid = functionid;
    }

    public int getAddPermission() {
        return addPermission;
    }

    public void setAddPermission(int addPermission) {
        this.addPermission = addPermission;
    }

    public int getViewpermission() {
        return viewpermission;
    }

    public void setViewpermission(int viewpermission) {
        this.viewpermission = viewpermission;
    }

    public int getEditpermission() {
        return editpermission;
    }

    public void setEditpermission(int editpermission) {
        this.editpermission = editpermission;
    }

    public int getDeletepermission() {
        return deletepermission;
    }

    public void setDeletepermission(int deletepermission) {
        this.deletepermission = deletepermission;
    }

    public UrlSetting(int functionid, int addPermission, int viewpermission, int editpermission, int deletepermission) {
        this.functionid = functionid;
        this.addPermission = addPermission;
        this.viewpermission = viewpermission;
        this.editpermission = editpermission;
        this.deletepermission = deletepermission;
    }

    public UrlSetting() {
    }

    @Override
    public String toString() {
        return "UrlSetting{" + "functionid=" + functionid + ", addPermission=" + addPermission + ", viewpermission=" + viewpermission + ", editpermission=" + editpermission + ", deletepermission=" + deletepermission + '}';
    }

   
    
    
}
