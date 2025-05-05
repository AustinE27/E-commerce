package com.example.ecommercestoreprojecttemp;
/**
 * databaseSearch
 * 4/13/25
 * Austin Engstrom
 * Searchs the database connected to the program for an item with in it
 * It must be extactly the same as the name in the database with plans to updated to shared letters
 * Inpts are url username password tablename columnname and the userinput with it outputting anything that shares a name as the input
 * Important data structures are string, scanner, prepaired statement, and result set
 * Algorithms are user input retreival to allow a user to find specific items
 * Query contsruction to allow for the prepared statement with user input
 * Result iteration to show and duplicate items 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class databaseSearch {
    public static void searchDatabase(String url, String username, String password, String tableName, String columnName){
       /* Sets a string for a query search from the database
        */ 
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName +" = ?";
        /* Creates the scanner and asks for user input while estabishing a connection to the database to dispay
        * the results of the search
         */
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter item name or sku: ");
            String userInput = scanner.nextLine();
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userInput);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Search Results:");
            while (rs.next()) {
                System.out.println("Item: " + rs.getString("product_name"));
            }
            /*
             * closes rs stmt and conn
             */
            rs.close(); 
            stmt.close();
            conn.close();
        /*
         * catchs error and runs printStackTrace
         */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
