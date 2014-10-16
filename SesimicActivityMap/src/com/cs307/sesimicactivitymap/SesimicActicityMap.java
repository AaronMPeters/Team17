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
	public SesimicActicityMap (){
		this.layout = new VerticalLayout();
		this.buttons = new HorizontalLayout();
		this.googleMap =  new GoogleMap(new LatLon(40.424318, -86.912367), "AIzaSyARW8kBrGU5sRt5rUQY10ggN_SU_jA9jKg");
		googleMap.setWidth("100%");
		googleMap.setHeight("400px");
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
		this.button1 = new Button("EventsSeimicactivityMap1");
		this.button2 = new Button("EventsEventsViewMap1");
		this.button3 = new Button("EventsSensorViewMap1");
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
		
		//A few fake map zones in order to demonstrate color filling
				//
				//San Francisco area
				ArrayList<LatLon> sfcoords = new ArrayList<LatLon>();
				sfcoords.add(new LatLon(36.4, -121.6));
				sfcoords.add(new LatLon(37.1, -122.3));
				sfcoords.add(new LatLon(38.4, -123.0));
				sfcoords.add(new LatLon(37.8, -120.0));
				sfcoords.add(new LatLon(36.7, -119.8));
				sfcoords.add(new LatLon(36.4, -121.6));
				
				GoogleMapPolygon sanfran = new GoogleMapPolygon(sfcoords);
				sanfran.setFillColor("#FF0000");
				sanfran.setFillOpacity(.35);
				sanfran.setStrokeColor("#FF0000");
				sanfran.setStrokeOpacity(.8);
				googleMap.addPolygonOverlay(sanfran);
				
				//Midwest area
				ArrayList<LatLon> mwcoords = new ArrayList<LatLon>();
				mwcoords.add(new LatLon(43, -103));
				mwcoords.add(new LatLon(41, -93));
				mwcoords.add(new LatLon(36, -91));
				mwcoords.add(new LatLon(37, -102));
				mwcoords.add(new LatLon(43, -103));
				
				GoogleMapPolygon midwest = new GoogleMapPolygon(mwcoords);
				midwest.setFillColor("#00FF00");
				midwest.setFillOpacity(.35);
				midwest.setStrokeColor("#00FF00");
				midwest.setStrokeOpacity(.8);
				googleMap.addPolygonOverlay(midwest);
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