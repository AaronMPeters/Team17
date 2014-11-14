package com.cs307.sesimicactivitymap;

import com.cs307.database.Seismic_Events;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolygon;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Slider;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.*;

import javax.persistence.EntityManager;
//import javax.persistence.;
public class EventsViewMap {
	VerticalLayout layout;
	HorizontalLayout buttons;
	HorizontalLayout sliderarea;
	HorizontalLayout listarea;
	GoogleMap googleMap;
	private EntityManager em;
	Table table;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Slider sliderbar;
	Button sliderbutton;
	ArrayList<GoogleMapPolygon> polygons = new ArrayList<GoogleMapPolygon>();


	private JPAContainer<?> events;
	public EventsViewMap (JPAContainer<?> events){
		this.layout = new VerticalLayout();
		this.events = events;
		this.table = new Table();
		this.buttons = new HorizontalLayout();
		this.sliderarea = new HorizontalLayout();
		this.listarea = new HorizontalLayout();
		this.button1 = new Button("Seimic Activity Map");
		this.button2 = new Button("Events View Map");
		this.button3 = new Button("Sensor View Map");
		this.button4 = new Button("Events List");
		this.sliderbutton = new Button ("Filter");
		this.sliderbar = new Slider(1, 10000);
		
		button1.setHeight("100%");
		button2.setHeight("100%");
		button3.setHeight("100%");
		button4.setHeight("100%");
		sliderbar.setHeight("100%");
		listarea.setHeight("100%");
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
		buttons.addComponent(listarea);
		listarea.addComponent(button2);
		buttons.addComponent(button3);
		listarea.addComponent(button4);
		buttons.addComponent(sliderarea);
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
			GoogleMapMarker event = new GoogleMapMarker("events_"+s.getId(),new LatLon(latitude, longitude),false);
			googleMap.addMarker(event);
			
			GoogleMapInfoWindow win = new GoogleMapInfoWindow ("Intensity:"+s.getIntensity()+"--Date:"+s.getTime_stamp(), event);
			
			OpenInfoWindowOnMarkerClickListener infoWindowOpener = new OpenInfoWindowOnMarkerClickListener(
	                googleMap, event, win);
	        googleMap.addMarkerClickListener(infoWindowOpener);

		}
		
		
	
		em.getTransaction().commit();
		button4.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window sub = new Window();
				VerticalLayout subC = new VerticalLayout();
				sub.setContent(subC);
				table.addContainerProperty("ID", Integer.class, null);
				table.addContainerProperty("Latitude", Double.class, null);
				table.addContainerProperty("Longtitude", Double.class, null);
				table.addContainerProperty("Time", String.class, null);
				table.addContainerProperty("Depth", Double.class, null);
				table.addContainerProperty("Intensity", Double.class, null);
				
				for(Iterator it = co.iterator(); it.hasNext();){
					Seismic_Events s = (Seismic_Events) it.next();
					table.addItem(new Object[]{s.getId(),s.getLatitude(),s.getLongitude(),s.getTime_stamp(),s.getDepth(),s.getIntensity() }, null);
					
				}
				subC.addComponent(table);
				UI.getCurrent().addWindow(sub);
				sub.center();
				
			}
			
		});
		
		
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