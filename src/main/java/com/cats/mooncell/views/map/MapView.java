package com.cats.mooncell.views.map;
import java.util.Random;

import com.cats.mooncell.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.Feature;
import com.vaadin.flow.component.map.configuration.View;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.layer.FeatureLayer;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.security.RolesAllowed;
import org.json.JSONObject;@PageTitle("Map")
@Route(value = "map", layout = MainLayout.class)
@RolesAllowed("USER")
public class MapView extends HorizontalLayout {
	static Random rand_lat = new Random();
	static Random rand_long = new Random();
	static double min_lat = 8.4;
	static double max_lat = 37.6;
	static double min_long = 68.7;
	static double max_long = 97.25;
	
    public static class Location {
        private int id;
        private String country;
        private String city;
        private String place;
        private double latitude;
        private double longitude;
        public Location() {
        	get_curr_loc();
        }
        public Location(int id, String country, String city, String place, double latitude, double longitude) {
            this.id = id;
            this.country = country;
            this.city = city;
            this.place = place;
            this.latitude = latitude;
            this.longitude = longitude;

        }
        
        public int getId() {
            return id;
        }

        public String getCountry() {
            return country;
        }

        public String getCity() {
            return city;
        }

        public String getPlace() {
            return place;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void get_curr_loc() {
    	    String url = "https://ipinfo.io/json";
    	    HttpClient httpClient = HttpClient.newHttpClient();
    	    HttpRequest request = HttpRequest.newBuilder()
    	            .uri(URI.create(url))
    	            .GET()
    	            .build();
    	    try {
    	        HttpResponse<java.io.InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
    	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()));
    	        String jsonResponse = reader.lines().collect(Collectors.joining());
    	        JSONObject jsonObject = new JSONObject(jsonResponse);
    	        String loc = jsonObject.getString("loc");
    	        String[] coordinates = loc.split(",");
    	        latitude  = Double.parseDouble(coordinates[0]);
    	        longitude = Double.parseDouble(coordinates[1]);
    	        city  = jsonObject.getString("city");
    	        place = jsonObject.getString("region");

    	       }
    	       catch (IOException s) {
    	    	   s.printStackTrace();
    	       }
    	       catch(InterruptedException s){
    	    	 
    	       }
    	}
    	
    }
    static Location curr_loc = new Location(); 
    
    
    

    private static Location[] locations = new Location[]{
            new Location(1,"India","Bangalore","WAREHOUSE INDIA BANGALORE AREA 1",12.9743,77.6099),
            new Location(2,"India","Bangalore","WAREHOUSE INDIA BANGALORE AREA 2",12.9985,77.5929),
            new Location(3,"India","Bangalore","WAREHOUSE INDIA BANGALORE AREA 3",12.9762,77.5902),
            new Location(4,"India","Bangalore","WAREHOUSE INDIA BANGALORE AREA 4",12.9507,77.5848),
            new Location(5,"India","Bangalore","WAREHOUSE INDIA BANGALORE AREA 5",12.8123,77.577),
            new Location(6,"India","Bangalore","WAREHOUSE INDIA BANGALORE AREA 6",13.1986,77.7066),
            new Location(7,"India","Bangalore","WAREHOUSE INDIA BANGALORE AREA 7",12.8459,77.6609),
            new Location(8,"India","Bangalore","WAREHOUSE INDIA BANGALORE AREA 8",12.969,77.749),
            new Location(9,"India","Delhi","WAREHOUSE INDIA DELHI AREA 1",28.6315,77.2167),
            new Location(10,"India","Delhi","WAREHOUSE INDIA DELHI AREA 2",28.6315,77.2479),
            new Location(11,"India","Delhi","WAREHOUSE INDIA DELHI AREA 3",28.5466,77.2732),
            new Location(12,"India","Delhi","WAREHOUSE INDIA DELHI AREA 4",28.6428,77.121),
            new Location(13,"India","Delhi","WAREHOUSE INDIA DELHI AREA 5",28.6547,77.1404),
            new Location(14,"India","Delhi","WAREHOUSE INDIA DELHI AREA 6",28.5762,77.1998),
            new Location(15,"India","Delhi","WAREHOUSE INDIA DELHI AREA 7",28.5733,77.0128),
            new Location(16,"India","Delhi","WAREHOUSE INDIA DELHI AREA 8",28.6182,77.2911),
            new Location(17,"India","Delhi","WAREHOUSE INDIA DELHI AREA 9",28.7041,77.1025),
            new Location(18,"India","Delhi","WAREHOUSE INDIA DELHI AREA 10",28.6737,77.2895	),
            new Location(19,"India","Delhi","WAREHOUSE INDIA DELHI AREA 11",28.6964,77.1492),
            new Location(20, "India", "Madrid", "Tracking Order 1", min_lat + rand_lat.nextDouble()*(max_lat-min_lat)/2, min_long + rand_long.nextDouble()*(max_long-min_long)/2),
            new Location(21, "India", "Minsk", "Tracking Order 2", min_lat + rand_lat.nextDouble()*(max_lat-min_lat)/2, min_long + rand_long.nextDouble()*(max_long-min_long)/2),
            new Location(22, "India", "Monaco", "Tracking Order 3", min_lat + rand_lat.nextDouble()*(max_lat-min_lat)/2, min_long + rand_long.nextDouble()*(max_long-min_long)/2),
            new Location(23, "India", "Nicosia", "Tracking Order 4", min_lat + rand_lat.nextDouble()*(max_lat-min_lat)/2, min_long + rand_long.nextDouble()*(max_long-min_long)/2),
            new Location(24, "India", "Nuuk", "Tracking Order 5", min_lat + rand_lat.nextDouble()*(max_lat-min_lat)/2, min_long + rand_long.nextDouble()*(max_long-min_long)/2),
 
            
            new Location(25, curr_loc.getPlace(), curr_loc.getCity(), "Current Location", curr_loc.getLatitude(),curr_loc.getLongitude())};
    

    private Map map = new Map();
    MenuBar menuBar = new MenuBar();
    SubMenu moveToSubMenu = menuBar.addItem("Move To...").getSubMenu();
    
    private UnorderedList cardList;
    private java.util.Map<Location, Button> locationToCard = new HashMap<>();

    private List<Location> filteredLocations;
    private java.util.Map<Feature, Location> featureToLocation = new HashMap<>();

    public MapView() {
        addClassName("map-view");
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        map.getElement().setAttribute("theme", "borderless");
        map.getElement().setAttribute("class", "map");
        map.setHeightFull();
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setSpacing(false);
        sidebar.setPadding(false);

        sidebar.setWidth("auto");
        sidebar.addClassNames("sidebar");
        TextField searchField = new TextField();
        searchField.setPlaceholder("Search");
        searchField.setWidthFull();
        searchField.addClassNames(Padding.MEDIUM, BoxSizing.BORDER);
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(e -> {
            updateFilter(searchField.getValue().toLowerCase());
        });
        searchField.setClearButtonVisible(true);
        searchField.setSuffixComponent(new Icon("lumo", "search"));

        Scroller scroller = new Scroller();
        scroller.addClassNames(Padding.Horizontal.MEDIUM, Width.FULL, BoxSizing.BORDER);

        cardList = new UnorderedList();
        cardList.addClassNames("card-list", Gap.XSMALL, Display.FLEX, FlexDirection.COLUMN, ListStyleType.NONE,
                Margin.NONE, Padding.NONE);
        sidebar.add(searchField, scroller);
        scroller.setContent(cardList);

        add(map, sidebar);

        configureMap();
        updateCardList();
    }

    private void centerMapOn(Location location) {
        View view = map.getView();
        view.setCenter(new Coordinate(location.getLongitude(), location.getLatitude()));
        view.setZoom(14);
    }

    private void scrollToCard(Location location) {
        locationToCard.get(location).scrollIntoView();
    }

    private void centerMapDefault() {
        View view = new View();
        view.setCenter(new Coordinate(7, 55));
        view.setZoom(4.4f);
        map.setView(view);
    }

    private void configureMap() {

        this.centerMapDefault();

        this.map.addFeatureClickListener(e -> {
            Feature feature = e.getFeature();
            Location location = featureToLocation.get(feature);
            this.centerMapOn(location);
            this.scrollToCard(location);
        });

        this.updateFilter("");
    }

    private void updateCardList() {
        cardList.removeAll();
        locationToCard.clear();
        for (Location location : filteredLocations) {
            Button button = new Button();
            button.addClassNames(Height.AUTO, Padding.MEDIUM);
            button.addClickListener(e -> {
                centerMapOn(location);
            });

            Span card = new Span();
            card.addClassNames("card", Width.FULL, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Gap.XSMALL);
            Span country = new Span(location.getCountry());
            country.addClassNames(TextColor.SECONDARY);
            Span city = new Span(location.getCity());
            city.addClassNames(FontSize.XLARGE, FontWeight.SEMIBOLD, TextColor.HEADER, Padding.Bottom.XSMALL);
            Span place = new Span(location.getPlace());
            place.addClassNames(TextColor.SECONDARY);

            card.add(country, city, place);

            button.getElement().appendChild(card.getElement());
            cardList.add(new ListItem(button));
            locationToCard.put(location, button);
        }
    }

    private void updateFilter(String filter) {
        featureToLocation.clear();
        filteredLocations = Stream.of(locations)
                .filter(location -> location.place.toLowerCase().contains(filter)
                        || location.city.toLowerCase().contains(filter)
                        || location.country.toLowerCase().contains(filter))
                .collect(Collectors.toList());

        FeatureLayer featureLayer = this.map.getFeatureLayer();

        for (Feature f : featureLayer.getFeatures().toArray(Feature[]::new)) {
            featureLayer.removeFeature(f);
        }

        this.filteredLocations.forEach((location) -> {
            MarkerFeature feature = new MarkerFeature(new Coordinate(location.getLongitude(), location.getLatitude()));
            featureToLocation.put(feature, location);
            featureLayer.addFeature(feature);
        });
        updateCardList();
    }
}
