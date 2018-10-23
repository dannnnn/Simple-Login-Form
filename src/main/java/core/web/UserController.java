package core.web;


import core.User;
import core.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;

    private boolean match;
    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value ="/{username}", method = RequestMethod.GET)
    public String showUserProfile(@PathVariable String username, Model model){
        User user = userRepository.findByUsername(username);
        model.addAttribute(user);
        return "profile";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String processLogin(User user, Errors errors, Model model){
        if(errors.hasErrors()){
            return "home";
        }

        User users = userRepository.validateUser(user);
        if(null != users){
            match = passwordEncoder.matches(user.getPassword(),users.getPassword());
            if(match){
                return "redirect:/user/" + user.getUsername();
            } else{
                model.addAttribute("logError", "logError");
                return "redirect:/?error";
            }
        }else{
            model.addAttribute("logError", "logError");
            return "redirect:/?error";
        }
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationForm( Model model){
        model.addAttribute(new User());
        return "registerForm";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST )
    public String processRegistration(@Valid User user, Errors errors){
        if(errors.hasErrors()){
            return "registerForm";
        }

        try {
            User userExists = userRepository.findByUsername(user.getUsername());
            if (userExists != null) {
                return "redirect:/user/register?error";
            }
        } catch (Exception e){}
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }
}