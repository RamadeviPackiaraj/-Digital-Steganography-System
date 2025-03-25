// ImageSteganography.java
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSteganography implements Steganography {
    
    @Override
    public void encode(String inputFile, String message, String outputFile) {
        try {
            BufferedImage image = ImageIO.read(new File(inputFile));
            int messageLength = message.length();
            int width = image.getWidth();
            int height = image.getHeight();
            
            if (messageLength * 8 > width * height) {
                System.out.println("Message too long to encode in image.");
                return;
            }

            int charIndex = 0;
            int bitIndex = 0;
            int currentChar = message.charAt(charIndex);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (charIndex >= messageLength) break;
                    
                    int pixel = image.getRGB(x, y);
                    int newPixel = (pixel & 0xFFFFFFFE) | ((currentChar >> bitIndex) & 1);
                    
                    image.setRGB(x, y, newPixel);
                    bitIndex++;

                    if (bitIndex == 8) {
                        bitIndex = 0;
                        charIndex++;
                        if (charIndex < messageLength) {
                            currentChar = message.charAt(charIndex);
                        }
                    }
                }
            }

            ImageIO.write(image, "png", new File(outputFile));
            System.out.println("Message encoded successfully!");

        } catch (IOException e) {
            System.out.println("Error processing image: " + e.getMessage());
        }
    }

    @Override
    public String decode(String encodedFile) {
        try {
            BufferedImage image = ImageIO.read(new File(encodedFile));
            int width = image.getWidth();
            int height = image.getHeight();

            StringBuilder message = new StringBuilder();
            int currentChar = 0, bitIndex = 0;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);
                    currentChar |= (pixel & 1) << bitIndex;
                    bitIndex++;

                    if (bitIndex == 8) {
                        if (currentChar == 0) return message.toString(); // Stop on null character
                        message.append((char) currentChar);
                        bitIndex = 0;
                        currentChar = 0;
                    }
                }
            }

            return message.toString();

        } catch (IOException e) {
            System.out.println("Error reading image: " + e.getMessage());
        }
        return "";
    }
}
