package com.mycompany.springbootcrudtutorial;

import com.mycompany.springbootcrudtutorial.user.User;
import com.mycompany.springbootcrudtutorial.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

//für die Test class
@DataJpaTest
//Test Real-Database will be executed instead of the Default-In-Memory-Database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Keep the data commited to the database
@Rollback(false)
public class UserRepositoryTest {

    //Referenz zu UserRepository
    @Autowired private UserRepository repo;

    //Test 1 - neuen Benutzer hinzufügen
    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("alekspre@mail.at");
        user.setPassword("0000");
        user.setFirstname("Aleksandar");
        user.setLastname("Prekodravac");

        //save User in the Database  (save-Methode in der CrudRepository Interface)
        User savedUser = repo.save(user);

        //API testen ob nicht leer und sinnvole ID
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    //Test 2 - For listing users in the database
    @Test
    public void testListAll(){
        //findAll-Method in the CrudRepository Interface(iterable collection)
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        //über jedes User-Objekt iterieren
        for (User user: users) {
            System.out.println(user);
        } // in der User-Klasse eine @override-toString-Methode generieren
    }
    //Test 3 updating the user
    @Test
    public void testUpdateUser(){
        //Variable für die userID
        Integer userId = 1;
        //findByID refered to the CrudRepository
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("0000");
        //Update and save the changes in the database
        repo.save(user);

        //Zum Testen wird der User per ID abgerufen
        User updatedUser = repo.findById(userId).get();
        //Überprüfung mit Assertions
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("0000");
    }
    //4.Test: Receiving User by ID
    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }
    //5.Test: Deleting User by ID
    @Test
    public void testDeleteUserById(){
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
