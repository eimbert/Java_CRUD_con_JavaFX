package application;

import java.sql.*;

public class ConnectorSQL {
	private Connection con = null;
	private Statement st;
	private ResultSet rs;
	
	private  int startConnection() {
		try {
			con = DriverManager.getConnection(ConstantsDB.dbConnection, ConstantsDB.dbUser, ConstantsDB.dbPassword);
			st = con.createStatement();
			return ErrCode.NO_ERROR;
		}catch(SQLException e) {
			return ErrCode.CONNECTION_ERROR;
		}
	}
	public int closeConnection() {
		try {
			con.close();
			st.close();
			return ErrCode.NO_ERROR;
		}catch(SQLException e) {
			return ErrCode.CONNECTION_ERROR;
		}
	}
	
	public  ResultSet getRs() {
		return rs;
	}
	
	/***
	 * Execute SQL for UPDATE, DELETE, INSERT commands
	 * @param insert SQL sentence
	 * @return if the command is ok or error code
	 */
	public int executeUpdateSQL(String insert) {
		try {
			if(startConnection()!=ErrCode.NO_ERROR) return ErrCode.CONNECTION_ERROR;
			int result=st.executeUpdate(insert);
			closeConnection();
			if (result == 0)
				return ErrCode.QUERY_ERROR;
			else
				return ErrCode.NO_ERROR;
		}catch(SQLException e) {
			return ErrCode.QUERY_ERROR;
		}
	}
	/**
	 * Execute SQL for SELECT command
	 * @param SQL sentence
	 * @return if the command is ok or error code
	 */
	public int executeSelectSQL(String consulta) {
		try {
			if(startConnection()!=ErrCode.NO_ERROR) return ErrCode.CONNECTION_ERROR;
			rs = st.executeQuery(consulta);
			return ErrCode.NO_ERROR;
		}catch(SQLException e) {
			return ErrCode.QUERY_ERROR;
		}
	}
}

