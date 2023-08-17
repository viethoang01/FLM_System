/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

/**
 *
 * @author SHD
 */
public class PermissionSetting {
    private int page_id;
    private List<Permission> list;
    private String pageName;

    public PermissionSetting() {
    }

    public PermissionSetting(int page_id, List<Permission> list, String pageName) {
        this.page_id = page_id;
        this.list = list;
        this.pageName = pageName;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public List<Permission> getList() {
        return list;
    }

    public void setList(List<Permission> list) {
        this.list = list;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    
    
}
