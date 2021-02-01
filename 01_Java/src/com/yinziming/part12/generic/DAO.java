package com.yinziming.part12.generic;

import org.junit.jupiter.api.Test;

abstract class DAO<T> {//ORM

    //添加一条记录
    public void add(T t) {
    }

    //删除一条记录
    public boolean remove(T t) {
        return false;
    }

    //修改一条记录
    public void update(int index, T t) {
    }

    //查询一条记录
    public T getIndex(int index) {
        return null;
    }

    //查询多条记录
    public T[] getList(Integer[] indexes) {
        return null;
    }
}

class Customer {
}

class CustomerDAO extends DAO<Customer> {
}

class DAOTest {
    @Test
    public void test() {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.add(new Customer());
        customerDAO.getList(new Integer[]{1, 2, 3});
    }
}

class Student {
}

class StudentDAO extends DAO<Student> {

}
