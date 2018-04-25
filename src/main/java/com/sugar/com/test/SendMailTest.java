package com.sugar.com.test;

import com.sugar.com.entity.Student;
import com.sugar.com.util.SendStudentMailUtil;

import java.util.ArrayList;
import java.util.List;

public class SendMailTest {

    public static void main(String[] args) {
        SendStudentMailUtil util = new SendStudentMailUtil();
        util.sendResultMail(mockStudents());
    }

    public static List<Student> mockStudents(){

        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.setName("小明");
        student.setAge(79);
        student.setSex(1);
        studentList.add(student);
        return studentList;
    }
}
