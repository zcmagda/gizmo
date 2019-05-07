package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import to.gizmo.entities.User;
import to.gizmo.entities.Workspace;
import to.gizmo.repositories.UserRepository;
import to.gizmo.repositories.WorkspaceRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/workspace")
public class WorkspaceController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    @GetMapping("create")
    public String create(Model model)
    {
        Workspace workspace = new Workspace();
        workspace.setUser(getDefaultUser());
        model.addAttribute("workspace", workspace);

        return "workspace/create";
    }

    @PostMapping("create")
    public String createProcess(@Valid Workspace workspace)
    {
        workspace.setUser(getDefaultUser());
        workspaceRepository.save(workspace);

        return "redirect:/workspace/read/" + workspace.getId();
    }

    @GetMapping("read/{id:[0-9]+}")
    public String read(Model model, @PathVariable Integer id)
    {
        Optional<Workspace> optional = workspaceRepository.findById(id);
        if (!optional.isPresent()) {
            return "404";
        }
        model.addAttribute("workspace", optional.get());

        return "workspace/read";
    }

    @GetMapping("update/{id:[0-9]+}")
    public String update(Model model, @PathVariable Integer id)
    {
        Optional<Workspace> optional = workspaceRepository.findById(id);
        if (!optional.isPresent()) {
            return "404";
        }
        model.addAttribute("workspace", optional.get());

        return "workspace/update";
    }

    @PostMapping("update/{id:[0-9]+}")
    public String updateProcess(@PathVariable Integer id, @Valid Workspace workspace)
    {
        workspace.setId(id);
        workspace.setUser(getDefaultUser());
        workspaceRepository.save(workspace);

        return "redirect:/workspace/read/" + workspace.getId();
    }

    @PostMapping("delete/{id:[0-9]+}")
    public String deleteProcess(@PathVariable Integer id)
    {
        Optional<Workspace> optional = workspaceRepository.findById(id);
        if (!optional.isPresent()) {
            return "404";
        }
        Workspace workspace = optional.get();
        workspaceRepository.delete(workspace);

        return "redirect:/";
    }

    private User getDefaultUser()
    {
        Optional<User> user = userRepository.findById(1);
        if (!user.isPresent()) {
            throw new RuntimeException("Default user not found");
        }

        return user.get();
    }
}
