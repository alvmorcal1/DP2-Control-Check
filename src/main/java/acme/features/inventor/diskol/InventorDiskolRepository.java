package acme.features.inventor.diskol;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Diskol;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorDiskolRepository extends AbstractRepository{

	@Query("select d from Diskol d where d.artifact.id =:masterId")
	Collection<Diskol> findAllDiskolByArtifactId(int masterId);

	@Query("select d from Diskol d where d.id =:id")
	Diskol findDiskolById(int id);

	@Query("select d from Diskol d where d.code=:code")
	Diskol findDiskolByCode(String code);
	
	@Query("select sc.acceptedCurrencies from Configuration sc")
	String findAllAcceptedCurrencies();

}
