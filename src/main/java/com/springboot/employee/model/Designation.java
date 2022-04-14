package com.springboot.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document(collection = "Designation")
public class Designation {

	private int dsg_id;

	@NonNull
	private String designationName;

	public int getDsg_id() {
		return dsg_id;
	}

	public void setDsg_id(int dsg_id) {
		this.dsg_id = dsg_id;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

}
