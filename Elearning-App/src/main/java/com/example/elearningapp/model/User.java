package com.example.elearningapp.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastName;
    @Column(unique = true)
    private String userName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private boolean enable;//it is to know if the customer confirmed its account with the email confirmation
    private boolean isDeleted;
    private boolean receiveNotifications;
    private String pwd;
    private Boolean status;
    private LocalDateTime joinedAt;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Lob
    @Column(name = "userPicture",columnDefinition = "LONGBLOB")
    byte[] picture;
    @Lob
    @Column(name = "teacherVideo",columnDefinition = "LONGBLOB")
    byte[] video;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return enable;
    }
}

