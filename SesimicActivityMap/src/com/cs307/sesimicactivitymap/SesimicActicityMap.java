package com.cs307.sesimicactivitymap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.cs307.database.Seismic_Events;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolygon;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import java.math.*;

import javax.persistence.EntityManager;

public class SesimicActicityMap {
	VerticalLayout layout;
	HorizontalLayout buttons; 
	
	GoogleMap googleMap;
	Button button1;
	Button button2;
	Button button3;
	ArrayList<GoogleMapPolygon> polygons = new ArrayList<GoogleMapPolygon>();
	private int polyCount = 0;
	private EntityManager em;
	
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
		return color;
		
	}
	
	public SesimicActicityMap (){
		this.layout = new VerticalLayout();
		this.buttons = new HorizontalLayout();
		this.googleMap =  new GoogleMap(new LatLon(39.833333, -98.583333), 4, "AIzaSyARW8kBrGU5sRt5rUQY10ggN_SU_jA9jKg");
		googleMap.setWidth("100%");
		setMapSize();
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
		this.button1 = new Button("Seismic Activity Map");
		this.button2 = new Button("Events View Map");
		this.button3 = new Button("Sensor View Map");
		buttons.addComponent(button1);		
		buttons.addComponent(button2);
		buttons.addComponent(button3);
		buttons.setWidth("100%");
		layout.addComponent(buttons);
		layout.addComponent(googleMap);
		layout.setMargin(true);
		layout.setSizeFull();
		layout.setHeightUndefined();
		
		em = JPAContainerFactory.createEntityManagerForPersistenceUnit("SAM");
		em.getTransaction().begin();
		javax.persistence.Query q =  em.createQuery("SELECT c FROM Seismic_Events c WHERE c.intensity > 3.0 AND c.latitude > 22.5 AND c.latitude < 50 AND c.longitude < -60 AND c.longitude > -130");
		final Collection co = q.getResultList();
		for(Iterator i = co.iterator(); i.hasNext();){
			Seismic_Events s = (Seismic_Events) i.next();
			double latitude = s.getLatitude();
			double longitude = s.getLongitude();
			makePoly(latitude, longitude, s.getIntensity());
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