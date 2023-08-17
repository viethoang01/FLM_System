/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_access;

import entity.Permission;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class PermissionDAO {

    public List<Permission> getListPermissionByPagingAndFilter(String filter, int offset) {
        Connection con = connectMySql();
        String sql = "select permission.*,s1.name as roleName,s2.name as pageName from permission,setting s1,setting s2 where s1.setting_id = permission.role_id and s2.setting_id = permission.page_id and s1.name like ? limit 10 offset ?;";
        if(filter.equals(""))
            sql = "select permission.*,s1.name as roleName,s2.name as pageName from permission,setting s1,setting s2 where s1.setting_id = permission.role_id and s2.setting_id = permission.page_id limit 10 offset ?;";
        List<Permission> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            if(!filter.equals("")){
                ps.setString(1, filter);
                ps.setInt(2, offset);
            }else
                ps.setInt(1, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Permission p = new Permission(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getString(8), rs.getString(9));
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public List<Permission> getAllPermissionByFilter(String filter) {
        Connection con = connectMySql();
        String sql = "select permission.*,s1.name as roleName,s2.name as pageName from permission,setting s1,setting s2 where s1.setting_id = permission.role_id and s2.setting_id = permission.page_id;";
        if(!filter.equals(""))
            sql = "select permission.*,s1.name as roleName,s2.name as pageName from permission,setting s1,setting s2 where s1.setting_id = permission.role_id and s2.setting_id = permission.page_id and s1.name like ?";
        List<Permission> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            if(!filter.equals(""))
                ps.setString(1, filter);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Permission p = new Permission(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getString(8), rs.getString(9));
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void updatePermission(Permission permission) {
        Connection con = connectMySql();
        String sql = "update permission set permission.access_all = ?,permission.can_read = ?,permission.can_add = ?,permission.can_edit = ?,permission.can_delete = ? where permission.role_id = ? and permission.page_id = ?";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setBoolean(1, permission.isAccess_all());
            ps.setBoolean(2, permission.isCan_read());
            ps.setBoolean(3, permission.isCan_add());
            ps.setBoolean(4, permission.isCan_edit());
            ps.setBoolean(5, permission.isCan_delete());
            ps.setInt(6, permission.getRole_id());
            ps.setInt(7, permission.getPage_id());
            ps.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        System.out.println(new PermissionDAO().getAllRolesOfUser(3));

    }

    public List<String> getAllRolesOfUser(int user_id) {
        Connection con = connectMySql();
        String sql = "select setting.name from user,user_role,setting where user.user_id = user_role.user_id and setting.setting_id = user_role.role_id and user.user_id = ?;";
        
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
        }
        return list;
    }

}
