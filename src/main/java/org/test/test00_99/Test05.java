package org.test.test00_99;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 问题：
 * 代码实现jdbc，并进行基本的操作
 */
public class Test05 {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            Statement statement = conn.createStatement();
            String sql = "select uid from user";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String uid = rs.getString(1);
                System.out.println(uid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
