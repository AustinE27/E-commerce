import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class databaseSearch {
    public static void searchDatabase(String url, String username, String password, String tableName, String columnName){
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName +" = ?";

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
            
            rs.close(); 
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
