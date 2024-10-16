import java.io.*;
import java.util.*;

public class Zad3 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Odczyt danych z pliku CSV
        System.out.println("Enter the CSV filename: ");
        String csvFileName = scanner.nextLine();

        File csvFile = new File(csvFileName);
        if (!csvFile.exists()) {
            System.out.println("File " + csvFileName + " does not exist.");
            return;
        }

        Map<String, Integer> currencyCount = new HashMap<>();
        int emailComCount = 0;

        try (FileReader fileReader = new FileReader(csvFile)) {
            Scanner fileScanner = new Scanner(fileReader);

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                String currency = data[4];
                String email = data[2];

                currencyCount.put(currency, currencyCount.getOrDefault(currency, 0) + 1);

                if (email.endsWith(".com")) {
                    emailComCount++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file.");
        }

        System.out.println("Number of people earning in each currency:");
        for (Map.Entry<String, Integer> entry : currencyCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Number of emails ending with .com: " + emailComCount);

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter student data " + (i + 1) + ":");
            System.out.print("Firstname: ");
            String firstName = scanner.nextLine();
            System.out.print("Lastname: ");
            String lastName = scanner.nextLine();
            System.out.print("Index number: ");
            String studentId = scanner.nextLine();
            students.add(new Student(firstName, lastName, studentId));
        }

        createXML(students);
    }

    private static void createXML(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("students.xml"))) {
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<Students>");

            for (Student student : students) {
                writer.println("  <Student>");
                writer.println("    <FirstName>" + student.getFirstName() + "</FirstName>");
                writer.println("    <LastName>" + student.getLastName() + "</LastName>");
                writer.println("    <StudentID>" + student.getStudentId() + "</StudentID>");
                writer.println("  </Student>");
            }

            writer.println("</Students>");
            System.out.println("Xml file saved as students.xml");

        } catch (IOException e) {
            System.out.println("Error occurred while writing the XML file.");
        }
    }
}

class Student {
    private String firstName;
    private String lastName;
    private String studentId;

    public Student(String firstName, String lastName, String studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentId() {
        return studentId;
    }
}
