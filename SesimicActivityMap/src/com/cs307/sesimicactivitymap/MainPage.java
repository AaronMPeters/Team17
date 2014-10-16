package com.cs307.sesimicactivitymap;

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
public class MainPage extends UI {

	//public static final String PERSISTENCE_UNIT = "SAM";
	//private JPAContainer<Sensor> sensors;
	//private Table sensorTable;
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MainPage.class, widgetset = "com.cs307.sesimicactivitymap.widgetset.SesimicactivitymapWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		SesimicActicityMap first = new SesimicActicityMap();
		setContent(first.getLayout());
	//	sensors = JPAContainerFactory.make(Sensor.class, PERSISTENCE_UNIT);
	//	sensorTable = new Table(null,sensors);
		
		//layout.addComponent(sensorTable);


		
	}

}