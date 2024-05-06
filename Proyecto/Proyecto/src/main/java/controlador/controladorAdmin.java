package controlador;

import Modelo.Pista;
import Modelo.Sucursal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class controladorAdmin implements Initializable {

    Connection conexion;

    ///////////////////////////Elementos de la vista///////////////////////////
    @FXML
    private Label message;

    //################################Sucursal################################//
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

        ///////////////////////////Funciones///////////////////////////

    
    public Connection getConnection() {
        Connection conn;
        try {
            //conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/libreria", "root", "root");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tfg", "admin", "gwo47BRcHwGE");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    }

}
