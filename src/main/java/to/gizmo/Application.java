package to.gizmo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import to.gizmo.entities.Workspace;
import to.gizmo.entities.User;
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
    private PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode("password"));
        userRepository.save(user);

        log.info("Inserting default workspaces");
        for (int i = 1; i < 5; i++) {
            Workspace workspace = new Workspace();
            workspace.setId(i);
            workspace.setUser(user);
            workspace.setTitle("workspace " + i);
            workspaceRepository.save(workspace);
        }

        log.info("Fetching all users");
        Iterable<User> users = userRepository.findAll();

        users.forEach(u -> log.info(u.toString()));
    }

}
