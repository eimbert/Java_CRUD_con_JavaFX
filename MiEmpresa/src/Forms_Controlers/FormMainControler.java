package Forms_Controlers;

import application.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;



public class FormMainControler implements Initializable {
	protected static CustomerOperations sqlClientes; 
	protected static ProductsOperations sqlProductos;
	protected ArrayList<ControladorPieChart> graficoCircular;
	protected ObservableList<TableColumn<?,?>> coleccionTabs;
	protected ArrayList<HashMap<String, Integer>> datosDelGrafico;
	protected static String comunidad = "";
	private Boolean esNuevoRegistro;
	private Boolean editando;
	private int tabIndex = 0;
	
	
	@FXML
	private Tab TAB_Productos;
    @FXML
    private Tab TAB_pedidos;
    @FXML
    private Tab TAB_Clientes;
	@FXML
    private BorderPane FRM_Main;	
    @FXML
    private TextField TXT_NombreCliente;
    @FXML
    private TitledPane PANE_FichaCliente;
    @FXML
    private DatePicker DTA_FechaAltaCliente;
    @FXML
    private Button BTN_GuardarCliente;
    @FXML
    private ComboBox<String> CMB_ComunidadCliente;
    @FXML
    private TextField TXT_ApellidosCliente;
    @FXML
    private TitledPane PANE_ListadoGeneral;
    @FXML
    private TableColumn<Client, String> TBL_FechaAlta;
    @FXML
    private MenuItem MNUNuevoProducto;
    @FXML
    private MenuItem MNUBuscarCliente;
    @FXML
    private MenuItem MNUListadoPedidos;
    @FXML
    private MenuItem MNUBorrarPedido;
    @FXML
    private PieChart charClientes;
    @FXML
    private TableView<Client> TBL_Clientes;
    @FXML
    private MenuItem MNUListadoGeneralProductos;
    @FXML
    private TableColumn<Client, String> TBL_Apellidos;
    @FXML
    private MenuItem MNUBuscarProducto;
    @FXML
    private MenuItem MNUModificarPedido;
    @FXML
    private TableColumn<Client, Integer> TBL_IdCliente;
    @FXML
    private MenuItem MNUNuevoCliente;
    @FXML
    private MenuItem MNUModificarCliente;
    @FXML
    private MenuItem MNUNuevoPedido;
    @FXML
    private MenuItem MNUModificarProducto;
    @FXML
    private MenuItem MNUBorrarCliente;
    @FXML
    private MenuItem MNUBuscarPedido;
    @FXML
    private MenuItem MNU_GrabarClientes;
    @FXML
    private TableColumn<Client, String> TBL_Nombre;
    @FXML
    private MenuItem MNUBorrarProducto;
    @FXML
    private TableColumn<Client, String> TBL_Comunidad;
    @FXML
    private MenuItem MNUListadoGeneralClientes;
    @FXML
    private CheckBox CHK_Filtro;
    @FXML
    private Accordion CTL_Accordion;
    @FXML
    private TextField TXT_IdCliente;
    @FXML
    private TextField TXT_FiltroCliente;
    @FXML
    private ContextMenu CMNU_MenuContextualCliente;    
    @FXML
    private MenuItem CMNU_BorrarCliente;
    @FXML
    private MenuItem CMNU_ModificarCliente;
    @FXML
    private TabPane TAB_ControlTabulador;
    @FXML
    private TextField TXT_Stock;
    @FXML
    private TextField TXT_Descripcion;
    @FXML
    private TableColumn<Product, String> COL_DescripcionProducto;
    @FXML
    private TitledPane PANE_FichaProductos;
    @FXML
    private TableColumn<Product, Integer> COL_IdProducto;
    @FXML
    private TableColumn<Product, String> COL_Producto;
    @FXML
    private Button BTN_GrabarProducto;
    @FXML
    private TableColumn<Product, Integer> COL_StockProducto;
    @FXML
    private TableView<Product> TBL_Productos;
    
    @FXML
    void contextualModificarCliente(ActionEvent event) {
		CTL_Accordion.setExpandedPane(PANE_FichaCliente);
		pasarDatosDeTableAFicha();
		esNuevoRegistro = false;
		editando=true;
    }

    @FXML
    void contextualBorrarCliente(ActionEvent event) {

    }
    
    @FXML
    void openFrmNewClient(ActionEvent event) throws Exception {
    	CTL_Accordion.setExpandedPane(PANE_FichaCliente);
    	esNuevoRegistro = true;
    }
    
    @FXML
    void saveChangesClients(ActionEvent event) {
    	sqlClientes.grabarDatos();
    	sqlClientes.grabarModificacionesClientes();
    }
    
