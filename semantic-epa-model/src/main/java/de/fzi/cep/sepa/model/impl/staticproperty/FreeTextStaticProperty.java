package de.fzi.cep.sepa.model.impl.staticproperty;

import javax.persistence.Entity;

import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;

@Namespaces({"sepa", "http://sepa.event-processing.org/sepa#",
	 "dc",   "http://purl.org/dc/terms/"})
@RdfsClass("sepa:FreeTextStaticProperty")
@Entity
public class FreeTextStaticProperty extends StaticProperty {

	private static final long serialVersionUID = 5029422126802713205L;

	@RdfProperty("sepa:hasValue")
	String value;
	
	@RdfProperty("sepa:hasType")
	String type;
	
	public FreeTextStaticProperty() {
		super();
	}
	
	public FreeTextStaticProperty(FreeTextStaticProperty other) {
		super(other);
		this.type = other.getType();
		this.value = other.getValue();
	}
	
	public FreeTextStaticProperty(String name, String description)
	{
		super(name, description);
	}
	
	public FreeTextStaticProperty(String name, String description, String type)
	{
		super(name, description);
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void accept(StaticPropertyVisitor visitor) {
		visitor.visit(this);
	}	
	
}
