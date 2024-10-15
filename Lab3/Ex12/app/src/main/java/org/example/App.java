package org.example;

import org.joda.time.LocalTime;

public class App {
    String getGreeting() {
        return "Hello World!";
    }
    public static void main(String[] args) {
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: " + currentTime);
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
    }
}

class Greeter {
    public String sayHello() {
        return "Hello World!";
    }
}
