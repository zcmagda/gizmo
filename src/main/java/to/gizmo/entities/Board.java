package to.gizmo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board
{
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "workspace_id", nullable = false)
    private Workspace workspace;

    private String title;

    private Integer priority;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<Card> cards;

    @Override
    public String toString()
    {
        List<String> boardCards = new ArrayList<>();
        for (Card card : cards) {
            boardCards.add(card.toString());
        }

        return String.format("Board[id=%d, title='%s', priority=%d, cards=[%s]]", id, title, priority, String.join(", ", boardCards));
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Workspace getWorkspace()
    {
        return workspace;
    }

    public void setWorkspace(Workspace workspace)
    {
        this.workspace = workspace;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public List<Card> getCards()
    {
        return cards;
    }

    public void setCards(List<Card> cards)
    {
        this.cards = cards;
    }
}
