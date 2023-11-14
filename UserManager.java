import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserManager {
    private static final String USER_FILE_PATH = "src/users.txt";

    public static boolean registerUser(String username, String password) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_PATH, true));
            writer.write(username + "," + password);
            writer.newLine();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}