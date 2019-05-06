package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import to.gizmo.entities.Workspace;
import to.gizmo.entities.User;
import to.gizmo.repositories.WorkspaceRepository;
import to.gizmo.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class GizmoController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        model.addAttribute("currentYear", localDate.getYear());

        HttpSession session = request.getSession();
        String welcomeMessage = "Hello stranger";
        if (null != session.getAttribute("userId")) {
            Optional<User> found = userRepository.findById((Integer) session.getAttribute("userId"));
            if (found.isPresent()) {
                User user = found.get();
                welcomeMessage = "Hello " + user.getUsername() + ", my friend";
                model.addAttribute("user", user);
            }
        }
        model.addAttribute("welcomeMessage", welcomeMessage);

        //get workspaces
        List<Workspace> workspaces = workspaceRepository.findAll();
        if(!workspaces.isEmpty()) {
            model.addAttribute("workspaces", workspaces);
        }
        model.addAttribute("workspace", new Workspace());

        return "index";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (null == session.getAttribute("userId")) {
            model.addAttribute("user", new User());
        } else {
            return "redirect:/";
        }
        return "register";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String error, Model model) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        HttpSession session = request.getSession();
        if (null == session.getAttribute("userId")) {
            model.addAttribute("user", new User());
        } else {
            return "redirect:/";
        }
        model.addAttribute("firstCheck", "dddd");
        return "login";
    }

    @RequestMapping("/secured")
    public String secured(HttpServletRequest request, Model model) {
        model.addAttribute("welcomeMessage", "Hello in secured");

        return "index";
    }
}
