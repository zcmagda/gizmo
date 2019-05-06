package to.gizmo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import to.gizmo.entities.Board;
import to.gizmo.entities.Card;
import to.gizmo.entities.Workspace;
import to.gizmo.entities.User;
import to.gizmo.repositories.BoardRepository;
import to.gizmo.repositories.CardRepository;
import to.gizmo.repositories.WorkspaceRepository;
import to.gizmo.repositories.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CardRepository cardRepository;

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings)
    {
        log.info("Inserting default user");
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        userRepository.save(user);

        log.info("Inserting default workspaces");
        for (int i = 1; i <= 3; i++) {
            Workspace workspace = new Workspace();
            workspace.setUser(user);
            workspace.setTitle("workspace " + i);
            workspaceRepository.save(workspace);
            for (int j = 1; j <= 3; j++) {
                Board board = new Board();
                board.setWorkspace(workspace);
                board.setTitle("board " + j);
                board.setPriority(j);
                boardRepository.save(board);
                for (int k = 1; k <= 3; k++) {
                    Card card = new Card();
                    card.setBoard(board);
                    card.setTitle("card " + k);
                    card.setContent("content " + k);
                    card.setPriority(k);
                    cardRepository.save(card);
                }
            }
        }

        log.info("Fetching all users");
        Iterable<User> users = userRepository.findAll();

        users.forEach(u -> log.info(u.toString()));
    }

}
