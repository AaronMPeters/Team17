package com.cs307.database;

import javax.persistence.*;

@Entity
@Table(name = "Sensor")
public class Sensor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String location;
	private String time_stamp;
	private String depth;
	private String intensity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

}
