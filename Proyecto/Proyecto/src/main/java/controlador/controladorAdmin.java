package controlador;

import Modelo.Modulo;
import Modelo.Permiso;
import Modelo.Persona;
import Modelo.Pista;
import Modelo.Rol;
import Modelo.Sucursal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

public class controladorAdmin implements Initializable {

    Connection conexion;
    controladorLogin cl;
    
    int rol = 1;
    Persona persona;
    
    public boolean visible(int rol){
        if(rol == 1){
            return true;
        }else{
            return false;
        } 
    }
    
    ///////////////////////////Elementos de la vista///////////////////////////
    @FXML
    private Label message;
    
    @FXML
    private TabPane tbGeneral;
    
    //################################Sucursal################################//
    
    
    @FXML
    private Tab tabSucursal;

    @FXML
    private FlowPane accionesSucursal;
    
    @FXML
    private TextField tfIdSucursal;

    @FXML
    private TextField tfCiudad;

    @FXML
    private TextField tfCodigoPostal;

    @FXML
    private TextField tfDireccion;

    @FXML
    private TextField tfTelSucursal;

    @FXML
    private Button btnInsertarSucursal;

    @FXML
    private Button btnActualizarSucursal;

    @FXML
    private Button btnEliminarSucursal;

    @FXML
    private TableView<Sucursal> tvSucursal;

    @FXML
    private TableColumn<Sucursal, String> tcCiudad;

    @FXML
    private TableColumn<Sucursal, Integer> tcCodPos;

    @FXML
    private TableColumn<Sucursal, Integer> tcTelSucu;

    @FXML
    private TableColumn<Sucursal, String> tcDireccion;

    @FXML
    private TableColumn<Sucursal, Integer> tcIDSucursal;

    //################################Reservas################################//
    @FXML
    private TextField tfidPistaReserva;

    @FXML
    private TextField tfDNIReserva;

    @FXML
    private TextField tfHoraIni;

    @FXML
    private TextField tfDuracion;

    @FXML
    private TextField tfFecha;

    @FXML
    private Button btnInsertarReserva;

    @FXML
    private Button btnActualizarReserva;

    @FXML
    private Button btnEliminarReserva;

    @FXML
    private TableView<?> tvReservas;

    @FXML
    private TableColumn<?, ?> tcIDPistaReserva;

    @FXML
    private TableColumn<?, ?> tcDNIReserva;

    @FXML
    private TableColumn<?, ?> tcHoraIni;

    @FXML
    private TableColumn<?, ?> tcDuracion;

    @FXML
    private TableColumn<?, ?> tcfecha;

    //################################Pistas################################//
    @FXML
    private TextField tfIdPista;

    @FXML
    private TextField tfActividad;

    @FXML
    private TextField tfPrecioH;

    @FXML
    private Button btnActualizarPista;

    @FXML
    private Button btnEliminarPista;

    @FXML
    private Button btnInsertarPista;

    @FXML
    private TableView<Pista> tvPistas;

    @FXML
    private TableColumn<Pista, Integer> tcIdPista;

    @FXML
    private TableColumn<Pista, Integer> tcPrecioH;

    @FXML
    private TableColumn<Pista, String> tcActividad;

    //################################Usuarios################################//
    @FXML
    private TextField tfDNIUsu;

    @FXML
    private TextField tfNombreUsu;

    @FXML
    private TextField tfApellidosUsu;

    @FXML
    private TextField tfCorreoUsu;

    @FXML
    private TextField tfTelUsu;

    @FXML
    private TextField tfUsuario;

    @FXML
    private Button btnInsertarUsuario;

    @FXML
    private Button btnActualizarUsuario;

    @FXML
    private Button btnEliminarUsuario;

    @FXML
    private TableView<?> tvUsuarios;

    @FXML
    private TableColumn<?, ?> tcDNIUsu;

    @FXML
    private TableColumn<?, ?> tcNombreUsu;

    @FXML
    private TableColumn<?, ?> tcApellidosUsu;

    @FXML
    private TableColumn<?, ?> tcCorreoUsu;

    @FXML
    private TableColumn<?, ?> tcTelUsu;

    @FXML
    private TableColumn<?, ?> tcUsu;

    //################################Empleados################################//
    @FXML
    private TextField tfDNIEmple;
    @FXML
    private TextField tfNombreEmple;
    @FXML
    private TextField tfApellidosEmple;

    @FXML
    private TextField tfCorreoEmple;

    @FXML
    private TextField tfTelEmple;

    @FXML
    private TextField tfSueldo;
    @FXML
    private Button btnInsertarEmple;
    @FXML
    private Button btnActualizarEmple;

    @FXML
    private Button btnEliminarEmple;

    @FXML
    private TableView<?> tvEmpleados;
    @FXML
    private TableColumn<?, ?> tcDNIEmple;
    @FXML
    private TableColumn<?, ?> tcNombreEmple;
    @FXML
    private TableColumn<?, ?> tcApellidosEmple;

    @FXML
    private TableColumn<?, ?> tcCorreoEmple;

