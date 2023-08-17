/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author SHD
 */
public class Decision {
    private int decision_id;
    private String decision_no;
    private String decision_date;
    private int creator_id;

    public Decision() {
    }

    public Decision(int decision_id, String decision_no, String decision_date, int creator_id) {
        this.decision_id = decision_id;
        this.decision_no = decision_no;
        this.decision_date = decision_date;
        this.creator_id = creator_id;
    }

    public int getDecision_id() {
        return decision_id;
    }

    public void setDecision_id(int decision_id) {
        this.decision_id = decision_id;
    }

    public String getDecision_no() {
        return decision_no;
    }

    public void setDecision_no(String decision_no) {
        this.decision_no = decision_no;
    }

    public String getDecision_date() {
        return decision_date;
    }

    public void setDecision_date(String decision_date) {
        this.decision_date = decision_date;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    @Override
    public String toString() {
        return "Decision{" + "decision_id=" + decision_id + ", decision_no=" + decision_no + ", decision_date=" + decision_date + ", creator_id=" + creator_id + '}';
    }
    
    
}
