/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author SHD
 */
public class Permission {
    private int role_id;
    private int page_id;
    private boolean access_all;
    private boolean can_read;
    private boolean can_add;
    private boolean can_edit;
    private boolean can_delete;
    private String pageName;
    private String roleName;

    public Permission() {
    }

    public Permission(int role_id, int page_id, boolean access_all,boolean can_read, boolean can_add, boolean can_edit, boolean can_delete,String pageName,String roleName) {
        this.role_id = role_id;
        this.page_id = page_id;
        this.access_all = access_all;
        this.can_read = can_read;
        this.can_add = can_add;
        this.can_edit = can_edit;
        this.can_delete = can_delete;
        this.pageName = pageName;
        this.roleName = roleName;
    }

    public boolean isCan_read() {
        return can_read;
    }

    public void setCan_read(boolean can_read) {
        this.can_read = can_read;
    }
    
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public int getRole_id() {
        return role_id;
    }
    
    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    
    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public boolean isAccess_all() {
        return access_all;
    }

    public void setAccess_all(boolean access_all) {
        this.access_all = access_all;
    }

    public boolean isCan_add() {
        return can_add;
    }

    public void setCan_add(boolean can_add) {
        this.can_add = can_add;
    }

    public boolean isCan_edit() {
        return can_edit;
    }

    public void setCan_edit(boolean can_edit) {
        this.can_edit = can_edit;
    }

    public boolean isCan_delete() {
        return can_delete;
    }

    public void setCan_delete(boolean can_delete) {
        this.can_delete = can_delete;
    }
    
}
