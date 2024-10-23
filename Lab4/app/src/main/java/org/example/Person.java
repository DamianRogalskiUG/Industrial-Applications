package org.example;

public class Person {
    private String firstName;
    private String lastname;
    private String email;
    private String company;

    public Person(String firstName, String lastname, String email, String company) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.company = company;
    }

    // Gettery
    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
