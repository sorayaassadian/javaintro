package gui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.Person;

public class JSONTest {

	public static void main(String[] args) {
		try {
		Writer writer=new FileWriter("c:\\temp\\output.json");
		Gson gson= new GsonBuilder().serializeNulls().create();
		//gson.toJson("Hallo", writer);
		//gson.toJson(12345, writer);
		
		Person p= new Person();
	    p.setVorname("Hans");
	    p.setNachname("Mayer");
	    p.setPlz(1234);
		
		gson.toJson(p, writer);
		
		writer.flush();
		writer.close();
		
		}	catch (IOException e) {
			e.printStackTrace();
			}
	}

}
