package com.example.shop.controller;

import com.example.shop.dto.UserDto;
import com.example.shop.entity.User;
import com.example.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
     @Autowired
    UserRepository userRepository;
    @GetMapping("signIn")
    public String user(Model model){
     model.addAttribute("user",new UserDto());
     return "userOperation";
    }
    @PostMapping()
    public String check(UserDto userDto,Model model){
        model.addAttribute("user",new UserDto());
        Optional<User> users = userRepository.findByUserName(userDto.getUserName());
        if (users.isEmpty()) return "notFound";

        if (users.get().getRole().equals("user")){
            return "redirect:/book";
        }
        return "notFound";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new UserDto());
        return "register";
    }
    @PostMapping("register")
    public String add(Model model,UserDto userDto){
        Optional<User> optionalUser = userRepository.findByUserName(userDto.getUserName());
        if (optionalUser.isPresent()) {
            return "busy";
        }
        if (!userDto.getPassword().equals(userDto.getPre_password())){
            return "notEquals";
        }
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName());
        userRepository.save(user);
        return "redirect:/book";
    }


}
