package com.example.doubleDataSource.entity2;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foo", schema = "bar", catalog = "")
public class Foo {
    @Id
    @GeneratedValue
    private Integer id;
    private String bar;
}
