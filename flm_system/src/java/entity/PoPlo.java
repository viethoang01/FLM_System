/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author SHD
 */
public class PoPlo {

    int plo_id;
    int po_id;

    public PoPlo(int plo_id, int po_id) {
        this.plo_id = plo_id;
        this.po_id = po_id;
    }

    public int getPlo_id() {
        return plo_id;
    }

    public void setPlo_id(int plo_id) {
        this.plo_id = plo_id;
    }

    public int getPo_id() {
        return po_id;
    }

    public void setPo_id(int po_id) {
        this.po_id = po_id;
    }

}
