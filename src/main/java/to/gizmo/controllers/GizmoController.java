package to.gizmo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import to.gizmo.entities.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class GizmoController
{

    @RequestMapping("/")
    public String index(Model model)
    {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        model.addAttribute("user", new User());
        model.addAttribute("name", "name");
        model.addAttribute("currentYear", localDate.getYear());
        return "index";
    }

}
