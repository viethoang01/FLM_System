/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_access;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.org.apache.regexp.internal.RE;

import entity.Curiculum;
import entity.Plo;
import entity.PloSubject;
import entity.Po;
import entity.Subject;
import entity.SubjectGroup;
import entity.User;
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
 * @author dell
 */
public class SubjectDAO {

    public boolean checkDup(Subject subject) {
        List<Subject> subjects = getAllSubject();
        for (Subject subject1 : subjects) {
            if (subject1.getCode().equalsIgnoreCase(subject.getCode())) {
                return false;
            }
        }
        return true;
    }

    public boolean insertSubject(Subject subject) {

        String sql = "insert into subject (code, name, semester,no_credit,is_active)\n"
                + "values(?,?,?,?,?)";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subject.getCode());
            ps.setString(2, subject.getName());
            ps.setInt(3, subject.getSemester());
            ps.setInt(4, subject.getNo_credit());
            ps.setBoolean(5, true);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        }
        return false;

    }

    public List<Subject> getAllSubjectByCID(String curriculum_id) {
        Connection con = connectMySql();
        String sql = "select tb2.*,sg.group_type from (select tb1.*, c.name as curriculum_name from (\n"
                + "                    select s.*, cs.curriculum_id,cs.subject_group_id from `subject` s inner join curriculum_subject cs\n"
                + "                    on cs.subject_id = s.subject_id) as tb1 inner join curriculum c\n"
                + "                    on c.curriculum_id = tb1.curriculum_id) as tb2 inner join subject_group sg\n"
                + "                    on sg.subject_group_id = tb2.subject_group_id where tb2.curriculum_id = 1"
                + " ";
        List<Subject> subjectList = new ArrayList<>();

        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, curriculum_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSemester(rs.getInt("semester"));
                subject.setNo_credit(rs.getInt("no_credit"));
                subject.setName(rs.getString("name"));
                subject.setCode(rs.getString("code"));

                subjectList.add(subject);
            }
        } catch (SQLException e) {
        }
        return subjectList;
    }

    public List<Subject> getAllSubject() {
        Connection con = connectMySql();
        String sql = "select * from subject";
        List<Subject> subjectList = new ArrayList<>();

        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSemester(rs.getInt("semester"));
                subject.setNo_credit(rs.getInt("no_credit"));
                subject.setName(rs.getString("name"));
                subject.setCode(rs.getString("code"));
                subject.setIs_active(rs.getBoolean("is_active"));

                subjectList.add(subject);
            }
        } catch (SQLException e) {
        }
        return subjectList;
    }

    public List<Subject> getAllSubjectByIndex(int index, int size) {
        Connection con = connectMySql();
        String sql = "select rs1.* from(select row_number() over(order by subject_id asc) as rownum,s.* from `subject` s ) as rs1 \n"
                + "where rownum >= ((?-1) * ?) + 1 AND rownum <=  ? * ?";
        List<Subject> subjectList = new ArrayList<>();

        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, index);
            ps.setInt(2, size);
            ps.setInt(3, index);
            ps.setInt(4, size);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSemester(rs.getInt("semester"));
                subject.setNo_credit(rs.getInt("no_credit"));
                subject.setName(rs.getString("name"));
                subject.setCode(rs.getString("code"));
                subject.setIs_active(rs.getBoolean("is_active"));

                subjectList.add(subject);
            }
        } catch (SQLException e) {
        }
        return subjectList;
    }

    public static void main(String[] args) {
        SubjectDAO subjectDAO = new SubjectDAO();
        System.out.println(subjectDAO.getAllSubject("sort", "", 1, 1)
);
    }

    public void addCurriculumSubject(String cid, String sid) {
        String sql = "INSERT INTO `swp391_bl5`.`curriculum_subject` (`curriculum_id`, `subject_id`) VALUES (?, ?);";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cid);
            ps.setString(2, sid);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
    }

    public void addPlo(String name, String desc, String cid) {
        String sql = "INSERT INTO `swp391_bl5`.`plo` (`name`, `description`, `curriculum_id`)  \n"
                + "VALUES (?, ?, ?);";
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++=====================================");
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setString(3, cid);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCurriculumSubject(String cid, String sid) {
        String sql = "delete from curriculum_subject where curriculum_id = ? and subject_id=?";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cid);
            ps.setString(2, sid);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
    }

    public void addPloSubject(String cid, String sid, String pid) {
        String sql = "insert into plo_subject (plo_id,curriculum_id,subject_id) values (?,?,?)";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pid);
            ps.setString(2, cid);
            ps.setString(3, sid);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
    }

    public void deletePloSubject(String cid, String sid, String pid) {
        String sql = " delete from plo_subject where plo_id = " + pid + " and curriculum_id= " + cid + " and subject_id =  " + sid;
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Subject> getListSubjectNotMappedByCid(String cid) {
        ArrayList<Subject> list = new ArrayList<>();
        Connection con = connectMySql();
        String sql = "select * from subject where subject_id not in (select subject_id from curriculum_subject where curriculum_id=?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, cid);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject u = new Subject(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6) == 1);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Subject> getListSubjectMappedByCid(String cid) {
        ArrayList<Subject> list = new ArrayList<>();
        Connection con = connectMySql();
        String sql = "select * from subject where subject_id in (select subject_id from curriculum_subject where curriculum_id=?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject u = new Subject(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6) == 1);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<PloSubject> getPloSubjectByCuriculumn(int curId) {
        Connection con = connectMySql();
        List<PloSubject> ploSubjects = new ArrayList<>();
        String sql = "select * from plo_subject where curriculum_id = ?;";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, curId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ploSubjects.add(new PloSubject(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
        }
        return ploSubjects;
    }

    public List<Subject> getAllSubjectForMapping(int curriculum_id) {
        Connection con = connectMySql();
        String sql = "select subject.*,subject_group.name as group_name from subject,curriculum_subject,subject_group where subject.subject_id = curriculum_subject.subject_id and curriculum_subject.subject_group_id = subject_group.subject_group_id and curriculum_subject.curriculum_id = ?;";
        List<Subject> subjectList = new ArrayList<>();

        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, curriculum_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSemester(rs.getInt("semester"));
                subject.setNo_credit(rs.getInt("no_credit"));
                subject.setName(rs.getString("name"));
                subject.setCode(rs.getString("code"));
                subject.setIs_active(rs.getBoolean("is_active"));
                subject.setSubject_group_name(rs.getString("group_name"));
                subjectList.add(subject);
            }
        } catch (SQLException e) {
        }
        return subjectList;
    }

    public Subject getSubjectById(String sid) {
        Connection con = connectMySql();
        String sql = "select * from subject where subject_id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject u = new Subject(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6) == 1);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Curiculum getCurriculumById(String cid) {
        Connection con = connectMySql();
        String sql = "SELECT * FROM swp391_bl5.curriculum where curriculum_id = " + cid;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Curiculum(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8));

            }
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Plo> getListPloNotMappedByCid(String cid, String sid) {
        ArrayList<Plo> list = new ArrayList<>();
        Connection con = connectMySql();
        String sql = "select * from plo where plo_id not in (select plo_id from plo_subject where  subject_id= ? ) and curriculum_id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, sid);
            ps.setString(2, cid);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plo u = new Plo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Plo> getListPloMappedByCid(String cid, String sid) {
        ArrayList<Plo> list = new ArrayList<>();
        Connection con = connectMySql();
        String sql = "select * from plo where plo_id in (select plo_id from plo_subject where  subject_id= ? ) and curriculum_id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, sid);
            ps.setString(2, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plo u = new Plo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteMapping(int curId) {
        Connection con = connectMySql();
        String sql = "delete from plo_subject where plo_subject.curriculum_id = ?;";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, curId);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void addMapping(int subject_id, int plo_id, int curId) {
        Connection con = connectMySql();
        String sql = "insert into plo_subject values (?,?,?);";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(2, curId);
            ps.setInt(1, plo_id);

            ps.setInt(3, subject_id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public List<Subject> getAllSubject(String sort, String txt, int offset, int curriculum_id) {
        Connection con = connectMySql();
        List<Subject> list = new ArrayList<>();
        String sql = "select tb1.*, c.name as curriculum_name from (\n" +
" select s.*, cs.curriculum_id,cs.subject_group_id from `subject` s inner join curriculum_subject cs\n" +
"       on cs.subject_id = s.subject_id) as tb1 inner join curriculum c\n" +
"                         on c.curriculum_id = tb1.curriculum_id where tb1.curriculum_id = ? and tb1.code like ?";
        try {
            if (txt.equalsIgnoreCase("")) {
                sql = "select tb1.*, c.name as curriculum_name from (\n" +
" select s.*, cs.curriculum_id,cs.subject_group_id from `subject` s inner join curriculum_subject cs\n" +
"       on cs.subject_id = s.subject_id) as tb1 inner join curriculum c\n" +
"                         on c.curriculum_id = tb1.curriculum_id where tb1.curriculum_id = ?";
            }

            switch (sort) {
                case "subject_id":
                    sql += " order by tb1.subject_id ";
                    break;
                case "name":
                    sql += " order by tb1.name ";
                    break;
                case "code":
                    sql += " order by tb1.code ";
                    break;

                default:
                    sql += " order by tb1.semester ";
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
                Subject subject = new Subject();
                subject.setSubject_id(rs.getInt("subject_id"));
                subject.setSemester(rs.getInt("semester"));
                subject.setNo_credit(rs.getInt("no_credit"));
                subject.setName(rs.getString("name"));
                subject.setCode(rs.getString("code"));
                subject.setIs_active(rs.getBoolean("is_active"));
                list.add(subject);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public boolean checkSubjectExist(String subject_code, int curId) {
        Connection con = connectMySql();
        String sql = "                        select count(1) from (\n" +
" select s.*, cs.curriculum_id,cs.subject_group_id from `subject` s inner join curriculum_subject cs\n" +
"       on cs.subject_id = s.subject_id) as tb1 inner join curriculum c\n" +
"                         on c.curriculum_id = tb1.curriculum_id where tb1.curriculum_id = ? and tb1.code = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(2, subject_code);
            ps.setInt(1, curId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (Exception e) {

        }
        return false;
    }

    public int getTotalItem(String txt, int curri_id) {
        Connection con = connectMySql();
        String sql = "select count(1) from (\n" +
" select s.*, cs.curriculum_id,cs.subject_group_id from `subject` s inner join curriculum_subject cs\n" +
"       on cs.subject_id = s.subject_id) as tb1 inner join curriculum c\n" +
"                         on c.curriculum_id = tb1.curriculum_id where tb1.curriculum_id = ? and tb1.code like ?";
        try {

            if (txt.equalsIgnoreCase("")) {
                sql = "select count(1) from (\n" +
" select s.*, cs.curriculum_id,cs.subject_group_id from `subject` s inner join curriculum_subject cs\n" +
"       on cs.subject_id = s.subject_id) as tb1 inner join curriculum c\n" +
"                         on c.curriculum_id = tb1.curriculum_id where tb1.curriculum_id = ? ";
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

    public ArrayList<SubjectGroup> getListSubjectGroupByCurri_ID(int cid) {
        ArrayList<SubjectGroup> list = new ArrayList<>();
        Connection con = connectMySql();
        String sql = "SELECT * FROM swp391_bl5.subject_group where curriculum_id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SubjectGroup u = new SubjectGroup();
                u.setSubject_group_id(rs.getInt("subject_group_id"));
                u.setGroup_type(rs.getString("group_type"));
                u.setName(rs.getString("name"));
                u.setCurriculum_id(rs.getInt("curriculum_id"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertSubjectCurriculum(int curr_sub_id, int curr_id, int subject_id) {

        String sql = "insert into curriculum_subject \n"
                + "values(?,?,?)";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, curr_id);
            ps.setInt(2, subject_id);
            ps.setInt(3, curr_sub_id);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        }
        return false;

    }

    public int getMaxSub_ID() {
        Connection con = connectMySql();
        String sql = "SELECT max(subject_id) FROM swp391_bl5.subject;";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean addCurriculumSubject(String cid, String sid, String sub_group_id) {
        System.out.println(cid+"|"+sid+"|"+sub_group_id);
        boolean check = false;
        String sql = "INSERT INTO `swp391_bl5`.`curriculum_subject` (`curriculum_id`, `subject_id`,`subject_group_id`) VALUES (?, ?, ?);";
        try {
            Connection con = new DBContext().connectMySql();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(2, sid);
            ps.setString(1, cid);
            
            
            ps.setString(3, sub_group_id);
            
            ps.executeUpdate();
            check = true;
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            check = false;
        }
        return  check;
    }
}
