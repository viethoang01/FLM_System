/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author SHD
 */
public class Curiculum {
    private int curriculum_id;
    private String code;
    private String name;
    private String description;
    private int decision_id;
    private String total_credit;
    private String decision_no;
    private String decision_dated;
    private int owner_id;
    private boolean is_active;
    
    public Curiculum() {
    }

    public Curiculum(int curriculum_id, String code, String name, String description, int decision_id, String total_credit, int owner_id, boolean is_active) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.decision_id = decision_id;
        this.total_credit = total_credit;
        this.owner_id = owner_id;
        this.is_active = is_active;
    }

    public Curiculum(int curriculum_id, String code, String name, String description, String total_credit, String decision_no, String decision_date) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.total_credit = total_credit;
        this.decision_no = decision_no;
        this.decision_dated = decision_date;
    }
    
     public Curiculum(int curriculum_id, String code, String name, String description, int decision_id, String total_credit, int owner_id) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.decision_id = decision_id;
        this.total_credit = total_credit;
        this.owner_id = owner_id;
        this.is_active = is_active;
    }
    public Curiculum(int curriculum_id, String code, String name, String description, String total_credit,int decision_id, boolean is_active) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.total_credit = total_credit;
        this.is_active = is_active;
        this.decision_id = decision_id;
    }
    public Curiculum(int curriculum_id, String code, String name, String description, String total_credit, boolean is_active) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.total_credit = total_credit;
        this.is_active = is_active;
    }
    public int getCurriculum_id() {
        return curriculum_id;
    }

    public String getDecision_no() {
        return decision_no;
    }

    public void setDecision_no(String decision_no) {
        this.decision_no = decision_no;
    }

    public String getDecision_dated() {
        return decision_dated;
    }

    public void setDecision_dated(String decision_dated) {
        this.decision_dated = decision_dated;
    }
    

    public void setCurriculum_id(int curriculum_id) {
        this.curriculum_id = curriculum_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDecision_id() {
        return decision_id;
    }

    public void setDecision_id(int decision_id) {
        this.decision_id = decision_id;
    }

    public String getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(String total_credit) {
        this.total_credit = total_credit;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    
}