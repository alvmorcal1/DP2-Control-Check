package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Diskol;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Diskol>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Diskol> request) {
		final int id = request.getModel().getInteger("id");
		
		final Diskol diskol = this.repository.findChimpumById(id);
		
		return (diskol!=null) &&
			(request.isPrincipal(diskol.getArtifact().getInventor()));
	}

	@Override
	public Diskol findOne(final Request<Diskol> request) {
		return this.repository.findChimpumById(request.getModel().getInteger("id"));
	}

	@Override
	public void unbind(final Request<Diskol> request, final Diskol entity, final Model model) {
		request.unbind(entity, model, "code", "creationMoment", "title", "description", "startDate","finishDate","budget","link");
		
	}

}
