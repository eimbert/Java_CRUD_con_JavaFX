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
import javafx.scene.control.Label;
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
	private Boolean esNuevoRegistro, editando, esNuevoProducto, editandoProducto;
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
    private Accordion PRO_Accordion;
    @FXML
    private TitledPane PANE_ListadoProductos;
    @FXML
    private Label TXT_IdProducto;
    @FXML
    private TextField TXT_Producto;


    
    @FXML
    void contextualModificarCliente(ActionEvent event) {
		if(TBL_Clientes.isFocused()) {
			CTL_Accordion.setExpandedPane(PANE_FichaCliente);
			pasarDatosDeTableAFicha();
			esNuevoRegistro = false;
			editando=true;
		}else if(TBL_Productos.isFocused()) {
			PRO_Accordion.setExpandedPane(PANE_FichaProductos);
			pasarDatosDeTableAFichaProductos();
			esNuevoProducto = false;
			editandoProducto=true;
		}
    }

    @FXML
    void contextualBorrarCliente(ActionEvent event) {

    }
    
    @FXML
    void openFrmNewClient(ActionEvent event)  {
    	TAB_ControlTabulador.getSelectionModel().select(0);    	
    	CTL_Accordion.setExpandedPane(PANE_FichaCliente);
    	esNuevoRegistro = true;
    }
    
    @FXML
    void openFrmNewProduct(ActionEvent event)  {
    	TAB_ControlTabulador.getSelectionModel().select(1);
    	PRO_Accordion.setExpandedPane(PANE_FichaProductos);
    	esNuevoProducto = true;
    }
    
    @FXML
    void saveChangesClients(ActionEvent event) {
    	sqlClientes.grabarDatos();
    	sqlClientes.grabarModificacionesClientes();
    }
    
    public FormMainControler() {
    	super();
    	esNuevoRegistro=esNuevoProducto=true;
    	editando=editandoProducto=false;
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
    	CMB_ComunidadCliente.getItems().addAll("Andalucía", "Aragón", "Islas Baleares", "Catalunya", "Canarias", "Cantabria", "Castilla-La Mancha", "Castilla y León", 
    			"Comunidad de Madrid", "Comunidad Foral de Navarra", "Comunidad Valenciana", "Extremadura", "Galicia", 
    			"País Vasco", "Principado de Asturias", "Región de Murcia", "La Rioja", "Ceuta", "Melilla");
    	
    	CTL_Accordion.setExpandedPane(PANE_ListadoGeneral);		
    	PRO_Accordion.setExpandedPane(PANE_ListadoProductos);
		TBL_Clientes.setContextMenu(CMNU_MenuContextualCliente);
		TBL_Productos.setContextMenu(CMNU_MenuContextualCliente);
		graficoCircular.add(new ControladorPieChart("Clientes por Comunidades", sqlClientes.agruparComunidades(), charClientes, true));
		graficoCircular.add(new ControladorPieChart("Stock Productos", sqlProductos.contarStockProductos(), charClientes, false));
		
		refrescarGrafico(tabIndex);	
		llenarTabViewClientes();
		llenarTabViewProductos();
		activarEventosDelFormularioClientes();
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
	private void pasarDatosDeTableAFicha() {
		Client clienteSeleccionado = TBL_Clientes.getSelectionModel().getSelectedItem();
		
		TXT_IdCliente.setText(clienteSeleccionado.getIdCliente()+"");
		TXT_NombreCliente.setText(clienteSeleccionado.getNombre());
		TXT_ApellidosCliente.setText(clienteSeleccionado.getApellidos());
		DTA_FechaAltaCliente.setPromptText(clienteSeleccionado.getFechaAlta());
		CMB_ComunidadCliente.setValue(clienteSeleccionado.getComunidad());
	}
	
	private void pasarDatosDeTableAFichaProductos() {
		Product productoSeleccionado = TBL_Productos.getSelectionModel().getSelectedItem();
		
		TXT_IdProducto.setText(productoSeleccionado.getIdProducto()+"");
		TXT_Producto.setText(productoSeleccionado.getProducto());
		TXT_Descripcion.setText(productoSeleccionado.getDescripcion());
		TXT_Stock.setText(productoSeleccionado.getStock()+"");
		
	}
	
	/*************************************************************
	*** Método para agrupar todos los eventos del formulario *****
	**************************************************************/
	//Eventos de CLientes ****************************************
	private void activarEventosDelFormularioClientes() {
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
		//Evento boton guardar producto
		BTN_GrabarProducto.setOnAction((ActionEvent e) -> {
			if(esNuevoProducto)
				sqlProductos.newProduct(TXT_Producto.getText(), TXT_Descripcion.getText(), Integer.parseInt(TXT_Stock.getText()));
			else
				sqlProductos.updateProduct(Integer.parseInt(TXT_IdProducto.getText()), TXT_Producto.getText(), TXT_Descripcion.getText(), Integer.parseInt(TXT_Stock.getText()));
			llenarTabViewProductos();
			refrescarGrafico(tabIndex);
			PRO_Accordion.setExpandedPane(PANE_ListadoProductos);
			borrarCamposFormularioProducto();
			editandoProducto=false;
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
			if(comunidad!=null) {
				CHK_Filtro.setSelected(true);
				CHK_Filtro.setText("Mostrando clientes de " + comunidad + " desmarca para general");
				CHK_Filtro.setVisible(true);
				llenarTabViewClientes();
			}
		});
   						
	}
	
	private void borrarCamposFormularioCliente() {
		this.TXT_IdCliente.setText("");
		this.TXT_NombreCliente.setText("");
		this.TXT_ApellidosCliente.setText("");
		this.DTA_FechaAltaCliente.setValue(LocalDate.now());
		this.CMB_ComunidadCliente.getSelectionModel().selectFirst();
	}	
	
	private void borrarCamposFormularioProducto() {
		this.TXT_IdProducto.setText("");
		this.TXT_Producto.setText("");
		this.TXT_Descripcion.setText("");
		this.TXT_Stock.setText("");
	}	
}