    @FXML
    private TableColumn<?, ?> tcTelefonoEmple;

    @FXML
    private TableColumn<?, ?> tcSueldo;

    //################################Mantenimiento################################//
    @FXML
    private TextField tfDNIManteni;

    @FXML
    private TextField tfIdPistaManteni;

    @FXML
    private TextField tfTipoT;

    @FXML
    private Button btnInsertarManteni;

    @FXML
    private Button btnActualizarManteni;

    @FXML
    private Button btnEliminarManteni;

    @FXML
    private TableView<?> tvMantenimiento;

    @FXML
    private TableColumn<?, ?> tcDNIManteni;

    @FXML
    private TableColumn<?, ?> tcIdPistaManteni;

    @FXML
    private TableColumn<?, ?> tcTipoT;

    //################################Rol################################//
    @FXML
    private TextField tfIdRol;

    @FXML
    private TextField tfDenominacion;

    @FXML
    private Button btnInsertarRol;
    
    @FXML
    private Button btnActualizarRol;

    @FXML
    private Button btnEliminarRol;

    @FXML
    private TableView<Rol> tvRol;

    @FXML
    private TableColumn<Rol, Integer> tcIdRol;

    @FXML
    private TableColumn<Rol, String> tcDenominacion;

//################################Modulo################################//
    @FXML
    private TextField tfIdModulo;

    @FXML
    private TextField tfModulo;

    @FXML
    private Button btnInsertarModulo;
    
    @FXML
    private Button btnActualizarModulo;

    @FXML
    private Button btnEliminarModulo;

    @FXML
    private TableView<Modulo> tvModulo;

    @FXML
    private TableColumn<Modulo, Integer> tcIdModulo;

    @FXML
    private TableColumn<Modulo, String> tcModulo;

    //################################Modulo################################//
    @FXML
    private TextField tfIdModuloPer;

    @FXML
    private TextField tfIdRolPer;

    @FXML
    private TextField tfPermisos;
    
    @FXML
    private Button btnInsertarPermiso;
    
    @FXML
    private Button btnActualizarPermiso;

    @FXML
    private Button btnEliminarPermiso;

    @FXML
    private TableView<Permiso> tvPermisos;

    @FXML
    private TableColumn<Permiso, Integer> tcIdModuloPer;

    @FXML
    private TableColumn<Permiso, Integer> tcIdRolPer;
    
    @FXML
    private TableColumn<Permiso, String> tcPermisos;
    ///////////////////////////Eventos de la vista///////////////////////////
    @FXML
    void actualizarEmple(ActionEvent event) {

    }

    @FXML
    void actualizarManteni(ActionEvent event) {

    }

    @FXML
    void actualizarPista(ActionEvent event) {

    }

    @FXML
    void actualizarReserva(ActionEvent event) {

    }

    @FXML
    void actualizarSucursal(ActionEvent event) {

    }

    @FXML
    void actualizarUsuario(ActionEvent event) {

    }

    @FXML
    void eliminarEmple(ActionEvent event) {

    }

    @FXML
    void eliminarManteni(ActionEvent event) {

    }

    @FXML
    void eliminarPista(ActionEvent event) {

    }

    @FXML
    void eliminarReserva(ActionEvent event) {

    }

    @FXML
    void eliminarUsuario(ActionEvent event) {

    }

    @FXML
    void insertarEmple(ActionEvent event) {

    }

    @FXML
    void insertarManteni(ActionEvent event) {

    }

    @FXML
    void insertarPista(ActionEvent event) {

    }

    @FXML
    void insertarReserva(ActionEvent event) {

    }

    @FXML
    void insertarSucursal(ActionEvent event) {

    }

    @FXML
    void insertarUsuario(ActionEvent event) {

    }

    @FXML
    void insertarRol(ActionEvent event) {

    }

    @FXML
    void actualizarRol(ActionEvent event) {

    }

    @FXML
    void eliminarRol(ActionEvent event) {

    }

    @FXML
    void insertarModulo(ActionEvent event) {

    }

    @FXML
    void actualizarModulo(ActionEvent event) {

    }

    @FXML
    void eliminarModulo(ActionEvent event) {

    }

    @FXML
    void insertarPermiso(ActionEvent event) {

    }

    @FXML
    void actualizarPermiso(ActionEvent event) {

    }

    @FXML
    void eliminarPermiso(ActionEvent event) {

    }
        ///////////////////////////Funciones///////////////////////////

    
    public Connection getConnection() {
        Connection conn;
        try {
            //conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libreria", "root", "root");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tfg2", "admin", "gwo47BRcHwGE");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error de conexión");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return null;
        }
    }

