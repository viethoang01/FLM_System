/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_access;

import entity.User;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utils.DBContext.connectMySql;

/**
 *
 * @author SHD
 */
public class UserDAO {
public boolean checkDupUsername(String username) {
         boolean check = false;
        User u = new User();
        String sql = "SELECT count(1) FROM swp391_bl5.user where user_name = ?";
ResultSet rs;
        try {
             try (Connection con = DBContext.connectMySql(); PreparedStatement ps = con.prepareStatement(sql)) {
                 ps.setString(1, username);
                  rs = ps.executeQuery();
                 if (rs.next()) {
                     if(rs.getInt(1)>=1){
                      check = true;   
                     }else{
                         check = false;
                     }
                 }
             }
             rs.close();
        } catch (SQLException e) {
        }
        return check;
    }

public boolean checkDupMobile(String mobile) {
         boolean check = false;
        User u = new User();
        String sql = "SELECT count(1) FROM swp391_bl5.user where mobile = ?";
ResultSet rs;
        try {
             try (Connection con = DBContext.connectMySql(); PreparedStatement ps = con.prepareStatement(sql)) {
                 ps.setString(1, mobile);
                  rs = ps.executeQuery();
                 if (rs.next()) {
                     if(rs.getInt(1)>=1){
                      check = true;   
                     }else{
                         check = false;
                     }
                 }
             }
             rs.close();
        } catch (SQLException e) {
        }
        return check;
    }
    public User getAccount(String email, String password) {
        Connection con = connectMySql();
        String sql = "select user.user_id,user.full_name,user.user_name,user.email,user.avatar,user.mobile,user.title,user.company,user.job,user.password,setting.name from user,setting,user_role where user.email like ? and user.password like ?"
                + " and setting.setting_id = user_role.role_id and user.user_id = user_role.user_id;";
        ArrayList<User> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("mobile"),
                        "",
                        rs.getString("name"),
                        rs.getString("job"),
                        rs.getString("title"),
                        rs.getString("company"),
                        rs.getString("avatar")
                );
                return u;

            }
        } catch (SQLException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        System.out.println(new UserDAO().getAccount("huy@gmail.com", "123456").getAvatar());
        User user = new User();
        user.setUser_id(1);
        user.setAvatar("images/2anna_shaw.jpg");
        user.setCompany("fu22");
        user.setEmail("a@gmail.com");
        user.setFull_name("admaa");
        user.setJob("ue");
        user.setMobile("0988903540");
        user.setTitle("ddt");
        user.setUser_name("ad");
        
        System.out.println(userDAO.getPassword(1));
    }

    public User checkLogin(String username, String password) {
        User u = new User();
        String sql = "select * from user where (email = ? or mobile = ?) and password= ? ";

        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
        }
        return u;
    }

    public User getUser(String username) {
        User u = new User();
        String sql = "select u.*, s.title from (user u inner join user_role ur\n"
                + "	on u.user_id = ur.user_id)  inner join setting s\n"
                + "    on s.setting_id = ur.role_id\n"
                + "    where u.user_name = ? ";

        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
        }
        return u;
    }

    public User getUserNoRole(String username) {
        User u = new User();
        String sql = "select * from user where user_name= ? ";

        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
        }
        return u;
    }

    public void resetPass(String pass, String username) {
        String sql = "update user set password = ? where email = ?";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, username);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
    }

    public void registerU(String email, String full_name, String pass) {
        String sql = "insert into user (full_name, user_name, email, password)\n"
                + "values(?,?,?,?);";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, full_name);
            ps.setString(2, email);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertRole(int user_id) {
        String sql = "insert into user_role (role_id, user_id, is_active)\n"
                + "values(1,?,1)";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public User getAccount2(String email, String password) {
        Connection con = connectMySql();
        String sql = "select * from user where user.email =? and user.password = ?"
                + " ";
        User u = null;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), "", rs.getString(7));

            }
        } catch (SQLException e) {
        }
        return u;
    }

    public User getUser(int user_id) {
        Connection con = connectMySql();
        String sql = "select * from user where user_id= ?"
                + " ";
        User u = null;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("mobile"),
                        "",
                        "",
                        rs.getString("job"),
                        rs.getString("title"),
                        rs.getString("company"),
                        rs.getString("avatar")
                );

                return u;
            }
        } catch (SQLException e) {
        }
        return u;
    }

    public String getPassword(int user_id) {
        String pass = "";
        Connection con = connectMySql();
        String sql = "select * from user where user_id= ?"
                + " ";
        User u = null;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pass = rs.getString("password");

            }
        } catch (SQLException e) {
        }
        return pass;
    }

    public boolean updateUser(User user) {
        boolean check = false;
        Connection con = connectMySql();
        String sql = "update  user "
                + "set full_name = ?,"
                + " user_name = ?,"
                + " `email`= ?,"
                + " `mobile`= ?,"
                + " `job`= ?,"
                + " `title`= ?,"
                + " `company`= ?,"
                + " `avatar`= ? "
                + " where user_id= ? "
                + " ";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, user.getFull_name());
            ps.setString(2, user.getUser_name());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getMobile());
            ps.setString(5, user.getJob());
            ps.setString(6, user.getTitle());
            ps.setString(7, user.getCompany());
            ps.setString(8, user.getAvatar());

            ps.setInt(9, user.getUser_id());

            ps.executeUpdate();
            check = true;
        } catch (SQLException e) {
        }
        return check;
    }
