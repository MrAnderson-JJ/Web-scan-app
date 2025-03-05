package com.scan_app.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "scan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scan {
    @Id
    @Column(unique = true, nullable = false)
    private String scanId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String scanType;
    private Date scanStartTime;
}
