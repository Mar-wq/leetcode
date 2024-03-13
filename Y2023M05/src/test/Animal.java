package test;

public class Animal {
    String nama;

    public Animal() {
    }

    public Animal(String nama) {
        this.nama = nama;
    }

    void show() {
        System.out.println("animal");
    }
}

class Dog extends Animal{
    String name;

    public Dog( String name) {
        this.name = name;
    }
}
