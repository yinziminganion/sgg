package com.yinziming.part04.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addMoney() {
        String s = "update account set money=money+100 where id=1";
        int update = jdbcTemplate.update(s);
        System.out.println("update = " + update);
    }

    @Override
    public void reduceMoney() {
        String s = "update account set money=money-100 where id=2";
        int update = jdbcTemplate.update(s);
        System.out.println("update = " + update);
    }
}
