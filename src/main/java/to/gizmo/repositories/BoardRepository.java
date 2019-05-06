package to.gizmo.repositories;

import org.springframework.data.repository.CrudRepository;
import to.gizmo.entities.Board;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Integer>
{
    List<Board> findAll();
}
