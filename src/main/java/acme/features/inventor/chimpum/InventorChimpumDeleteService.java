package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorChimpumDeleteService implements AbstractDeleteService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		boolean result;
		int id;
		Chimpum chimpum;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		result = chimpum!=null && request.isPrincipal(chimpum.getArtifact().getInventor());
		
		return result;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		request.bind(entity, errors, "code","title","description","startDate","finishDate","budget","link");
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		request.unbind(entity, model, "code","creationMoment","title","description","startDate","finishDate","budget","link");
		
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		Chimpum result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findChimpumById(id);
		return result;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Chimpum> request, final Chimpum entity) {
		this.repository.delete(entity);
		
	}
	

}
