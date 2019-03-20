package Forms_Controlers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import application.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controler_FRM_Supermercado implements Initializable {
	ArrayList<Cajero> cajas = new ArrayList<Cajero>();
    
	   @FXML
	    private Label LBLTexto1_2;

	    @FXML
	    private Label TXTCajero2;

	    @FXML
	    private Label TXTCajero3_3;

	    @FXML
	    private Label LBLTexto1;

	    @FXML
	    private Label TXTCajero2_3;

	    @FXML
	    private Label TXTCajero3;

	    @FXML
	    private ProgressBar Bar_Two;

	    @FXML
	    private ProgressBar Bar_Three;

	    @FXML
	    private Label LBL_Porcentaje1;

	    @FXML
	    private Label TXTCajero2_2;
	    
	    @FXML
	    private Label TXTCajero3_2;

	    @FXML
	    private ProgressBar Bar_One;

	    @FXML
	    private Label LBLTexto1_1;
	    
	    @FXML
	    private ProgressIndicator PI_1;
	    @FXML
	    private ProgressIndicator PI_11;
	    @FXML
	    private ProgressIndicator PI_12;
	    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cajas.add(new Cajero("primera", Bar_One, PI_1));
    	cajas.add(new Cajero("segunda", Bar_Two, PI_11));
    	cajas.add(new Cajero("tercera", Bar_Three, PI_12));
    	    	
    	
    	cajas.get(0).setOnRunning((EventHandler) -> {
    		LBLTexto1.setText("Ventas Iniciadas");
    	});
    	cajas.get(1).setOnRunning((EventHandler) -> {
    		TXTCajero2.setText("Ventas Iniciadas");
    	});
    	cajas.get(2).setOnRunning((EventHandler) -> {
    		TXTCajero3.setText("Ventas Iniciadas");
    	});
    	
    	cajas.get(0).setOnSucceeded((EventHandler) ->{ 
			LBLTexto1.setText("Caja finalizada ");
			LBLTexto1_1.setText("Clientes tratados: " + cajas.get(0).getNumeroClientes());
			LBLTexto1_2.setText("Importe vendido: " + cajas.get(0).getSaldo());
			PI_1.setProgress(1);
			
		});
    	cajas.get(1).setOnSucceeded((EventHandler) ->{ 
			TXTCajero2.setText("Caja finalizada ");
			TXTCajero2_2.setText("Clientes tratados: " + cajas.get(1).getNumeroClientes());
			TXTCajero2_3.setText("Importe vendido: " + cajas.get(1).getSaldo());
			PI_11.setProgress(1);
		});
		
		cajas.get(2).setOnSucceeded((EventHandler) ->{ 
			TXTCajero3.setText("Caja finalizada");
			TXTCajero3_2.setText("Clientes tratados: " + cajas.get(2).getNumeroClientes());
			TXTCajero3_3.setText("Importe vendido: " + cajas.get(2).getSaldo());
			PI_12.setProgress(1);
		});	 
		
    	ExecutorService executorService = Executors.newFixedThreadPool(cajas.size());
    	cajas.forEach(c -> executorService.execute(c));
    	 
    	executorService.shutdown();
	
    }
}
