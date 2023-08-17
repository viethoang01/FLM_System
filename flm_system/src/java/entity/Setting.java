/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author nguye
 */
public class Setting {
    private int settingid;
    private String type;
    private String name;
    private String value;
    private int displayorder;
    private int isactive;

    public Setting() {
    }

    public Setting(int settingid, String type, String name, String value, int displayorder, int isactive) {
        this.settingid = settingid;
        this.type = type;
        this.name = name;
        this.value = value;
        this.displayorder = displayorder;
        this.isactive = isactive;
    }

    public int getSettingid() {
        return settingid;
    }

    public void setSettingid(int settingid) {
        this.settingid = settingid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(int displayorder) {
        this.displayorder = displayorder;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    @Override
    public String toString() {
        return "Setting{" + "settingid=" + settingid + ", type=" + type + ", name=" + name + ", value=" + value + ", displayorder=" + displayorder + ", isactive=" + isactive + '}';
    }

   
    
    
}