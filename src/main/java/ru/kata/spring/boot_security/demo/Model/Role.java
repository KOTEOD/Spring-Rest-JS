package ru.kata.spring.boot_security.demo.Model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;

    public String getAuthority() {
        return name;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getRoleNameWithoutPrefix() {
        return name.substring(5);

    }
}