    public FormMainControler() {
    	super();
    	esNuevoRegistro=true;
    	editando=false;
    	sqlClientes= new OperacionesSQLClientes();
    	sqlProductos = new OperacionesSQLProductos();
    	graficoCircular = new ArrayList<ControladorPieChart>();
    	datosDelGrafico = new ArrayList<HashMap<String, Integer>>();
    	sqlClientes.seleccionarClientes();    	
    	sqlProductos.seleccionarProductos();  // recuperar productos de la bd al array
    	datosDelGrafico.add(sqlClientes.agruparComunidades());
    	datosDelGrafico.add(sqlProductos.contarStockProductos());
    	
    	
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	CMB_ComunidadCliente.getItems().addAll("Andaluc�a", "Arag�n", "Islas Baleares", "Catalunya", "Canarias", "Cantabria", "Castilla-La Mancha", "Castilla y Le�n", 
    			"Comunidad de Madrid", "Comunidad Foral de Navarra", "Comunidad Valenciana", "Extremadura", "Galicia", 
    			"Pa�s Vasco", "Principado de Asturias", "Regi�n de Murcia", "La Rioja", "Ceuta", "Melilla");
    	
    	CTL_Accordion.setExpandedPane(PANE_ListadoGeneral);		
		TBL_Clientes.setContextMenu(CMNU_MenuContextualCliente);
		graficoCircular.add(new ControladorPieChart("Clientes por Comunidades", sqlClientes.agruparComunidades(), charClientes));
		graficoCircular.add(new ControladorPieChart("Stock Productos", sqlProductos.contarStockProductos(), charClientes));
		
		refrescarGrafico(tabIndex);	
		llenarTabViewClientes();
		llenarTabViewProductos();
		activarEventosDelFormulario();
		borrarCamposFormularioCliente();	
		
		//coleccionTabs.addAll(TBL_Clientes.getColumns());
    	//esNuevoRegistro=true;
	}
	
	public void refrescarGrafico(int ind) {
		graficoCircular.get(ind).refresh(datosDelGrafico.get(tabIndex));
		graficoCircular.get(tabIndex).setMouseCursor();
		graficoCircular.get(tabIndex).setLisener();
		
	}
	public void llenarTabViewProductos() {
		
		ObservableList<Product> data = FXCollections.observableList(sqlProductos.getProductos());
		
		asignarDataTabViewProductos(data);		
	
	}
	
	public void llenarTabViewClientes() {
		
		ObservableList<Client> data = FXCollections.observableList(sqlClientes.getClientes());
		
		if(comunidad!=""){
			data = FXCollections.observableList(data.stream().filter(c -> comunidad.equals(c.getComunidad())).collect(Collectors.toList()));
		}
		asignarDataTabView(data);		
	
	}
	
	public void llenarTabView(List<Client> datosFiltrados) {
		ObservableList<Client> data = FXCollections.observableList((ArrayList)datosFiltrados);
		asignarDataTabView(data);
	}
	
	private void asignarDataTabView(ObservableList<Client> data) {
		TBL_Clientes.setItems(data);
		
		TBL_IdCliente.setCellValueFactory(new  PropertyValueFactory<Client,Integer>("idcliente"));
		TBL_Nombre.setCellValueFactory(new PropertyValueFactory<Client,String>("nombre"));
		TBL_Apellidos.setCellValueFactory(new PropertyValueFactory<Client,String>("apellidos"));
		TBL_FechaAlta.setCellValueFactory(new PropertyValueFactory<Client,String>("fechaAlta"));	
		TBL_Comunidad.setCellValueFactory(new PropertyValueFactory<Client,String>("comunidad"));
	}
	