public boolean updateUserNoAvatar(User user) {
        boolean check = false;
        Connection con = connectMySql();
        String sql = "update  user "
                + "set full_name = ?,"
                + " user_name = ?,"
                + " `email`= ?,"
                + " `mobile`= ?,"
                + " `job`= ?,"
                + " `title`= ?,"
                + " `company`= ? "
                + " where user_id= ? "
                + " ";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, user.getFull_name());
            ps.setString(2, user.getUser_name());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getMobile());
            ps.setString(5, user.getJob());
            ps.setString(6, user.getTitle());
            ps.setString(7, user.getCompany());

            ps.setInt(8, user.getUser_id());

            ps.executeUpdate();
            check = true;
        } catch (SQLException e) {
        }
        return check;
    }
    public boolean addAccount(User user) {
        boolean check = false;
        Connection con = connectMySql();
        String sql = "INSERT INTO `swp391_bl5`.`user` (`full_name`, `user_name`, `email`, `mobile`, `password`, `job`, `title`, `company`, `status`)"
                + "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, '1');";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, user.getFull_name());
            ps.setString(2, user.getUser_name());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getMobile());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getJob());
            ps.setString(7, user.getTitle());
            ps.setString(8, user.getCompany());

            ps.executeUpdate();
            check = true;
        } catch (SQLException e) {
        }
        return check;
    }

    public boolean changePasswod(String user_id, String password) {
        boolean check = false;
        Connection con = connectMySql();
        String sql = "update  user "
                + "set password = ? "
                + " where user_id= ? "
                + " ";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, user_id);

            ps.executeUpdate();
            check = true;
        } catch (SQLException e) {
        }
        return check;
    }

    public boolean loadImg(InputStream inputStream, int user_id) {
        boolean check = false;
        String sql = "UPDATE user SET avatar = ? WHERE user_id = ?";
        try {
            Connection con = connectMySql();
            PreparedStatement statement = con.prepareStatement(sql);

            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(1, inputStream);
                statement.setInt(2, user_id);
            }

            statement.executeUpdate();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public byte[] Retrieve(int id) {
        String sql = "SELECT avatar FROM user WHERE user_id = ?";
        byte[] byteArray = null;
        try {
            Connection con = connectMySql();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Blob blob = rs.getBlob("avatar");
                byteArray = blob.getBytes(1, (int) blob.length());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return byteArray;
    }

    public ArrayList<User> getListUser() {
        ArrayList<User> list = new ArrayList<>();
        Connection con = connectMySql();
        String sql = "select * from user ";
        User u = null;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("mobile"),
                        rs.getString("password"),
                        "",
                        rs.getString("job"),
                        rs.getString("title"),
                        rs.getString("company"),
                        rs.getString("avatar"), rs.getInt("status")
                );
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean changeStatus(String user_id, String status) {
        boolean check = false;
        Connection con = connectMySql();
        String sql = "update  user "
                + "set status = ?"
                + " where user_id= ? "
                + " ";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, user_id);

            ps.executeUpdate();
            check = true;
        } catch (SQLException e) {
        }
        return check;
    }

    public boolean deleteUser(String user_id) {
        boolean check = false;
        Connection con = connectMySql();
        String sql = " DELETE FROM user_role WHERE user_id = ? ";
        String sql2 = " DELETE FROM user WHERE user_id = ? ";

        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(user_id));
            ps.executeUpdate();
            PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(sql2);
            ps2.setInt(1, Integer.valueOf(user_id));
            ps2.executeUpdate();
            check = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean changeAvatar(String user_id, String relativePath) {
        boolean check = false;
        Connection con = connectMySql();
        String sql = "update  user "
                + "set avatar = ?"
                + " where user_id= ? "
                + " ";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, relativePath);
            ps.setString(2, user_id);

            ps.executeUpdate();
            check = true;
        } catch (SQLException e) {
        }
        return check;
    }
}
