package to.gizmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import to.gizmo.entities.Board;
import to.gizmo.entities.Card;
import to.gizmo.repositories.BoardRepository;
import to.gizmo.repositories.CardRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/card")
public class CardController
{
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CardRepository cardRepository;

    @GetMapping("create/{boardId:[0-9]+}")
    public String create(@PathVariable Integer boardId, Model model)
    {
        Card card = new Card();
        Optional<Board> optional = boardRepository.findById(boardId);
        if (!optional.isPresent()) {
            throw new RuntimeException("Workspace not found");
        }
        card.setBoard(optional.get());
        model.addAttribute("card", card);

        return "card/create";
    }

    @PostMapping("create/{boardId:[0-9]+}")
    public String createProcess(@PathVariable Integer boardId, @Valid Card card)
    {
        Optional<Board> optional = boardRepository.findById(boardId);
        if (!optional.isPresent()) {
            throw new RuntimeException("Workspace not found");
        }
        card.setBoard(optional.get());
        cardRepository.save(card);

        return "redirect:/workspace/read/" + card.getBoard().getWorkspace().getId();
    }

    @GetMapping("update/{id:[0-9]+}")
    public String update(Model model, @PathVariable Integer id)
    {
        Optional<Card> optional = cardRepository.findById(id);
        if (!optional.isPresent()) {
            return "404";
        }
        model.addAttribute("card", optional.get());

        return "card/update";
    }

    @PostMapping("update/{id:[0-9]+}")
    public String updateProcess(@PathVariable Integer id, @Valid Card card)
    {
        card.setId(id);
        card.setBoard(getDefaultBoard());
        cardRepository.save(card);

        return "redirect:/workspace/read/" + card.getBoard().getWorkspace().getId();
    }

    @PostMapping("delete/{id:[0-9]+}")
    public String deleteProcess(@PathVariable Integer id)
    {
        Optional<Card> optional = cardRepository.findById(id);
        if (!optional.isPresent()) {
            return "404";
        }
        Card card = optional.get();
        Integer workspaceId = card.getBoard().getWorkspace().getId();
        cardRepository.delete(card);

        return "redirect:/workspace/read/" + workspaceId;
    }

    private Board getDefaultBoard()
    {
        Optional<Board> board = boardRepository.findById(1);
        if (!board.isPresent()) {
            throw new RuntimeException("Default board not found");
        }

        return board.get();
    }
}
