package com.mycompany.springbootcrudtutorial.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    //Methodenname kommt durch die Konvention
    public Long countById(Integer id);
}
