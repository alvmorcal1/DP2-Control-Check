package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Chimpum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository{

	@Query("select c from Chimpum c where c.artifact.id =:masterId")
	Collection<Chimpum> findAllChimpumByArtifactId(int masterId);

	@Query("select c from Chimpum c where c.id =:id")
	Chimpum findChimpumById(int id);

	@Query("select c from Chimpum c where c.code=:code")
	Chimpum findChimpumByCode(String code);
	
	@Query("select sc.acceptedCurrencies from Configuration sc")
	String findAllAcceptedCurrencies();

}
