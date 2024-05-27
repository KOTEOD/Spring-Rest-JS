package ru.kata.spring.boot_security.demo.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private int age;
    @Column
    boolean flagAccess;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;


    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public boolean isFlagAccess() {
        return flagAccess;
    }

    public void setFlagAccess(boolean flagAccess) {
        this.flagAccess = flagAccess;
    }

    public User() {
    }

    public User(String username, int age, String password, boolean flagAccess) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.flagAccess = flagAccess;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapAuthorities(roles);
    }
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<Role> roles) {
        return roles.stream().map(r->new SimpleGrantedAuthority(r.getUsername())).collect(Collectors.toList());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User" + '\n' +
                "id=" + id +
                ", name = " + username + '\'' +
                ", age = " + age ;
    }
}
