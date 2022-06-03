package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChimpumController extends AbstractController<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumListService listService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
	}
}
