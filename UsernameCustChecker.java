package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UsernameCustChecker {
	public static boolean isUsernameAvailable(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\\\Users\\\\borhen\\\\Desktop\\\\TP JAVA\\\\Projecttest\\\\src\\\\application\\\\Customer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String existingUsername = line.split(",")[0]; // Assuming usernames are stored as the first value in each line
                if (existingUsername.equals(username)) {
                    return false; // Username already exists
                }
            }
        } catch (IOException error) {
            System.out.println("Error reading user database: " + error.getMessage());
        }
        return true; // Username is available
	}

}
