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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;



public class FormMainControler implements Initializable {
	protected static CustomerOperations sqlClientes; 
	protected static ProductsOperations sqlProductos;
	protected static OrdersOperations sqlOrders;
	protected static ArrayList<OrderTemp> orderTemp;
	protected ArrayList<PieChartControler> graficoCircular;
	protected ObservableList<TableColumn<?,?>> coleccionTabs;
	//protected ArrayList<HashMap<String, Integer>> datosDelGrafico;
	private String comunidad = "";
	private Boolean esNuevoRegistro, editando, esNuevoProducto, editandoProducto;
	private int tabIndex = 0;
	
	//************************************************* Opciones de Menu
	@FXML
    private BorderPane FRM_Main;
	@FXML
    private MenuItem MNUNuevoProducto;
    @FXML
    private MenuItem MNUBuscarCliente;
    @FXML
    private MenuItem MNUListadoPedidos;
    @FXML
    private MenuItem MNUBorrarPedido;
    @FXML
    private MenuItem MNUListadoGeneralProductos;
    @FXML
    private MenuItem MNUBuscarProducto;
    @FXML
    private MenuItem MNUModificarPedido;
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
    private MenuItem MNUBorrarProducto;
    @FXML
    private MenuItem MNUListadoGeneralClientes;
    @FXML
    private ContextMenu CMNU_MenuContextualCliente;   
    @FXML
    private MenuItem CMNU_BorrarCliente;
    @FXML
    private MenuItem CMNU_ModificarCliente;
    //************************************************* Panel Clientes
    @FXML
    protected Tab TAB_Clientes;
    @FXML
    private TitledPane PANE_FichaCliente;
    @FXML
    protected DatePicker DTA_FechaAltaCliente;
    @FXML
    protected Button BTN_GuardarCliente;
    @FXML
    protected TextField TXT_NombreCliente;
    @FXML
    protected ComboBox<String> CMB_ComunidadCliente;
    @FXML
    protected TextField TXT_ApellidosCliente;
    @FXML
    private TitledPane PANE_ListadoGeneral;
    @FXML
    protected TableColumn<Client, String> TBL_FechaAlta;
    @FXML
    protected TableView<Client> TBL_Clientes;
    @FXML
    protected TableColumn<Client, String> TBL_Apellidos;
    @FXML
    protected TableColumn<Client, Integer> TBL_IdCliente;
    @FXML
    protected TableColumn<Client, String> TBL_Nombre;
    @FXML
    protected TableColumn<Client, String> TBL_Comunidad;
    @FXML
    private CheckBox CHK_Filtro;
    @FXML
    private Accordion CTL_Accordion;
    @FXML
    protected TextField TXT_IdCliente;
    @FXML
    protected TextField TXT_FiltroCliente;
    //************************************************* Panel Productos
   	@FXML
	protected Tab TAB_Productos;
    @FXML
    protected Tab TAB_pedidos;
    @FXML
    protected TextField TXT_Stock;
    @FXML
    protected TextField TXT_Descripcion;
    @FXML
    protected TableColumn<Product, String> COL_DescripcionProducto;
    @FXML
    private TitledPane PANE_FichaProductos;
    @FXML
    protected TableColumn<Product, Integer> COL_IdProducto;
    @FXML
    protected TableColumn<Product, String> COL_Producto;
    @FXML
    protected Button BTN_GrabarProducto;
    @FXML
    protected TableColumn<Product, Integer> COL_StockProducto;
    @FXML
    protected TableView<Product> TBL_Productos;
    @FXML
    private Accordion PRO_Accordion;
    @FXML
    private TitledPane PANE_ListadoProductos;
    @FXML
    protected Label TXT_IdProducto;
    @FXML
    protected TextField TXT_Producto;
    //************************************************* Panel Pedidos
    @FXML
    protected TextField TXT_CantidadPedido;
    @FXML
    protected TableView<Client> Tableview_ClientesPedidos;
    @FXML
    protected TableColumn<Client, Integer> ColumnIdClientePedidos;
    @FXML
    protected TableColumn<Product, Integer> ColumnIdProductoPedido;
    @FXML
    protected TableView<Product> TableView_ProductosPedidos;
    @FXML
    protected TextField TXT_BuscarProductoPedido;
    @FXML
    protected TextField TXT_BuscarClientePedidos;
    @FXML
    protected TableColumn<Product, String> ColumnProductoPedido;
    @FXML
    protected TableColumn<Client, String> ColumnNombrePedidos;
    @FXML
    protected TableColumn<Product, Integer> ColumnStockPedidos;
    @FXML
    protected TableColumn<Client, String> ColumnApellidosPedido;
    @FXML
    private Button BTN_NuevoPedido;
    //************************************************* Controles Tab ListadoPedidos
    @FXML
    protected TableColumn<OrderTemp, String> descripcion_ListadoPedidos;
    @FXML
    protected TableColumn<OrderTemp, String> producto_ListadoPedidos;
    @FXML
    protected TableColumn<OrderTemp, Integer> cantidad_ListadoPedidos;
    @FXML
    protected TableColumn<OrderTemp, Integer> IdPedido_ListadoPedidos;
    @FXML
    protected TableView<OrderTemp> TableView_ListadoPedidos;
    @FXML
    protected TableColumn<OrderTemp, String> nombre_ListadoPedidos;
    @FXML
    protected TableColumn<OrderTemp, String> apellidos_ListadoPedidos;
    
