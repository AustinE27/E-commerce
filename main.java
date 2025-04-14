public class main {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/product";
        String username = "root";
        String password = "password";

        String tableName = "product_list";
        String columnName = "product_name";

        databaseSearch.searchDatabase(url, username, password, tableName, columnName);
    }
}
