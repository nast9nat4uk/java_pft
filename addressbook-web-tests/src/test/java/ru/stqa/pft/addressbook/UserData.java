package ru.stqa.pft.addressbook;

public class UserData {
    private final String name;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String email;

    public UserData(String name, String lastName, String address, String phone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
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
}