	private void asignarDataTabViewProductos(ObservableList<Product> data) {
		TBL_Productos.setItems(data);
		
		COL_IdProducto.setCellValueFactory(new  PropertyValueFactory<Product,Integer>("idproducto"));
		COL_Producto.setCellValueFactory(new  PropertyValueFactory<Product,String>("producto"));
		COL_DescripcionProducto.setCellValueFactory(new  PropertyValueFactory<Product,String>("descripcion"));
		COL_StockProducto.setCellValueFactory(new  PropertyValueFactory<Product,Integer>("stock"));
		
	}
	public void pasarDatosDeTableAFicha() {
		Client clienteSeleccionado = TBL_Clientes.getSelectionModel().getSelectedItem();
		
		TXT_IdCliente.setText(clienteSeleccionado.getIdCliente()+"");
		TXT_NombreCliente.setText(clienteSeleccionado.getNombre());
		TXT_ApellidosCliente.setText(clienteSeleccionado.getApellidos());
		DTA_FechaAltaCliente.setPromptText(clienteSeleccionado.getFechaAlta());
		CMB_ComunidadCliente.setValue(clienteSeleccionado.getComunidad());
	}
	
	
	/*************************************************************
	*** M�todo para agrupar todos los eventos del formulario *****
	**************************************************************/
	private void activarEventosDelFormulario() {
		//Evento del boton guardarCliente
		BTN_GuardarCliente.setOnAction((ActionEvent e) ->{
	    	if(esNuevoRegistro)
	    		sqlClientes.newClient(TXT_NombreCliente.getText(), TXT_ApellidosCliente.getText(), DTA_FechaAltaCliente.getValue().toString(), CMB_ComunidadCliente.getValue());
	    	else {
	    		LocalDate fecha = DTA_FechaAltaCliente.getValue();
	    		sqlClientes.updateClient(Integer.parseInt(TXT_IdCliente.getText()), TXT_NombreCliente.getText(), TXT_ApellidosCliente.getText(), fecha.toString(), CMB_ComunidadCliente.getValue());
	    	}
	    	llenarTabViewClientes();
	    	datosDelGrafico.set(tabIndex, sqlClientes.agruparComunidades());
	    	refrescarGrafico(tabIndex);
	    	CTL_Accordion.setExpandedPane(PANE_ListadoGeneral);
	    	borrarCamposFormularioCliente();
	    	editando=false;

		});
		//Evento del chequer para quitar filtro de la tabla de datos TableView
		CHK_Filtro.setOnMouseClicked(( MouseEvent mouseEvent)->{
			if(!CHK_Filtro.isSelected()) {
				comunidad="";
	            llenarTabViewClientes();
	            CHK_Filtro.setSelected(false);
	            CHK_Filtro.setVisible(false);
			}              
		});
		//Evento cuando se hace doble click en la tabla de datos clientes TableView
		TBL_Clientes.setOnMouseClicked(( MouseEvent mouseEvent) ->{
			if(mouseEvent.getClickCount()==2) {
				CTL_Accordion.setExpandedPane(PANE_FichaCliente);
				pasarDatosDeTableAFicha();
				esNuevoRegistro = false;
				editando=true;
			}
		});		
		//Evento Click en el tabview de listado general
		PANE_ListadoGeneral.setOnMouseClicked((MouseEvent mouseEvent) ->{
			esNuevoRegistro = true;
			editando=false;
			borrarCamposFormularioCliente();
		});		
		//Evento click en el panel de ficha de cliente del tabview
		PANE_FichaCliente.setOnMouseClicked((MouseEvent mouseEvent) ->{
			if(!editando) {
				borrarCamposFormularioCliente();
				esNuevoRegistro = true;
				editando=true;
			}		
		});	
		//Evento de pulsar tecla en el campo de texto de filtrar
		TXT_FiltroCliente.setOnKeyReleased((KeyEvent keyEvent) ->{
			comunidad="";
            CHK_Filtro.setSelected(false);
            CHK_Filtro.setVisible(false);
			if(TXT_FiltroCliente.getText().length()>0) {
				List<Client> newList= sqlClientes.getClientes().stream().filter( c -> c.getNombre().substring(0, TXT_FiltroCliente.getText().length()).toLowerCase().equals(TXT_FiltroCliente.getText().toLowerCase())).collect(Collectors.toList());
				llenarTabView(newList);
			}else
				llenarTabViewClientes();
		});
		
		TAB_ControlTabulador.setOnMouseClicked((MouseEvent event) ->{
			if(TAB_Clientes.isSelected()) tabIndex=0;
			else if(this.TAB_Productos.isSelected()) tabIndex=1;
			else if(this.TAB_pedidos.isSelected()) tabIndex=2;
			//mostrarGrafico(tabIndex);
			refrescarGrafico(tabIndex);	
		});
		
		charClientes.setOnMouseClicked((MouseEvent event)->{
			comunidad = graficoCircular.get(tabIndex).getDatoSectorSeleccionado();
			CHK_Filtro.setSelected(true);
	    	CHK_Filtro.setText("Mostrando clientes de " + comunidad + " desmarca para general");
	    	CHK_Filtro.setVisible(true);
	    	llenarTabViewClientes();
		});
   						
	}
	
	private void borrarCamposFormularioCliente() {
		this.TXT_IdCliente.setText("");
		this.TXT_NombreCliente.setText("");
		this.TXT_ApellidosCliente.setText("");
		this.DTA_FechaAltaCliente.setValue(LocalDate.now());
		this.CMB_ComunidadCliente.getSelectionModel().selectFirst();
	}	
	
	//Activa los eventos del gr�fico, se pone por separado para poderlo llamar en cada cambio de datos
//	private void activarEventosDelGrafico() {
		//Poner el cursor de mano cuando se entra en cada porci�n del gr�fico,
		//no es un evento, es una propiedad del nodo
		//for (final PieChart.Data data: charClientes.getData()) {
		//    data.getNode().setCursor(Cursor.HAND);
		//}
		
		//Evento de cada porci�n del gr�fico
		/*for (final PieChart.Data data: charClientes.getData()) {
		    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    	@Override public void handle(MouseEvent e) {
		    		comunidad = data.getName();
		    		CHK_Filtro.setSelected(true);
		            CHK_Filtro.setText("Mostrando clientes de " + comunidad + " desmarca para general");
		            CHK_Filtro.setVisible(true);
		            //mostrar valores de la region seleccionada.
		            try {
		            	llenarTabView();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
		    });
		}*/	
//	}
}
