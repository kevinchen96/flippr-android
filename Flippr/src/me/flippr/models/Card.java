package me.flippr.models;

public class Card {
	private String names;
	private String jobs;
	private String locations;
	private String titles;
	private String places;
	private Integer pictures;
	
	public Card(String name, String job, String location, String title, String place, Integer picture){
		names = name;
		jobs = job;
		locations = location;
		titles = title;
		places = place;
		pictures = picture;
	}
	public String getNames(){
		return names;
	}
	public String getJobs(){
		return jobs;
	}
	public String getLocations(){
		return locations;
	}
	public String getTitles(){
		return titles;
	}
	public String getPlaces(){
		return places;
	}
	public Integer getPictures(){
		return pictures;
	}
}
