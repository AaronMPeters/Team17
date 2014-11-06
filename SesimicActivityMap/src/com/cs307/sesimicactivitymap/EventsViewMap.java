package com.cs307.sesimicactivitymap;

import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolygon;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;

import java.util.*;
public class EventsViewMap {
	VerticalLayout layout;
	HorizontalLayout buttons;
	HorizontalLayout sliderarea;
	GoogleMap googleMap;
	Button button1;
	Button button2;
	Button button3;
	Slider sliderbar;
	Button sliderbutton;
	ArrayList<GoogleMapPolygon> polygons = new ArrayList<GoogleMapPolygon>();


	public EventsViewMap (){
		this.layout = new VerticalLayout();
		this.buttons = new HorizontalLayout();
		this.sliderarea = new HorizontalLayout();
		this.button1 = new Button("Seimic Activity Map");
		this.button2 = new Button("Events View Map");
		this.button3 = new Button("Sensor View Map");
		this.sliderbutton = new Button ("Filter");
		this.sliderbar = new Slider(1, 10000);
		
		button1.setHeight("100%");
		button2.setHeight("100%");
		button3.setHeight("100%");
		sliderbar.setHeight("100%");
		sliderbutton.setHeight("100%");
		sliderarea.setWidth("100%");
		sliderbar.setOrientation(SliderOrientation.HORIZONTAL);
		sliderbar.setWidth("100%");
		this.googleMap =  new GoogleMap(new LatLon(39.833333, -98.583333), 4, "AIzaSyARW8kBrGU5sRt5rUQY10ggN_SU_jA9jKg");

		googleMap.setWidth("100%");
		googleMap.setHeight("400px");
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
		sliderarea.addComponent(sliderbar);
		sliderarea.addComponent(sliderbutton);
		buttons.addComponent(button1);		
		buttons.addComponent(button2);
		buttons.addComponent(button3);
		buttons.addComponent(sliderarea);
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
			makePoly (lat[i], lon[i], 2);

			GoogleMapInfoWindow win = new GoogleMapInfoWindow ("Current Activity: ", seisEvents[i]);
			
			OpenInfoWindowOnMarkerClickListener infoWindowOpener = new OpenInfoWindowOnMarkerClickListener(
	                googleMap, seisEvents[i], win);
	        googleMap.addMarkerClickListener(infoWindowOpener);
		}
	}
	public void makePoly(double lat, double lon, double mag){
		double trig = 0.70710678118;	//equivalent to cos(45) and sin(45)
		double r = mag*.4 + .5;	//radius determined by magnitude
		ArrayList<LatLon> points = new ArrayList<LatLon>();
		points.add(new LatLon(lat, lon+r));					//0 top
		points.add(new LatLon(lat+trig*r, lon+trig*r));		//1 upper right
		points.add(new LatLon(lat+r, lon));					//2 right
		points.add(new LatLon(lat+trig*r, lon-trig*r));		//3 lower right
		points.add(new LatLon(lat, lon-r));					//4 bottom
		points.add(new LatLon(lat-trig*r, lon-trig*r));		//5 lower left
		points.add(new LatLon(lat-r, lon));					//6 left
		points.add(new LatLon(lat-trig*r, lon+trig*r));		//7 upper left
		GoogleMapPolygon poly = new GoogleMapPolygon(points);
		if(mag<=0){
			//sets colors to blue
			poly.setFillColor("#0000FF");
			poly.setStrokeColor("#0000FF");
		} else if(mag<=3){
			//sets colors to green
			poly.setFillColor("#00FF00");
			poly.setStrokeColor("#00FF00");
		} else if(mag<=7){
			//sets colors to yellow
			poly.setFillColor("#FFFF00");
			poly.setStrokeColor("#FFFF00");
		} else{
			//sets colors to red
			poly.setFillColor("#FF0000");
			poly.setStrokeColor("#FF0000");
		}
		poly.setFillOpacity(.35);
		poly.setStrokeOpacity(.8);
		googleMap.addPolygonOverlay(poly);
		polygons.add(poly);
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