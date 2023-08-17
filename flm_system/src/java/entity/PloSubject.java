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
public class PloSubject {
    private int plo_id;
    private int curriculum_id;
    private int subject_id;

    public PloSubject(int plo_id, int curriculum_id, int subject_id) {
        this.plo_id = plo_id;
        this.curriculum_id = curriculum_id;
        this.subject_id = subject_id;
    }

    public int getPlo_id() {
        return plo_id;
    }

    public void setPlo_id(int plo_id) {
        this.plo_id = plo_id;
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
    
    
}
