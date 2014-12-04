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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class SensorViewMap {
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
		this.googleMap =  new GoogleMap(new LatLon(39.833333, -98.583333), 4, "AIzaSyARW8kBrGU5sRt5rUQY10ggN_SU_jA9jKg");
		googleMap.setWidth("100%");
		setMapSize();
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
		this.sensors = sensors;
		this.button1 = new Button("Seismic Activity Map");
		this.button2 = new Button("Events View Map");
		this.button3 = new Button("Sensor View Map");
		buttons.addComponent(button1);		
		buttons.addComponent(button2);
		buttons.addComponent(button3);
		buttons.setWidth("100%");
		buttons.setHeight("100%");
		layout.addComponent(buttons);
		layout.addComponent(googleMap);
		layout.setMargin(true);
		layout.setSizeFull();
		layout.setHeightUndefined();

		int item;
		//System.out.println(sensors.getItemIds());
		Iterator<Object> o = sensors.getItemIds().iterator();
		while(o.hasNext()){
			item = (Integer) o.next();
			Item item2 = sensors.getItem(item);
			double latitude = (Double) item2.getItemProperty("latitude").getValue();
			double longitdue = (Double) item2.getItemProperty("longitude").getValue();
			GoogleMapMarker sensor = new GoogleMapMarker("sensor_" +(Integer)item2.getItemProperty("id").getValue(), new LatLon(latitude,longitdue),false);
			googleMap.addMarker(sensor);
			String name = (String) item2.getItemProperty("name").getValue();
			String depth = (String) item2.getItemProperty("depth").getValue();
			
			GoogleMapInfoWindow win = new GoogleMapInfoWindow ("Name:"+name+"\n"+"elevation:"+depth, sensor);
			OpenInfoWindowOnMarkerClickListener infoWindowOpener = new OpenInfoWindowOnMarkerClickListener(
	                googleMap, sensor, win);
	        googleMap.addMarkerClickListener(infoWindowOpener);

			
			
		}
	}
	public void setMapSize() {
		int height = UI.getCurrent().getPage().getBrowserWindowHeight() - 60;
		googleMap.setHeight("" + height + "px");
		return;
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
