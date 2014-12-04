package com.cs307.sesimicactivitymap;

import javax.servlet.annotation.WebServlet;

import com.cs307.database.Seismic_Events;
import com.cs307.database.Sensor;
import com.vaadin.tapio.googlemaps.*;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
@Theme("sesimicactivitymap")
public class MainPage extends UI {
	
	public int currentPage;
	public static final String PERSISTENCE_UNIT = "SAM";
	private JPAContainer<Sensor> sensors;
	private JPAContainer<Seismic_Events> events;
	//private Table sensorTable;
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MainPage.class, widgetset = "com.cs307.sesimicactivitymap.widgetset.SesimicactivitymapWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		sensors = JPAContainerFactory.make(Sensor.class, PERSISTENCE_UNIT);
		events =  JPAContainerFactory.make(Seismic_Events.class, PERSISTENCE_UNIT);
		final SesimicActicityMap first = new SesimicActicityMap();
		final EventsViewMap second = new EventsViewMap(events);
		final SensorViewMap third = new SensorViewMap(sensors);
		//System.out.println(sensors.geti);
		setContent(first.getLayout());
		currentPage = 1;
		
	//	sensorTable = new Table(null,sensors);
		
		//layout.addComponent(sensorTable);
		
		
		first.getButton1().addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(first.getLayout());
				currentPage = 1;
			}
		});
		first.getButton2().addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(second.getLayout());
				currentPage = 2;
			}
		});
		first.getButton3().addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(third.getLayout());
				currentPage = 3;
			}
		});
		second.getButton1().addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(first.getLayout());
				currentPage = 1;
			}
		});
		second.getButton2().addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(second.getLayout());
				currentPage = 2;
			}
		});
		second.getButton3().addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(third.getLayout());
				currentPage = 3;
			}
		});
		third.getButton1().addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(first.getLayout());
				currentPage = 1;
			}
		});
		third.getButton2().addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(second.getLayout());
				currentPage = 2;
			}
		});
		third.getButton3().addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				setContent(third.getLayout());
				currentPage = 3;
			}
		});
		
		//new Listener resizeListener = Page.BrowserWindowResizeListener;
		Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
		    @Override
		    public void browserWindowResized(BrowserWindowResizeEvent event) {
		    	if (currentPage == 1) {
		    		first.setMapSize();
		    	} else if (currentPage == 2) {
		    		second.setMapSize();
		    	} else if (currentPage == 3) {
		    		third.setMapSize();
		    	}
		        Notification.show("Browser resized to "+event.getWidth()+"x"+event.getHeight());
		    }
		});
	}

}