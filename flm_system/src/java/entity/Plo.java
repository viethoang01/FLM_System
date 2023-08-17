/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author SHD
 */
public class Plo {
    private int plo_id;
    private String name;
    private String description;
    private int curriculum_id;

    public Plo() {
    }

    public Plo(int plo_id, String name, String description, int curriculum_id) {
        this.plo_id = plo_id;
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
    }

    public int getPlo_id() {
        return plo_id;
    }

    public void setPlo_id(int plo_id) {
        this.plo_id = plo_id;
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