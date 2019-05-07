package to.gizmo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import to.gizmo.entities.Board;

@Repository
public interface BoardRepository extends CrudRepository<Board, Integer>
{
}
