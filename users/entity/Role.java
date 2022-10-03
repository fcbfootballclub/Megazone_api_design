package com.example.demo.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@Accessors(chain = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_title", unique = true)
    private String roleTitle;

    @ManyToMany(mappedBy = "roleSet")
    @ToString.Exclude
    @JsonIgnore
    private Set<User> userSet;
}
