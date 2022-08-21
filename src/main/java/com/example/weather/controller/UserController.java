package com.example.weather.controller;

import com.example.weather.exceptions.ResourceNotFoundException;
import com.example.weather.service.user.UserRegistrationDto;
import com.example.weather.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserController {

    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //<form th:action="@{/registration}" method="post" th:object="${user}"></form>
    //when the thymeleaf template wants to store a form data
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }
    @GetMapping //when e get request is performed on /registration the registration.html is called
    public String showRegistrationForm(){
        return "registration";
    }
    @PostMapping // when e post request is performed on registration.html the user is saved into the db
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) throws ResourceNotFoundException {
        if (userService.findUserByEmail(userRegistrationDto.getEmail())==null) {
            userService.save(userRegistrationDto);
            return "redirect:/registration?success";
        }
        return "redirect:/registration?exist";
    }

    public String getUserLoggedIn(Model model){
        model.addAttribute("name", userService.loggedInUser());
        return "index";
    }
}
