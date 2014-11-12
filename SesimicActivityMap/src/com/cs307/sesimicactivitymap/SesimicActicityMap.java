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
import java.math.*;

public class SesimicActicityMap {
	VerticalLayout layout;
	HorizontalLayout buttons;
	GoogleMap googleMap;
	Button button1;
	Button button2;
	Button button3;
	ArrayList<GoogleMapPolygon> polygons = new ArrayList<GoogleMapPolygon>();
	private int polyCount = 0;
	
	/*
	 * Draw a colored polygon to the map
	 * 
	 * @param lat the center point latitude
	 * @param lon the center point longitude
	 * @param mag the magnitude of the event
	 */
	public void makePoly(double lat, double lon, double mag){
		polyCount++;
		double trig = 0.70710678118;	//equivalent to cos(45) and sin(45)
		double r = mag*.4 + .5;	//radius determined by magnitude
		String color;
		ArrayList<LatLon> points = new ArrayList<LatLon>();
		for (int i = 0; i <= 360; i++) {
			points.add(new LatLon(lat + r * Math.cos(((double)i / 180) * Math.PI), lon + r * Math.sin(((double)i / 180) * Math.PI)));
		}

		GoogleMapPolygon poly = new GoogleMapPolygon(points);
		poly.setGeodesic(true);
		color = getColor(mag);
		poly.setFillColor(color);
		poly.setStrokeColor(color);
		poly.setFillOpacity(.35);
		poly.setStrokeOpacity(.8);
		googleMap.addPolygonOverlay(poly);
		polygons.add(poly);
	}
	
	public String getColor(double mag) {
		String color = "#";
		int red, green, blue;
		red = 255;
		green = 255;
		blue = 0;
		if (mag >= 3 && mag < 6.5)	{
			red = (int) ((double)(mag - 3) * 255 / 3.5);
		} else if (mag >= 6.5){
			green = (int)((double)(10 - mag) * 255 / 3.5) ;
		} else {
			red = (int) (3 - mag) * 255 / 3;
			green = (int) (3 - mag) * 255 / 3;
			blue = 255;
		}
		color = (red == 0) ? color.concat("00") : color.concat(Integer.toHexString(red));
		color = (green == 0) ? color.concat("00") : color.concat(Integer.toHexString(green));
		color = (blue == 0) ? color.concat("00"): color.concat(Integer.toHexString(blue));
		System.out.println("Color for polygon[" + polyCount + "], is " + color + " " + red + " " + green);
		return color;
		
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
		makePoly(38.0, -90.0, 5);
		makePoly(32.76, -96.79, 7.0);
		makePoly(44.3, -110.2, 6.7);
		makePoly(41.0, -86.0, 1);
		makePoly(42.0, -86.0, 2);
		
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