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
public class Po {
    private int po_id;
    private String name;
    private String description;
    private int curriculum_id;

    public Po() {
    }

    public Po(int po_id, String name, String description, int curriculum_id) {
        this.po_id = po_id;
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
    }

    public int getPo_id() {
        return po_id;
    }

    public void setPo_id(int po_id) {
        this.po_id = po_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(int curriculum_id) {
        this.curriculum_id = curriculum_id;
    }
    
}
