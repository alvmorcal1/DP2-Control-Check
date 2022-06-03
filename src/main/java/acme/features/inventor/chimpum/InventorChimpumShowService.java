package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		final int id = request.getModel().getInteger("id");
		
		final Chimpum chimpum = this.repository.findChimpumById(id);
		
		return (chimpum!=null) &&
			(request.isPrincipal(chimpum.getArtifact().getInventor()));
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		return this.repository.findChimpumById(request.getModel().getInteger("id"));
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		request.unbind(entity, model, "code", "creationMoment", "title", "description", "startDate","finishDate","budget","link");
		
	}

}
