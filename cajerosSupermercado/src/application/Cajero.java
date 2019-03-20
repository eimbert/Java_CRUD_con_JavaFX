package application;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class Cajero extends Task<Long>{
	private long numeroClientes = 0;
	private long inicioCajero;
	private String nombreCaja;
	private double saldo;
	private long clientesAtendidos;
	private long finCajero;
	long procesoVenta;
	ProgressBar p1, p2;
	ProgressIndicator pI;

			
	public Cajero(String nombreCaja, ProgressBar p1, ProgressIndicator pI) {
		this.nombreCaja = nombreCaja;
		this.saldo = 0;
		this.clientesAtendidos = 0;
		this.procesoVenta=0;
		this.p1 = p1;
		this.pI = pI;
	}
	
	
	public long getProcesoVenta() {
		return procesoVenta;
	}

	public long getTiempoInvertido() {
		return finCajero - inicioCajero;
	}
	public long getNumeroClientes() {
		return numeroClientes;
	}

	public String getNombreCaja() {
		return nombreCaja;
	}

	public double getSaldo() {
		return saldo;
	}

	public long getClientesAtendidos() {
		return clientesAtendidos;
	}

	public void vender() throws InterruptedException {
		saldo += new Random().nextInt(20)+1;
		clientesAtendidos++;
	
		
	}

	@Override
	protected  Long call() throws Exception {
		inicioCajero = System.currentTimeMillis();
		
		numeroClientes = 8000; //new Random().nextInt(10000)+1;
		Random random = new Random();
		long cambiarValor = new Random().nextInt(50)+1;
		long y=0;
		long espera = random.nextInt(5)+1;
		for(procesoVenta=0; procesoVenta<numeroClientes; procesoVenta++) {
			if (y++ > cambiarValor) {
				espera = random.nextInt(5)+1;
				y = 0;
				cambiarValor = new Random().nextInt(150)+1;
			}
			Double progreso = (double)procesoVenta/numeroClientes;
			
			p1.setProgress(progreso);
			pI.setProgress(progreso);
			
			Thread.sleep(espera);
			vender();
		}
		
		finCajero = System.currentTimeMillis();
		return null;
	}
}
