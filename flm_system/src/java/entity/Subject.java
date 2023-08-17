/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author SHD
 */
public class Subject {
    private int subject_id;
    private String code;
    private String name;
    private int semester;
    private int no_credit;
    private boolean is_active;
    private String subject_group_name;

    public Subject() {
    }

    public String getSubject_group_name() {
        return subject_group_name;
    }

    public void setSubject_group_name(String subject_group_name) {
        this.subject_group_name = subject_group_name;
    }
    
    public Subject(int subject_id, String code, String name, int semester, int no_credit, boolean is_active,String subject_group_name) {
        this.subject_id = subject_id;
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.no_credit = no_credit;
        this.is_active = is_active;
        this.subject_group_name = subject_group_name;
    }

    public Subject(int subject_id, String code, String name, int semester, int no_credit, boolean is_active) {
        this.subject_id = subject_id;
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.no_credit = no_credit;
        this.is_active = is_active;
    }
    
    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getNo_credit() {
        return no_credit;
    }

    public void setNo_credit(int no_credit) {
        this.no_credit = no_credit;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "Subject{" + "subject_id=" + subject_id + ", code=" + code + ", name=" + name + ", semester=" + semester + ", no_credit=" + no_credit + ", is_active=" + is_active + '}';
    }
    
    
}
