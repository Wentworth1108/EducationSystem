package cn.gcs.classinfo.entity;

import java.io.Serializable;

public class Elective implements Serializable {
	
	private ElectiveId id;

	public Elective() {
	}

	public Elective(ElectiveId id) {
		this.id = id;
	}

	public ElectiveId getId() {
		return id;
	}

	public void setId(ElectiveId id) {
		this.id = id;
	}
	
	
}
