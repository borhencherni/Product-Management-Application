package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TestAdmin {
	    public static void main(String[] args) {
	        try {
	            // Specify the path to your .txt file
	            String filePath = "C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\admin.txt";
	            
	            // Open the file for reading
	            BufferedReader reader = new BufferedReader(new FileReader(filePath));
	            
	            String line;
	            
	            // Read each line from the file
	            while ((line = reader.readLine()) != null) {
	                // Process each line of data
	                System.out.println(line); // For demonstration, simply print the line
	            }
	            
	            // Close the file
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


