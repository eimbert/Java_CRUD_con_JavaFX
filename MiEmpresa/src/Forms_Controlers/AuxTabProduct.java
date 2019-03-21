package Forms_Controlers;

import application.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import application.*;

public class AuxTabProduct {
	
	public static void llenarTabViewProductos(FormMainControler parentForm, ArrayList<Product> datos ) {
		 
		 ObservableList<Product> data = FXCollections.observableList(datos);
		 asignarDataTabViewProductos(parentForm, data);
		  
	}

	public static void asignarDataTabViewProductos(FormMainControler parentForm, ObservableList<Product> data) {
		parentForm.TBL_Productos.setItems(data);
		
		parentForm.COL_IdProducto.setCellValueFactory(new  PropertyValueFactory<Product,Integer>("idproducto"));
		parentForm.COL_Producto.setCellValueFactory(new  PropertyValueFactory<Product,String>("producto"));
		parentForm.COL_DescripcionProducto.setCellValueFactory(new  PropertyValueFactory<Product,String>("descripcion"));
		parentForm.COL_StockProducto.setCellValueFactory(new  PropertyValueFactory<Product,Integer>("stock"));
		
	}
		
	public static void pasarDatosDeTableAFichaProductos(FormMainControler parentForm) {
		Product productoSeleccionado = parentForm.TBL_Productos.getSelectionModel().getSelectedItem();
		
		parentForm.TXT_IdProducto.setText(productoSeleccionado.getIdProducto()+"");
		parentForm.TXT_Producto.setText(productoSeleccionado.getProducto());
		parentForm.TXT_Descripcion.setText(productoSeleccionado.getDescripcion());
		parentForm.TXT_Stock.setText(productoSeleccionado.getStock()+"");
		
	}
	public static void borrarCamposFormularioProducto(FormMainControler parentForm) {
		parentForm.TXT_IdProducto.setText("");
		parentForm.TXT_Producto.setText("");
		parentForm.TXT_Descripcion.setText("");
		parentForm.TXT_Stock.setText("");
	}	
}
