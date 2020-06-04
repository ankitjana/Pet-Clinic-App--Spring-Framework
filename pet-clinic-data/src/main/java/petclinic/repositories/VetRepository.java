package petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.model.Vet;
import petclinic.services.CrudService;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
