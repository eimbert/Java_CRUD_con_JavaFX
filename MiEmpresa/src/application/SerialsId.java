package application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SerialsId {
	private static int newIdCliente = 0;
	private static int newIdProductos = 0;
	private static int newIdPedido = 0;
	
	public static int obtenerSerialsId() {
		ConnectorSQL db = new ConnectorSQL();
		int valorId;
		
		//valorId = db.executeSelectSQL("SELECT MAX(idCliente) AS nextId FROM clientes ORDER BY idCliente DESC");
		//if(valorId!= ErrCode.QUERY_ERROR) newIdCliente = obtenerId(db.getRs());
		
		//valorId = db.executeSelectSQL("SELECT MAX(idProducto) AS nextId FROM productos ORDER BY idProducto DESC");
		//if(valorId!= ErrCode.QUERY_ERROR) newIdProductos = obtenerId(db.getRs());
			
		valorId = db.executeSelectSQL("SELECT MAX(idPedido) AS nextId FROM pedidos ORDER BY idPedido DESC");
		if(valorId!= ErrCode.QUERY_ERROR) newIdPedido = obtenerId(db.getRs());
			
		return valorId;
	}
	
	private static int obtenerId(ResultSet rs) {
		try {
			rs.next();
			int valor = rs.getInt(1);
			return valor;
		}catch(SQLException e) {
			return ErrCode.QUERY_ERROR;
		}
	}

	public static String verIndices() {
		return "Indices: " + newIdCliente + " " + newIdProductos + " " + newIdPedido;
	}
	
	public static int getNewIdCliente() {
		return ++newIdCliente;
	}

	public static int getNewIdProductos() {
		return ++newIdProductos;
	}

	public static int getNewIdPedido() {
		return ++newIdPedido;
	}
	
}
