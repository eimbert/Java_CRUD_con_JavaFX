package application;

public class ErrCode {
	final static int NO_ERROR = 0;
	final static int CONNECTION_ERROR = -1;
	final static int QUERY_ERROR = -2;
	
	public static void mostrarMsgPantalla(int s) {
		switch(s) {
			case ErrCode.NO_ERROR:
				System.out.println("Opercion realizada correctamente.");
				break;
			case ErrCode.CONNECTION_ERROR:
				System.out.println("Error conexión a la base de datos.");
				break;
			case ErrCode.QUERY_ERROR:
				System.out.println("Error en la operación con la base de datos.");
				break;
			default:
				System.out.println("Error desconocido.");
				break;
		}
	}
}

