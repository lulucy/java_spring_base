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
@Table(name = "file", schema = "bar", catalog = "")
public class File {
    @Id
    @GeneratedValue
    private String fileid;
    private String filename;
}
