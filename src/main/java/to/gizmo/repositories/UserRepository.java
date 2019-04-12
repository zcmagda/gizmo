package to.gizmo.repositories;

import org.springframework.data.repository.CrudRepository;
import to.gizmo.entities.User;

public interface UserRepository extends CrudRepository<User, Integer>
{
}
