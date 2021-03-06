package acme.features.inventor.patronageReport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageReportController extends AbstractController<Inventor, PatronageReport>{

	@Autowired
	protected InventorPatronageReportListService listService;
	
	@Autowired
	protected InventorPatronageReportShowService showService;
	
	
	@Autowired
	protected InventorPatronageReportCreateService createService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);	
		super.addCommand("create", this.createService);
	}
}
