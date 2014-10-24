package com.cs307.sesimicactivitymap;

import java.util.Iterator;

import com.cs307.database.Seismic_Events;
import com.cs307.database.Sensor;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Item;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class SensorViewMap {
	//public static final String PERSISTENCE_UNIT = "SAM";
	private JPAContainer<?> sensors;
	VerticalLayout layout;
	HorizontalLayout buttons;
	GoogleMap googleMap;
	Button button1;
	Button button2;
	Button button3;
	public SensorViewMap (JPAContainer<?> sensors){
		this.layout = new VerticalLayout();
		this.buttons = new HorizontalLayout();
		this.googleMap =  new GoogleMap(new LatLon(40.424318, -86.912367), "AIzaSyARW8kBrGU5sRt5rUQY10ggN_SU_jA9jKg");
		googleMap.setWidth("100%");
		googleMap.setHeight("400px");
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
		this.sensors = sensors;
		this.button1 = new Button("Seimic Activity Map");
		this.button2 = new Button("Events View Map");
		this.button3 = new Button("Sensor View Map");
		//button1.setWidth("100%");
		//button2.setWidth("100%");
		//button3.setWidth("100%");
		buttons.addComponent(button1);		
		buttons.addComponent(button2);
		buttons.addComponent(button3);
		buttons.setWidth("100%");
		layout.addComponent(buttons);
		layout.addComponent(googleMap);
		layout.setMargin(true);
		layout.setSizeFull();
		layout.setHeightUndefined();
	//	sensors = JPAContainerFactory.make(Sensor.class, PERSISTENCE_UNIT);
		
		double[] lats = {	
				51.882,
				82.503,
				34.946,
				38.056,
				64.874,
				44.586,
				28.11,
				29.962,
				16.733,
				57.783,
				21.42,
				8.802,
				28.216,
				33.611,
				19.757,
				44.121,
				40.636,
				32.31,
				38.229,
				36.13
		};		
		double[] longs = {
				-176.684,
				-62.35,
				-106.457,
				-91.245,
				-147.862,
				-123.305,
				-81.433,
				-95.838,
				-169.529,
				-152.583,
				-158.011,
				167.613,
				-177.37,
				-116.456,
				-155.533,
				-104.036,
				-77.888,
				-110.785,
				-86.294,
				-87.83
		};
		int item;
		System.out.println(sensors.getItemIds());
		Iterator<Object> o = sensors.getItemIds().iterator();
		while(o.hasNext()){
			item = (Integer) o.next();
			Item item2 = sensors.getItem(item);
			double latitude = (Double) item2.getItemProperty("latitude").getValue();
			double longitdue = (Double) item2.getItemProperty("longitude").getValue();
			//System.out.println(latitude);
			GoogleMapMarker sensor = new GoogleMapMarker("sensor_" +(Integer)item2.getItemProperty("id").getValue(), new LatLon(latitude,longitdue),false);
			googleMap.addMarker(sensor);
			GoogleMapInfoWindow win = new GoogleMapInfoWindow ("Add a new infowindow", sensor);
			OpenInfoWindowOnMarkerClickListener infoWindowOpener = new OpenInfoWindowOnMarkerClickListener(
	                googleMap, sensor, win);
	        googleMap.addMarkerClickListener(infoWindowOpener);
			
			
			
		}
		/*
		for (int i = 0; i < lats.length; i++){
			GoogleMapMarker sensor = new GoogleMapMarker("sensor_" + i, new LatLon(lats[i], longs[i]), false);
			googleMap.addMarker(sensor);
			GoogleMapInfoWindow win = new GoogleMapInfoWindow ("Add a new infowindow", sensor);
			
			OpenInfoWindowOnMarkerClickListener infoWindowOpener = new OpenInfoWindowOnMarkerClickListener(
	                googleMap, sensor, win);
	        googleMap.addMarkerClickListener(infoWindowOpener);
			
		}	
		*/	
	}
	public VerticalLayout getLayout() {
		return layout;
	}
	public Button getButton1() {
		return this.button1;
	}
	public Button getButton2() {
		return this.button2;
	}
	public Button getButton3() {
		return this.button3;
	}
}
