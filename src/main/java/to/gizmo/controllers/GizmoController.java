package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import to.gizmo.entities.Workspace;
import to.gizmo.entities.User;
import to.gizmo.repositories.UserRepository;
import to.gizmo.repositories.WorkspaceRepository;

import javax.validation.Valid;

@Controller
public class GizmoController
{
    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Workspace> workspaces = workspaceRepository.findAll();

        model.addAttribute("workspaces", workspaces);

        return "index";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String registerProcess(@Valid User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login(Model model)
    {
        model.addAttribute("user", new User());

        return "login";
    }
}
