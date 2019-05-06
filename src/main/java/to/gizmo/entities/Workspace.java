package to.gizmo.entities;

import javax.persistence.*;

@Entity
public class Workspace
{
    @Id
    @Column(name = "workspace_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 100, unique = true)
    private String title;

    @Override
    public String toString()
    {
        return String.format("Workspace[id=%d, title='%s']", id, title);
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
}
