package camt.se234.unittest.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Builder
public class User {
    String username;
    String password;
    String name;
    LocalDate dateOfBirth;
    String telephoneNo;

    public User(String username, String password, String name, LocalDate dateOfBirth, String telephoneNo) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.telephoneNo = telephoneNo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    //eiei
}
