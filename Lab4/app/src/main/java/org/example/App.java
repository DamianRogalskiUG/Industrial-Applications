package org.example;

import java.io.InputStream;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.example.Person;

public class App {
    public String getGreeting() {
        return "Welcome User";
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

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                String firstName = data[0];
                String lastName = data[1];
                String email = data[2];
                String company = data[3];

                System.out.println("Name: " + firstName + ", Lastname: " + lastName + ", Email: " + email + ", Company: " + company);
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error occured while reading file: " + e.getMessage());
        }
    }
}
