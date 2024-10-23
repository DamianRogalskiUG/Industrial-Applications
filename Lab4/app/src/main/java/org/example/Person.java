package org.example;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String company;

    public Person(String firstName, String lastName, String email, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
    }

    // Gettery i Settery

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
