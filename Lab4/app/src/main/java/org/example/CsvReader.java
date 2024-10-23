package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.InputStream;
import org.springframework.stereotype.Service;


@Service
public class CsvReader {
    public static List<Person> readEmployeesCsv() {
        List<Person> employees = new ArrayList<>();
        try {
            InputStream inputStream = App.class.getClassLoader().getResourceAsStream("MOCK_DATA.csv");
            if (inputStream == null) {
                System.out.println("MOCK_DATA.csv not found");
            }

            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNextLine()) {
                String header = scanner.nextLine();
                System.out.println("Headers: " + header);
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                String firstName = data[0];
                String lastName = data[1];
                String email = data[2];
                String company = data[3];

                Person employee = new Person(firstName, lastName, email, company);
                employees.add(employee);

            }

            scanner.close();
            return employees;
        } catch (Exception e) {
            System.out.println("Error occured while reading file: " + e.getMessage());
        }
        return employees;

    }
}