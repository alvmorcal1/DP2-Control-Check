package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Diskol;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository{

	@Query("select c from Diskol c where c.artifact.id =:masterId")
	Collection<Diskol> findAllChimpumByArtifactId(int masterId);

	@Query("select c from Diskol c where c.id =:id")
	Diskol findChimpumById(int id);

	@Query("select c from Diskol c where c.code=:code")
	Diskol findChimpumByCode(String code);
	
	@Query("select sc.acceptedCurrencies from Configuration sc")
	String findAllAcceptedCurrencies();

}
