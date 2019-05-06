package to.gizmo.repositories;

import org.springframework.data.repository.CrudRepository;
import to.gizmo.entities.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<Board, Integer>
{
    List<Board> findAll();

    Optional<Board> findByTitle(String title);
}
