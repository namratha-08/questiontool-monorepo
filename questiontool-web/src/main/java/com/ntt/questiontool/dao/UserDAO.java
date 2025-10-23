package com.ntt.questiontool.dao;

import com.ntt.questiontool.model.User;
import com.ntt.questiontool.util.DBConnection;
import com.ntt.questiontool.util.PasswordUtil;
import java.sql.*;

public class UserDAO {
    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, username);
            ps.setString(2, PasswordUtil.hashPassword(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
