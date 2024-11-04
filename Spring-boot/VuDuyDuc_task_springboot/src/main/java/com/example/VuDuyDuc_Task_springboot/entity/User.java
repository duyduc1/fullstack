package com.example.VuDuyDuc_Task_springboot.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String username;
    private Integer numberphone;
    private String password;
    private String role;

    @Transient
    private String token;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Companies company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id" , referencedColumnName = "id" , nullable = false)
    @JsonIgnore
    private Gender gender;

    // Implementing UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // or add logic to return roles/authorities
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // or add logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // or add logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // or add logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // or add logic if needed
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", numberphone=" + numberphone +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
