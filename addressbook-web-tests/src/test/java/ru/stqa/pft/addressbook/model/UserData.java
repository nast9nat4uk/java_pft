package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("user")
public class UserData {

    private  String name;
    private  String lastName;
    private  String address;
    private  String email;
    private  String group;
    @XStreamOmitField
    private int id = 0;
    private String home;
    private String mobile;
    private String work;
    private String email2;
    private String email3;
    private String allPhones;
    private String allEmails;
    private String fullname;
    private String allInfo;
    private File photo;


    public UserData withName(String name) {
        this.name = name;
        return this;
    }

    public UserData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserData withFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withGroup(String group) {
        this.group = group;
        return this;
    }


    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public UserData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return  this;
    }

    public UserData withWorkPhone(String work) {
        this.work = work;
        return  this;
    }

    public UserData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public UserData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return  this;
    }

    public UserData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public UserData withAllInfo(String allInfo) {
        this.allInfo = allInfo;
        return this;
    }

    public UserData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (id != userData.id) return false;
        if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
        return lastName != null ? lastName.equals(userData.lastName) : userData.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAllInfo() {
        return allInfo;
    }

    public File getPhoto() {
        return photo;
    }

}
