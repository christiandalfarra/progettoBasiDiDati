package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/booking","chri","ciao1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
