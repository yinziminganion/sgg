package com.yinziming.part01.ioc.xml;

import org.springframework.beans.factory.FactoryBean;

public class MyBean implements FactoryBean<Student> {
    private Student student = new Student();

    @Override
    public Student getObject() throws Exception {
        return student;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
