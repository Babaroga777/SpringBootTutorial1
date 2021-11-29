package com.mycompany.springbootcrudtutorial.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //Reference to UserRepository
    @Autowired private UserRepository repo;

    //Lists all users in the Database
    public List<User> listAll(){
        return (List<User>)repo.findAll(); //findAll returns on Iterable Object + cast the return Type to List
    }

    public void save(User user) {
        repo.save(user);
    }
    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }else{
            throw new UserNotFoundException("Could NOT find any Users with ID: "+id);
        }
    }
    //Method for deleting User, first check if there is any user with the specified ID
    //Declare in the UserRepository class
    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Could NOT find any Users with ID: "+id);
        }else {
            repo.deleteById(id);
        }
    }
}
