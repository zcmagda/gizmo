package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import to.gizmo.entities.Board;
import to.gizmo.entities.Workspace;
import to.gizmo.repositories.BoardRepository;
import to.gizmo.repositories.WorkspaceRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController
{
    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("create/{workspaceId:[0-9]+}")
    public String create(@PathVariable Integer workspaceId, Model model)
    {
        Board board = new Board();
        Optional<Workspace> optional = workspaceRepository.findById(workspaceId);
        if (!optional.isPresent()) {
            throw new RuntimeException("Workspace not found");
        }
        board.setWorkspace(optional.get());
        model.addAttribute("board", board);

        return "board/create";
    }

    @PostMapping("create/{workspaceId:[0-9]+}")
    public String createProcess(@PathVariable Integer workspaceId, @Valid Board board)
    {
        Optional<Workspace> optional = workspaceRepository.findById(workspaceId);
        if (!optional.isPresent()) {
            throw new RuntimeException("Workspace not found");
        }
        board.setWorkspace(optional.get());
        boardRepository.save(board);

        return "redirect:/workspace/read/" + workspaceId;
    }

    @GetMapping("update/{id:[0-9]+}")
    public String update(Model model, @PathVariable Integer id)
    {
        Optional<Board> optional = boardRepository.findById(id);
        if (!optional.isPresent()) {
            return "404";
        }
        model.addAttribute("board", optional.get());

        return "board/update";
    }

    @PostMapping("update/{id:[0-9]+}")
    public String updateProcess(@PathVariable Integer id, @Valid Board board)
    {
        board.setId(id);
        board.setWorkspace(getDefaultWorkspace());
        boardRepository.save(board);

        return "redirect:/workspace/read/" + board.getWorkspace().getId();
    }

    @PostMapping("delete/{id:[0-9]+}")
    public String deleteProcess(@PathVariable Integer id)
    {
        Optional<Board> optional = boardRepository.findById(id);
        if (!optional.isPresent()) {
            return "404";
        }
        Board board = optional.get();
        Integer workspaceId = board.getWorkspace().getId();
        boardRepository.delete(board);

        return "redirect:/workspace/read/" + workspaceId;
    }

    private Workspace getDefaultWorkspace()
    {
        Optional<Workspace> workspace = workspaceRepository.findById(1);
        if (!workspace.isPresent()) {
            throw new RuntimeException("Default workspace not found");
        }

        return workspace.get();
    }
}
