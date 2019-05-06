package to.gizmo.repositories;

import org.springframework.data.repository.CrudRepository;
import to.gizmo.entities.Card;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer>
{
    List<Card> findAll();
}
