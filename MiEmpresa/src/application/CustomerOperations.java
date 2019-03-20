package application;

import java.util.*;

public abstract class CustomerOperations {
	protected ArrayList<Client> clientes;
	
	public CustomerOperations() {
		this.clientes = new ArrayList<Client>();
	}
	
	public void updateClient(int idcliente, String nombre, String apellidos, String fecha, String comunidad) {
		for(Client s: clientes) {
			if(s.getIdCliente()==idcliente) {
				s.setIdCliente(idcliente);
				s.setNombre(nombre);
				s.setApellidos(apellidos);
				s.setFechaAlta(fecha);
				s.setComunidad(comunidad);
				s.setIsModified(true);
			}
		}
	}
	
	public void newClient(int idcliente, String nombre, String apellidos, String fecha, String comunidad) {
		clientes.add(new Client(idcliente, nombre, apellidos, fecha, comunidad));
	}
	
	public void newClient(String nombre, String apellidos, String fecha, String comunidad) {
		clientes.add(new Client(nombre, apellidos, fecha, comunidad));
	}
	
	private  int  contarElementosPorComnunidades(String comunidad) {
		int res = (int) clientes.stream().filter(a -> comunidad.equals(a.getComunidad())).count();	
		return res; 
	}
	
	public void vaciarArray() {
		if (grabarDatos() == 0 && grabarModificacionesClientes() == 0)
			clientes.clear();
		
	}
	public HashMap<String, Integer> agruparComunidades() {
		HashSet<String> comunidades = new HashSet<String>();
		HashMap<String, Integer> comunidadesValor = new HashMap<String, Integer>();
		
		clientes.stream().forEach((c)->comunidades.add(c.getComunidad()));
		//hace lo mismo que la líne anterior
		//comunidades.addAll(clientes.stream().map(Client::getComunidad).collect(Collectors.toList()));
		
		comunidades.stream().forEach((c)->{ 
			comunidadesValor.put(c, contarElementosPorComnunidades(c));
		});
		
		return comunidadesValor;
	}
	
	public ArrayList<Client> getClientes(){
		return clientes;
	}
	
	public abstract int buscarCliente(String name, String surname);
	public abstract int grabarDatos();
	public abstract int borrarCliente(String name, String surname);
	public abstract int actualizarDatos(String name, String surname, String name2, String surname2);
	public abstract int seleccionarClientes(); 
	public abstract int grabarModificacionesClientes();
	
}


