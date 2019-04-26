package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import to.gizmo.entities.Board;
import to.gizmo.repositories.BoardRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class BoardController {
    @Autowired
    BoardRepository boardRepository;

    @PostMapping("/addBoard")
    public String addBoard(@Valid Board board, Model model) {

        Optional<Board> found = boardRepository.findByName(board.getName());
        if (found.isPresent()) {
            return "redirect:/";
        } else {
            boardRepository.save(board);
            return "redirect:/";
        }

    }
}
