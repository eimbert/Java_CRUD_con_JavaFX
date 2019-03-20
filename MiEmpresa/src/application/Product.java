package application;

public class Product {
	private int idProducto;
	private String producto;
	private String descripcion;
	private int stock;
	private Boolean isSave;
	private Boolean isUpdated;

	
	public Product(String producto, String descripcion, int stock) {
		this.idProducto = 0;
		this.producto = producto;
		this.descripcion = descripcion;
		this.stock = stock;
		this.isSave = false;
		this.isUpdated = false;
	}
	public Product(int idProducto, String producto, String descripcion, int stock) {
		this.idProducto = idProducto;
		this.producto = producto;
		this.descripcion = descripcion;
		this.stock = stock;
		this.isSave = true;
		this.isUpdated = false;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Boolean getIsSave() {
		return isSave;
	}
	public void setIsSave(Boolean isSave) {
		this.isSave = isSave;
	}
	public Boolean getIsUpdated() {
		return isUpdated;
	}
	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	
}
