package Forms_Controlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


public class AuxTabListadoPedidos {
	
	public static void cargarPedidos(FormMainControler parentForm, ArrayList<OrderTemp> datos) {
		
		ObservableList<OrderTemp> data = FXCollections.observableList((ArrayList)datos);
		parentForm.TableView_ListadoPedidos.setItems(data);
		
		parentForm.IdPedido_ListadoPedidos.setCellValueFactory(new  PropertyValueFactory<OrderTemp,Integer>("idPedido"));
		parentForm.nombre_ListadoPedidos.setCellValueFactory(new PropertyValueFactory<OrderTemp,String>("nombre"));
		parentForm.apellidos_ListadoPedidos.setCellValueFactory(new PropertyValueFactory<OrderTemp,String>("apellidos"));
		parentForm.producto_ListadoPedidos.setCellValueFactory(new PropertyValueFactory<OrderTemp,String>("producto"));
		parentForm.descripcion_ListadoPedidos.setCellValueFactory(new PropertyValueFactory<OrderTemp,String>("descripcion"));
		parentForm.cantidad_ListadoPedidos.setCellValueFactory(new PropertyValueFactory<OrderTemp,Integer>("cantidad"));
	}
	
	public static void pasarPedidoAOrderTemp(ArrayList<OrderTemp> orderTemp, ArrayList<Order> orders, ArrayList<Client> clients, ArrayList<Product> products) {
		orderTemp.clear();
		orders.stream().forEach(a -> {
			OrderTemp ord = new OrderTemp();
			ord.setIdPedido(a.getIdPedido());
			ord.setCantidad(a.getCantidad());
			clients.stream().filter(c -> c.getIdCliente()==a.getIdCliente()).forEach(c -> {ord.setNombre(c.getNombre()); ord.setApellidos(c.getApellidos());} );
			products.stream().filter(p -> p.getIdProducto()==a.getIdProducto()).forEach(p -> {ord.setProducto(p.getProducto()); ord.setDescripcion(p.getDescripcion());});
			orderTemp.add(ord);
		});
	}
}
