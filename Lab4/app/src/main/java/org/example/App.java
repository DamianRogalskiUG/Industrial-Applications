package org.example;

import java.io.InputStream;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.example.Person;
import org.example.CsvReader;
import java.util.ArrayList;
import java.util.List;
import org.example.Company;


public class App {
    public String getGreeting() {
        return "Welcome User";
    }

    public static void main(String[] args) {

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

        Company.sortByLastName(employeesList);
        Company.filterByCompany(employeesList, "Twitterbridge");

    }
}