package to.gizmo.repositories;

import org.springframework.data.repository.CrudRepository;
import to.gizmo.entities.Workspace;

import java.util.List;
import java.util.Optional;

public interface WorkspaceRepository extends CrudRepository<Workspace, Integer>
{
    List<Workspace> findAll();

    Optional<Workspace> findByTitle(String title);
}
