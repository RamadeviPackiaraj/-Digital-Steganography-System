// TextSteganography.java
import java.io.*;

public class TextSteganography implements Steganography {

    @Override
    public void encode(String inputFile, String message, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(message);
            System.out.println("Message successfully encoded in text file!");
        } catch (IOException e) {
            System.out.println("Error encoding message: " + e.getMessage());
        }
    }

    @Override
    public String decode(String encodedFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(encodedFile))) {
            StringBuilder message = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                message.append(line);
            }
            return message.toString();
        } catch (IOException e) {
            System.out.println("Error decoding message: " + e.getMessage());
        }
        return "";
    }
}
