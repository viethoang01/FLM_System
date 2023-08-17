/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author SHD
 */
public class SubjectGroup {
    private int subject_group_id;
    private int curriculum_id;
    private String group_type;
    private String name;

    public SubjectGroup() {
    }

    public SubjectGroup(int subject_group_id, int curriculum_id, String group_type, String name) {
        this.subject_group_id = subject_group_id;
        this.curriculum_id = curriculum_id;
        this.group_type = group_type;
        this.name = name;
    }

    public int getSubject_group_id() {
        return subject_group_id;
    }

    public void setSubject_group_id(int subject_group_id) {
        this.subject_group_id = subject_group_id;
    }

    public int getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(int curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
