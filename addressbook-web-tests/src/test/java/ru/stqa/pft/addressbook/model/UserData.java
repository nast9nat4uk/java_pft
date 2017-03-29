package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("user")
@Entity
@Table(name="addressbook")

public class UserData {
    @Expose
    @Column(name = "firstname")
    private  String name;
    @Expose
    @Column(name = "lastname")
    private  String lastName;
    @Column(name = "address")
    @Type(type="text")
    private  String address;
    @Column(name = "email")
    @Type(type="text")
    private  String email;

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = 0;
    @Column(name = "home")
    @Type(type="text")
    private String home;
    @Type(type="text")
    @Column(name = "mobile")
    private String mobile;
    @Type(type="text")
    @Column(name = "work")
    private String work;
    @Column(name = "email2")
    @Type(type="text")
    private String email2;
    @Column(name = "email3")
    @Type(type="text")
    private String email3;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String fullname;
    @Transient
    private String allInfo;

    @Column(name = "photo")
    @Type(type="text")
    private String  photo;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public UserData withGroups(Set<GroupData> groups) {
        this.groups = groups;
        return  this;
    }

    public UserData withName(String name) {
        this.name = name;
        return this;
    }

    public UserData withLastName(String lastName) {
        this.lastName = lastName;
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

    public int getId() {
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

    public String getEmail() {
        return email;
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

    public File getPhoto() {
        if(photo!=null) {
            return new File(photo);
        } else {
            return  null;
        }
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

}
