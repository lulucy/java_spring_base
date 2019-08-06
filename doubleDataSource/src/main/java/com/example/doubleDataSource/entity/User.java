package com.example.doubleDataSource.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "T_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;
}
