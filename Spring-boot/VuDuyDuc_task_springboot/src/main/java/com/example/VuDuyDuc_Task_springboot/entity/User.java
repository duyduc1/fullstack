package com.example.VuDuyDuc_Task_springboot.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private Integer numberphone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Companies company;

    @Column(name = "companyname")
    private String companyname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id" , referencedColumnName = "id" , nullable = false)
    private Gender gender;

    @Column(name = "gioitinh")
    private String gioitinh;
}
