package application;

public class OrderTemp {
	private int idPedido;
	private String nombre;
	private String apellidos;
	private String producto;
	private String descripcion;
	private int cantidad;
	
	public OrderTemp(int idPedido, String nombre, String apellidos, String producto, String descripcion, int cantidad) {
		super();
		this.idPedido = idPedido;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.producto = producto;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}
	public OrderTemp() {
		
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}	
}
