package petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.model.PetType;
import petclinic.services.CrudService;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
