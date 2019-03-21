package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class OrdersOperations {
	protected ArrayList<Order> pedidos;
	
	public OrdersOperations() {
		pedidos = new ArrayList<Order>();
	}
	
	public void updateOrder(int idpedido, int idcliente, int idproducto, int cantidad) {
		for(Order o: pedidos) {
			if(o.getIdPedido()==idpedido) {
				o.setIdCliente(idcliente);
				o.setIdProducto(idproducto);
				o.setCantidad(cantidad);
			}
		}
	}
	
	public void newOrder(int idPedido, int idCliente, int idProducto, int cantidad) {
		pedidos.add(new Order(idPedido, idCliente, idProducto, cantidad));
	}
	
	public void newOrder(int idCliente, int idProducto, int cantidad) {
		pedidos.add(new Order(idCliente, idProducto, cantidad));
	}
	
		
	public void vaciarArray() {
		if (grabarPedidos() == 0 && grabarModificacionesPedidos() == 0)
			pedidos.clear();
		
	}
		
	public ArrayList<Order> getProductos(){
		return pedidos;
	}
	

	public abstract int grabarPedidos();
	public abstract int seleccionarPedidos(); 
	public abstract int grabarModificacionesPedidos();
	
	
}
