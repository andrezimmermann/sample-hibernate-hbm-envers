package sample.data.jpa.domain;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Audited
public class Foo {

	private Integer key;

	private String description;

	private String unimportantDescription;

	public String getDescription() {
		return description;
	}

	public Integer getKey() {
		return key;
	}

	@NotAudited
	public String getUnimportantDescription() {
		return unimportantDescription;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public void setUnimportantDescription(String unimportantDescription) {
		this.unimportantDescription = unimportantDescription;
	}
}
