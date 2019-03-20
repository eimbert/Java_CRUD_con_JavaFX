package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class OperacionesSQLProductos extends ProductsOperations {

	public  OperacionesSQLProductos() {
		super();
	}
	
	public int seleccionarProductos()  {
		ConnectorSQL db = new ConnectorSQL();
		int codErr = db.executeSelectSQL("SELECT * FROM productos");
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
			newProduct(rs.getInt("idproducto"), rs.getString("producto"), rs.getString("descripcion"), rs.getInt("stock"));
		}
	}	
	
	public int grabarProductos() {
		ConnectorSQL db = new ConnectorSQL();
		for(Product cl: super.productos) {
			if (!cl.getIsSave()) {
				int codErr;
				codErr = db.executeUpdateSQL("INSERT INTO productos (producto, descripcion, stock) VALUES ('" + cl.getProducto() + "','" + cl.getDescripcion()+"',"+ cl.getStock());
				if (codErr == ErrCode.NO_ERROR) {
					cl.setIsSave(true);
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
	
	public int grabarModificacionesProductos() {
		ConnectorSQL db = new ConnectorSQL();
		for(Product cl: super.productos) {
			if (cl.getIsUpdated()) {
				int codErr;
				codErr = db.executeUpdateSQL("UPDATE productos SET producto='"+cl.getProducto()+"',descripcion='"+cl.getDescripcion()+"', stock="+ cl.getDescripcion() + " WHERE idproducto="+ cl.getIdProducto());
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
