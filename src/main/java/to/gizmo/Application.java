package to.gizmo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings)
    {
        log.info("Seeding database");
        for (int u = 1; u <= 1; u++) {
            User user = new User();
            user.setId(u);
            user.setUsername("user " + u);
            user.setPassword(passwordEncoder.encode("password"));
            userRepository.save(user);
            for (int w = 1; w <= 3; w++) {
                Workspace workspace = new Workspace();
                workspace.setUser(user);
                workspace.setTitle("workspace " + w);
                workspaceRepository.save(workspace);
                for (int b = 1; b <= 3; b++) {
                    Board board = new Board();
                    board.setWorkspace(workspace);
                    board.setTitle("board " + b);
                    board.setPriority(b);
                    boardRepository.save(board);
                    for (int c = 1; c <= 3; c++) {
                        Card card = new Card();
                        card.setBoard(board);
                        card.setTitle("card " + c);
                        card.setContent("content " + c);
                        card.setPriority(c);
                        cardRepository.save(card);
                    }
                }
            }
        }

        log.info("Fetching users");
        Iterable<User> users = userRepository.findAll();

        users.forEach(u -> log.info(u.toString()));
    }

}
