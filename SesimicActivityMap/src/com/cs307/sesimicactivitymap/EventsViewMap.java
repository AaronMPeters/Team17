package com.cs307.sesimicactivitymap;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.*;
public class EventsViewMap {
	VerticalLayout layout;
	HorizontalLayout buttons;
	GoogleMap googleMap;
	Button button1;
	Button button2;
	Button button3;
	public EventsViewMap (){
		this.layout = new VerticalLayout();
		this.buttons = new HorizontalLayout();
		this.googleMap =  new GoogleMap(new LatLon(40.424318, -86.912367), "AIzaSyARW8kBrGU5sRt5rUQY10ggN_SU_jA9jKg");
		googleMap.setWidth("100%");
		googleMap.setHeight("400px");
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
		this.button1 = new Button("SeimicActivityMap2");
		this.button2 = new Button("EventsViewMap2");
		this.button3 = new Button("SensorViewMap2");
		button1.setWidth("100%");
		button2.setWidth("100%");
		button3.setWidth("100%");
		buttons.addComponent(button1);		
		buttons.addComponent(button2);
		buttons.addComponent(button3);
		buttons.setWidth("100%");
		layout.addComponent(buttons);
		layout.addComponent(googleMap);
		layout.setMargin(true);
		layout.setSizeFull();
		layout.setHeightUndefined();
		
		double [] lat = {
				34.50399, 
				47.60657, 
				30.2240747, 
				42.652016, 
				41.139455, 
				35.084623, 
				41.878247, 
				37.7756, 
				46.003962, 
				40.807084, 
				44.519358, 
				21.30891, 

			
		};
		double [] lon = {
				-96.94965,
				-122.33180,
				-92.0198637,
				-73.755054,
				-104.821064,
				-106.651178,
				-87.629767,
				-122.4193,
				-112.534456,
				-96.682386,
				-88.019972,
				-157.85752
		};
		
		GoogleMapMarker [] seisEvents = new GoogleMapMarker[12];
		Random rn = new Random();
		for(int i = 0; i < 12; i++){
			int rating = rn.nextInt(10) + 1;
			seisEvents[i] = new GoogleMapMarker(Integer.toString(rating), new LatLon(lat[i], lon[i]), false);
			googleMap.addMarker(seisEvents[i]);
			
			//GoogleMapMarker sensor = new GoogleMapMarker("sensor_" + i, new LatLon(lats[i], longs[i]), false);
			//googleMap.addMarker(sensor);
			GoogleMapInfoWindow win = new GoogleMapInfoWindow ("Current Activity: ", seisEvents[i]);
			
			OpenInfoWindowOnMarkerClickListener infoWindowOpener = new OpenInfoWindowOnMarkerClickListener(
	                googleMap, seisEvents[i], win);
	        googleMap.addMarkerClickListener(infoWindowOpener);
		}
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