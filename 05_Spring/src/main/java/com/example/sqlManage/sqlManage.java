package com.example.sqlManage;

import com.example.models.Student;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sqlManage {
    private Connection con = null;
//    private DataSource ds = null;

    public sqlManage() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");
//        System.out.println("Database driver is loaded");
//
//        this.con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Student?useSSL=false","hbstudent","hbstudent");
//        System.out.println("Database connected");

        // use hikari connection pool
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/Student");
        config.setUsername("hbstudent");
        config.setPassword("hbstudent");
        config.addDataSourceProperty("connectionTimeout", "1000");
        config.addDataSourceProperty("idleTimeout", "60000");
        config.addDataSourceProperty("maximumPoolSize", "10");
        DataSource ds = new HikariDataSource(config);
        this.con = ds.getConnection();
    }

    public void close() throws SQLException {
        this.con.close();
    }

    public void insertStudent(Student student) throws SQLException {
        String sql = "insert into students(name,age,high)value(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setInt(2,student.getAge());
        ps.setInt(3,student.getHigh());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteStudent(int studentId) throws SQLException {
        String sql = "delete from students where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, studentId);
        ps.executeUpdate();
        ps.close();
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();

        String sql = "select * from students";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();//结果集

        while(resultSet.next())//遍历
        {
            int id=resultSet.getInt(1);
            String name=resultSet.getString(2);
            int age=resultSet.getInt(3);
            int high=resultSet.getInt(4);
            list.add(new Student(id,name,age,high));
        }
        ps.close();
        return list;
    }

    public void insertGroupStudents(Student[] students) throws SQLException {
        this.con.setAutoCommit(false);//不自动提交
        String sql="insert into students(name,age,high)value(?,?,?)";
        PreparedStatement ps =con.prepareStatement(sql);
        for(int i=0;i<students.length;i++)
        {
            ps.setString(1,students[i].getName());
            ps.setInt(2,students[i].getAge());
            ps.setInt(3,students[i].getHigh());
            ps.addBatch();
            if(i%1000==0)
            {
                ps.executeBatch();
            }
        }
        ps.executeBatch();
        con.commit();
        ps.close();
    }
}
