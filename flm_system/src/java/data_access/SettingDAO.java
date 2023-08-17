/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_access;

import entity.Setting;
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
public class SettingDAO {
    Connection con = connectMySql();
    public List<Setting> getAllSetting() {
        Connection con = connectMySql();
        String sql = "SELECT * FROM setting;";
        List<Setting> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Setting getSetting(String id) {
        Connection con = connectMySql();
        String sql = "SELECT * FROM setting WHERE setting_id = ?;";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getInt(6));
                return setting;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public void UpdateSetting(String id, String name, String type, String desc, String status, String order) {
        Connection con = connectMySql();
        PreparedStatement pre = null;
        String sql = "UPDATE setting SET type = ?, name = ?, value = ?, display_order = ?, isActive = ? WHERE setting.setting_id = ?;";
        try {
            pre = con.prepareStatement(sql);
            pre.setString(1, type);
            pre.setString(2, name);
            pre.setString(3, desc);
            pre.setString(4, order);
            pre.setString(5, status);
            pre.setString(6, id);
            pre.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void insertSetting( String name, String type, String desc, String status, String order) {
        Connection con = connectMySql();
        PreparedStatement pre = null;
        String sql = "INSERT INTO setting (type, name, value, display_order, isActive) VALUES ( ?, ?, ?, ?, ?);";
        try {
            pre = con.prepareStatement(sql);
            pre.setString(1, type);
            pre.setString(2, name);
            pre.setString(3, desc);
            pre.setString(4, order);
            pre.setString(5, status);
            pre.executeUpdate();
        } catch (Exception e) {
        }
    }
    public List<Setting> sortSetting(int index) {
     int begin = 1;
        int end = 3;
        for (int i = 2; i <= index; i++) {
            begin += 3;
            end += 3;
        }
        String sql = "SELECT *FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY display_order asc) AS RowNum\n" +
"                                             FROM setting\n" +
"                                           ) AS RowNum\n" +
"                                             WHERE RowNum BETWEEN " + begin + " AND " + end;
        List<Setting> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
        }
        return list;
    }
 
    public int getTotal(){
        String sql = "select count(*) from setting";
        try{
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        }catch(Exception e){
            
        }
        return 0;
    }
    
    public List<Setting> pagingSetting(int index){
        List<Setting> list = new ArrayList<>();
         String sql = "select * from setting  limit 3 OFFSET ?;";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, (index-1)*3);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public void editActive(String id){
        String sql = "update setting set isActive = 1 where setting_id  = ?";
        try{
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);        
            ps.setString(1, id);
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
     public void editDisActive(String id){
        String sql = "update setting set isActive = 0 where setting_id  = ?";
        try{
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);        
            ps.setString(1, id);
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
     public int count(String txtSearch){
          String sql = "select count(*) from setting where name like ? ";
        try{
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
                 ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                return rs.getInt(1);
            }
        }catch(Exception e){
            
        }
        return 0;
     }
    public List<Setting> searchSetting(int index,String name){
        List<Setting> list = new ArrayList<>();
         String sql = "select * from setting where name like '%" + name + "%' limit 3 OFFSET ?;";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, (index-1)*3);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public List<String> getAllRole(){
        List<String> list = new ArrayList<>();
         String sql = "select setting.name from setting where setting.type like 'role';";
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
    

    public static void main(String[] args) {
//        System.out.println(new SettingDAO());
        SettingDAO da = new SettingDAO();
        da.UpdateSetting("1","name", "type", "desc","10", "1" );
        da.insertSetting("name", "type", "desc","10", "1" );
        System.out.println(da.getAllRole());
    }
}