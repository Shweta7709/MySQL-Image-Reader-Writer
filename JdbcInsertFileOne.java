import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcInsertFileOne {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/symbiosis";
        String user = "root";
        String password = "12345678";
        String filePath = "D:\\DELL\\College_Training\\JDBC\\wallpaper.png"; // source image
File file = new File(filePath);
if (!file.exists()) {
    System.out.println("Error: File does not exist at " + filePath);
} else {
    System.out.println("File found, proceeding with database insertion.");
}



        try {
            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Prepare SQL statement
            String sql = "INSERT INTO person (name, image) values (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set values for the prepared statement
            statement.setString(1, "wallpaper");

            // Set image file as Blob
            InputStream inputStream = new FileInputStream(new File(filePath));
            statement.setBlob(2, inputStream);

            // Execute the update
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Image inserted successfully.");
            }

            // Close resources
            inputStream.close();
            statement.close();
            conn.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
