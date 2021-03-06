package acme.features.inventor.artifact;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Artifact;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorArtifactRepository extends AbstractRepository{

	@Query("select a from Artifact a where a.id = :id")
	Artifact findArtifactById(int id);
	
	@Query("select a from Artifact a where a.code = :code")
	Artifact findArtifactByCode(String code);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findInventorById(int id);
	
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);
	
	@Query("select a from Artifact a where a.inventor.id = :id")
	Collection<Artifact> findArtifactsByInventorId(int id);
	
	@Query("SELECT sc.acceptedCurrencies from Configuration sc")
	String findAllAcceptedCurrencies();
	
	@Query("SELECT sc.defaultCurrency from Configuration sc")
	String findDefaultCurrency();
	
	@Query("SELECT a from Artifact a where a.isPublic = :visibility")
	List<Artifact> findAllPublics(boolean visibility);
}
