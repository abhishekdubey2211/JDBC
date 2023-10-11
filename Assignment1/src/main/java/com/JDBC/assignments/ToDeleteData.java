package com.JDBC.assignments;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class ToDeleteData {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/acs_tenant_2";
        final String user = "root";
        final String pass = "ABHI";

        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the book_id you want to delete: ");
        int deletebook_id = sc.nextInt();

        try {
            conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT book_id FROM Book_manager WHERE book_id = ? AND isdelete =0";
            cstmt = conn.prepareCall(query);
            cstmt.setInt(1, deletebook_id);
            rs = cstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("********************No such book_id found********************");
                return;
            } else {
                System.out.println("Book with book_id " + deletebook_id + " found.");
            }

            String updateQuery = "UPDATE Book_manager SET isdelete = 1 WHERE book_id = ?";
            CallableStatement updateStmt = conn.prepareCall(updateQuery);
            updateStmt.setInt(1, deletebook_id);
            updateStmt.executeUpdate();
            System.out.println("********************Delete Query::   >>>>>>>>> " + updateStmt.toString()+"********************");
            System.out.println("********************Data Deleted Successfully********************");

            System.out.println("********************Retrieving the Updated Data From Database********************");
            String retrieveQuery = "SELECT book_id, tg_bookname, category_id, book_name, book_author, book_price, book_description, active, isdelete FROM Book_manager WHERE book_id = ?";
            CallableStatement retrieveStmt = conn.prepareCall(retrieveQuery);
            retrieveStmt.setInt(1, deletebook_id);
            rs = retrieveStmt.executeQuery();
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

                System.out.println("********************Updated Data Retrieved Successfully:********************");
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
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cstmt != null) {
                    cstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
