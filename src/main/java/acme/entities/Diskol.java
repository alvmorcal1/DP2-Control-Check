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
public class Diskol extends AbstractEntity{

	protected static final long serialVersionUID = 1L;
	
	@Pattern(regexp = "^\\w{2,4}:([0-9]{2}):(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$")
	@NotBlank
	protected String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationMoment;	
	
	@Length(max=100)
	@NotBlank
	protected String theme;
	
	@Length(max=255)
	@NotBlank
	protected String summary;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull	
	protected Date finishDate;
	
	@Valid
	@NotNull
	protected Money quota;
	
	@URL
	protected String additionalInfo;
	
	//Relacion
	
	@Valid
	@ManyToOne(optional = false)
	protected Artifact artifact;
}
