package to.gizmo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import to.gizmo.entities.User;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        model.addAttribute("welcomeMessage", "Hello my friend: " + user.getEmail());
        return "index";
    }
}
