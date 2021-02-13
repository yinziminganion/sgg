package com.yinziming.part03.jdbctemplate.bean;

import org.springframework.stereotype.Component;

@Component
public class Book {
    private String id;
    private String username;
    private String status;

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
