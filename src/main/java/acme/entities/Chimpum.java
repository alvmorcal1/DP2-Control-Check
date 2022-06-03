package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chimpum extends AbstractEntity{

	protected static final long serialVersionUID = 1L;
	
	@Pattern(regexp = "[A-Z]{3}-([0-9]{2})/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])")
	@NotBlank
	protected String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationMoment;	
	
	@Length(max=100)
	@NotBlank
	protected String title;
	
	@Length(max=255)
	@NotBlank
	protected String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull	
	protected Date finishDate;
	
	@Valid
	@NotNull
	protected Money budget;
	
	@URL
	protected String link;
	
	//Relacion
	
	@Valid
	@ManyToOne(optional = true)
	protected Artifact artifact;
}
