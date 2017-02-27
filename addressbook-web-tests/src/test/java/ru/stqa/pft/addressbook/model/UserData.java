package ru.stqa.pft.addressbook.model;

public class UserData {
    private final String name;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String email;
    private String group;

    public UserData(String name, String lastName, String address, String phone, String email, String group ) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
