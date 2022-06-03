package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.ArtifactType;
import acme.entities.Diskol;
import acme.features.inventor.artifact.InventorArtifactRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChimpumListService implements AbstractListService<Inventor, Diskol>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected InventorArtifactRepository artifactRepository;
	
	@Override
	public boolean authorise(final Request<Diskol> request) {
		
		final boolean result;
		final int masterId = request.getModel().getInteger("masterId");
		
		final Artifact artifact = this.artifactRepository.findArtifactById(masterId);
		result = (artifact!=null &&
			request.isPrincipal(artifact.getInventor()) &&
			artifact.getArtifactType().equals(ArtifactType.TOOL));
		
		return result;
	}

	@Override
	public Collection<Diskol> findMany(final Request<Diskol> request) {
		final int masterId = request.getModel().getInteger("masterId");
		return this.repository.findAllChimpumByArtifactId(masterId);
	}

	@Override
	public void unbind(final Request<Diskol> request, final Diskol entity, final Model model) {
		request.unbind(entity, model, "code", "creationMoment","title","description","budget");
		
	}
	
	@Override
	public void unbind(final Request<Diskol> request, final Collection<Diskol> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
	}

	
}
