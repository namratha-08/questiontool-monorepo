package com.ntt.questiontool.util;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Connection successful!");
            } else {
                System.out.println("❌ Connection failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
