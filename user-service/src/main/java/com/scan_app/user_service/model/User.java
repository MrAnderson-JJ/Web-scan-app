package com.scan_app.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private String id;
    @OneToMany(mappedBy = "user")
    private List<Scan> scans = new ArrayList<>();

    public User(String id) {
        this.id = id;
        this.scans = new ArrayList<>();
    }
}
