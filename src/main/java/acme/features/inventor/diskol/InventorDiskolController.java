package acme.features.inventor.diskol;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Diskol;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorDiskolController extends AbstractController<Inventor, Diskol>{

	@Autowired
	protected InventorDiskolListService listService;
	
	@Autowired
	protected InventorDiskolShowService showService;
	
	@Autowired
	protected InventorDiskolCreateService createService;
	
	@Autowired
	protected InventorDiskolUpdateService updateService;
	
	@Autowired
	protected InventorDiskolDeleteService deleteService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
}
