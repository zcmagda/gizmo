package to.gizmo.entities;

import javax.persistence.*;

@Entity
public class Card
{
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    private String title;

    private String content;

    private Integer priority;

    @Override
    public String toString()
    {
        return String.format("Card[id=%d, title='%s', content='%s', priority=%d]", id, title, content, priority);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }
}
