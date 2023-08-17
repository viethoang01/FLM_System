/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_access;

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
public class PloDAO {

    public List<Plo> getAllPlo(String sort, String txt, int offset, int curriculum_id) {
        Connection con = connectMySql();
        List<Plo> list = new ArrayList<>();
        String sql = "select * from plo where plo.curriculum_id = ? and plo.name like ?";
        try {
            if (txt.equalsIgnoreCase("")) {
                sql = "select * from plo where plo.curriculum_id = ?";
            }

            switch (sort) {
                case "plo_id":
                    sql += " order by plo.curriculum_id ";
                    break;
                case "name":
                    sql += " order by plo.name ";
                    break;
                case "description":
                    sql += " order by plo.description ";
                    break;

                default:
                    sql += " order by plo.curriculum_id ";
                    break;
            }
            sql += " limit 3 offset ?;";

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            if (!txt.equals("")) {
                ps.setInt(1, curriculum_id);
                ps.setString(2, '%' + txt + '%');
                ps.setInt(3, offset);
            } else {
                ps.setInt(1, curriculum_id);
                ps.setInt(2, offset);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plo p = new Plo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new PloDAO().getAllPlo("", "o", 1, 1));
    }

    public int getTotalItem(String txt, int curri_id) {
        Connection con = connectMySql();
        List<Plo> list = new ArrayList<>();
        String sql = "select count(*) from plo where plo.curriculum_id = ? and plo.name like ?";
        try {

            if (txt.equalsIgnoreCase("")) {
                sql = "select count(*) from plo where plo.curriculum_id = ?";
            }
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            if (!txt.equalsIgnoreCase("")) {
                ps.setString(2, '%' + txt + '%');
            }
            ps.setInt(1, curri_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public Plo getPlo(int id) {
        Connection con = connectMySql();

        String sql = "select * from plo where plo.plo_id = ?";
        try {

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Plo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
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

    public void addPlo(Plo plo) {
        Connection con = connectMySql();
        String sql = "insert into plo values (0,?,?,?);";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, plo.getName());
            ps.setString(2, plo.getDescription());
            ps.setInt(3, plo.getCurriculum_id());
            ps.executeUpdate();
        } catch (Exception e) {

        }
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
