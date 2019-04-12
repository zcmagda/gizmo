package to.gizmo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import to.gizmo.entities.User;

@SpringBootApplication
public class Application implements CommandLineRunner
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception
    {
        log.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE users(email VARCHAR(255), password VARCHAR(255))");
        jdbcTemplate.update("INSERT INTO users(email, password) VALUES ('user', 'password')");

        log.info("Querying for all users");
        jdbcTemplate.query("SELECT * FROM users",
            (rs, rowNum) -> new User(rs.getString("email"), rs.getString("password"))
        ).forEach(user -> log.info(user.getEmail()));
    }

}
