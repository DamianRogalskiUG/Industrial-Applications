package org.example;

import org.example.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.InputStream;
import org.example.App;


public class Company {
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
    public static void filterByCompany(List<Person> employees, String company) {
        System.out.println("\nEmployees from " + company + ":");
        employees.stream()
                .filter(employee -> employee.getCompany().equalsIgnoreCase(company))
                .forEach(System.out::println);
    }
    public static void sortByLastName(List<Person> employees) {
        System.out.println("\nEmployees sorted by last name:");
        employees.stream()
                .sorted((e1, e2) -> e1.getLastname().compareToIgnoreCase(e2.getLastname()))
                .forEach(System.out::println);
    }
}