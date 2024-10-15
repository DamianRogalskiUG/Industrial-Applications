import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Zad1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the filename: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        
        if (file.exists()) {
            System.out.println("File exists!");
            int lineCount = 0;
            int letterCount = 0;
            int digitCount = 0;
            int whiteSpaceCount = 0;
            
            try (FileReader fileReader = new FileReader(file)) {
                int character;
                boolean newLine = true;
                while ((character = fileReader.read()) != -1) {
                    if (newLine) {
                        lineCount++;
                        newLine = false;
                    }
                    char ch = (char) character;
                    if (Character.isLetter(ch)) {
                        letterCount++;
                    } else if (Character.isDigit(ch)) {
                        digitCount++;
                    } else if (Character.isWhitespace(ch)) {
                        whiteSpaceCount++;
                        if (ch == '\n') {
                            newLine = true;
                        }
                    }
                }
                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of letters: " + letterCount);
                System.out.println("Number of digits: " + digitCount);
                System.out.println("Number of white spaces: " + whiteSpaceCount);

            } catch (IOException e) {
                System.out.println("Error occurred while reading the file.");
            }

        } else {
            System.out.println("File doesn't exist!");
            System.out.println("Creating new file.");
            
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write("Witaj, świecie!");
                System.out.println("Saved to file " + fileName);
            } catch (IOException e) {
                System.out.println("Error occurred while creating a file.");
            }
        }

        try {
            long fileSize = Files.size(Paths.get(fileName));
            System.out.println("File size: " + fileSize + " bytes.");
        } catch (IOException e) {
            System.out.println("Error occurred while checking the file size.");
        }
    }
}
