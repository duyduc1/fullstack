package com.example.VuDuyDuc_Task_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "gender")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String genders;

    @OneToMany(mappedBy = "gender" , cascade = CascadeType.ALL)
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

}
