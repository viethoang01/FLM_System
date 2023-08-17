/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author DUCHIEUPC.COM
 */
public class CurriculumSubject {

    int curriculum_id;
    int subject_id;
    int subject_group_id;

    public CurriculumSubject(int curriculum_id, int subject_id, int subject_group_id) {
        this.curriculum_id = curriculum_id;
        this.subject_id = subject_id;
        this.subject_group_id = subject_group_id;
    }

    public CurriculumSubject(int curriculum_id, int subject_id) {
        this.curriculum_id = curriculum_id;
        this.subject_id = subject_id;
    }

    public int getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(int curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getSubject_group_id() {
        return subject_group_id;
    }

    public void setSubject_group_id(int subject_group_id) {
        this.subject_group_id = subject_group_id;
    }
    
    
}
