package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import to.gizmo.entities.User;
import to.gizmo.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Controller
public class GizmoController
{
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model)
    {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        model.addAttribute("user", new User());
        model.addAttribute("name", "name");
        model.addAttribute("currentYear", localDate.getYear());

        HttpSession session = request.getSession();
        String welcomeMessage = "Hello stranger";
        if (null != session.getAttribute("userId")) {
            Optional<User> found = userRepository.findById((Integer) session.getAttribute("userId"));
            if (found.isPresent()) {
                User user = found.get();
                welcomeMessage = "Hello my friend: " + user.getEmail();
            }
        }
        model.addAttribute("welcomeMessage", welcomeMessage);

        return "index";
    }
}
