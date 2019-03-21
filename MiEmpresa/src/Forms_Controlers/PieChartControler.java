package Forms_Controlers;

import java.util.HashMap;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;

public class PieChartControler {
	private PieChart objetoPieChart;
	private HashMap<String, Integer> streamDatos;
	private ObservableList<PieChart.Data> pieChartData;
	private String titulo;
	private String datoSectorSeleccionado;
	private Boolean isSelectionPosible;
	
	public PieChartControler(String titulo, HashMap<String, Integer> datos, PieChart objeto, Boolean isSelectionPosible) {
		this.titulo=titulo;
		streamDatos = datos;
		objetoPieChart = objeto;
		this.isSelectionPosible = isSelectionPosible;
		objetoPieChart.setTitle(titulo);
		objetoPieChart.setData(pieChartData);
		pieChartData = FXCollections.observableArrayList();
		generarDatosGrafico();
	}
	
	public PieChartControler(String titulo, PieChart objeto) {
		this.titulo=titulo;
		objetoPieChart = objeto;
	}
	
	public String getDatoSectorSeleccionado() {
		return datoSectorSeleccionado;
	}

	private void generarDatosGrafico() {	
		@SuppressWarnings("rawtypes")
		Iterator it = streamDatos.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			pieChartData.add(new PieChart.Data(key ,streamDatos.get(key)));
		}
	}
	
	public void refresh(HashMap<String, Integer> datos) {
		pieChartData.clear();
		objetoPieChart.setTitle(titulo);
		streamDatos = datos;
		pieChartData = FXCollections.observableArrayList();
		objetoPieChart.setData(pieChartData);
		generarDatosGrafico();
	}
	
	public void setMouseCursor() {
		if(isSelectionPosible)
			for (final PieChart.Data data: objetoPieChart.getData()) {
				data.getNode().setCursor(Cursor.HAND);
			}
	}
	public void setLisener() {
		if(isSelectionPosible)
			for (final PieChart.Data data: objetoPieChart.getData()) {
				data.getNode().setOnMouseClicked((MouseEvent event)->{
					datoSectorSeleccionado = data.getName();	
				});
			}
	}
	
	public ObservableList<PieChart.Data> getData(){
		return pieChartData;
	}
	
}
