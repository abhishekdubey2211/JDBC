/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.JDBC.assignments;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Abhishek
 */
public class ToInsertData {

    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/acs_tenant_2";
        final String user = "root";
        final String pass = "ABHI";

        String driver = "";
        Connection conn = null;

        CallableStatement cstmt = null;
        CallableStatement cstmt0 = null;
        CallableStatement cstmt1 = null;
        ResultSet rs = null;
        String query = null;
        String query0 = null;
        String query1 = null;

        int book_id = 0;
        String tg_bookname = null;
        int category_id = 0;
        String book_name = null;
        String book_author = null;
        Float book_price = 0f;
        String book_description = null;
        int active = 0;
        int isdelete = 0;

        try {
            conn = DriverManager.getConnection(url, user, pass);
            
            System.out.println("**********Inserting the data***************");
            query0 = "INSERT INTO Book_manager(tg_bookname ,  category_id ,  book_name , book_author ,    book_price ,  book_description ,  active , isdelete  ) Values(?,?,?,?,?,?,?,0);";
            cstmt0 = conn.prepareCall(query0);
            cstmt0.setString(1, "Adventure");
            cstmt0.setInt(2, 101);
            cstmt0.setString(3, "JAVA WORLD");
            cstmt0.setString(4, "Pro. Sachin Yadav");
            cstmt0.setFloat(5, 1564.25f);
            cstmt0.setString(6, "This is amazing java book wrote to impresses DD");
            cstmt0.setInt(7, 1);
            cstmt0.executeUpdate();
            System.out.println("Data Insertion Done Sucessfull>>>>>>>>>" + cstmt0.toString());

            System.out.println("**********Retriving the last Inserted id***************");
            int lastInsertedId = 0;
            query = "Select last_insert_id() as LastInsertedBook_id";
            cstmt = conn.prepareCall(query);
            rs = cstmt.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    lastInsertedId = rs.getInt("LastInsertedBook_id");
                    System.out.println("Last Inserted Boook Id :: " + lastInsertedId);
                }
            }
            
            System.out.println("**********Retriving the Inserted Data From DataBase***************");
            query1 = "SELECT book_id , tg_bookname ,  category_id ,  book_name , book_author , book_price , book_description , active , isdelete FROM Book_manager WHERE book_id = ? and isdelete = 0";
            cstmt1 = conn.prepareCall(query1);
            cstmt1.setInt(1, lastInsertedId);
            rs = cstmt1.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    book_id = rs.getInt("book_id");
                    tg_bookname = rs.getString("tg_bookname");
                    category_id = rs.getInt("category_id");
                    book_name = rs.getString("book_name");
                    book_author = rs.getString("book_author");
                    book_price = rs.getFloat("book_price");
                    book_description = rs.getString("book_description");
                    active = rs.getInt("active");
                    isdelete = rs.getInt("isdelete");
                }
                System.out.println("**********Inserted Data Retrived Sucessfully Sucessfully*************"
                        + "\nbook_id: " + book_id
                        + "\ntg_bookname: " + tg_bookname
                        + "\ncategory_id: " + category_id
                        + "\nbook_name: " + book_name
                        + "\nbook_author: " + book_author
                        + "\nbook_price: " + book_price
                        + "\nbook_description: " + book_description
                        + "\nactive: " + active
                        + "\nisdelete: " + isdelete);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // This will print the exception stack trace
        } finally {
            cstmt = null;
            conn = null;
            query = null;
        }

    }
}
