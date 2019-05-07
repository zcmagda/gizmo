package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import to.gizmo.entities.User;
import to.gizmo.entities.Workspace;
import to.gizmo.repositories.UserRepository;
import to.gizmo.repositories.WorkspaceRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class WorkspaceController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    @PostMapping("/addWorkspace")
    public String addWorkspace(@Valid Workspace workspace, Model model)
    {
        Optional<Workspace> found = workspaceRepository.findByTitle(workspace.getTitle());
        if (found.isPresent()) {
            return "redirect:/";
        } else {
            Optional<User> user = userRepository.findById(1);
            if (!user.isPresent()) {
                return "redirect:/";
            }
            workspace.setUser(user.get());
            workspaceRepository.save(workspace);
            return "redirect:/";
        }
    }
}
