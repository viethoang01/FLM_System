/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author SHD
 */
public class User {
    private int user_id;
    private String full_name;
    private String user_name;
    private String email;
    private String mobile;
    private String password;
    private String role;
    private String job;
    private String title,company;
    private int status;
    
    private String avatar;
    
    public User() {
    }
    public User(int user_id, String full_name, String user_name, String email, String mobile, String password) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public User(int user_id, String full_name, String user_name, String email, String mobile, String password, String role) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }

    

    public User(int user_id, String full_name, String user_name, String email, String mobile, String password, String role, String job, String title, String company, String avatar) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
        this.job = job;
        this.title = title;
        this.company = company;
        this.avatar = avatar;
    }
    public User(int user_id, String full_name, String user_name, String email, String mobile, String password, String role, String job, String title, String company, String avatar,int status) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
        this.job = job;
        this.title = title;
        this.company = company;
        this.avatar = avatar;
        this.status = status;
    }
    public User(int user_id, String full_name, String user_name, String email, String mobile, String password, String role, String avatar) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", full_name=" + full_name + ", user_name=" + user_name + ", email=" + email + ", mobile=" + mobile + ", password=" + password + ", role=" + role + ", avatar=" + avatar + '}';
    }

    
    
}
