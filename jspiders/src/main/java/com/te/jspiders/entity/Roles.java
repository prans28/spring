package com.te.jspiders.entity;

import java.util.List;

import javax.persistence.*;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Roles class has only two fields i.e. roleId and roleName
 * and also roleName is unique for the class so that we don't store
 * multiple roles with different primary key.
 *
 * This class also had a bi-directional ManyToMany relationship with the class
 * AppUser.
 * */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;
    @Column(unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<AppUser> appUsers;
}
