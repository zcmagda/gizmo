package to.gizmo.repositories;

import org.springframework.data.repository.CrudRepository;
import to.gizmo.entities.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>
{
    List<User> findAll();
    List<User> findByEmail(String email);
}
