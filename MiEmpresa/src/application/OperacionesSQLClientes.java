package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class OperacionesSQLClientes extends CustomerOperations{
	
	
	public  OperacionesSQLClientes() {
		super();
	}
	public int buscarCliente(String name, String surname) {
		ConnectorSQL db = new ConnectorSQL();
		int codErr = db.executeSelectSQL("SELECT * FROM clientes c WHERE c.nombre='"+ name +"' AND apellidos='"+ surname +"'");
		return codErr;
	}
	
	public int borrarCliente(String name, String surname) {
		ConnectorSQL db = new ConnectorSQL();
		int codErr = db.executeUpdateSQL("DELETE FROM clientes WHERE nombre='"+ name +"' AND apellidos='"+ surname +"'");
		return codErr;
	}
	
	public int seleccionarClientes()  {
		ConnectorSQL db = new ConnectorSQL();
		int codErr = db.executeSelectSQL("SELECT * FROM clientes");
		if (codErr == ErrCode.NO_ERROR) 
			try{ 
				cargarStreamEnArray(db.getRs()); 
			} catch (SQLException e) {
				
			}
		db.closeConnection();
		return codErr;
	}
	
	private void cargarStreamEnArray(ResultSet rs) throws SQLException {
		while(rs.next()) {
			newClient(rs.getInt("idcliente"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("fechaalta"), rs.getString("comunidad"));
		}
	}	
	
	public int actualizarDatos(String name, String surname, String name2, String surname2) {
		ConnectorSQL db = new ConnectorSQL();
		
		int codErr = db.executeUpdateSQL("UPDATE clientes SET Nombre='"+name2+"',Apellidos='"+surname2+"' WHERE nombre='"+ name +"' AND apellidos='"+ surname +"'");
		return codErr;
	}
	
	public int grabarDatos() {
		ConnectorSQL db = new ConnectorSQL();
		for(Client cl: super.clientes) {
			if (!cl.isItSave()) {
				int codErr;
				codErr = db.executeUpdateSQL("INSERT INTO clientes (nombre, apellidos, fechaalta, comunidad) VALUES ('" + cl.getNombre() + "','" + cl.getApellidos()+"','"+ cl.getFechaAlta() +"','"+ cl.getComunidad() +"')");
				if (codErr == ErrCode.NO_ERROR) {
					cl.setItSave(true);
				}
				else
					return ErrCode.CONNECTION_ERROR;
			}
		}
		try {
			db.closeConnection();
		}catch(Exception e) {
			
		}
		return ErrCode.NO_ERROR;
	}
	
	public int grabarModificacionesClientes() {
		ConnectorSQL db = new ConnectorSQL();
		for(Client cl: super.clientes) {
			if (cl.isModified()) {
				int codErr;
				codErr = db.executeUpdateSQL("UPDATE clientes SET Nombre='"+cl.getNombre()+"',Apellidos='"+cl.getApellidos()+"', fechaalta='"+ cl.getFechaAlta() + "',comunidad='"+ cl.getComunidad() +"' WHERE idcliente="+ cl.getIdCliente());
				if (codErr == ErrCode.NO_ERROR) {
					cl.setIsModified(false);
				}
				else
					return ErrCode.CONNECTION_ERROR;
			}
		}
		try {
			db.closeConnection();
		}catch(Exception e) {
			
		}
		return ErrCode.NO_ERROR;
	}
	
	public HashMap<String, Integer> clientesPorProvincia(){
		HashMap<String, Integer> provincias = new HashMap<String, Integer>();

		ConnectorSQL db = new ConnectorSQL();
		int codErr = db.executeSelectSQL("SELECT comunidad, Count(comunidad) FROM clientes GROUP BY comunidad");
		if (codErr == ErrCode.NO_ERROR) 
			try{ 
				while(db.getRs().next()) {
					provincias.put(db.getRs().getString(1),db.getRs().getInt(2));
				}
			} catch (SQLException e) {
				
			}
		db.closeConnection();		
		return provincias;
	}
	
	public int listadoGeneral() {
		
		return ErrCode.NO_ERROR;
	}	
}