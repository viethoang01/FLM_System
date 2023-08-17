/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_access;

import entity.Curiculum;
import entity.Decision;
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
public class CurriculumDAO {
    public List<Curiculum> getAllCuriculum(String sort,String filter,String txt,int offset){
        Connection con = connectMySql();
        List<Curiculum> list = new ArrayList<>();
        String sql = "select curriculum.curriculum_id,curriculum.code,curriculum.name,curriculum.description,curriculum.total_credit,decision.decision_no,decision.decision_date from curriculum,decision where curriculum.decision_id = decision.decision_id and";
        try {
            if(filter.equalsIgnoreCase("code"))
                sql += " curriculum.code like ? ";
            else
                sql += " curriculum.name like ? ";
            if(txt.equalsIgnoreCase(""))
                sql = "select curriculum.curriculum_id,curriculum.code,curriculum.name,curriculum.description,curriculum.total_credit,decision.decision_no,decision.decision_date from curriculum,decision where curriculum.decision_id = decision.decision_id ";
            
            
            switch (sort) {
                case "curriculum_id":
                    sql += " order by curriculum.curriculum_id ";
                    break;
                case "code":
                    sql += " order by curriculum.code ";
                    break;
                case "name":
                    sql += " order by curriculum.name ";
                    break;
                case "description":
                    sql += " order by curriculum.description ";
                    break;
                case "total_credit":
                    sql += " order by curriculum.total_credit ";
                    break;
                case "decision_id":
                    sql += " order by curriculum.decision_id ";
                    break;
                default:
                    sql += " order by curriculum.curriculum_id ";
                    break;
            }
            sql += " limit 3 offset ?;";
            
            
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            
            
            if(!txt.equals("")){
                ps.setString(1, '%'+txt+'%');
                ps.setInt(2,offset);
            }else{
                ps.setInt(1,offset);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Curiculum c = new Curiculum(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                list.add(c);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public int getTotalItem(String filter, String txt) {
        Connection con = connectMySql();
        List<Curiculum> list = new ArrayList<>();
        String sql = "select count(*) from curriculum,decision where curriculum.decision_id = decision.decision_id and ";
        try {
            if(filter.equalsIgnoreCase("code"))
                sql += " curriculum.code like ?;";
            else
                sql += " curriculum.name like ?;";
            if(txt.equalsIgnoreCase(""))
                sql = "select count(*) from curriculum,decision where curriculum.decision_id = decision.decision_id";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            if(!txt.equalsIgnoreCase(""))
                ps.setString(1, '%'+txt+'%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }
    

    public Curiculum getByID(int id) {
        Connection con = connectMySql();
        List<Curiculum> list = new ArrayList<>();
        String sql = "select curriculum.curriculum_id,curriculum.code,curriculum.name,curriculum.description,curriculum.total_credit,decision.decision_no,decision.decision_date from curriculum,decision where curriculum.decision_id = decision.decision_id and curriculum.curriculum_id = ? ";
        try {
            
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Curiculum(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public Curiculum getByIDToEdit(int id) {
        Connection con = connectMySql();
        List<Curiculum> list = new ArrayList<>();
        String sql = "select curriculum.curriculum_id,curriculum.code,curriculum.name,curriculum.description,curriculum.total_credit,curriculum.is_active from curriculum where curriculum.curriculum_id = ?";
        try {
            
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Curiculum(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void update(Curiculum curiculum) {
        Connection con = connectMySql();
        String sql = "update curriculum set code = ?,name = ?,description = ?,total_credit = ?,is_active = ? where curriculum_id  = ?";
        try{
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);        
            ps.setString(1, curiculum.getCode());
            ps.setString(2, curiculum.getName());
            ps.setString(3, curiculum.getDescription());
            ps.setString(4, curiculum.getTotal_credit());
            ps.setBoolean(5, curiculum.isIs_active());
            ps.setInt(6,curiculum.getCurriculum_id());
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }

    public void add(Curiculum curiculum,int ownerID) {
        Connection con = connectMySql();
        String sql = "insert into curriculum values (0,?,?,?,?,?,?,?);";
        try{
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);        
            ps.setString(1, curiculum.getCode());
            ps.setString(2, curiculum.getName());
            ps.setString(3, curiculum.getDescription());
            ps.setInt(4, curiculum.getDecision_id());
            ps.setString(5, curiculum.getTotal_credit());
            ps.setInt(6, ownerID);
            ps.setBoolean(7, curiculum.isIs_active());         
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
    public static void main(String[] args) {
        for (Curiculum curri : new CurriculumDAO().getAllCuriculum("name", "code", "", 0)) {
            System.out.println(curri.getName());
        }
    }

    public List<Decision> getDecisions() {
        Connection con = connectMySql();
        List<Decision> list = new ArrayList<>();
        String sql = "select decision.* from decision";
        try {
            
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Decision d = new Decision(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                list.add(d);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public List<String> getAllCode() {
        Connection con = connectMySql();
        List<String> list = new ArrayList<>();
        String sql = "select curriculum.code from curriculum";
        try {
            
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
        }
        return list;
    }

}
