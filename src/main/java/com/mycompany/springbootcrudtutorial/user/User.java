package com.mycompany.springbootcrudtutorial.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    //die Werte von der ID-Spalte werden automatisch von der Datenbank generiert
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Email wird mit nullable = false required und mit unique = true darf sie nur einmal verwendet werden, Länge 45
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    //Passwortslänge 15
    @Column(length = 15, nullable = false)
    private String password;

    //Mit name kann man die Spalte benennen, ansonsten wird der Variablenname verwendet
    @Column(length = 45, nullable = false, name = "first_name")
    private String firstName;

    @Column(length = 45, nullable = false, name = "last_name")
    private String lastName;

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
