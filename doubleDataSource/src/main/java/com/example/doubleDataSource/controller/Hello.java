package com.example.doubleDataSource.controller;

import com.example.doubleDataSource.entity.User;
import com.example.doubleDataSource.entity2.File;
import com.example.doubleDataSource.entity2.Foo;
import com.example.doubleDataSource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class Hello {


    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @RequestMapping("/getOne")
    public List getuser(){
        User user = User.builder().name("zhangsan").age(20).build();
        userRepository.save(user);

        List list = userRepository.getUserByAgeBefore(60);
        return list;
    }

    @Autowired
    private JdbcTemplate source2JdbcTemplate;

    @RequestMapping("/insertH2")
    public File insertH2(){
        String creatsql = "CREATE table file(FILEID VARCHAR(12) PRIMARY KEY,FILENAME VARCHAR(255))";
        source2JdbcTemplate.update(creatsql);

        String sql = "insert into file(fileid,filename) VALUES ('2342','asdfa')";
        source2JdbcTemplate.update(sql);

        List list = new ArrayList<File>();
        source2JdbcTemplate.query("select * from file", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                File file = new File();
                file.setFileid(resultSet.getString("fileid"));
                file.setFilename(resultSet.getString("filename"));
                list.add(file);
            }
        });
        return (File) list.iterator().next();
    }

    @RequestMapping("/getbar")
    public String getBar(){
        List list = new ArrayList<Foo>();
        source2JdbcTemplate.query("select * from foo", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Foo foo = new Foo();
                foo.setId(resultSet.getInt("id"));
                foo.setBar(resultSet.getString("bar"));
                list.add(foo);
            }
        });
        return  list.iterator().next().toString();
    }

}
