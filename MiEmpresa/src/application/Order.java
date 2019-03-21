package application;

public class Order {
	private int idPedido;
	private int idCliente;
	private int idProducto;
	private int cantidad;
	private String fechaPedido;
	private Boolean isSaved;
	private Boolean isUpdated;
	
	
	public Order(int idCliente, int idProducto, int cantidad) {
		super();
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		isSaved = false;
		isUpdated =false;
	}
	
	public Order(int idPedido, int idCliente, int idProducto, int cantidad) {
		super();
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		isSaved = true;
		isUpdated = false;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Boolean getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(Boolean isSaved) {
		this.isSaved = isSaved;
	}

	public Boolean getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	
	
}
