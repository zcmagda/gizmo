package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import to.gizmo.entities.User;
import to.gizmo.repositories.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UsersController
{
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) {
            return "redirect:/?err=Nope";
        }
        Optional<User> found = userRepository.findByUsername(user.getUsername());
        if (found.isPresent()) {
            return "redirect:/login?user=exist";
        } else {
            userRepository.save(user);
            return "redirect:/login?user=maylogin";
        }
    }
}
