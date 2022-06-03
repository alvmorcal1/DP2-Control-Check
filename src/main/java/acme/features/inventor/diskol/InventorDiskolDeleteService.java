package acme.features.inventor.diskol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Diskol;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorDiskolDeleteService implements AbstractDeleteService<Inventor, Diskol>{

	@Autowired
	protected InventorDiskolRepository repository;
	
	@Override
	public boolean authorise(final Request<Diskol> request) {
		boolean result;
		int id;
		Diskol diskol;
		
		id = request.getModel().getInteger("id");
		diskol = this.repository.findDiskolById(id);
		result = diskol!=null && request.isPrincipal(diskol.getArtifact().getInventor());
		
		return result;
	}

	@Override
	public void bind(final Request<Diskol> request, final Diskol entity, final Errors errors) {
		request.bind(entity, errors, "code","theme","summary","startDate","finishDate","quota","additionalInfo");
		
	}

	@Override
	public void unbind(final Request<Diskol> request, final Diskol entity, final Model model) {
		request.unbind(entity, model, "code","creationMoment","theme","summary","startDate","finishDate","quota","additionalInfo");
		
	}

	@Override
	public Diskol findOne(final Request<Diskol> request) {
		Diskol result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findDiskolById(id);
		return result;
	}

	@Override
	public void validate(final Request<Diskol> request, final Diskol entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Diskol> request, final Diskol entity) {
		this.repository.delete(entity);
		
	}
	

}
