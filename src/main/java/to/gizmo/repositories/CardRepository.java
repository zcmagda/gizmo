package to.gizmo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import to.gizmo.entities.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer>
{
}
