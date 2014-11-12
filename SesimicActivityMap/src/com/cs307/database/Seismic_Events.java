package com.cs307.database;

import javax.persistence.*;

@Entity
@Table(name = "Seismic_Events")
public class Seismic_Events {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private double latitude;
	private double longitude;
	private String time_stamp;
	private Double depth;
	private Double intensity;
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
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	public Double getDepth() {
		return depth;
	}
	public void setDepth(Double depth) {
		this.depth = depth;
	}
	public Double getIntensity() {
		return intensity;
	}
	public void setIntensity(Double intensity) {
		this.intensity = intensity;
	}
	
	
}
