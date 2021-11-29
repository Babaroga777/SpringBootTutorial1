package com.mycompany.springbootcrudtutorial.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired private UserService service; //Reference to instance of UserService

    //Method for showing the user list on the Homepage,handles the request of user data
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        //Puts the list attribute in the list model
        model.addAttribute("listUsers",listUsers);
        return "users";
    }
    //Method Add New User
    //Referenz zu users.html
    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }
    //Method: For save User Data
    @PostMapping("/users/save")
    //User user - because we create an object in the user_form.html
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        service.save(user);
        redirectAttributes.addFlashAttribute("message", "The User has been successfully saved!");
        return "redirect:/users";
    }
    //Method for editing User Data
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id")Integer id,Model model,RedirectAttributes redirectAttributes){
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle","Edit User with ID: "+id);
            return "user_form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }
    //Method for deleting user in the listing page
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id")Integer id,RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message","The User with ID "+id+" has been deleted!");
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}


