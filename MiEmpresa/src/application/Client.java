package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
	private int idCliente;
	private String nombre;
	private String apellidos;
	private String comunidad;
	private String fechaAlta;
	private boolean itSave;
	private boolean itModified;
	
	public Client(String nombre, String apellidos, String fecha, String comunidad) {
		this.idCliente = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaAlta = fecha;
		this.comunidad = comunidad;
		this.itSave = false;
		this.itModified = false;
	}
	public Client(int idcliente, String nombre, String apellidos, String fecha, String comunidad) {
		this.idCliente = idcliente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.comunidad = comunidad;
		this.itSave = true;
		this.itModified = false;
		try {
			this.fechaAlta = fecha;
		}catch(Exception e){
			this.fechaAlta = null;
		}
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public boolean isItSave() {
		return itSave;
	}
	public void setItSave(boolean itSave) {
		this.itSave = itSave;
	}
	public boolean isModified() {
		return itModified;
	}
	public void setIsModified(Boolean isModified) {
		this.itModified = isModified;
	}
	
	public String getComunidad() {
		return comunidad;
	}
	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}
	
	public Client getClient() {
		return this;
	}
	@Override
	public String toString() {
		return "Client [idCliente=" + idCliente + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaAlta="
				+ fechaAlta + "]";
	}
	
	
	
}
