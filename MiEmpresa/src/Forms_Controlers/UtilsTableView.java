package Forms_Controlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import application.Product;
import application.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


public class UtilsTableView {

	public static void llenarTabView(List<?> datosFiltrados, FormMainControler form) {
		ObservableList<?> data = FXCollections.observableList((ArrayList)datosFiltrados);
		asignarDataTabView(data, form);
	}

	public static void llenarTabView() {
		ObservableList<Client> data = FXCollections.observableList(sqlClientes.getClientes());
		
		if(comunidad!=""){
			data = FXCollections.observableList(data.stream().filter(c -> comunidad.equals(c.getComunidad())).collect(Collectors.toList()));
		}
		asignarDataTabView(data);		
	}
	
	private static void asignarDataTabView(ObservableList<?> data, FormMainControler form) {
		form.TabViewProductos.setItems(data);
		
		form.TBL_IdCliente.setCellValueFactory(new  PropertyValueFactory<Client,Integer>("idcliente"));
		form.TBL_Nombre.setCellValueFactory(new PropertyValueFactory<Client,String>("nombre"));
		form.TBL_Apellidos.setCellValueFactory(new PropertyValueFactory<Client,String>("apellidos"));
		TBL_FechaAlta.setCellValueFactory(new PropertyValueFactory<Client,String>("fechaAlta"));	
		TBL_Comunidad.setCellValueFactory(new PropertyValueFactory<Client,String>("comunidad"));
	}
	
	public static void pasarDatosDeTableAFicha() {
		Client clienteSeleccionado = TBL_Clientes.getSelectionModel().getSelectedItem();
		
		TXT_IdCliente.setText(clienteSeleccionado.getIdCliente()+"");
		TXT_NombreCliente.setText(clienteSeleccionado.getNombre());
		TXT_ApellidosCliente.setText(clienteSeleccionado.getApellidos());
		DTA_FechaAltaCliente.setPromptText(clienteSeleccionado.getFechaAlta());
		CMB_ComunidadCliente.setValue(clienteSeleccionado.getComunidad());
	}
}
