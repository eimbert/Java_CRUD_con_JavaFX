package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ProductsOperations {
	protected ArrayList<Product> productos;
	
	public ProductsOperations() {
		this.productos = new ArrayList<Product>();
	}
	
	public void updateProduct(int idproducto, String producto, String descripcion, int stock) {
		for(Product s: productos) {
			if(s.getIdProducto()==idproducto) {
				s.setIdProducto(idproducto);
				s.setProducto(producto);
				s.setDescripcion(descripcion);
				s.setStock(stock);
				s.setIsUpdated(true);
			}
		}
	}
	
	public void newProduct(int idproducto, String producto, String descripcion, int stock) {
		productos.add(new Product(idproducto, producto, descripcion, stock));
	}
	
	public void newProduct(String producto, String descripcion, int stock) {
		productos.add(new Product(producto, descripcion, stock));
	}
	
	public  HashMap<String, Integer>  contarStockProductos() {
		Map<String, Integer > stockDisponible = productos.stream().collect(Collectors.toMap(obj -> obj.getProducto(), obj -> obj.getStock()));
		return (HashMap<String, Integer>)stockDisponible; 
	}
	
	public void vaciarArray() {
		if (grabarProductos() == 0 && grabarModificacionesProductos() == 0)
			productos.clear();
		
	}
		
	public ArrayList<Product> getProductos(){
		return productos;
	}
	

	public abstract int grabarProductos();
	public abstract int seleccionarProductos(); 
	public abstract int grabarModificacionesProductos();
	
	
}
