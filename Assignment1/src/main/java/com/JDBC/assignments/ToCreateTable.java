/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.JDBC.assignments;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ToCreateTable {

    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/acs_tenant_2";
        final String user = "root";
        final String pass = "ABHI";

        String driver = "";
        Connection conn = null;

        CallableStatement cstmt = null;
        CallableStatement cstmt0 = null;
        CallableStatement cstmt1 = null;
        String query = null;
        String query0 = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("************Connection Done Sucessfully******************");
            
            query0 = "SHOW TABLES LIKE 'Book_manager'";
            cstmt0 = conn.prepareCall(query0);
            if (cstmt0.executeQuery().next()) {
                System.out.println("***********'Book_manager' Table Exists---> Droping the Existing Table*****************");
                String dropQuery = "DROP TABLE Book_manager";
                cstmt1 = conn.prepareCall(dropQuery);
                cstmt1.executeUpdate();
                System.out.println("***********'Book_manager' Table Droped Sucessfully*****************");
            }

            System.out.println("***********Creating New Table 'Book_manager'*****************");
            query = "CREATE TABLE Book_manager ( book_id INT PRIMARY KEY AUTO_INCREMENT, tg_bookname VARCHAR(100),  category_id INT,  book_name VARCHAR(100), book_author VARCHAR(100),    book_price INT,  book_description VARCHAR(225),  isdelete BIT(1), active TINYINT(1) );";
            cstmt = conn.prepareCall(query);
            System.out.println("*********Query Executed Sucessfully*********>>>>>>>" +cstmt.toString());
            cstmt.executeUpdate();
            System.out.println("*********'Book_manager' Table Creation Done Sucessfully**************");
            cstmt.close();
        } catch (SQLException e) {
                e.printStackTrace(); // This will print the exception stack trace
        } finally {
            cstmt = null;
            conn = null;
            query = null;
        }

    }
}
