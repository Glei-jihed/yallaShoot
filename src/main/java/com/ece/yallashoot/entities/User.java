package com.ece.yallashoot.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user",
        indexes = {
                @Index(name = "idx_id", columnList = "id"),
                @Index(name = "idx_age", columnList = "age"),
                @Index(name = "idx_first_name", columnList = "first_name"),
                @Index(name = "idx_last_name", columnList = "last_name"),
                @Index(name = "idx_phone", columnList = "phone"),
                @Index(name = "idx_email", columnList = "email")
})
public class User implements UserDetails {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;


    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "age", nullable = false)
    private int age;


    @Temporal(TemporalType.DATE)
    private Date inscriptionDate;


    @Column(unique = true, name = "email", nullable = false)
    private String email;


    private String password;


    private boolean connected;


    @Column(unique = true, nullable = false, name = "phone")
    private String phone;



    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Lob
    private byte[] profilePicture;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<Game> games;

    //========================== PrePersist methods ====================================================================



    @PrePersist
    private void onCreateDate() {
        this.inscriptionDate = new Date();

    }



    //==================================================================================================================
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
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


    //===========================PreUpdate methods =====================================================================


}