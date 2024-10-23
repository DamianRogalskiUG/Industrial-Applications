package org.example;

import java.io.InputStream;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.example.Person;
import java.util.ArrayList;
import java.util.List;

public class App {
    public String getGreeting() {
        return "Welcome User";
    }

    public static void filterByCompany(List<Person> employees, String company) {
        System.out.println("\nPracownicy z firmy " + company + ":");
        employees.stream()
                .filter(employee -> employee.getCompany().equalsIgnoreCase(company))
                .forEach(System.out::println);
    }

    public static void sortByLastName(List<Person> employees) {
        System.out.println("\nPracownicy posortowani według nazwiska:");
        employees.stream()
                .sorted((e1, e2) -> e1.getLastname().compareToIgnoreCase(e2.getLastname()))
                .forEach(System.out::println);
    }


    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Person prezes = (Person) context.getBean("Prezes");
        Person wiceprezes = (Person) context.getBean("Wiceprezes");
        Person sekretarka = (Person) context.getBean("Sekretarka");

        System.out.println(prezes);
        System.out.println(wiceprezes);
        System.out.println(sekretarka);

        try {
            InputStream inputStream = App.class.getClassLoader().getResourceAsStream("MOCK_DATA.csv");
            if (inputStream == null) {
                System.out.println("MOCK_DATA.csv not found");
                return;
            }

            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNextLine()) {
                String header = scanner.nextLine();
                System.out.println("Headers: " + header);
            }
            List<Person> employees = new ArrayList<>();
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
//            System.out.println(employees);

            sortByLastName(employees);
            filterByCompany(employees, "Twitterbridge");


            scanner.close();
        } catch (Exception e) {
            System.out.println("Error occured while reading file: " + e.getMessage());
        }
    }
}