    public ObservableList<Permiso> dameListaPermisos() {
        ObservableList<Permiso> listaPermisos = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM permisos";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Permiso permiso;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD
                    permiso = new Permiso(rs.getInt("id_rol"), rs.getInt("id_modulo"), rs.getString("permisos"));
                    
                    listaPermisos.add(permiso);
                }
            } catch (SQLException e) {
            }
            return listaPermisos;
        }
        return null;
    }
    
    public ObservableList<Modulo> dameListaModulo() {
        ObservableList<Modulo> listaModulos = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM modulos";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Modulo modulo;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD
                    modulo = new Modulo(rs.getInt("id_modulo"), rs.getString("modulo"));
                    
                    listaModulos.add(modulo);
                }
            } catch (SQLException e) {
            }
            return listaModulos;
        }
        return null;
    }
    
    public ObservableList<Rol> dameListaRol() {
        ObservableList<Rol> listaRoles = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM rol";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Rol rol;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD
                    rol = new Rol(rs.getInt("id_rol"), rs.getString("denominacion"));
                    System.out.println(rol.toString());
                    listaRoles.add(rol);
                }
            } catch (SQLException e) {
            }
            return listaRoles;
        }
        return null;
    }
    
    public ObservableList<Sucursal> dameListaSucursal() {
        ObservableList<Sucursal> listaSucursales = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM sucursal";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Sucursal sucursal;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD
                    sucursal = new Sucursal(rs.getInt("idSucursal"), rs.getString("ciudad"), rs.getInt("codigoPostal"), rs.getString("direccion"), rs.getInt("telefono"));
                    listaSucursales.add(sucursal);
                }
            } catch (SQLException e) {
            }
            return listaSucursales;
        }
        return null;
    }

    public ObservableList<Pista> dameListaPista() {
        ObservableList<Pista> listaPistas = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM pistas";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Pista pista;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD
                    pista = new Pista(rs.getInt("id_pista"), (int) rs.getDouble("precioHora"), rs.getString("actividad"), rs.getInt("idSucursal"));
                    listaPistas.add(pista);
                }
            } catch (SQLException e) {
            }
            return listaPistas;
        }
        return null;
    }

    /*
    public String sacarRol(int id){
        String retorno = null;
            try {
                Connection connection = getConnection();
                String query = "SELECT denominacion FROM rol WHERE id_rol = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultado = preparedStatement.executeQuery();

                if (resultado.next()) {
                     retorno = resultado.getString("denominacion");
                }
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
            return retorno;
    }
    
    */
    public void enviaAControlador(Persona per) {
        this.persona = per;
    }
    
    public void setControladorEnlace(controladorLogin c) {
        //System.out.println("pERMISOS FALTANTES: "+permisosFaltantes("CR--"));
        //tbGeneral.getTabs().remove(tabSucursal);
        //accionesSucursal.getChildren().remove(btnInsertarSucursal);
        message.setText("Bienvenido");

        ObservableList<Sucursal> listaS = dameListaSucursal();

        //Los campos han de coincidir con los campos del objeto Sucursal
        tcIDSucursal.setCellValueFactory(new PropertyValueFactory<Sucursal, Integer>("idSucursal"));
        tcCiudad.setCellValueFactory(new PropertyValueFactory<Sucursal, String>("ciudad"));
        tcCodPos.setCellValueFactory(new PropertyValueFactory<Sucursal, Integer>("codigoPostal"));
        tcDireccion.setCellValueFactory(new PropertyValueFactory<Sucursal, String>("direccion"));
        tcTelSucu.setCellValueFactory(new PropertyValueFactory<Sucursal, Integer>("telefono"));

        tvSucursal.setItems(listaS);

        ObservableList<Pista> listaP = dameListaPista();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdPista.setCellValueFactory(new PropertyValueFactory<Pista, Integer>("idPista"));
        tcPrecioH.setCellValueFactory(new PropertyValueFactory<Pista, Integer>("precioHora"));
        tcActividad.setCellValueFactory(new PropertyValueFactory<Pista, String>("actividad"));

        tvPistas.setItems(listaP);
        
        ObservableList<Rol> listaRol = dameListaRol();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdRol.setCellValueFactory(new PropertyValueFactory<Rol, Integer>("idRol"));
        tcDenominacion.setCellValueFactory(new PropertyValueFactory<Rol, String>("denominacion"));

        tvRol.setItems(listaRol);
        
        
        ObservableList<Modulo> listaModulo = dameListaModulo();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdModulo.setCellValueFactory(new PropertyValueFactory<Modulo, Integer>("idModulo"));
        tcModulo.setCellValueFactory(new PropertyValueFactory<Modulo, String>("modulo"));

        tvModulo.setItems(listaModulo);
        
        ObservableList<Permiso> listaPermisos = dameListaPermisos();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdRolPer.setCellValueFactory(new PropertyValueFactory<Permiso, Integer>("idRol"));
        tcIdModuloPer.setCellValueFactory(new PropertyValueFactory<Permiso, Integer>("idModulo"));
        tcPermisos.setCellValueFactory(new PropertyValueFactory<Permiso, String>("permiso"));

        tvPermisos.setItems(listaPermisos);
    }
    
    public String permisosFaltantes(String permisos){
        String aux = "";
        String todos = "CRUD";
        for (char p : todos.toCharArray()){
            if (!permisos.contains(String.valueOf(p))) {
                aux=aux+p;
            }
        }
        return aux;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
