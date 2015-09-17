package me.flippr.models;

public class Contact {
	private String Entryname;
	private String Entrytitle;
	
	public Contact(String name, String title){
		Entryname = name;
		Entrytitle = title;
	}
	public String getNames() {
		// TODO Auto-generated method stub
		return Entryname;
	}

	public String getTitles() {
		// TODO Auto-generated method stub
		return Entrytitle;
	}

}
