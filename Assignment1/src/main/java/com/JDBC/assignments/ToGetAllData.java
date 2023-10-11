/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.JDBC.assignments;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Abhishek
 */
public class ToGetAllData {
    
    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/acs_tenant_2";
        final String user = "root";
        final String pass = "ABHI";

        String driver = "";
        Connection conn = null;

        CallableStatement cstmt = null;
        String query = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            query = "SELECT book_id , tg_bookname ,  category_id ,  book_name , book_author , book_price , book_description , active , isdelete FROM Book_manager;";
            cstmt = conn.prepareCall(query);
            rs = cstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                    System.out.println("********************NO DATA FOUND IN DATABASE..........********************");
            }else{
                 while (rs.next()) {
                    int bookId = rs.getInt("book_id");
                    String tgBookname = rs.getString("tg_bookname");
                    int categoryId = rs.getInt("category_id");
                    String bookName = rs.getString("book_name");
                    String bookAuthor = rs.getString("book_author");
                    float bookPrice = rs.getFloat("book_price");
                    String bookDescription = rs.getString("book_description");
                    int active = rs.getInt("active");
                    int isDelete = rs.getInt("isdelete");

                    System.out.println("********************Fetched Data Retrieved Successfully: with Book id :"+bookId+" ********************");
                    System.out.println("book_id: " + bookId);
                    System.out.println("tg_bookname: " + tgBookname);
                    System.out.println("category_id: " + categoryId);
                    System.out.println("book_name: " + bookName);
                    System.out.println("book_author: " + bookAuthor);
                    System.out.println("book_price: " + bookPrice);
                    System.out.println("book_description: " + bookDescription);
                    System.out.println("active: " + active);
                    System.out.println("isdelete: " + isDelete);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // This will print the exception stack trace
        } finally {
            cstmt = null;
            conn = null;
            query = null;
        }

    }
}
