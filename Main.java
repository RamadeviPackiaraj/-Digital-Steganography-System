// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Steganography steganography = null;

        System.out.println("Welcome to Digital Steganography System!");
        System.out.println("1. Image Steganography");
        System.out.println("2. Text File Steganography");
        System.out.print("Select option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        if (choice == 1) {
            steganography = new ImageSteganography();
        } else if (choice == 2) {
            steganography = new TextSteganography();
        } else {
            System.out.println("Invalid choice! Exiting...");
            return;
        }

        System.out.println("1. Encode Message");
        System.out.println("2. Decode Message");
        System.out.print("Select operation: ");
        
        int operation = scanner.nextInt();
        scanner.nextLine();

        if (operation == 1) {
            System.out.print("Enter input file path: ");
            String inputFile = scanner.nextLine();
            System.out.print("Enter secret message: ");
            String message = scanner.nextLine();
            System.out.print("Enter output file path: ");
            String outputFile = scanner.nextLine();
            steganography.encode(inputFile, message, outputFile);
        } else if (operation == 2) {
            System.out.print("Enter encoded file path: ");
            String encodedFile = scanner.nextLine();
            String message = steganography.decode(encodedFile);
            System.out.println("Decoded Message: " + message);
        } else {
            System.out.println("Invalid operation! Exiting...");
        }

        scanner.close();
    }
}
//javac -d bin src/*.java
//java -cp bin Main

