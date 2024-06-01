package ru.kata.spring.boot_security.demo.Model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {

    }

    public String getAuthority() {
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
}
