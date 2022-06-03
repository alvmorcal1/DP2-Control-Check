package acme.features.inventor.chimpum;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Diskol;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, Diskol>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Diskol> request) {
		boolean result;
		int id;
		Diskol diskol;
		
		id = request.getModel().getInteger("id");
		diskol = this.repository.findChimpumById(id);
		result = diskol!=null && request.isPrincipal(diskol.getArtifact().getInventor()) ;
		
		return result;
	}

	@Override
	public void bind(final Request<Diskol> request, final Diskol entity, final Errors errors) {
		request.bind(entity, errors, "code","title","description","startDate","finishDate","budget","link");
		
	}

	@Override
	public void unbind(final Request<Diskol> request, final Diskol entity, final Model model) {
		request.unbind(entity, model, "code","creationMoment","title","description","startDate","finishDate","budget","link");
		
	}

	@Override
	public Diskol findOne(final Request<Diskol> request) {
		Diskol result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findChimpumById(id);
		return result;
	}

	@Override
	public void validate(final Request<Diskol> request, final Diskol entity, final Errors errors) {
		if(!errors.hasErrors("code")) {
			
			final Diskol diskol = this.repository.findChimpumByCode(entity.getCode());
			errors.state(request, diskol==null || diskol.getId() == entity.getId(), "code", "inventor.chimpum.form.error.duplicated_code");
			final String code = entity.getCode();
			
			final Date date = entity.getCreationMoment();
			final SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
			final String dateFormated = formatter.format(date);			
			
			final String dateToFormat = code.split("-")[1];
			
			errors.state(request, dateFormated.equals(dateToFormat),"code", "inventor.chimpum.form.error.invalid_date");
		}
		
		if(!errors.hasErrors("startDate")) {
			final Calendar calendar = new GregorianCalendar();
			final Date minimunDate;
			
			calendar.setTime(entity.getCreationMoment());
			calendar.add(Calendar.MONTH, 1);
			
			minimunDate = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimunDate), "startDate", "inventor.chimpum.form.error.start_date");
		}
		
		if(!errors.hasErrors("budget")) {
			final String entityCurrency;
			final Double amount;
			final String[] acceptedCurrencies;
			final List<String> currencies;
			
			entityCurrency = entity.getQuota().getCurrency();
			amount = entity.getQuota().getAmount();
			errors.state(request, amount > 0, "budget", "inventor.artifact.form.error.negative");
			acceptedCurrencies=this.repository.findAllAcceptedCurrencies().split(",");
			
			currencies = Arrays.asList(acceptedCurrencies);
			errors.state(request, currencies.contains(entityCurrency) , "budget", "inventor.chimpum.form.error.no_accepted_currency");
		}
		
		if(!errors.hasErrors("finishDate")) {
			Calendar calendar;
			Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getStartDate());
			
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			minimunDate = calendar.getTime();
			
			errors.state(request, entity.getFinishDate().equals(minimunDate), "finishDate", "inventor.chimpum.form.error.finishDate");
		}
		
	}

	@Override
	public void update(final Request<Diskol> request, final Diskol entity) {
		this.repository.save(entity);
		
	}

}
