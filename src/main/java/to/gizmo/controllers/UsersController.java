package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import to.gizmo.entities.User;
import to.gizmo.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult, Model model) {
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

//    @PostMapping("/login")
//    public String login(@Valid User user, BindingResult bindingResult, HttpServletRequest request, Model model) {
//            return "redirect:/?err=Nope";
//        if (bindingResult.hasErrors()) {
//        }
////        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<User> found = userRepository.findByUsername(user.getUsername());
//        if (found.isPresent()) {
//            HttpSession session = request.getSession();
//            session.setAttribute("userId", user.getId());
//            return "redirect:/";
//        } else {
//            return "redirect:/register";
//        }
//
//    }
}
