package com.cs307.sesimicactivitymap;

import javax.servlet.annotation.WebServlet;
import com.vaadin.tapio.googlemaps.*;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("sesimicactivitymap")
public class SesimicactivitymapUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = SesimicactivitymapUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		layout.addComponent(button);
		
		GoogleMap googleMap =  new GoogleMap(new LatLon(60.440963, 22.25122), "AIzaSyARW8kBrGU5sRt5rUQY10ggN_SU_jA9jKg");
		googleMap.setSizeFull();
		googleMap.setMin
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);
		layout.addComponent(googleMap);
	}

}