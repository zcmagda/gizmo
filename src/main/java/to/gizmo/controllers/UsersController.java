package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import to.gizmo.entities.User;
import to.gizmo.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersController
{
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "redirect:/?err=Nope";
        }

        userRepository.save(user);

        return "redirect:/?msg=Yup!";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpSession session = request.getSession();

        session.setAttribute("userId", user.getId());

        return "redirect:/?msg=Yup!";
    }
}
