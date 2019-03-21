package Forms_Controlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import application.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class AuxTabClient {
	/**
	 * 
	 * @param parentForm Fromulario desde donde se produce la llamada
	 * @param datos ArrayList Client
	 * @param filtro Comunidad por la que filtrar o nulo para general
	 */
	public static void llenarTabViewClientes(FormMainControler parentForm, ArrayList<Client> datos, String filtro) {
		
		ObservableList<Client> data = FXCollections.observableList(datos);
		
		if(filtro!=""){
			data = FXCollections.observableList(data.stream().filter(c -> filtro.equals(c.getComunidad())).collect(Collectors.toList()));
		}
		asignarDataTabView(parentForm, data);		
	}
	
	public static void llenarTabView(FormMainControler parentForm, List<Client> datosFiltrados) {
		ObservableList<Client> data = FXCollections.observableList((ArrayList)datosFiltrados);
		asignarDataTabView(parentForm, data);
	}
	
	private static void asignarDataTabView(FormMainControler parentForm, ObservableList<Client> data) {
		parentForm.TBL_Clientes.setItems(data);
		
		parentForm.TBL_IdCliente.setCellValueFactory(new  PropertyValueFactory<Client,Integer>("idcliente"));
		parentForm.TBL_Nombre.setCellValueFactory(new PropertyValueFactory<Client,String>("nombre"));
		parentForm.TBL_Apellidos.setCellValueFactory(new PropertyValueFactory<Client,String>("apellidos"));
		parentForm.TBL_FechaAlta.setCellValueFactory(new PropertyValueFactory<Client,String>("fechaAlta"));	
		parentForm.TBL_Comunidad.setCellValueFactory(new PropertyValueFactory<Client,String>("comunidad"));
	}
	
	public static void pasarDatosDeTableAFicha(FormMainControler parentForm) {
		Client clienteSeleccionado = parentForm.TBL_Clientes.getSelectionModel().getSelectedItem();
		
		parentForm.TXT_IdCliente.setText(clienteSeleccionado.getIdCliente()+"");
		parentForm.TXT_NombreCliente.setText(clienteSeleccionado.getNombre());
		parentForm.TXT_ApellidosCliente.setText(clienteSeleccionado.getApellidos());
		parentForm.DTA_FechaAltaCliente.setPromptText(clienteSeleccionado.getFechaAlta());
		parentForm.CMB_ComunidadCliente.setValue(clienteSeleccionado.getComunidad());
	}
	
	public static void borrarCamposFormularioCliente(FormMainControler parentForm) {
		parentForm.TXT_IdCliente.setText("");
		parentForm.TXT_NombreCliente.setText("");
		parentForm.TXT_ApellidosCliente.setText("");
		parentForm.DTA_FechaAltaCliente.setValue(LocalDate.now());
		parentForm.CMB_ComunidadCliente.getSelectionModel().selectFirst();
	}	
}
