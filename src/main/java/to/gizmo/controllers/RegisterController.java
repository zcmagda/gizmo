package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import to.gizmo.entities.User;
import to.gizmo.repositories.UserRepository;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            model.addAttribute("welcomeMessage", "Hello my friend: " + user.getEmail());
        } else {
            model.addAttribute("exist", true);
            model.addAttribute("existingMail", user.getEmail());
        }

        return "index";
    }
}
