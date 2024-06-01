package controlador;

import Modelo.Empleado;
import Modelo.Mantenimiento;
import Modelo.Modulo;
import Modelo.Permiso;
import Modelo.Persona;
import Modelo.Pista;
import Modelo.Reserva;
import Modelo.Rol;
import Modelo.Sucursal;
import Modelo.Trabajan;
import Modelo.Usuario;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

    public boolean visible(int rol) {
        if (rol == 1) {
            return true;
        } else {
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
    private TableView<Reserva> tvReservas;

    @FXML
    private TableColumn<Reserva, Integer> tcIDPistaReserva;

    @FXML
    private TableColumn<Reserva, String> tcDNIReserva;

    @FXML
    private TableColumn<Reserva, String> tcHoraIni;

    @FXML
    private TableColumn<Reserva, Float> tcDuracion;

    @FXML
    private TableColumn<Reserva, Date> tcfecha;
    @FXML
    private TableColumn<Reserva, Float> tcPrecioReserva;

    //################################Pistas################################//
    @FXML
    private TextField tfIdPista;

    @FXML
    private TextField tfActividad;

    @FXML
    private TextField tfPrecioH;

    @FXML
    private ComboBox cbSucursalPista;

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
    private TableView<Usuario> tvUsuarios;

    @FXML
    private TableColumn<Usuario, String> tcDNIUsu;

    @FXML
    private TableColumn<Usuario, String> tcNombreUsu;

    @FXML
    private TableColumn<Usuario, String> tcApellidosUsu;

    @FXML
    private TableColumn<Usuario, String> tcCorreoUsu;

    @FXML
    private TableColumn<Usuario, Integer> tcTelUsu;

    @FXML
    private TableColumn<Usuario, String> tcUsu;

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
    private ComboBox cbRolEmple;

    @FXML
    private TableView<Empleado> tvEmpleados;
    @FXML
    private TableColumn<Empleado, String> tcDNIEmple;
    @FXML
    private TableColumn<Empleado, String> tcNombreEmple;
    @FXML
    private TableColumn<Empleado, String> tcApellidosEmple;

    @FXML
    private TableColumn<Empleado, String> tcCorreoEmple;

    @FXML
    private TableColumn<Empleado, Integer> tcTelefonoEmple;

    @FXML
    private TableColumn<Empleado, Float> tcSueldo;

    //################################Mantenimiento################################//
    @FXML
    private ComboBox cbDniManteni;

    @FXML
    private ComboBox cbIdPistaManteni;

    @FXML
    private TextField tfTipoT;
    
    @FXML
    private DatePicker dpFechaManteni;
    
    @FXML
    private TextField tfDuracionManteni;

    @FXML
    private Button btnInsertarManteni;

    @FXML
    private Button btnActualizarManteni;

    @FXML
    private Button btnEliminarManteni;

    @FXML
    private TableView<Mantenimiento> tvMantenimiento;

    @FXML
    private TableColumn<Mantenimiento, String> tcDNIManteni;

    @FXML
    private TableColumn<Mantenimiento, Integer> tcIdPistaManteni;

    @FXML
    private TableColumn<Mantenimiento, String> tcTipoT;
    @FXML
    private TableColumn<Mantenimiento, Date> tcInicioMantenimiento;
    @FXML
    private TableColumn<Mantenimiento, Float> tcDuracionMantenimiento;

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

    //################################Permisos################################//
    @FXML
    private ComboBox cbModuloPer;

    @FXML
    private ComboBox cbIdRolPer;
    
    @FXML
    private CheckBox cbxActualizar;

    @FXML
    private CheckBox cbxCrear;

    @FXML
    private CheckBox cbxEliminar;

    @FXML
    private CheckBox cbxListar;

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

    //################################Trabajan################################//
    @FXML
    private TextField tfDNITrabajan;

    @FXML
    private TextField tfIDSucursalTrabajan;

    @FXML
    private TextField tfInicioTrabajan;

    @FXML
    private TextField tfFinTrabajan;

    @FXML
    private Button btnInsertarTrabajan;

    @FXML
    private Button btnEliminarTrabajan;

    @FXML
    private Button btnActualizarTrabajan;

    @FXML
    private TableView<Trabajan> tvTrabajan;

    @FXML
    private TableColumn<Trabajan, Integer> tcIdSucursalTrabajan;

    @FXML
    private TableColumn<Trabajan, String> tcDNITrabaja;

    @FXML
    private TableColumn<Trabajan, Date> tcInicioTrabajan;

    @FXML
    private TableColumn<Trabajan, Date> tcFinTrabajan;

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
        Rol rol = new Rol();
        if (!tfDNIEmple.getText().isEmpty() && !tfNombreEmple.getText().isEmpty() && !tfApellidosEmple.getText().isEmpty() && !tfCorreoEmple.getText().isEmpty() && !tfTelEmple.getText().isEmpty() && !tfSueldo.getText().isEmpty() && !cbRolEmple.getValue().toString().isEmpty()) {
            String query = "INSERT INTO personas VALUES (?, ?, ?, ?, ?, ?, ?)";
            rol = (Rol) cbRolEmple.getValue();
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, tfDNIEmple.getText());
                preparedStatement.setString(2, tfNombreEmple.getText());
                preparedStatement.setString(3, tfApellidosEmple.getText());
                preparedStatement.setString(4, tfCorreoEmple.getText());
                preparedStatement.setString(5, tfTelEmple.getText());
                preparedStatement.setString(6, tfDNIEmple.getText());

                preparedStatement.setString(7, rol.getIdRol() + "");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
            String query2 = "INSERT INTO empleados VALUES (?,?)";
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query2);
                preparedStatement.setString(1, tfDNIEmple.getText());
                preparedStatement.setString(2, tfSueldo.getText());
                preparedStatement.executeUpdate();

                tvEmpleados.setItems(dameListaEmpleados());
                tfNombreEmple.setText("");
                tfApellidosEmple.setText("");
                tfCorreoEmple.setText("");
                tfTelEmple.setText("");
                tfSueldo.setText("");
                cbRolEmple.setValue(null);
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }

        } else {
            System.out.println("Algun campo vacío");
        }
    }

    @FXML
    void insertarManteni(ActionEvent event) {
        
        Empleado emp = new Empleado();
        Pista pista = new Pista();
        if (cbDniManteni.getValue() != null && cbIdPistaManteni.getValue() != null && !tfTipoT.getText().isEmpty() && dpFechaManteni.getValue() != null && !tfDuracionManteni.getText().isEmpty()) {
            String query = "INSERT INTO mantienen VALUES (" + null + ", ?, ?, ?, ?, ?)";
            emp = (Empleado) cbDniManteni.getValue();
            pista = (Pista) cbIdPistaManteni.getValue();
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, emp.getDni());
                preparedStatement.setString(2, pista.getIdPista()+"");
                preparedStatement.setString(3, tfTipoT.getText());
                preparedStatement.setString(4, dpFechaManteni.getValue().format(DateTimeFormatter.ISO_DATE).toString());
                preparedStatement.setString(5, tfDuracionManteni.getText());
                
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }

            tvMantenimiento.setItems(dameListaMantenimiento());
            cbDniManteni.setValue(null);
            cbIdPistaManteni.setValue(null);
            tfTipoT.setText("");
            dpFechaManteni.setValue(null);
            tfDuracionManteni.setText("");

        } else {
            System.out.println("Algun valor esta vacio");
        }
        
        
    }

    @FXML
    void insertarPista(ActionEvent event) {
        Sucursal suc = new Sucursal();

        if (!tfActividad.getText().isEmpty() && !tfPrecioH.getText().isEmpty() && cbSucursalPista.getValue() != null) {
            String query = "INSERT INTO pistas VALUES (" + null + ", ?, ?, ?)";
            suc = (Sucursal) cbSucursalPista.getValue();
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, tfPrecioH.getText());
                preparedStatement.setString(2, tfActividad.getText());
                preparedStatement.setString(3, suc.getIdSucursal() + "");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }

            tvPistas.setItems(dameListaPista());
            tfActividad.setText("");
            tfPrecioH.setText("");
            cbSucursalPista.setValue(null);

        } else {
            System.out.println("Algun valor esta vacio");
        }

    }

    @FXML
    void insertarReserva(ActionEvent event) {
Empleado emp = new Empleado();
        Pista pista = new Pista();
        if (cbDniManteni.getValue() != null && cbIdPistaManteni.getValue() != null && !tfTipoT.getText().isEmpty() && dpFechaManteni.getValue() != null && !tfDuracionManteni.getText().isEmpty()) {
            String query = "INSERT INTO mantienen VALUES (" + null + ", ?, ?, ?, ?, ?)";
            emp = (Empleado) cbDniManteni.getValue();
            pista = (Pista) cbIdPistaManteni.getValue();
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, emp.getDni());
                preparedStatement.setString(2, pista.getIdPista()+"");
                preparedStatement.setString(3, tfTipoT.getText());
                preparedStatement.setString(4, dpFechaManteni.getValue().format(DateTimeFormatter.ISO_DATE).toString());
                preparedStatement.setString(5, tfDuracionManteni.getText());
                
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }

            tvMantenimiento.setItems(dameListaMantenimiento());
            cbDniManteni.setValue(null);
            cbIdPistaManteni.setValue(null);
            tfTipoT.setText("");
            dpFechaManteni.setValue(null);
            tfDuracionManteni.setText("");

        } else {
            System.out.println("Algun valor esta vacio");
        }
    }

    @FXML
    void insertarSucursal(ActionEvent event) {

        if (!tfCiudad.getText().isEmpty() && !tfCodigoPostal.getText().isEmpty() && !tfDireccion.getText().isEmpty() && !tfTelSucursal.getText().isEmpty()) {
            String query = "INSERT INTO sucursal VALUES (" + null + ", ?, ?, ?, ?)";

            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, tfCiudad.getText());
                preparedStatement.setString(2, tfCodigoPostal.getText());
                preparedStatement.setString(3, tfDireccion.getText());
                preparedStatement.setString(4, tfTelSucursal.getText());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
            tvSucursal.setItems(dameListaSucursal());
            tfCiudad.setText("");
            tfCodigoPostal.setText("");
            tfDireccion.setText("");
            tfTelSucursal.setText("");
        } else {
            System.out.println("Algun campo vacío");
        }

    }

    @FXML
    void insertarUsuario(ActionEvent event) {
        if (!tfDNIUsu.getText().isEmpty() && !tfNombreUsu.getText().isEmpty() && !tfApellidosUsu.getText().isEmpty() && !tfCorreoUsu.getText().isEmpty() && !tfTelUsu.getText().isEmpty() && !tfUsuario.getText().isEmpty()) {
            String query = "INSERT INTO personas VALUES (?, ?, ?, ?, ?, ?, 4)";

            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, tfDNIUsu.getText());
                preparedStatement.setString(2, tfNombreUsu.getText());
                preparedStatement.setString(3, tfApellidosUsu.getText());
                preparedStatement.setString(4, tfCorreoUsu.getText());
                preparedStatement.setString(5, tfTelUsu.getText());
                preparedStatement.setString(6, tfDNIUsu.getText());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
            String query2 = "INSERT INTO usuarios VALUES (?,?)";
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query2);
                preparedStatement.setString(1, tfDNIUsu.getText());
                preparedStatement.setString(2, tfUsuario.getText());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
            tvUsuarios.setItems(dameListaUsuarios());
            tfDNIUsu.setText("");
            tfNombreUsu.setText("");
            tfApellidosUsu.setText("");
            tfCorreoUsu.setText("");
            tfTelUsu.setText("");
            tfUsuario.setText("");

        } else {
            System.out.println("Algun campo vacío");
        }
    }

    @FXML
    void insertarRol(ActionEvent event) {
        if (!tfDenominacion.getText().isEmpty()) {
            String query = "INSERT INTO rol VALUES (" + null + ", ?)";

            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, tfDenominacion.getText());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
            tvRol.setItems(dameListaRol());
        } else {
            System.out.println("Inserta una denominacion");
        }
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
        Rol rol = new Rol();
        Modulo modulo = new Modulo();
        if(cbModuloPer.getValue() != null && cbIdRolPer.getValue() != null){
            String query = "INSERT INTO permisos VALUES (?, ?, ?)";
            
            String permiso = "";
            if(cbxCrear.isSelected()){
                permiso += "C";
            }
            if(cbxListar.isSelected()){
                permiso += "R";
            }
            if(cbxActualizar.isSelected()){
                permiso += "U";
            }
            if(cbxEliminar.isSelected()){
                permiso += "D";
            }
            rol = (Rol) cbIdRolPer.getValue();
            modulo = (Modulo) cbModuloPer.getValue();
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, rol.getIdRol() + "");
                preparedStatement.setString(2, modulo.getIdModulo() + "");
                preparedStatement.setString(3, permiso);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
            tvPermisos.setItems(dameListaPermisos());
            cbModuloPer.setValue(null);
            cbIdRolPer.setValue(null);
            cbxCrear.setSelected(false);
            cbxListar.setSelected(false);
            cbxActualizar.setSelected(false);
            cbxEliminar.setSelected(false);
        } else {
            System.out.println("Falta un campo");
        }
        
        
    }

    @FXML
    void actualizarPermiso(ActionEvent event) {

    }

    @FXML
    void eliminarPermiso(ActionEvent event) {

    }

    @FXML
    void insertarTrabajan(ActionEvent event) {

    }

    @FXML
    void actualizarTrabajan(ActionEvent event) {

    }

    @FXML
    void eliminarTrabajan(ActionEvent event) {

    }
