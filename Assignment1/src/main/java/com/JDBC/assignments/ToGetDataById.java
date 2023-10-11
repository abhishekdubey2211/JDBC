/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.JDBC.assignments;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author Abhishek
 */
public class ToGetDataById {

    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/acs_tenant_2";
        final String user = "root";
        final String pass = "ABHI";

        String driver = "";
        Connection conn = null;

        CallableStatement cstmt = null;
        String query = null;
        ResultSet rs = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the book_id you want to Fetch: ");
        int fetchbook_id = sc.nextInt();

        try {
            conn = DriverManager.getConnection(url, user, pass);
            query = "SELECT book_id FROM Book_manager WHERE book_id = ? AND isdelete =0";
            cstmt = conn.prepareCall(query);
            cstmt.setInt(1, fetchbook_id);
            rs = cstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("********************No such book_id found********************");
                return;
            } else {
                System.out.println("Book with book_id " + fetchbook_id + " found.");
            }
            System.out.println("********************Data Fetched Successfully********************");

            System.out.println("********************Retrieving the Fetched Data From Database********************");
            String Query = "SELECT book_id, tg_bookname, category_id, book_name, book_author, book_price, book_description, active, isdelete FROM Book_manager WHERE book_id = ? AND isdelete = 0";
            CallableStatement cstmt1 = conn.prepareCall(Query);
            cstmt1.setInt(1, fetchbook_id);
            rs = cstmt1.executeQuery();
            System.out.println("********************Query :: >>> "+cstmt1.toString()+"********************");

            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                String tgBookname = rs.getString("tg_bookname");
                int categoryId = rs.getInt("category_id");
                String bookName = rs.getString("book_name");
                String bookAuthor = rs.getString("book_author");
                float bookPrice = rs.getFloat("book_price");
                String bookDescription = rs.getString("book_description");
                int active = rs.getInt("active");
                int isDelete = rs.getInt("isdelete");

                System.out.println("********************Fetched Data Retrieved Successfully:********************");
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cstmt = null;
            conn = null;
            query = null;
        }

    }
}
