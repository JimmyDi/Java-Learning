package com.example.sqlManage;

import com.example.models.Student;

import java.sql.SQLException;
import java.util.List;

public class Program {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        sqlManage sqlmanage=new sqlManage();

        // add students
//        sqlmanage.insertStudent(new Student("bigsai", 22, 180));
//        sqlmanage.insertStudent(new Student("bigpian", 21, 165));
//        sqlmanage.insertStudent(new Student("doudou", 20, 160));

        // delete students
//        int studentId = 2;
//        sqlmanage.deleteStudent(studentId);

        // get students
//        List<Student> students = sqlmanage.getAllStudents();
//        for(Student student: students) {
//            System.out.println(student.toString());
//        }

        // add group students using PreparedStatement
//        Student students[]=new Student[5];
//        for(int i=0;i<students.length;i++)
//        {
//            students[i]=new Student("路人"+i, 12, 156);
//        }
//        sqlmanage.insertGroupStudents(students);
    }
}
