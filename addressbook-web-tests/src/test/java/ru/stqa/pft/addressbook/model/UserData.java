package ru.stqa.pft.addressbook.model;

public class UserData {
    private final String id;
    private final String name;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String email;
    private String group;

    public UserData(String id, String name, String lastName, String address, String phone, String email, String group ) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public UserData( String name, String lastName, String address, String phone, String email, String group ) {
        this.id = null;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (id != null ? !id.equals(userData.id) : userData.id != null) return false;
        if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
        return lastName != null ? lastName.equals(userData.lastName) : userData.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getId() {
        return id;
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
