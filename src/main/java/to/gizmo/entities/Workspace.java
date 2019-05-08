package to.gizmo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@Entity
public class Workspace
{
    @Id
    @Column(name = "workspace_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 100, unique = true)
    private String title;

    @OneToMany(mappedBy = "workspace", fetch = FetchType.EAGER)
    private Collection<Board> boards;

    @Override
    public String toString()
    {
        List<String> workspaceBoards = new ArrayList<>();
        for (Board board : getBoards()) {
            workspaceBoards.add(board.toString());
        }

        return String.format("Workspace[id=%d, title='%s', boards=[%s]]", id, title, String.join(", ", workspaceBoards));
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Collection<Board> getBoards()
    {
        return new LinkedHashSet<>(boards);
    }

    public void setBoards(List<Board> boards)
    {
        this.boards = boards;
    }
}
