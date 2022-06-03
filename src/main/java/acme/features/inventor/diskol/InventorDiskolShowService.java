package acme.features.inventor.diskol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Diskol;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorDiskolShowService implements AbstractShowService<Inventor, Diskol>{

	@Autowired
	protected InventorDiskolRepository repository;
	
	@Override
	public boolean authorise(final Request<Diskol> request) {
		final int id = request.getModel().getInteger("id");
		
		final Diskol diskol = this.repository.findDiskolById(id);
		
		return (diskol!=null) &&
			(request.isPrincipal(diskol.getArtifact().getInventor()));
	}

	@Override
	public Diskol findOne(final Request<Diskol> request) {
		return this.repository.findDiskolById(request.getModel().getInteger("id"));
	}

	@Override
	public void unbind(final Request<Diskol> request, final Diskol entity, final Model model) {
		request.unbind(entity, model, "code", "creationMoment", "theme", "summary", "startDate","finishDate","quota","additionalInfo");
		
	}

}
