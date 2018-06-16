package view.utility;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.table.TRequestModel;

public class ReqInfoPane extends HBox {

	Label totLabel = new Label();
	Label pendLabel = new Label();
	Label appLabel = new Label();
	Label rejLabel = new Label();
	Label needLabel = new Label();
	double tot = 0;
	double pen = 0;
	double app = 0;
	double rej = 0;
	
	public ReqInfoPane() {
		this.setSpacing(20);
		this.getChildren().addAll(totLabel,pendLabel,appLabel,rejLabel,needLabel);
		this.setAlignment(Pos.CENTER);
	}
	
	public void loadTotal(ObservableList<TRequestModel> data) {
		tot = 0;
		pen = 0;
		app = 0;
		rej = 0;
		for (TRequestModel m : data) {
			tot += m.getHours();
			if (m.getStatus().equals("Pending")) {
				pen += m.getHours();
			} else if (m.getStatus().equals("Approved")) {
				app += m.getHours();
			} else if (m.getStatus().equals("Rejected")) {
				rej += m.getHours();
			}
		}
		totLabel.setText("Total: "+tot);
		pendLabel.setText("Pending: "+pen);
		appLabel.setText("Approved: "+app);
		rejLabel.setText("Rejected: "+rej);
		needLabel.setText("Needed: "+(480-tot));
	}

	public double getTot() {
		return tot;
	}

	public double getPen() {
		return pen;
	}

	public double getApp() {
		return app;
	}

	public double getRej() {
		return rej;
	}
}
