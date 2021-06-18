package JSONReader;

import java.util.List;

public class Customer {
    private int id;
    private List<String> names;
    private int age;
    private List<String> addresses;

    public Customer(int id, List<String> names, int age, List<String> addresses) {
        this.id = id;
        this.names = names;
        this.age = age;
        this.addresses = addresses;
    }

    public int getID() {
        return this.id;
    }

    public List<String> getNames() {
        return this.names;
    }

    public int getAge() {
        return this.age;
    }

    public List<String> getAddresses() {
        return this.addresses;
    }
}