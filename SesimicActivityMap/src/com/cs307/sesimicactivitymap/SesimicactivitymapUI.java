/*package com.cs307.sesimicactivitymap;

import javax.servlet.annotation.WebServlet;

import com.cs307.database.Sensor;
import com.vaadin.tapio.googlemaps.*;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("sesimicactivitymap")
public class SesimicactivitymapUI extends UI {

	//public static final String PERSISTENCE_UNIT = "SAM";
	//private JPAContainer<Sensor> sensors;
	//private Table sensorTable;
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = SesimicactivitymapUI.class, widgetset = "com.cs307.sesimicactivitymap.widgetset.SesimicactivitymapWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSizeFull();
		
		setContent(layout);
	//	sensors = JPAContainerFactory.make(Sensor.class, PERSISTENCE_UNIT);
	//	sensorTable = new Table(null,sensors);
		HorizontalLayout buttons = new HorizontalLayout();

		Button button1 = new Button("SeimicactivityMap");
		Button button2 = new Button("EventsViewMap");
		Button button3 = new Button("SensorViewMap");

		button1.setWidth("100%");
		button2.setWidth("100%");
		button3.setWidth("100%");
		buttons.addComponent(button1);		
		buttons.addComponent(button2);
		buttons.addComponent(button3);
		buttons.setWidth("100%");
		buttons.setHeight("100%");
		layout.addComponent(buttons);
		
		//layout.addComponent(sensorTable);
		GoogleMap googleMap =  new GoogleMap(new LatLon(40.424318, -86.912367), "AIzaSyARW8kBrGU5sRt5rUQY10ggN_SU_jA9jKg");
		googleMap.setWidth("100%");
		googleMap.setHeight("400px");
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
		GoogleMapMarker marker = new GoogleMapMarker("test", googleMap.getCenter(), false);
		googleMap.addMarker(marker);
		GoogleMapInfoWindow window = new GoogleMapInfoWindow ("Add a new infowindow", marker);
		googleMap.openInfoWindow(window);
		layout.addComponent(googleMap);
		layout.setHeightUndefined();
		final VerticalLayout layout2 = new VerticalLayout();
		layout2.setSizeFull();
		Button button1A = new Button("SeimicactivityMap");
		Button button2A = new Button("EventsViewMap");
		Button button3A = new Button("SensorViewMap");
		layout2.setHeightUndefined();
		layout2.addComponent(button1A);
		layout2.addComponent(button2A);
		layout2.addComponent(button3A);

		button1.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(layout2);
			}
		});

		
	}

}*/