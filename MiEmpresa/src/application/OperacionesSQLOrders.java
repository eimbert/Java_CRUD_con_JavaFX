package application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperacionesSQLOrders extends OrdersOperations {

	
	
	public OperacionesSQLOrders() {
		super();
		
	}
	
	@Override
	public int seleccionarPedidos()  {
		ConnectorSQL db = new ConnectorSQL();
		int codErr = db.executeSelectSQL("SELECT * FROM pedidos");
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
			newOrder(rs.getInt("idpedido"), rs.getInt("idcliente"), rs.getInt("idproducto"), rs.getInt("cantidad"));
		}
	}
	
	@Override
	public int grabarPedidos() {
		ConnectorSQL db = new ConnectorSQL();
		for(Order cl: super.pedidos) {
			if (!cl.getIsSaved()) {
				int codErr;
				codErr = db.executeUpdateSQL("INSERT INTO pedidos (idcliente, idproducto, cantidad) VALUES ('" + cl.getIdCliente() + "','" + cl.getIdProducto()+"',"+ cl.getCantidad());
				if (codErr == ErrCode.NO_ERROR) {
					cl.setIsSaved(true);
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


	@Override
	public int grabarModificacionesPedidos() {
		ConnectorSQL db = new ConnectorSQL();
		for(Order cl: super.pedidos) {
			if (cl.getIsUpdated()) {
				int codErr;
				codErr = db.executeUpdateSQL("UPDATE pedidos SET idcliente='"+cl.getIdCliente()+"',idproducto='"+cl.getIdProducto()+"', cantidad="+ cl.getCantidad() + " WHERE idpedido="+ cl.getIdPedido());
				if (codErr == ErrCode.NO_ERROR) {
					cl.setIsUpdated(false);
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

}
