import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Zad3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String csvFileName = "data.csv";
        Map<String, Integer> currencyCount = new HashMap<>();
        int emailCount = 0;

        try (FileReader fileReader = new FileReader(csvFileName)) {
            int character;
            StringBuilder lineBuilder = new StringBuilder();
            
            while ((character = fileReader.read()) != -1) {
                if (character == '\n') {
                    String line = lineBuilder.toString();
                    String[] data = line.split(",");
                    if (data.length >= 3) {
                        String currency = data[1];
                        String email = data[2];

                        currencyCount.put(currency, currencyCount.getOrDefault(currency, 0) + 1);

                        if (email.endsWith(".com")) {
                            emailCount++;
                        }
                    }
                    lineBuilder.setLength(0);
                } else {
                    lineBuilder.append((char) character);
                }
            }

            if (lineBuilder.length() > 0) {
                String line = lineBuilder.toString();
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String currency = data[1];
                    String email = data[2];

                    currencyCount.put(currency, currencyCount.getOrDefault(currency, 0) + 1);

                    if (email.endsWith(".com")) {
                        emailCount++;
                    }
                }
            }

            System.out.println("Number of people earning in each currency:");
            for (Map.Entry<String, Integer> entry : currencyCount.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Number of emails ending with .com: " + emailCount);

        } catch (IOException e) {
            System.out.println("Error occurred while reading the CSV file.");
        }

        List<Student> students = new ArrayList<>();
        System.out.println("Enter the number of students (minimum 3): ");
        int studentCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < studentCount; i++) {
            System.out.println("Enter the student's first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter the student's last name: ");
            String lastName = scanner.nextLine();
            System.out.println("Enter the student's index number: ");
            String indexNumber = scanner.nextLine();
            students.add(new Student(firstName, lastName, indexNumber));
        }

        String xmlFileName = "students.xml";
        try (FileWriter xmlWriter = new FileWriter(xmlFileName)) {
            xmlWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xmlWriter.write("<students>\n");

            for (Student student : students) {
                xmlWriter.write("  <student>\n");
                xmlWriter.write("    <firstName>" + student.firstName + "</firstName>\n");
                xmlWriter.write("    <lastName>" + student.lastName + "</lastName>\n");
                xmlWriter.write("    <indexNumber>" + student.indexNumber + "</indexNumber>\n");
                xmlWriter.write("  </student>\n");
            }

            xmlWriter.write("</students>\n");
            System.out.println("Student data has been saved to " + xmlFileName);
        } catch (IOException e) {
            System.out.println("Error occurred while writing to XML file.");
        }
    }

    static class Student {
        String firstName;
        String lastName;
        String indexNumber;

        Student(String firstName, String lastName, String indexNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.indexNumber = indexNumber;
        }
    }
}
