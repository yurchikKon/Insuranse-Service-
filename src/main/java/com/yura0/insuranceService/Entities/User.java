package com.yura0.insuranceService.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @JsonManagedReference("user-policy")
    @OneToOne(mappedBy = "usr")
    private Policy policy;

    @Column(name = "mail")
    private String mail;

    @JsonManagedReference("user-pretensions")
    @OneToMany(mappedBy = "usr")
    private List<Pretension> pretension;

    @JsonManagedReference("employee-pretensions")
    @OneToMany(mappedBy = "employee")
    private List<Pretension> task;

    @JsonManagedReference("usr-insurance")
    @OneToMany(mappedBy = "usr", fetch = FetchType.EAGER)
    private List<Insurance> insurances;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
