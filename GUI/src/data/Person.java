package data;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Person {
	
	private static ArrayList<Person> personenListe = new ArrayList<Person>();
	
	private String vorname;
	private String nachname;
	private String strasse;
	private int plz;
	private String ort;

	//rechtsklick + Source + generate getter setter
	
	
	public static ArrayList<Person> getPersonenListe() {
		return personenListe;
	}
	
	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public String getStrasse() {
		return strasse;
	}
	
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}
	
	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public static void write2Json() {
		
		try {
			Writer writer=new FileWriter("c:\\temp\\output.json");
			Gson gson= new GsonBuilder().serializeNulls().create();
			gson.toJson(Person.getPersonenListe(),writer);
			
			writer.flush();
			writer.close();
		} 
		catch (IOException x) {
			x.printStackTrace();
		}
		
	}
	
	public static void ReadFromJson() {
		try {
			Reader r= new FileReader("c:\\temp\\output.json");
			Gson gson= new GsonBuilder().serializeNulls().create();
		
			ArrayList<Person> personenlisteRead = new ArrayList<>();
			java.lang.reflect.Type listType= new TypeToken <ArrayList<Person>>(){}.getType();
			personenlisteRead= gson.fromJson(r, listType);
		}
		catch (IOException x) {
			x.printStackTrace();
		}
		
	}
	
	
	

	public String toString() {
		return getVorname() + " " + getNachname()+" "+ getStrasse()+" "+getPlz()+" "+getOrt();
	}
}
