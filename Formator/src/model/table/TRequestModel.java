package model.table;

import java.io.File;
import java.io.Serializable;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TRequestModel implements Serializable {

	private static final long serialVersionUID = 8371985685237235177L;
	
	public enum Status implements Serializable {
		Pending, Approved, Rejected;
		
		public String getString() {
			switch(this) {
			case Pending:
				return "Pending";
			case Approved:
				return "Approved";
			case Rejected:
				return "Rejected";
			default:
				return null;
			}
		}
	}
	
	private transient SimpleIntegerProperty id;
	private Integer idSer;
	private transient SimpleStringProperty desc;
	private String descSer;
	private transient SimpleStringProperty type;
	private String typeSer;
	private transient SimpleDoubleProperty hours;
	private Double hoursSer;
	private transient SimpleStringProperty status;
	private Status statusSer;
	private String comment;
	
	private File file;
	
	public TRequestModel(int i, String d, String t, double h, Status s, String c, File f) {
		idSer = i;
		descSer = d;
		typeSer = t;
		hoursSer = h;
		statusSer = s;
		comment = c;
		file = f;
		load();
	}
	
	public void load() {
		id = new SimpleIntegerProperty(idSer);
		desc = new SimpleStringProperty(descSer);
		type = new SimpleStringProperty(typeSer);
		hours = new SimpleDoubleProperty(hoursSer);
		status = new SimpleStringProperty(statusSer.getString());
	}
	
	public int getId() {
		return id.get();
	}
	
	public void setId(int i) {
		id.set(i);
	}
	
	public String getDesc() {
		return desc.get();
	}
	
	public void setDesc(String d) {
		desc.set(d);
	}
	
	public String getType() {
		return type.get();
	}
	
	public void setType(String t) {
		type.set(t);
	}
	
	public double getHours() {
		return hours.get();
	}
	
	public void setHours(double h) {
		hours.set(h);
	}
	
	public String getStatus() {
		return status.get();
	}
	
	public Status getStatusEnum() {
		return statusSer;
	}
	
	public void setStatus(String r) {
		status.set(r);
	}
	
	public void setStatusEnum(Status r) {
		statusSer = r;
		status.set(r.getString());
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
