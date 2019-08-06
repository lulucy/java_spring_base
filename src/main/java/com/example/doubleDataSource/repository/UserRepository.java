package com.example.doubleDataSource.repository;


import com.example.doubleDataSource.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    public User findByName(String name);

    public User save(User user);

    public List getUserByAgeBefore(Integer age);
}
