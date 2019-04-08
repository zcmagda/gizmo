package to.gizmo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class GizmoController
{

    @RequestMapping("/")
    public String greeting(Model model)
    {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        model.addAttribute("name", "name");
        model.addAttribute("currentYear", localDate.getYear());
        return "index";
    }

}
