package com.cs307.database;

import javax.persistence.*;

@Entity
@Table(name = "Sensor")
public class Sensor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private double latitude;
	private double longitude;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String year_added;
	private String depth;
	private String num_events;
	private String environment_type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getYear_added() {
		return year_added;
	}
	public void setYear_added(String year_added) {
		this.year_added = year_added;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getNum_events() {
		return num_events;
	}
	public void setNum_events(String num_events) {
		this.num_events = num_events;
	}
	public String getEnvironment_type() {
		return environment_type;
	}
	public void setEnvironment_type(String environment_type) {
		this.environment_type = environment_type;
	}
}