///////////////////////////Funciones///////////////////////////

    //Fuera
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
                    sucursal = new Sucursal(rs.getInt("id_sucursal"), rs.getString("ciudad"), rs.getInt("codigo_postal"), rs.getString("direccion"), rs.getInt("telefono"));
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
                    pista = new Pista(rs.getInt("id_pista"), (int) rs.getDouble("precioHora"), rs.getString("actividad"), rs.getInt("id_sucursal"));
                    listaPistas.add(pista);
                }
            } catch (SQLException e) {
            }
            return listaPistas;
        }
        return null;
    }

    public ObservableList<Usuario> dameListaUsuarios() {
        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM usuarios, personas where usuarios.dni_usuario = personas.dni";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Usuario usuario;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD

                    usuario = new Usuario(rs.getString("personas.dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getInt("telefono"), rs.getString("contrasena"), rs.getInt("rol"), rs.getString("usuario"));

                    listaUsuarios.add(usuario);
                }
            } catch (SQLException e) {
            }
            return listaUsuarios;
        }
        return null;
    }

    public ObservableList<Empleado> dameListaEmpleados() {
        ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM empleados, personas where empleados.dni_empleado = personas.dni";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Empleado empleado;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD

                    empleado = new Empleado(rs.getString("personas.dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getInt("telefono"), rs.getString("contrasena"), rs.getInt("rol"), rs.getFloat("sueldo"));

                    listaEmpleados.add(empleado);
                }
            } catch (SQLException e) {
            }
            return listaEmpleados;
        }
        return null;
    }

    public ObservableList<Reserva> dameListaReservas() {
        ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM reservas";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Reserva reserva;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD

                    reserva = new Reserva(rs.getInt("id_pista"), rs.getString("dni"), rs.getString("hora_inicio"), rs.getFloat("duracion"), rs.getDate("fecha"), rs.getInt("precio_reserva"));

                    listaReservas.add(reserva);
                }
            } catch (SQLException e) {
            }
            return listaReservas;
        }
        return null;
    }

    public ObservableList<Mantenimiento> dameListaMantenimiento() {
        ObservableList<Mantenimiento> listaMantenimientos = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM mantienen";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Mantenimiento mantenimiento;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD

                    mantenimiento = new Mantenimiento(rs.getInt("id_mantenimiento"), rs.getInt("id_pista"), rs.getString("dni"), rs.getString("tipo_trabajo"), rs.getFloat("duracion"), rs.getDate("fecha_ini"));

                    listaMantenimientos.add(mantenimiento);
                }
            } catch (SQLException e) {
            }
            return listaMantenimientos;
        }
        return null;
    }

    public ObservableList<Trabajan> dameListaTrabajan() {
        ObservableList<Trabajan> listaTrabajan = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM trabajan";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Trabajan trabajan;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD

                    trabajan = new Trabajan(rs.getString("dni_empleado"), rs.getInt("id_sucursal"), rs.getDate("fecha_ini"), rs.getDate("fecha_fin"));

                    listaTrabajan.add(trabajan);
                }
            } catch (SQLException e) {
            }
            return listaTrabajan;
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
        cbSucursalPista.setItems(listaS);

        ObservableList<Pista> listaP = dameListaPista();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdPista.setCellValueFactory(new PropertyValueFactory<Pista, Integer>("idPista"));
        tcPrecioH.setCellValueFactory(new PropertyValueFactory<Pista, Integer>("precioHora"));
        tcActividad.setCellValueFactory(new PropertyValueFactory<Pista, String>("actividad"));

        tvPistas.setItems(listaP);
        cbIdPistaManteni.setItems(listaP);
        ObservableList<Rol> listaRol = dameListaRol();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdRol.setCellValueFactory(new PropertyValueFactory<Rol, Integer>("idRol"));
        tcDenominacion.setCellValueFactory(new PropertyValueFactory<Rol, String>("denominacion"));

        tvRol.setItems(listaRol);
        cbIdRolPer.setItems(listaRol);
            
        cbRolEmple.setItems(listaRol);

        ObservableList<Modulo> listaModulo = dameListaModulo();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdModulo.setCellValueFactory(new PropertyValueFactory<Modulo, Integer>("idModulo"));
        tcModulo.setCellValueFactory(new PropertyValueFactory<Modulo, String>("modulo"));
        cbModuloPer.setItems(listaModulo);
        tvModulo.setItems(listaModulo);

        ObservableList<Permiso> listaPermisos = dameListaPermisos();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdRolPer.setCellValueFactory(new PropertyValueFactory<Permiso, Integer>("idRol"));
        tcIdModuloPer.setCellValueFactory(new PropertyValueFactory<Permiso, Integer>("idModulo"));
        tcPermisos.setCellValueFactory(new PropertyValueFactory<Permiso, String>("permiso"));

        tvPermisos.setItems(listaPermisos);

        ObservableList<Usuario> listaUsuarios = dameListaUsuarios();

        //Los campos han de coincidir con los campos del objeto Libros
        tcDNIUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("dni"));
        tcNombreUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        tcApellidosUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellidos"));
        tcCorreoUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        tcTelUsu.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("telefono"));
        tcUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usuario"));

        tvUsuarios.setItems(listaUsuarios);

        ObservableList<Empleado> listaEmpleados = dameListaEmpleados();

        //Los campos han de coincidir con los campos del objeto Libros
        tcDNIEmple.setCellValueFactory(new PropertyValueFactory<Empleado, String>("dni"));
        tcNombreEmple.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        tcApellidosEmple.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellidos"));
        tcCorreoEmple.setCellValueFactory(new PropertyValueFactory<Empleado, String>("correo"));
        tcTelefonoEmple.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("telefono"));
        tcSueldo.setCellValueFactory(new PropertyValueFactory<Empleado, Float>("sueldo"));

        tvEmpleados.setItems(listaEmpleados);
        cbDniManteni.setItems(listaEmpleados);
        ObservableList<Reserva> listaReservas = dameListaReservas();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIDPistaReserva.setCellValueFactory(new PropertyValueFactory<Reserva, Integer>("idPista"));
        tcDNIReserva.setCellValueFactory(new PropertyValueFactory<Reserva, String>("dni"));
        tcHoraIni.setCellValueFactory(new PropertyValueFactory<Reserva, String>("horaIni"));
        tcDuracion.setCellValueFactory(new PropertyValueFactory<Reserva, Float>("duracion"));
        tcfecha.setCellValueFactory(new PropertyValueFactory<Reserva, Date>("fecha"));
        tcPrecioReserva.setCellValueFactory(new PropertyValueFactory<Reserva, Float>("precioReserva"));
        tvReservas.setItems(listaReservas);

        ObservableList<Mantenimiento> listaMantenimientos = dameListaMantenimiento();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdPistaManteni.setCellValueFactory(new PropertyValueFactory<Mantenimiento, Integer>("idPista"));
        tcDNIManteni.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("dni"));
        tcTipoT.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("tipoTrabajo"));
        tcDuracionMantenimiento.setCellValueFactory(new PropertyValueFactory<Mantenimiento, Float>("duracion"));
        tcInicioMantenimiento.setCellValueFactory(new PropertyValueFactory<Mantenimiento, Date>("fecha"));
        tvMantenimiento.setItems(listaMantenimientos);

        ObservableList<Trabajan> listaTrabajan = dameListaTrabajan();

        //Los campos han de coincidir con los campos del objeto Libros Integer>("idPista"));
        tcDNITrabaja.setCellValueFactory(new PropertyValueFactory<Trabajan, String>("dni"));
        tcIdSucursalTrabajan.setCellValueFactory(new PropertyValueFactory<Trabajan, Integer>("idSucursal"));
        tcInicioTrabajan.setCellValueFactory(new PropertyValueFactory<Trabajan, Date>("fechaInicio"));
        tcFinTrabajan.setCellValueFactory(new PropertyValueFactory<Trabajan, Date>("fechaFin"));
        tvTrabajan.setItems(listaTrabajan);
    }

    public String permisosFaltantes(String permisos) {
        String aux = "";
        String todos = "CRUD";
        for (char p : todos.toCharArray()) {
            if (!permisos.contains(String.valueOf(p))) {
                aux = aux + p;
            }
        }
        return aux;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
