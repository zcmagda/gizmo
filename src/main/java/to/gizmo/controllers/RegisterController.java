package to.gizmo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import to.gizmo.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController
{
    @PostMapping("/register")
    public String registerUser(HttpServletRequest request, @Valid User user, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        request
            .getSession()
            .setAttribute("userId", 1);

        return "redirect:/";
    }
}
