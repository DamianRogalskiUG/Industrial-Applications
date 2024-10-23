package org.example;

import java.io.InputStream;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.example.Person;
import org.example.CsvReader;
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

        System.out.println("-----Important People-----");
        System.out.println(prezes);
        System.out.println(wiceprezes);
        System.out.println(sekretarka);

        System.out.println("-----Employees from CSV-----");

//      CsvReader.
        List<Person> employeesList = CsvReader.readEmployeesCsv();
        employeesList
                .forEach(employee -> System.out.println(employee));

      sortByLastName(employeesList);
      filterByCompany(employeesList, "Twitterbridge");

    }
}