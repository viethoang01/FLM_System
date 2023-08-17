/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_access;

import entity.Decision;
import entity.Plo;
import entity.Po;
import entity.PoPlo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static utils.DBContext.connectMySql;

/**
 *
 * @author SHD
 */
public class DecisionDAO {

    public List<Decision> getAllDecision(String sort, String txt, int offset) {
        Connection con = connectMySql();
        List<Decision> list = new ArrayList<>();
        String sql = "select * from Decision where decision_no like ?";
        try {
            if (txt.equalsIgnoreCase("")) {
                sql = "select * from Decision ";
            }

            switch (sort) {
                case "decision_no":
                    sql += " order by decision_no ";
                    break;
                case "decision_date":
                    sql += " order by decision_date ";
                    break;
                case "decision_id":
                    sql += " order by decision_id ";
                    break;

                default:
                    sql += " order by creator_id ";
                    break;
            }
            sql += " limit 3 offset ?;";

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            if (!txt.equals("")) {
               
                ps.setString(1, '%' + txt + '%');
                ps.setInt(2, offset);
            } else{
                ps.setInt(1, offset);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Decision p = new Decision(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new DecisionDAO().updateDecision(new Decision(1, "decision_no", "2000-04-01", 1)));
    }

    public int getTotalItem(String txt) {
        Connection con = connectMySql();
        List<Plo> list = new ArrayList<>();
        String sql = "select count(*) from decision where  decision_no like ?";
        try {

            if (txt.equalsIgnoreCase("")) {
                sql = "select count(*) from decision ";
            }
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            if (!txt.equalsIgnoreCase("")) {
                ps.setString(1, '%' + txt + '%');
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public Decision getDecision(int id) {
        Connection con = connectMySql();

        String sql = "select * from Decision where decision_id = ?";
        try {

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Decision(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Plo> getPlosByCuriculumn(int curId) {
        Connection con = connectMySql();
        List<Plo> plos = new ArrayList<>();
        String sql = "select * from plo where curriculum_id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, curId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                plos.add(new Plo(rs.getInt(1), rs.getString(2), rs.getString(3), curId));
            }
        } catch (SQLException e) {
        }
        return plos;
    }

    public List<Po> getPosByCuriculumn(int curId) {
        Connection con = connectMySql();
        List<Po> pos = new ArrayList<>();
        String sql = "select * from po where curriculum_id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, curId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pos.add(new Po(rs.getInt(1), rs.getString(2), rs.getString(3), curId));
            }
        } catch (SQLException e) {
        }
        return pos;
    }

    public List<PoPlo> getPoPloByCur(int curId) {
        Connection con = connectMySql();
        List<PoPlo> pos = new ArrayList<>();
        String sql = "select po_plo.plo_id, po_plo.po_id from po_plo\n"
                + "join po\n"
                + "on po_plo.po_id = po.po_id\n"
                + "join plo\n"
                + "on po_plo.plo_id = plo.plo_id\n"
                + "where plo.curriculum_id=?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, curId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pos.add(new PoPlo(rs.getInt(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
        }
        return pos;
    }

    public void deleteMapping(int curId) {
        Connection con = connectMySql();
        String sql = "delete po_plo from po_plo\n"
                + "INNER JOIN po\n"
                + "on po_plo.po_id = po.po_id\n"
                + "INNER JOIN plo\n"
                + "on po_plo.plo_id = plo.plo_id\n"
                + "where po.curriculum_id = ? and plo.curriculum_id = ?;";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, curId);
            ps.setInt(2, curId);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void addMapping(int po_id, int plo_id) {
        Connection con = connectMySql();
        String sql = "insert into po_plo values (?,?);";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, po_id);
            ps.setInt(2, plo_id);

            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public boolean updateDecision(Decision decision) {
        boolean check = false;
                
        Connection con = connectMySql();
        String sql = "update  decision set decision_no = ? ,decision_date = ?, creator_id = ? where decision_id = ?;";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(3, decision.getCreator_id());
            ps.setString(1, decision.getDecision_no());
            ps.setString(2,decision.getDecision_date());
            ps.setInt(4, decision.getDecision_id());
            ps.executeUpdate();
            check = true;
        } catch (Exception e) {

        }
        return check;
    }

    public boolean checkPloExist(String plo_name, int curId) {
        Connection con = connectMySql();
        String sql = "select count(1) from plo where plo.name = ? and curriculum_id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, plo_name);
            ps.setInt(2, curId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (Exception e) {

        }
        return false;
    }

}
