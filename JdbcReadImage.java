import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcReadImage {
    
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/symbiosis";
        String user = "root";
        String password = "12345678";
        String filePath = "D:\\DELL\\College_Training\\Demo_1\\wallpaper.png"; // Destination for the image

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT image FROM person WHERE name = ?";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "wallpaper");

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Blob blob = result.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();
                OutputStream outputStream = new FileOutputStream(filePath);

                int bytesRead;
                byte[] buffer = new byte[BUFFER_SIZE];

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();

                System.out.println(" File saved successfully at: " + filePath);
            } else {
                System.out.println(" No record found for the given name.");
            }

            conn.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
