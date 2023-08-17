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
import utils.DBContext;
import static utils.DBContext.connectMySql;

/**
 *
 * @author SHD
 */
public class PoDAO {

    public List<Po> getAllPo(String sort, String txt, int offset, int curriculum_id) {
        Connection con = connectMySql();
        List<Po> list = new ArrayList<>();
        String sql = "select * from po where po.curriculum_id = ? and po.name like ?";
        try {
            if (txt.equalsIgnoreCase("")) {
                sql = "select * from po where po.curriculum_id = ?";
            }

            switch (sort) {
                case "po_id":
                    sql += " order by po.curriculum_id ";
                    break;
                case "name":
                    sql += " order by po.name ";
                    break;
                case "description":
                    sql += " order by po.description ";
                    break;

                default:
                    sql += " order by po.curriculum_id ";
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
                Po p = new Po(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new PoDAO().getAllPo("", "o", 1, 1));
    }

    public int getTotalItem(String txt, int curri_id) {
        Connection con = connectMySql();
        List<Po> list = new ArrayList<>();
        String sql = "select count(*) from po where po.curriculum_id = ? and po.name like ?";
        try {

            if (txt.equalsIgnoreCase("")) {
                sql = "select count(*) from po where po.curriculum_id = ?";
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

    public Po getPo(int id) {
        Connection con = connectMySql();

        String sql = "select * from po where po.po_id = ?";
        try {

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Po(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException e) {
        }
        return null;
    }

//    public List<Po> getPosByCuriculumn(int curId) {
//        Connection con = connectMySql();
//        List<Po> pos = new ArrayList<>();
//        String sql = "select * from po where curriculum_id = ?";
//        try {
//            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
//            ps.setInt(1, curId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                pos.add(new Po(rs.getInt(1), rs.getString(2), rs.getString(3), curId));
//            }
//        } catch (SQLException e) {
//        }
//        return pos;
//    }
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

    public void addPo(Po po) {
        Connection con = connectMySql();
        String sql = "insert into po values (0,?,?,?);";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, po.getName());
            ps.setString(2, po.getDescription());
            ps.setInt(3, po.getCurriculum_id());
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public boolean checkPoExist(String po_name, int curId) {
        Connection con = connectMySql();
        String sql = "select count(1) from po where po.name = ? and curriculum_id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, po_name);
            ps.setInt(2, curId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean updatePo(Po po) {

        String sql = "update po set name = ? ,description = ? where po_id = ?";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, po.getName());
            ps.setString(2, po.getDescription());
            ps.setInt(3, po.getPo_id());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        }
        return false;

    }

    public boolean checkDup(String name) {
        boolean check = false;
        String sql = "select count(*) from po  where name = ?";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) >= 1) {
                    check = true;
                } else {
                    check = false;
                }
            }

            ps.executeUpdate();

        } catch (SQLException e) {
        }
        return check;

    }
}
