package Forms_Controlers;

import java.util.ArrayList;
import java.util.List;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class AuxTabPedidos {
		
	public static void cargarClientes(FormMainControler parentForm, List<Client> datos) {
		ObservableList<Client> data = FXCollections.observableList((ArrayList)datos);
		parentForm.Tableview_ClientesPedidos.setItems(data);
		
		parentForm.ColumnIdClientePedidos.setCellValueFactory(new  PropertyValueFactory<Client,Integer>("idcliente"));
		parentForm.ColumnNombrePedidos.setCellValueFactory(new PropertyValueFactory<Client,String>("nombre"));
		parentForm.ColumnApellidosPedido.setCellValueFactory(new PropertyValueFactory<Client,String>("apellidos"));
	}
	
	public static void cargarProductos(FormMainControler parentForm, List<Product> datos) {
		ObservableList<Product> data = FXCollections.observableList((ArrayList)datos);
		parentForm.TableView_ProductosPedidos.setItems(data);
		
		parentForm.ColumnIdProductoPedido.setCellValueFactory(new  PropertyValueFactory<Product,Integer>("idproducto"));
		parentForm.ColumnProductoPedido.setCellValueFactory(new PropertyValueFactory<Product,String>("producto"));
		parentForm.ColumnStockPedidos.setCellValueFactory(new PropertyValueFactory<Product,Integer>("stock"));
	
	}
	public static void borrarFormulario(FormMainControler parentForm) {
		parentForm.TXT_CantidadPedido.setText("0");
	}
}
