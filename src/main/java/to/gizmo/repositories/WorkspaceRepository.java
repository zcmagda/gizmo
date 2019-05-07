package to.gizmo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import to.gizmo.entities.Workspace;

import java.util.Optional;

@Repository
public interface WorkspaceRepository extends CrudRepository<Workspace, Integer>
{
}
