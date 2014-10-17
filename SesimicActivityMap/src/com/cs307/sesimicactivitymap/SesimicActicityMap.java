package com.cs307.sesimicactivitymap;

import java.util.ArrayList;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolygon;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class SesimicActicityMap {
	VerticalLayout layout;
	HorizontalLayout buttons;
	GoogleMap googleMap;
	Button button1;
	Button button2;
	Button button3;
	ArrayList<GoogleMapPolygon> polygons = new ArrayList<GoogleMapPolygon>();
	private int polyCount = 0;
	
	//Create a generic colored polygon on the map
	//around a center point
	public void makePoly(double lat, double lon, double mag){
		polyCount++;
		double offset = mag*.4 + .5; //scaled for aesthetics <3
		ArrayList<LatLon> points = new ArrayList<LatLon>();
		points.add(new LatLon(lat, lon-offset));
		points.add(new LatLon(lat+offset, lon));
		points.add(new LatLon(lat, lon+offset));
		points.add(new LatLon(lat-offset, lon));
		points.add(new LatLon(lat, lon-offset));
		GoogleMapPolygon poly = new GoogleMapPolygon(points);
		if(mag<=0){
			poly.setFillColor("#0000FF");
			poly.setStrokeColor("#0000FF");
		} else if(mag<=3){
			poly.setFillColor("#00FF00");
			poly.setStrokeColor("#00FF00");
		} else if(mag<=7){
			poly.setFillColor("#FFFF00");
			poly.setStrokeColor("#FFFF00");
		} else{
			poly.setFillColor("#FF0000");
			poly.setStrokeColor("#FF0000");
		}
		poly.setFillOpacity(.35);
		poly.setStrokeOpacity(.8);
		googleMap.addPolygonOverlay(poly);
		polygons.add(poly);
	}
	
	public SesimicActicityMap (){
		this.layout = new VerticalLayout();
		this.buttons = new HorizontalLayout();
		this.googleMap =  new GoogleMap(new LatLon(39.833333, -98.583333), 4, "AIzaSyARW8kBrGU5sRt5rUQY10ggN_SU_jA9jKg");
		googleMap.setWidth("100%");
		googleMap.setHeight("400px");
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
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
		
		//Make a few map zones to demonstrate awesome new function
		makePoly(40.0, -86.0, 0);
		makePoly(36.0, -120.0, 10);
		makePoly(41.0, -74.0, 4.3);
		
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