    //************************************************* Controles Generales  
    @FXML
    private PieChart charClientes;
    @FXML
    private TabPane TAB_ControlTabulador;   
    @FXML
    private HBox HBoxControl;
    
    @FXML
    void contextualModificarCliente(ActionEvent event) {
		if(TBL_Clientes.isFocused()) {
			CTL_Accordion.setExpandedPane(PANE_FichaCliente);
			AuxTabClient.pasarDatosDeTableAFicha(this);
			esNuevoRegistro = false;
			editando=true;
		}else if(TBL_Productos.isFocused()) {
			PRO_Accordion.setExpandedPane(PANE_FichaProductos);
			AuxTabProduct.pasarDatosDeTableAFichaProductos(this);
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
    	SerialsId.obtenerSerialsId();
    	esNuevoRegistro=esNuevoProducto=true;
    	editando=editandoProducto=false;
    	sqlClientes= new OperacionesSQLClientes();
    	sqlProductos = new OperacionesSQLProductos();
    	sqlOrders = new OperacionesSQLOrders();
    	orderTemp = new ArrayList<OrderTemp>();
    	graficoCircular = new ArrayList<PieChartControler>();
    	//datosDelGrafico = new ArrayList<HashMap<String, Integer>>();
    	
    	sqlClientes.seleccionarClientes();    	
    	sqlProductos.seleccionarProductos();  // recuperar productos de la bd al array
    	sqlOrders.seleccionarPedidos();
    
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	CMB_ComunidadCliente.getItems().addAll("Andalucía", "Aragón", "Islas Baleares", "Catalunya", "Canarias", "Cantabria", "Castilla-La Mancha", "Castilla y León", 
    			"Comunidad de Madrid", "Comunidad Foral de Navarra", "Comunidad Valenciana", "Extremadura", "Galicia", 
    			"País Vasco", "Principado de Asturias", "Región de Murcia", "La Rioja", "Ceuta", "Melilla");
    	
    	TableView_ListadoPedidos.setVisible(false);
    	CTL_Accordion.setExpandedPane(PANE_ListadoGeneral);		
    	PRO_Accordion.setExpandedPane(PANE_ListadoProductos);
		TBL_Clientes.setContextMenu(CMNU_MenuContextualCliente);
		TBL_Productos.setContextMenu(CMNU_MenuContextualCliente);
		graficoCircular.add(new PieChartControler("Clientes por Comunidades", sqlClientes.agruparComunidades(), charClientes, true));
		graficoCircular.add(new PieChartControler("Stock Productos", sqlProductos.contarStockProductos(), charClientes, false));
		
		graficoCircular.get(tabIndex).refresh();	
		
		AuxTabClient.llenarTabViewClientes(this, sqlClientes.getClientes(), comunidad);
		AuxTabProduct.llenarTabViewProductos(this, sqlProductos.getProductos());
		activarEventosDelFormularioClientes();
		AuxTabClient.borrarCamposFormularioCliente(this);	
	}
    
    public static void grabarCambios() {
    	sqlClientes.grabarDatos();
    	sqlClientes.grabarModificacionesClientes();
    	sqlProductos.grabarProductos();
    	sqlProductos.grabarModificacionesProductos();
    	sqlOrders.grabarPedidos();
    	sqlOrders.grabarModificacionesPedidos();
    }
	
	/**
	 * ** Método para agrupar todos los eventos del formulario ****
	 */
	
	private void activarEventosDelFormularioClientes() {
		/**
		 *  Evento del boton guardarCliente 
		 */
		BTN_GuardarCliente.setOnAction((ActionEvent e) ->{
	    	if(esNuevoRegistro)
	    		sqlClientes.newClient(TXT_NombreCliente.getText(), TXT_ApellidosCliente.getText(), DTA_FechaAltaCliente.getValue().toString(), CMB_ComunidadCliente.getValue());
	    	else {
	    		LocalDate fecha = DTA_FechaAltaCliente.getValue();
	    		sqlClientes.updateClient(Integer.parseInt(TXT_IdCliente.getText()), TXT_NombreCliente.getText(), TXT_ApellidosCliente.getText(), fecha.toString(), CMB_ComunidadCliente.getValue());
	    	}
	    	AuxTabClient.llenarTabViewClientes(this, sqlClientes.getClientes(), comunidad);
	    	graficoCircular.get(tabIndex).setStreamDatos(sqlClientes.agruparComunidades());
	    	graficoCircular.get(tabIndex).refresh();
	    	CTL_Accordion.setExpandedPane(PANE_ListadoGeneral);
	    	AuxTabClient.borrarCamposFormularioCliente(this);
	    	editando=false;

		});
		/**
		 *  Evento boton guardar producto 
		 */
		BTN_GrabarProducto.setOnAction((ActionEvent e) -> {
			if(esNuevoProducto)
				sqlProductos.newProduct(TXT_Producto.getText(), TXT_Descripcion.getText(), Integer.parseInt(TXT_Stock.getText()));
			else
				sqlProductos.updateProduct(Integer.parseInt(TXT_IdProducto.getText()), TXT_Producto.getText(), TXT_Descripcion.getText(), Integer.parseInt(TXT_Stock.getText()));
			AuxTabProduct.llenarTabViewProductos(this, sqlProductos.getProductos());
			graficoCircular.get(tabIndex).refresh();
			PRO_Accordion.setExpandedPane(PANE_ListadoProductos);
			AuxTabProduct.borrarCamposFormularioProducto(this);
			editandoProducto=false;
		});
		/**
		 *  Evento del chequer para quitar filtro de la tabla de datos TableView 
		 */
		CHK_Filtro.setOnMouseClicked(( MouseEvent mouseEvent)->{
			if(!CHK_Filtro.isSelected()) {
				comunidad="";
				AuxTabClient.llenarTabViewClientes(this, sqlClientes.getClientes(), comunidad);
	            CHK_Filtro.setSelected(false);
	            CHK_Filtro.setVisible(false);
			}              
		});
		/**
		 *  Evento cuando se hace doble click en la tabla de datos clientes TableView 
		 */
		TBL_Clientes.setOnMouseClicked(( MouseEvent mouseEvent) ->{
			if(mouseEvent.getClickCount()==2) {
				CTL_Accordion.setExpandedPane(PANE_FichaCliente);
				AuxTabClient.pasarDatosDeTableAFicha(this);
				esNuevoRegistro = false;
				editando=true;
			}
		});
		/**
		 *  Evento Click en el tabview de listado general 
		 */
		PANE_ListadoGeneral.setOnMouseClicked((MouseEvent mouseEvent) ->{
			esNuevoRegistro = true;
			editando=false;
			AuxTabClient.borrarCamposFormularioCliente(this);
		});		
		/**
		 *  Evento click en el panel de ficha de cliente del tabview 
		 */
		PANE_FichaCliente.setOnMouseClicked((MouseEvent mouseEvent) ->{
			if(!editando) {
				AuxTabClient.borrarCamposFormularioCliente(this);
				esNuevoRegistro = true;
				editando=true;
			}		
		});	
		/**
		 *  Evento de pulsar tecla en el campo de texto de filtrar
		 */
		TXT_FiltroCliente.setOnKeyReleased((KeyEvent keyEvent) ->{
			comunidad="";
            CHK_Filtro.setSelected(false);
            CHK_Filtro.setVisible(false);
			if(TXT_FiltroCliente.getText().length()>0) {
				List<Client> newList= sqlClientes.getClientes().stream().filter( c -> c.getNombre().substring(0, TXT_FiltroCliente.getText().length()).toLowerCase().equals(TXT_FiltroCliente.getText().toLowerCase())).collect(Collectors.toList());
				AuxTabClient.llenarTabView(this, newList);
			}else
				AuxTabClient.llenarTabViewClientes(this, sqlClientes.getClientes(), comunidad);
		});
		
		/**
		 **  Evento control TabView (clientes, productos, pedidos) 
		 */
		TAB_ControlTabulador.setOnMouseClicked((MouseEvent event) ->{
			if(TAB_Clientes.isSelected()) { //Seleccionado Tab de Clientes
				tabIndex=0;
				graficoCircular.get(tabIndex).refresh();
				TableView_ListadoPedidos.setVisible(false);
				comunidad="";
				AuxTabClient.llenarTabViewClientes(this, sqlClientes.getClientes(), comunidad);
			}
			else if(this.TAB_Productos.isSelected()) { // Seleccionado Tab de Productos
				tabIndex=1;
				graficoCircular.get(tabIndex).refresh();
				TableView_ListadoPedidos.setVisible(false);
				CHK_Filtro.setVisible(false);

			}else if(this.TAB_pedidos.isSelected()) { // Seleccionar Tab de Pedidos
				CHK_Filtro.setVisible(false);
				TableView_ListadoPedidos.setVisible(true);
				AuxTabPedidos.cargarClientes(this, sqlClientes.getClientes());
				AuxTabPedidos.cargarProductos(this, sqlProductos.getProductos());
				AuxTabListadoPedidos.pasarPedidoAOrderTemp(orderTemp, sqlOrders.getProductos(), sqlClientes.getClientes(), sqlProductos.getProductos());
				AuxTabListadoPedidos.cargarPedidos(this, orderTemp);
			}
		});
		/**
		 *  Evento controlar el click del ratón sobre el gráfico
		 */
		charClientes.setOnMouseClicked((MouseEvent event)->{
			comunidad = graficoCircular.get(tabIndex).getDatoSectorSeleccionado();
			if(comunidad!=null) {
				CHK_Filtro.setSelected(true);
				CHK_Filtro.setText("Mostrando clientes de " + comunidad + " desmarca para general");
				CHK_Filtro.setVisible(true);
				AuxTabClient.llenarTabViewClientes(this, sqlClientes.getClientes(), comunidad);
			}
		});		
		/**
		 *  Evento sobre el boton de nuevo pedido
		 */
		BTN_NuevoPedido.setOnMouseClicked((MouseEvent event) -> {
			if(AuxTabPedidos.comprbarAntesDePedido(this)) {
				int idCliente = Tableview_ClientesPedidos.getSelectionModel().getSelectedItem().getIdCliente();
				int idProducto = TableView_ProductosPedidos.getSelectionModel().getSelectedItem().getIdProducto();
				int cantidad = Integer.parseInt(TXT_CantidadPedido.getText());
				int auxNumPedido = SerialsId.getNewIdPedido();
				sqlOrders.newOrder(auxNumPedido, idCliente, idProducto, cantidad);
				orderTemp.add(new OrderTemp(auxNumPedido,sqlClientes.buscarCliente(idCliente).getNombre(),
													 	 sqlClientes.buscarCliente(idCliente).getApellidos(), 
													 	 sqlProductos.buscarProducto(idProducto).getProducto(), 
													 	 sqlProductos.buscarProducto(idProducto).getDescripcion(), 
													 	 cantidad));
				AuxTabListadoPedidos.cargarPedidos(this, orderTemp);
				AuxTabPedidos.borrarFormulario(this);
			}
		});
	}
}

