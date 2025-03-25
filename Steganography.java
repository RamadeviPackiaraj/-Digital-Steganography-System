public interface Steganography {
    void encode(String inputFile, String message, String outputFile);
    String decode(String encodedFile);
}
