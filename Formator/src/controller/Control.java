package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Serializer.Data;
import javafx.collections.ObservableList;
import model.User;
import model.table.TRequestModel;
import model.table.TStudentModel;
import model.table.TUserModel;
import view.utility.Factory;

public class Control {

	public static Map<String,List<TRequestModel>> reqMap;
	public static Map<String,List<TRequestModel>> subMap;
	public static Map<String,User> userMap;
	
	public static void saveAllData() {
		Serializer.serialize(Data.Login, userMap);
		Serializer.serialize(Data.Requests, reqMap);
		Serializer.serialize(Data.Submits, subMap);
	}
	
	public static void saveUserData() {
		Serializer.serialize(Data.Login, userMap);
	}
	
	public static void saveReqData() {
		Serializer.serialize(Data.Requests, reqMap);
	}
	
	public static void saveSubData() {
		Serializer.serialize(Data.Submits, subMap);
	}
	
	public static void copyFile(File source, File dest) {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	  /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     */
    static public String ExportResource(String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {
            stream = Control.class.getResourceAsStream(resourceName);//note that each / is a directory down in the "jar tree" been the jar the root of the tree
            if(stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(Control.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream(jarFolder + resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            stream.close();
            resStreamOut.close();
        }

        return jarFolder + resourceName;
    }
	
	@SuppressWarnings("unchecked")
	public static void loadAllData() {
		userMap = (Map<String, User>) Serializer.deserialize(Data.Login);
		reqMap = (Map<String, List<TRequestModel>>) Serializer.deserialize(Data.Requests);
		subMap = (Map<String, List<TRequestModel>>) Serializer.deserialize(Data.Submits);
	}
	
	@SuppressWarnings("unchecked")
	public static void loadUserData() {
		userMap = (Map<String, User>) Serializer.deserialize(Data.Login);
	}
	
	@SuppressWarnings("unchecked")
	public static void loadReqData() {
		reqMap = (Map<String, List<TRequestModel>>) Serializer.deserialize(Data.Requests);
	}
	
	@SuppressWarnings("unchecked")
	public static void loadSubData() {
		subMap = (Map<String, List<TRequestModel>>) Serializer.deserialize(Data.Submits);
	}
	
	@SuppressWarnings("unchecked")
	public static void loadStudData(ObservableList<TStudentModel> data) {
		Object o = Serializer.deserialize(Data.Submits);
		Map<String,List<TStudentModel>> studMap = null;
		if (o instanceof Map)
			studMap = (Map<String,List<TStudentModel>>) o;
		
		List<TStudentModel> list = new ArrayList<>();
		for (String name : studMap.keySet()) {
			list.add(new TStudentModel(name));
		}
		
		if (list.isEmpty())
			return;

		data.clear();
		for (TStudentModel m : list) {
			data.add(m);
		}
	}
	
	public static void loadReqData(String user, ObservableList<TRequestModel> data) {
		List<TRequestModel> list = reqMap.get(user);
		
		if (list == null)
			return;
		
		data.clear();
		for (TRequestModel m : list) {
			m.load();
			data.add(m);
		}
	}
	
	public static void loadUserData(ObservableList<TUserModel> data) {		
		data.clear();
		for (User m : userMap.values()) {
			data.add(new TUserModel(m.getName(), m.getPass(), m.getRole().toString()));
		}
	}
	
	public static void deleteFile(File file) {
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void openAction(TRequestModel item) {
		if (item == null) {
			Factory.showWarning("Please select a record!");
        	return;
		}
		if (Desktop.isDesktopSupported()) {
		    try {
		        File file = item.getFile();
		        if (file == null) {
		        	Factory.showWarning("None file attached!");
		        	return;
		        }
		        String os = System.getProperty("os.name");
		        if (os.contains("Windows")) {
		        	Desktop.getDesktop().open(file);
		        } else {
		        	String s = file.getAbsolutePath();
		        	StringBuilder f = new StringBuilder();
		        	for (char c : s.toCharArray()) {
		        		if (c == ' ')
		        			f.append("\\");
		        		f.append(c);
		        	}
		        	Runtime run = Runtime.getRuntime();
		        	run.exec("evince");
		        }
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }  catch (IllegalArgumentException ex) {
		        Factory.showError("Couldn't find the file!");
		    }
		}
	}
}
