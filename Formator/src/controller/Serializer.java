package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	
	public enum Data {
		Login,Requests,Submits;
	}
	
	public static String CONFIG_HOME;
	static String loginLoc;
	static String requestsLoc;
	static String submitsLoc;
	
	public static void createAppData() throws Exception {
	    File f = new File(Serializer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
	    CONFIG_HOME = f.getAbsolutePath();
	    CONFIG_HOME = CONFIG_HOME.substring(0, CONFIG_HOME.length()-12)+"Resources/";

	    loginLoc = CONFIG_HOME+"login.dat";
	    requestsLoc = CONFIG_HOME+"requests.dat";
	    submitsLoc = CONFIG_HOME+"submits.dat";
	}
	
	public static void serialize(Data type, Object o) {
		String archive = null;
		switch(type) {
		case Login:
			archive = loginLoc;
			break;
		case Requests:
			archive = requestsLoc;
			break;
		case Submits:
			archive = submitsLoc;
			break;
		}
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archive));
			
			out.writeObject(o);
			out.close();

			ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
			out = new ObjectOutputStream(bos) ;
			out.writeObject(o);
			out.close();
			System.out.println("Serialized!");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static Object deserialize(Data type) {
		String archive = null;
		switch(type) {
		case Login:
			archive = loginLoc;
			break;
		case Requests:
			archive = requestsLoc;
			break;
		case Submits:
			archive = submitsLoc;
			break;
		}
		try {
			FileInputStream fileIn = new FileInputStream(archive);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			Object o = in.readObject();
			
			in.close();
			fileIn.close();
			System.out.println("Deserialized!");
			
			return o;
		}catch(IOException i) {
			i.printStackTrace();
			return null;
		}catch(ClassNotFoundException c) {
			System.out.println("Map class not found");
			c.printStackTrace();
			return null;
		}
	}
}
