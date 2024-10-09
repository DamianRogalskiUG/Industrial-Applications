import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.*;

public class Zad2 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first filename: ");
        String fileName1 = scanner.nextLine();
        System.out.println("Enter the second filename: ");
        String fileName2 = scanner.nextLine();

        File file1 = new File(fileName1);
        File file2 = new File(fileName2);

        if (file1.exists()) {
            System.out.println("File " + fileName1 + " exists!");

            try (FileReader fileReader = new FileReader(file1);
                 FileWriter fileWriter = new FileWriter(file2, true)) {

                int character;
                while ((character = fileReader.read()) != -1) {
                    fileWriter.write(character);
                }
                System.out.println("Content from " + fileName1 + " has been appended to " + fileName2);
            } catch (IOException e) {
                System.out.println("Error occurred while reading or writing files.");
            }
        } else {
            System.out.println("File " + fileName1 + " does not exist.");
        }

        System.out.println("Enter another filename: ");
        String fileName3 = scanner.nextLine();
        System.out.println("Enter the number of last lines to display: ");
        int numberOfLastLines = scanner.nextInt();

        File otherFile = new File(fileName3);
        if (otherFile.exists()) {
            List<String> lines = Files.readAllLines(Paths.get(fileName3));
            int start = Math.max(0, lines.size() - numberOfLastLines);

            System.out.println("Last " + numberOfLastLines + " lines from file " + fileName3 + ":");
            for (int i = start; i < lines.size(); i++) {
                System.out.println(lines.get(i));
            }

        } else {
            System.out.println("File " + fileName3 + " does not exist.");
        }

        try (PrintWriter logWriter = new PrintWriter(new FileWriter("log.txt"))) {
            logWriter.println(fileName1 + ": " + Files.size(Paths.get(fileName1)) + " bytes");
            logWriter.println(fileName2 + ": " + Files.size(Paths.get(fileName2)) + " bytes");
            logWriter.println(fileName3 + ": " + Files.size(Paths.get(fileName3)) + " bytes");
            System.out.println("File sizes have been logged to log.txt.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to log.txt.");
        }
    }
}
