package acme.features.inventor.chimpum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Artifact;
import acme.entities.ArtifactType;
import acme.entities.Diskol;
import acme.features.inventor.artifact.InventorArtifactRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Diskol>{

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
	public void bind(final Request<Diskol> request, final Diskol entity, final Errors errors) {
		request.bind(entity, errors, "code","title","description","startDate","finishDate","budget","link");
		
	}

	@Override
	public void unbind(final Request<Diskol> request, final Diskol entity, final Model model) {
		request.unbind(entity, model, "code","title","description","startDate","finishDate","budget","link");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
	}

	@Override
	public Diskol instantiate(final Request<Diskol> request) {
		final Diskol diskol = new Diskol();
		
		final int masterId = request.getModel().getInteger("masterId");
		diskol.setArtifact(this.artifactRepository.findArtifactById(masterId));
		
		diskol.setCreationMoment(new Date());
		return diskol;
	}

	@Override
	public void validate(final Request<Diskol> request, final Diskol entity, final Errors errors) {
		if(!errors.hasErrors("code")) {
			
			final Diskol diskol = this.repository.findChimpumByCode(entity.getCode());
			errors.state(request, diskol==null || diskol.getId() == entity.getId(), "code", "inventor.chimpum.form.error.duplicated_code");
			final String code = entity.getCode();
			
			final LocalDate dateObj = LocalDate.now();
	        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
			final String comparator = dateObj.format(formatter);
			final String date = code.split("-")[1];
			
			errors.state(request, date.equals(comparator),"code", "inventor.chimpum.form.error.invalid_date");
		}
		
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			final Date minimunDate;
			calendar = new GregorianCalendar();
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
	public void create(final Request<Diskol> request, final Diskol entity) {
		this.repository.save(entity);
		
	}

	
}
