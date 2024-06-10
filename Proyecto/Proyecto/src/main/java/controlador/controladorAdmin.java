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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;

public class controladorAdmin implements Initializable {

    Connection conexion;
    controladorLogin cl;
    Reserva reserva = null;
    Mantenimiento mantenimiento = null;
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
    private Button btnCargarSucursal;

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

    private ContextMenu contextMenuSucursal = new ContextMenu();
    private MenuItem itemSucursal = new MenuItem("Eliminar");

    //################################Reservas################################//
    @FXML
    private Tab tabReservas;

    @FXML
    private ComboBox cbIdPistaReserva;

    @FXML
    private ComboBox cbDNIReserva;

    @FXML
    private TextField tfHoraIni;

    @FXML
    private TextField tfDuracionReserva;

    @FXML
    private DatePicker dpFechaReserva;

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
    private ContextMenu contextMenuReserva = new ContextMenu();
    private MenuItem itemReserva = new MenuItem("Eliminar");

    //################################Pistas################################//
    @FXML
    private Tab tabPistas;

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
    private Button btnCargarPista;

    @FXML
    private TableView<Pista> tvPistas;

    @FXML
    private TableColumn<Pista, Integer> tcIdPista;

    @FXML
    private TableColumn<Pista, Integer> tcPrecioH;

    @FXML
    private TableColumn<Pista, String> tcActividad;
    private ContextMenu contextMenuPista = new ContextMenu();
    private MenuItem itemPista = new MenuItem("Eliminar");

    //################################Usuarios################################//
    @FXML
    private Tab tabUsuarios;

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
    private Button btnCargarUsuario;
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
    private ContextMenu contextMenuUsuario = new ContextMenu();
    private MenuItem itemUsuario = new MenuItem("Eliminar");

    //################################Empleados################################//
    @FXML
    private Tab tabEmpleados;

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
    private Button btnCargarEmple;

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
    private ContextMenu contextMenuEmpleado = new ContextMenu();
    private MenuItem itemEmpleado = new MenuItem("Eliminar");

    //################################Mantenimiento################################//
    @FXML
    private Tab tabMantenimiento;

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
    private Button btnCargarManteni;

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
    private ContextMenu contextMenuMantenimiento = new ContextMenu();
    private MenuItem itemMantenimiento = new MenuItem("Eliminar");

    //################################Rol################################//
    @FXML
    private Tab tabRoles;

    @FXML
    private TextField tfIdRol;

    @FXML
    private TextField tfDenominacion;
    @FXML
    private Button btnCargarRol;

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

    private ContextMenu contextMenuRol = new ContextMenu();
    private MenuItem itemRol = new MenuItem("Eliminar");

    //################################Modulo################################//
    @FXML
    private Tab tabModulos;

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
    private ContextMenu contextMenuModulo = new ContextMenu();
    private MenuItem itemModulo = new MenuItem("Eliminar");

    //################################Permisos################################//
    @FXML
    private Tab tabPermisos;

    @FXML
    private ComboBox cbModuloPer;
    @FXML
    private Button btnCargarPermiso;
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
    private ContextMenu contextMenuPermiso = new ContextMenu();
    private MenuItem itemPermiso = new MenuItem("Eliminar");

    //################################Trabajan################################//
    @FXML
    private Tab tabTrabajan;

    @FXML
    private ComboBox cbDNITrabajan;

    @FXML
    private ComboBox cbIDSucursalTrabajan;

    @FXML
    private DatePicker dpInicioTrabajan;

    @FXML
    private DatePicker dpFinTrabajan;

    @FXML
    private Button btnInsertarTrabajan;

    @FXML
    private Button btnEliminarTrabajan;

    @FXML
    private Button btnActualizarTrabajan;

    @FXML
    private Button btnCargarTrabajan;

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
    private ContextMenu contextMenuTrabajan = new ContextMenu();
    private MenuItem itemTrabajan = new MenuItem("Eliminar");

///////////////////////////Eventos de la vista///////////////////////////
    //////////////////////Filtros//////////////////////
    @FXML
    private ComboBox cbBuscarSucursales;

    @FXML
    private TextField tfBuscarSucursales;

    @FXML
    private Button btnRestSuc;

    @FXML
    private Button btnBuscarSucursal;

    @FXML
    void restSuc() {
        tvSucursal.setItems(dameListaSucursal());
    }

    @FXML
    void buscarSucursal() {
        ObservableList<Sucursal> listaSucursales = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query = "SELECT * FROM sucursal WHERE " + cbBuscarSucursales.getValue().toString() + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfBuscarSucursales.getText());
            ResultSet rs = preparedStatement.executeQuery();
            Sucursal sucursal;
            while (rs.next()) { //Se usan los identificadores propios en la BBDD
                sucursal = new Sucursal(rs.getInt("id_sucursal"), rs.getString("ciudad"), rs.getInt("codigo_postal"), rs.getString("direccion"), rs.getInt("telefono"));
                listaSucursales.add(sucursal);
            }

            if (listaSucursales.size() == 0) {
                darAlerta(true, "No se ha encontrado nada para: " + cbBuscarSucursales.getValue().toString() + " = " + tfBuscarSucursales.getText());
                restSuc();
            } else {
                tvSucursal.setItems(listaSucursales);
            }

        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        cbBuscarSucursales.setValue(null);
        tfBuscarSucursales.setText("");
    }

    @FXML
    private ComboBox cbBuscarPistas;

    @FXML
    private TextField tfBuscarPistas;

    @FXML
    private Button btnRestPistas;

    @FXML
    private Button btnBuscarPistas;

    @FXML
    void restPistas() {
        tvPistas.setItems(dameListaPista());
    }

    @FXML
    void buscarPistas() {
        ObservableList<Pista> listaPistas = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query = "SELECT * FROM pistas WHERE " + cbBuscarPistas.getValue().toString() + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfBuscarPistas.getText());
            ResultSet rs = preparedStatement.executeQuery();
            Pista pista;
            while (rs.next()) { //Se usan los identificadores propios en la BBDD
                pista = new Pista(rs.getInt("id_pista"), rs.getInt("precioHora"), rs.getString("actividad"), rs.getInt("id_sucursal"));
                listaPistas.add(pista);
            }
            if (listaPistas.size() == 0) {
                darAlerta(true, "No se ha encontrado nada para: " + cbBuscarPistas.getValue().toString() + " = " + tfBuscarPistas.getText());
                restPistas();
            } else {
                tvPistas.setItems(listaPistas);
            }

        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        cbBuscarPistas.setValue(null);
        tfBuscarPistas.setText("");
    }

    @FXML
    private ComboBox cbBuscarReservas;

    @FXML
    private TextField tfBuscarReservas;

    @FXML
    private Button btnRestReservas;

    @FXML
    private Button btnBuscarReservas;

    @FXML
    void restReservas() {
        tvReservas.setItems(dameListaReservas(sacarPermiso(11, persona.getRol())));
    }

    @FXML
    void buscarReservas() {
        ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query = "SELECT * FROM reservas WHERE " + cbBuscarReservas.getValue().toString() + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfBuscarReservas.getText());
            ResultSet rs = preparedStatement.executeQuery();
            Reserva reserva;
            while (rs.next()) { //Se usan los identificadores propios en la BBDD
                reserva = new Reserva(rs.getInt("id_reserva"), rs.getInt("id_pista"), rs.getString("dni"), rs.getString("hora_inicio"), rs.getFloat("duracion"), rs.getString("fecha"), rs.getFloat("precio_reserva"));
                listaReservas.add(reserva);
            }
            if (listaReservas.size() == 0) {
                darAlerta(true, "No se ha encontrado nada para: " + cbBuscarReservas.getValue().toString() + " = " + tfBuscarReservas.getText());
                restReservas();
            } else {
                tvReservas.setItems(listaReservas);
            }

        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        cbBuscarReservas.setValue(null);
        tfBuscarReservas.setText("");
    }

    @FXML
    private ComboBox cbBuscarUsuarios;

    @FXML
    private TextField tfBuscarUsuarios;

    @FXML
    private Button btnRestUsuarios;

    @FXML
    private Button btnBuscarUsuarios;

    @FXML
    void restUsuarios() {
        tvUsuarios.setItems(dameListaUsuarios(sacarPermiso(8, persona.getRol())));
    }

    @FXML
    void buscarUsuarios() {
        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query = "SELECT * FROM usuarios, personas where usuarios.dni_usuario = personas.dni AND " + cbBuscarUsuarios.getValue().toString() + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfBuscarUsuarios.getText());
            ResultSet rs = preparedStatement.executeQuery();
            Usuario usuario;
            while (rs.next()) { //Se usan los identificadores propios en la BBDD
                usuario = new Usuario(rs.getString("personas.dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getInt("telefono"), rs.getString("contrasena"), rs.getInt("rol"), rs.getString("usuario"));
                listaUsuarios.add(usuario);
            }
            if (listaUsuarios.size() == 0) {
                darAlerta(true, "No se ha encontrado nada para: " + cbBuscarUsuarios.getValue().toString() + " = " + tfBuscarUsuarios.getText());
                restUsuarios();
            } else {
                tvUsuarios.setItems(listaUsuarios);
            }

        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        cbBuscarUsuarios.setValue(null);
        tfBuscarUsuarios.setText("");
    }

    @FXML
    private ComboBox cbBuscarEmpleados;

    @FXML
    private TextField tfBuscarEmpleados;

    @FXML
    private Button btnRestEmpleados;

    @FXML
    private Button btnBuscarEmpleados;

    @FXML
    void restEmpleados() {
        tvEmpleados.setItems(dameListaEmpleados());
    }

    @FXML
    void buscarEmpleados() {
        ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query = "SELECT * FROM empleados, personas where empleados.dni_empleado = personas.dni AND " + cbBuscarEmpleados.getValue().toString() + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfBuscarEmpleados.getText());
            ResultSet rs = preparedStatement.executeQuery();
            Empleado empleado;
            while (rs.next()) { //Se usan los identificadores propios en la BBDD
                empleado = new Empleado(rs.getString("personas.dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getInt("telefono"), rs.getString("contrasena"), rs.getInt("rol"), rs.getFloat("sueldo"));
                listaEmpleados.add(empleado);
            }
            if (listaEmpleados.size() == 0) {
                darAlerta(true, "No se ha encontrado nada para: " + cbBuscarEmpleados.getValue().toString() + " = " + tfBuscarEmpleados.getText());
                restEmpleados();
            } else {
                tvEmpleados.setItems(listaEmpleados);
            }

        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        cbBuscarEmpleados.setValue(null);
        tfBuscarEmpleados.setText("");
    }

    @FXML
    private ComboBox cbBuscarMantienen;

    @FXML
    private TextField tfBuscarMantienen;

    @FXML
    private Button btnRestMantienen;

    @FXML
    private Button btnBuscarMantienen;

    @FXML
    void restMantienen() {
        tvMantenimiento.setItems(dameListaMantenimiento());
    }

    @FXML
    void buscarMantienen() {
        ObservableList<Mantenimiento> listaMantenimientos = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query = "SELECT * FROM mantienen WHERE " + cbBuscarMantienen.getValue().toString() + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfBuscarMantienen.getText());
            ResultSet rs = preparedStatement.executeQuery();
            Mantenimiento mantenimiento;
            while (rs.next()) { //Se usan los identificadores propios en la BBDD
                mantenimiento = new Mantenimiento(rs.getInt("id_mantenimiento"), rs.getInt("id_pista"), rs.getString("dni"), rs.getString("tipo_trabajo"), rs.getFloat("duracion"), rs.getString("fecha_ini"));
                listaMantenimientos.add(mantenimiento);
            }
            if (listaMantenimientos.size() == 0) {
                darAlerta(true, "No se ha encontrado nada para: " + cbBuscarMantienen.getValue().toString() + " = " + tfBuscarMantienen.getText());
                restMantienen();
            } else {
                tvMantenimiento.setItems(listaMantenimientos);
            }
            cbBuscarMantienen.setValue(null);
            tfBuscarMantienen.setText("");
        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }

    }

    @FXML
    private ComboBox cbBuscarTrabajan;

    @FXML
    private TextField tfBuscarTrabajan;

    @FXML
    private Button btnRestTrabajan;

    @FXML
    private Button btnBuscarTrabajan;

    @FXML
    void restTrabajan() {
        tvTrabajan.setItems(dameListaTrabajan());
    }

    @FXML
    void buscarTrabajan() {
        ObservableList<Trabajan> listaTrabajan = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query = "SELECT * FROM trabajan WHERE " + cbBuscarTrabajan.getValue().toString() + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfBuscarTrabajan.getText());
            ResultSet rs = preparedStatement.executeQuery();
            Trabajan trabajan;
            while (rs.next()) { //Se usan los identificadores propios en la BBDD
                trabajan = new Trabajan(rs.getString("dni_empleado"), rs.getInt("id_sucursal"), rs.getString("fecha_ini"), rs.getString("fecha_fin"));
                listaTrabajan.add(trabajan);
            }
            if (listaTrabajan.size() == 0) {
                darAlerta(true, "No se ha encontrado nada para: " + cbBuscarTrabajan.getValue().toString() + " = " + tfBuscarTrabajan.getText());
                restTrabajan();
            } else {
                tvTrabajan.setItems(listaTrabajan);
            }
            cbBuscarTrabajan.setValue(null);
            tfBuscarTrabajan.setText("");
        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }

    }

    @FXML
    private ComboBox cbBuscarPermisos;

    @FXML
    private TextField tfBuscarPermisos;

    @FXML
    private Button btnRestPermisos;

    @FXML
    private Button btnBuscarPermisos;

    @FXML
    void restPermisos() {
        tvPermisos.setItems(dameListaPermisos());
    }

    @FXML
    void buscarPermisos() {
        ObservableList<Permiso> listaPermisos = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query = "SELECT * FROM permisos WHERE " + cbBuscarPermisos.getValue().toString() + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfBuscarPermisos.getText());
            ResultSet rs = preparedStatement.executeQuery();
            Permiso permiso;
            while (rs.next()) { //Se usan los identificadores propios en la BBDD
                permiso = new Permiso(rs.getInt("id_rol"), rs.getInt("id_modulo"), rs.getString("permisos"));
                listaPermisos.add(permiso);
            }
            if (listaPermisos.size() == 0) {
                darAlerta(true, "No se ha encontrado nada para: " + cbBuscarPermisos.getValue().toString() + " = " + tfBuscarPermisos.getText());
                restPermisos();
            } else {
                tvPermisos.setItems(listaPermisos);
            }
            cbBuscarPermisos.setValue(null);
            tfBuscarPermisos.setText("");
        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }

    }

    @FXML
    void cargarRol() {
        Rol rol = tvRol.getSelectionModel().getSelectedItem();
        if (rol != null) {
            tfIdRol.setText(rol.getIdRol() + "");
            tfDenominacion.setText(rol.getDenominacion());
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }

        darMensaje("Rol " + rol.getDenominacion() + " cargado");
    }

    @FXML
    void cargarPermiso() {
        Permiso per = tvPermisos.getSelectionModel().getSelectedItem();
        if (per != null) {
            cbIdRolPer.setValue(sacarRol(per.getIdRol()));
            cbModuloPer.setValue(sacarModulo(per.getIdModulo()));

            if (per.getPermiso().contains("C")) {
                cbxCrear.setSelected(true);
            } else {
                cbxCrear.setSelected(false);
            }
            if (per.getPermiso().contains("R")) {
                cbxListar.setSelected(true);
            } else {
                cbxListar.setSelected(false);
            }
            if (per.getPermiso().contains("U")) {
                cbxActualizar.setSelected(true);
            } else {
                cbxActualizar.setSelected(false);
            }
            if (per.getPermiso().contains("D")) {
                cbxEliminar.setSelected(true);
            } else {
                cbxEliminar.setSelected(false);
            }
            darMensaje("Permiso Cargado");
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void cargarSucursal() {
        Sucursal sucursal = tvSucursal.getSelectionModel().getSelectedItem();
        if (sucursal != null) {
            tfIdSucursal.setText(sucursal.getIdSucursal() + "");
            tfCiudad.setText(sucursal.getCiudad());
            tfCodigoPostal.setText(sucursal.getCodigoPostal() + "");
            tfDireccion.setText(sucursal.getDireccion());
            tfTelSucursal.setText(sucursal.getTelefono() + "");
            darMensaje("Sucursal cargada");
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void cargarReserva() {
        Reserva reservas = tvReservas.getSelectionModel().getSelectedItem();
        if (reservas != null) {
            cbIdPistaReserva.setValue(sacarPista(reservas.getIdPista()));
            cbDNIReserva.setValue(sacarUsuario(reservas.getDni()));
            tfHoraIni.setText(reservas.getHoraIni());
            tfDuracionReserva.setText(reservas.getDuracion() + "");
            LocalDate fecha = (reservas.getFecha() != null) ? LocalDate.parse(reservas.getFecha()) : LocalDate.now();
            reserva = reservas;
            dpFechaReserva.setValue(fecha);

            darMensaje("Reserva cargada");
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void cargarPista() {
        Pista pistas = tvPistas.getSelectionModel().getSelectedItem();
        if (pistas != null) {
            tfIdPista.setText(pistas.getIdPista() + "");
            tfPrecioH.setText(pistas.getPrecioHora() + "");
            tfActividad.setText(pistas.getActividad());
            cbSucursalPista.setValue(sacarSucursal(pistas.getIdSucursal()));
            darMensaje("Pista cargada");
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void cargarUsuario() {
        Usuario usuarios = tvUsuarios.getSelectionModel().getSelectedItem();
        if (usuarios != null) {
            tfDNIUsu.setText(usuarios.getDni());
            tfNombreUsu.setText(usuarios.getNombre());
            tfApellidosUsu.setText(usuarios.getApellidos());
            tfCorreoUsu.setText(usuarios.getCorreo());
            tfTelUsu.setText(usuarios.getTelefono() + "");
            tfUsuario.setText(usuarios.getUsuario());

            darMensaje("Usuario " + usuarios.getUsuario() + " cargado");
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void cargarEmple() {
        Empleado empleados = tvEmpleados.getSelectionModel().getSelectedItem();
        if (empleados != null) {
            tfDNIEmple.setText(empleados.getDni());
            tfNombreEmple.setText(empleados.getNombre());
            tfApellidosEmple.setText(empleados.getApellidos());
            tfCorreoEmple.setText(empleados.getCorreo());
            tfTelEmple.setText(empleados.getTelefono() + "");
            tfSueldo.setText(empleados.getSueldo() + "");
            cbRolEmple.setValue(sacarRol(empleados.getRol()));
            darMensaje("Empleado cargado");
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void cargarManteni() {
        Mantenimiento mantenimiento = tvMantenimiento.getSelectionModel().getSelectedItem();
        if (mantenimiento != null) {
            cbDniManteni.setValue(sacarEmpleado(mantenimiento.getDni()));
            cbIdPistaManteni.setValue(sacarPista(mantenimiento.getIdPista()));
            tfTipoT.setText(mantenimiento.getTipoTrabajo());
            LocalDate fecha = (mantenimiento.getFecha() != null) ? LocalDate.parse(mantenimiento.getFecha()) : LocalDate.now();
            dpFechaManteni.setValue(fecha);
            tfDuracionManteni.setText(mantenimiento.getDuracion() + "");
            this.mantenimiento = mantenimiento;

            darMensaje("Mantenimiento cargado");
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void cargarTrabajan() {
        Trabajan trabajan = tvTrabajan.getSelectionModel().getSelectedItem();
        if (trabajan != null) {
            cbDNITrabajan.setValue(sacarEmpleado(trabajan.getDni()));
            cbIDSucursalTrabajan.setValue(sacarSucursal(trabajan.getIdSucursal()));
            LocalDate fecha = (trabajan.getFechaInicio() != null) ? LocalDate.parse(trabajan.getFechaInicio()) : LocalDate.now();
            dpInicioTrabajan.setValue(fecha);
            LocalDate fechaF = (trabajan.getFechaFin() != null) ? LocalDate.parse(trabajan.getFechaFin()) : LocalDate.now();
            dpFinTrabajan.setValue(fechaF);

            darMensaje("Perdiodo de trabajo cargado");
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    public Usuario sacarUsuario(String dni) {
        Usuario retorno = null;

        try {

            Connection connection = getConnection();

            String query = "SELECT * FROM usuarios WHERE dni_usuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {

                //Rellenar datos de persona para pasarla al siguiente controlador
                Usuario u = new Usuario();
                u.setDni(resultado.getString("dni_usuario"));
                u.setUsuario(resultado.getString("usuario"));

                retorno = u;
            }
        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        return retorno;
    }

    public Empleado sacarEmpleado(String dni) {
        Empleado retorno = null;

        try {
            Connection connection = getConnection();

            String query = "SELECT * FROM empleados, personas where empleados.dni_empleado = personas.dni AND dni_empleado = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {

                //Rellenar datos de persona para pasarla al siguiente controlador
                Empleado e = new Empleado();
                e.setDni(resultado.getString("dni_empleado"));
                e.setSueldo(resultado.getFloat("sueldo"));
                e.setNombre(resultado.getString("nombre"));
                e.setApellidos(resultado.getString("apellidos"));

                retorno = e;
            }
        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        return retorno;
    }

    public Rol sacarRol(int id) {
        Rol retorno = null;
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM rol WHERE id_rol = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {

                //Rellenar datos de persona para pasarla al siguiente controlador
                Rol r = new Rol();
                r.setIdRol(resultado.getInt("id_rol"));
                r.setDenominacion(resultado.getString("denominacion"));

                retorno = r;
            }
        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        return retorno;
    }

    public Modulo sacarModulo(int id) {
        Modulo retorno = null;
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM modulos WHERE id_modulo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {

                //Rellenar datos de persona para pasarla al siguiente controlador
                Modulo r = new Modulo();
                r.setIdModulo(resultado.getInt("id_modulo"));
                r.setModulo(resultado.getString("modulo"));

                retorno = r;
            }
        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        return retorno;
    }

    public Pista sacarPista(int id) {
        Pista retorno = null;
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM pistas WHERE id_pista = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {

                //Rellenar datos de persona para pasarla al siguiente controlador
                Pista p = new Pista();
                p.setIdPista(resultado.getInt("id_pista"));
                p.setPrecioHora(resultado.getInt("precioHora"));
                p.setActividad(resultado.getString("actividad"));
                p.setIdSucursal(resultado.getInt("id_sucursal"));

                retorno = p;
            }
        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        return retorno;
    }

    public Sucursal sacarSucursal(int id) {
        Sucursal retorno = null;
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM sucursal WHERE id_sucursal = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {

                //Rellenar datos de persona para pasarla al siguiente controlador
                Sucursal s = new Sucursal();
                s.setIdSucursal(resultado.getInt("id_sucursal"));
                s.setCiudad(resultado.getString("ciudad"));
                s.setCodigoPostal(resultado.getInt("codigo_postal"));
                s.setDireccion(resultado.getString("direccion"));
                s.setTelefono(resultado.getInt("telefono"));

                retorno = s;
            }
        } catch (SQLException e) {
            darAlerta(false, e.getLocalizedMessage());
        }
        return retorno;
    }

    @FXML
    void actualizarEmple() {
        Rol rol = new Rol();
        if (!tfDNIEmple.getText().isEmpty() && !tfNombreEmple.getText().isEmpty() && !tfApellidosEmple.getText().isEmpty() && !tfCorreoEmple.getText().isEmpty() && !tfTelEmple.getText().isEmpty() && !tfSueldo.getText().isEmpty() && !cbRolEmple.getValue().toString().isEmpty()) {

            // Crear y configurar el cuadro de diálogo de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "UPDATE personas SET nombre = ?, apellidos = ?, correo = ?, telefono = ?, contrasena = ?, rol = ? WHERE dni = ?";
                rol = (Rol) cbRolEmple.getValue();
                try {
                    // Establecer conexión con la base de datos
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);

                    // Configurar los parámetros de la consulta
                    preparedStatement.setString(1, tfNombreEmple.getText());
                    preparedStatement.setString(2, tfApellidosEmple.getText());
                    preparedStatement.setString(3, tfCorreoEmple.getText());
                    preparedStatement.setString(4, tfTelEmple.getText());
                    preparedStatement.setString(5, tfDNIEmple.getText());
                    preparedStatement.setString(6, rol.getIdRol() + "");
                    preparedStatement.setString(7, tfDNIEmple.getText());
                    preparedStatement.executeUpdate();

                    String query2 = "UPDATE empleados SET sueldo = ? WHERE dni_empleado = ?";

                    PreparedStatement preparedStatement2 = conexion.prepareStatement(query2);
                    preparedStatement2.setString(1, tfSueldo.getText());
                    preparedStatement2.setString(2, tfDNIEmple.getText());
                    preparedStatement2.executeUpdate();

                    // Actualizar la vista con la nueva lista de empleados
                    tvEmpleados.setItems(dameListaEmpleados());
                    // Limpiar los campos de entrada
                    tfNombreEmple.setText("");
                    tfApellidosEmple.setText("");
                    tfCorreoEmple.setText("");
                    tfTelEmple.setText("");
                    tfSueldo.setText("");
                    cbRolEmple.setValue(null);

                    // Mostrar mensaje de éxito
                    darMensaje("Empleado actualizado");
                } catch (SQLException e) {
                    // Mostrar mensaje de error
                    darAlerta(false, e.getLocalizedMessage());
                }
            }
        } else {
            // Mostrar alerta si algún campo está vacío
            darAlerta(true, "Algún campo está vacío");
        }
    }

    @FXML
    void actualizarManteni() {
        Empleado emp = new Empleado();
        Pista pista = new Pista();
        if (cbDniManteni.getValue() != null && cbIdPistaManteni.getValue() != null && !tfTipoT.getText().isEmpty() && dpFechaManteni.getValue() != null && !tfDuracionManteni.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "UPDATE mantienen SET dni = ?, id_pista = ?, tipo_trabajo = ?, fecha_ini = ?, duracion = ? WHERE id_mantenimiento = ?";
                emp = (Empleado) cbDniManteni.getValue();
                pista = (Pista) cbIdPistaManteni.getValue();
                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, emp.getDni());
                    preparedStatement.setString(2, pista.getIdPista() + "");
                    preparedStatement.setString(3, tfTipoT.getText());
                    preparedStatement.setString(4, dpFechaManteni.getValue().format(DateTimeFormatter.ISO_DATE).toString());
                    preparedStatement.setString(5, tfDuracionManteni.getText());
                    preparedStatement.setString(6, this.mantenimiento.getIdMantenimiento() + "");
                    preparedStatement.executeUpdate();
                    darMensaje("Mantenimiento actualizado");
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }

                tvMantenimiento.setItems(dameListaMantenimiento());
                cbDniManteni.setValue(null);
                cbIdPistaManteni.setValue(null);
                tfTipoT.setText("");
                dpFechaManteni.setValue(null);
                tfDuracionManteni.setText("");
            }

        } else {
            darAlerta(true, "Algun valor esta vacio");
        }

    }

    @FXML
    void actualizarPista() {
        Sucursal suc = new Sucursal();

        if (!tfActividad.getText().isEmpty() && !tfPrecioH.getText().isEmpty() && cbSucursalPista.getValue() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

                String query = "UPDATE pistas SET precioHora = ?, actividad = ?, id_sucursal = ? WHERE id_pista = ?";
                suc = (Sucursal) cbSucursalPista.getValue();
                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, tfPrecioH.getText());
                    preparedStatement.setString(2, tfActividad.getText());
                    preparedStatement.setString(3, suc.getIdSucursal() + "");
                    preparedStatement.setString(4, tfIdPista.getText());
                    preparedStatement.executeUpdate();
                    darMensaje("Pista actualizada");
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }

                tvPistas.setItems(dameListaPista());
                tfIdPista.setText("");
                tfActividad.setText("");
                tfPrecioH.setText("");
                cbSucursalPista.setValue(null);
            }

        } else {
            darAlerta(true, "Algun valor esta vacio");
        }
    }

    @FXML
    void actualizarReserva() {
        Usuario usu = new Usuario();
        Pista pista = new Pista();
        if (cbDNIReserva.getValue() != null && cbIdPistaReserva.getValue() != null && !tfHoraIni.getText().isEmpty() && dpFechaReserva.getValue() != null && !tfDuracionReserva.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "UPDATE  reservas SET id_pista = ?, dni = ?, hora_inicio = ?, duracion = ?, fecha = ?, precio_reserva = ? WHERE id_reserva = ? ";
                usu = (Usuario) cbDNIReserva.getValue();
                pista = (Pista) cbIdPistaReserva.getValue();
                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, pista.getIdPista() + "");
                    preparedStatement.setString(2, usu.getDni());
                    preparedStatement.setString(3, tfHoraIni.getText());
                    preparedStatement.setString(4, tfDuracionReserva.getText());
                    preparedStatement.setString(5, dpFechaReserva.getValue().format(DateTimeFormatter.ISO_DATE).toString());
                    String query2 = "SELECT * FROM pistas WHERE id_pista = ?";
                    PreparedStatement preparedStatement2 = conexion.prepareStatement(query2);
                    preparedStatement2.setString(1, pista.getIdPista() + "");
                    System.out.println(query2);
                    ResultSet resultado1 = preparedStatement2.executeQuery();
                    float retorno = 0;
                    System.out.println("Aqui");
                    if (resultado1.next()) {
                        retorno = resultado1.getFloat("precioHora");
                        System.out.println(retorno);
                    }

                    float precio = (Float.parseFloat(tfDuracionReserva.getText()) / 60) * retorno;
                    preparedStatement.setString(6, precio + "");
                    preparedStatement.setString(7, reserva.getIdReserva() + "");

                    preparedStatement.executeUpdate();
                    darMensaje("Reserva actualizada");
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }

                tvReservas.setItems(dameListaReservas(sacarPermiso(11, persona.getRol())));
                cbDNIReserva.setValue(null);
                cbIdPistaReserva.setValue(null);
                tfHoraIni.setText("");
                dpFechaReserva.setValue(null);
                tfDuracionReserva.setText("");
            }

        } else {
            darAlerta(true, "Algun valor esta vacio");
        }
    }

    @FXML
    void actualizarSucursal() {

        if (!tfCiudad.getText().isEmpty() && !tfCodigoPostal.getText().isEmpty() && !tfDireccion.getText().isEmpty() && !tfTelSucursal.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

                String query = "UPDATE sucursal SET ciudad = ?, codigo_postal = ?, direccion = ?, telefono = ? WHERE id_sucursal = ?";
                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, tfCiudad.getText());
                    preparedStatement.setString(2, tfCodigoPostal.getText());
                    preparedStatement.setString(3, tfDireccion.getText());
                    preparedStatement.setString(4, tfTelSucursal.getText());
                    preparedStatement.setString(5, tfIdSucursal.getText());
                    preparedStatement.executeUpdate();

                    darMensaje("Sucursal actualizada");
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
                tvSucursal.setItems(dameListaSucursal());
                tfCiudad.setText("");
                tfCodigoPostal.setText("");
                tfDireccion.setText("");
                tfTelSucursal.setText("");

            }
        } else {
            darAlerta(true, "Inserta un nombre");
        }
    }

    @FXML
    void actualizarUsuario() {
        if (!tfDNIUsu.getText().isEmpty() && !tfNombreUsu.getText().isEmpty() && !tfApellidosUsu.getText().isEmpty() && !tfCorreoUsu.getText().isEmpty() && !tfTelUsu.getText().isEmpty() && !tfUsuario.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

                String query = "UPDATE personas SET nombre = ?, apellidos = ?, correo = ?, telefono = ?, contrasena = ?, rol = 4 WHERE dni = ?";

                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);

                    preparedStatement.setString(1, tfNombreUsu.getText());
                    preparedStatement.setString(2, tfApellidosUsu.getText());
                    preparedStatement.setString(3, tfCorreoUsu.getText());
                    preparedStatement.setString(4, tfTelUsu.getText());
                    preparedStatement.setString(5, tfDNIUsu.getText());
                    preparedStatement.setString(6, tfDNIUsu.getText());
                    preparedStatement.executeUpdate();
                    String query2 = "UPDATE usuarios SET usuario = ? WHERE dni_usuario = ?";

                    PreparedStatement preparedStatement2 = conexion.prepareStatement(query2);

                    preparedStatement2.setString(1, tfUsuario.getText());
                    preparedStatement2.setString(2, tfDNIUsu.getText());
                    preparedStatement2.executeUpdate();

                    tvUsuarios.setItems(dameListaUsuarios(sacarPermiso(8, persona.getRol())));
                    tfDNIUsu.setText("");
                    tfNombreUsu.setText("");
                    tfApellidosUsu.setText("");
                    tfCorreoUsu.setText("");
                    tfTelUsu.setText("");
                    tfUsuario.setText("");
                    //MODIFICAR
                    darMensaje("Usuario actualizado");
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
            }
        } else {
            darAlerta(true, "Algun campo vacío");
        }
    }

    @FXML
    void eliminarEmple() {

    }

    @FXML
    void eliminarManteni() {
        Mantenimiento mantenimiento = tvMantenimiento.getSelectionModel().getSelectedItem();
        if (mantenimiento != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "DELETE FROM mantienen WHERE id_mantenimiento = ? ";

                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, mantenimiento.getIdMantenimiento() + "");
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
                tvMantenimiento.setItems(dameListaMantenimiento());
                darMensaje("Mantenimiento eliminado");
            }
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void eliminarPista() {
        Pista pista = tvPistas.getSelectionModel().getSelectedItem();
        if (pista != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "DELETE FROM pistas WHERE id_pista = ? ";

                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, pista.getIdPista() + "");
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
                tvPistas.setItems(dameListaPista());
                darMensaje("Pista eliminada");
            }
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void eliminarReserva() {
        Reserva reserva = tvReservas.getSelectionModel().getSelectedItem();
        if (reserva != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "DELETE FROM reservas WHERE id_reserva = ? ";

                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, reserva.getIdReserva() + "");
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
                tvReservas.setItems(dameListaReservas(sacarPermiso(11, persona.getRol())));
                darMensaje("Reserva eliminada");
            }
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void eliminarUsuario() {

    }

    @FXML
    void eliminarSucursal() {
        Sucursal sucursal = tvSucursal.getSelectionModel().getSelectedItem();
        if (sucursal != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "DELETE FROM sucursal WHERE id_sucursal = ? ";

                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, sucursal.getIdSucursal() + "");
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
                tvSucursal.setItems(dameListaSucursal());
                darMensaje("Sucursal eliminada");
            }
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void insertarEmple() {
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

                String query2 = "INSERT INTO empleados VALUES (?,?)";
                PreparedStatement preparedStatement2 = conexion.prepareStatement(query2);
                preparedStatement2.setString(1, tfDNIEmple.getText());
                preparedStatement2.setString(2, tfSueldo.getText());
                preparedStatement2.executeUpdate();
                tvEmpleados.setItems(dameListaEmpleados());
                tfNombreEmple.setText("");
                tfApellidosEmple.setText("");
                tfCorreoEmple.setText("");
                tfTelEmple.setText("");
                tfSueldo.setText("");
                cbRolEmple.setValue(null);
                darMensaje("Empleado insertado");
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }

        } else {
            darAlerta(true, "Algun campo está vacío");
        }
    }

    @FXML
    void insertarManteni() {

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
                preparedStatement.setString(2, pista.getIdPista() + "");
                preparedStatement.setString(3, tfTipoT.getText());
                preparedStatement.setString(4, dpFechaManteni.getValue().format(DateTimeFormatter.ISO_DATE).toString());
                preparedStatement.setString(5, tfDuracionManteni.getText());

                preparedStatement.executeUpdate();
                darMensaje("Mantenimiento insertado");
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }

            tvMantenimiento.setItems(dameListaMantenimiento());
            cbDniManteni.setValue(null);
            cbIdPistaManteni.setValue(null);
            tfTipoT.setText("");
            dpFechaManteni.setValue(null);
            tfDuracionManteni.setText("");

        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }

    }

    @FXML
    void insertarPista() {
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
                darMensaje("Pista insertada");
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }

            tvPistas.setItems(dameListaPista());
            tfIdPista.setText("");
            tfActividad.setText("");
            tfPrecioH.setText("");
            cbSucursalPista.setValue(null);

        } else {
            darAlerta(true, "Falta algún campo");
        }

    }

    @FXML
    void insertarReserva() {
        System.out.println("Entra en insertar");
        Usuario usu = new Usuario();
        Pista pista = new Pista();
        if (cbDNIReserva.getValue() != null && cbIdPistaReserva.getValue() != null && !tfHoraIni.getText().isEmpty() && dpFechaReserva.getValue() != null && !tfDuracionReserva.getText().isEmpty()) {
            String query = "INSERT INTO reservas VALUES (" + null + ", ?, ?, ?, ?, ?, ?)";
            usu = (Usuario) cbDNIReserva.getValue();
            pista = (Pista) cbIdPistaReserva.getValue();
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, pista.getIdPista() + "");
                preparedStatement.setString(2, usu.getDni());
                preparedStatement.setString(3, tfHoraIni.getText());
                preparedStatement.setString(4, tfDuracionReserva.getText());
                preparedStatement.setString(5, dpFechaReserva.getValue().format(DateTimeFormatter.ISO_DATE).toString());
                String query2 = "SELECT * FROM pistas WHERE id_pista = ?";
                PreparedStatement preparedStatement2 = conexion.prepareStatement(query2);
                preparedStatement2.setString(1, pista.getIdPista() + "");

                ResultSet resultado = preparedStatement2.executeQuery();
                float retorno = 0;
                System.out.println("Aqui");
                if (resultado.next()) {
                    retorno = resultado.getFloat("precioHora");
                    System.out.println(retorno);
                }

                float precio = (Float.parseFloat(tfDuracionReserva.getText()) / 60) * retorno;
                preparedStatement.setString(6, precio + "");

                preparedStatement.executeUpdate();
                darMensaje("Reserva insertada");
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }

            tvReservas.setItems(dameListaReservas(sacarPermiso(11, persona.getRol())));
            cbDNIReserva.setValue(null);
            cbIdPistaReserva.setValue(null);
            tfHoraIni.setText("");
            dpFechaReserva.setValue(null);
            tfDuracionReserva.setText("");

        } else {
            darAlerta(true, "Algun valor esta vacio");
        }
    }

    @FXML
    void insertarSucursal() {

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
                darMensaje("Sucursal insertada");
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }
            tvSucursal.setItems(dameListaSucursal());
            tfCiudad.setText("");
            tfCodigoPostal.setText("");
            tfDireccion.setText("");
            tfTelSucursal.setText("");
        } else {
            darAlerta(true, "Algún campo esta vacío");
        }

    }

    @FXML
    void insertarUsuario() {
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

                String query2 = "INSERT INTO usuarios VALUES (?,?)";

                PreparedStatement preparedStatement2 = conexion.prepareStatement(query2);
                preparedStatement2.setString(1, tfDNIUsu.getText());
                preparedStatement2.setString(2, tfUsuario.getText());
                preparedStatement2.executeUpdate();

                tvUsuarios.setItems(dameListaUsuarios(sacarPermiso(8, persona.getRol())));
                tfDNIUsu.setText("");
                tfNombreUsu.setText("");
                tfApellidosUsu.setText("");
                tfCorreoUsu.setText("");
                tfTelUsu.setText("");
                tfUsuario.setText("");
                darMensaje("Usuario insertado");
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }

        } else {
            darAlerta(true, "Algún campo está vacío");
        }
    }

    @FXML
    void insertarRol() {
        if (!tfDenominacion.getText().isEmpty()) {
            String query = "INSERT INTO rol VALUES (" + null + ", ?)";

            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, tfDenominacion.getText());
                preparedStatement.executeUpdate();
                darMensaje("Rol insetado");
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }
            tvRol.setItems(dameListaRol());
        } else {
            darAlerta(true, "Inserta una denominación");
        }
    }

    @FXML
    void actualizarRol() {
        if (!tfDenominacion.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "UPDATE rol SET denominacion = ? WHERE id_rol = ? ";

                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, tfDenominacion.getText());
                    preparedStatement.setString(2, tfIdRol.getText());
                    preparedStatement.executeUpdate();
                    darMensaje("Rol Actualizado");
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
                tvRol.setItems(dameListaRol());
            }
        } else {
            darAlerta(true, "Inserta una denominacion");
        }
    }

    @FXML
    void eliminarRol() {
        Rol rol = tvRol.getSelectionModel().getSelectedItem();
        if (rol != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "DELETE FROM rol WHERE id_rol = ? ";

                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, rol.getIdRol() + "");
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
                tvRol.setItems(dameListaRol());
                darMensaje("Rol eliminado");
            }
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void insertarModulo() {

    }

    @FXML
    void actualizarModulo() {

    }

    @FXML
    void eliminarModulo() {

    }

    @FXML
    void insertarPermiso() {
        Rol rol = new Rol();
        Modulo modulo = new Modulo();
        if (cbModuloPer.getValue() != null && cbIdRolPer.getValue() != null) {
            String query = "INSERT INTO permisos VALUES (?, ?, ?)";

            String permiso = "";
            if (cbxCrear.isSelected()) {
                permiso += "C";
            }
            if (cbxListar.isSelected()) {
                permiso += "R";
            }
            if (cbxActualizar.isSelected()) {
                permiso += "U";
            }
            if (cbxEliminar.isSelected()) {
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
                darMensaje("Permiso insertado");
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }
            tvPermisos.setItems(dameListaPermisos());
            cbModuloPer.setValue(null);
            cbIdRolPer.setValue(null);
            cbxCrear.setSelected(false);
            cbxListar.setSelected(false);
            cbxActualizar.setSelected(false);
            cbxEliminar.setSelected(false);
        } else {
            darAlerta(true, "Algún campo esta vacío");
        }

    }

    @FXML
    void actualizarPermiso() {
        Rol rol = new Rol();
        Modulo modulo = new Modulo();
        if (cbModuloPer.getValue() != null && cbIdRolPer.getValue() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "UPDATE permisos SET permisos = ? WHERE id_rol = ? && id_modulo = ?  ";

                String permiso = "";
                if (cbxCrear.isSelected()) {
                    permiso += "C";
                }
                if (cbxListar.isSelected()) {
                    permiso += "R";
                }
                if (cbxActualizar.isSelected()) {
                    permiso += "U";
                }
                if (cbxEliminar.isSelected()) {
                    permiso += "D";
                }
                rol = (Rol) cbIdRolPer.getValue();
                modulo = (Modulo) cbModuloPer.getValue();

                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, permiso);
                    preparedStatement.setString(2, rol.getIdRol() + "");
                    preparedStatement.setString(3, modulo.getIdModulo() + "");
                    preparedStatement.executeUpdate();
                    darMensaje("Permiso actualizado");
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
                tvPermisos.setItems(dameListaPermisos());
                cbModuloPer.setValue(null);
                cbIdRolPer.setValue(null);
                cbxCrear.setSelected(false);
                cbxListar.setSelected(false);
                cbxActualizar.setSelected(false);
                cbxEliminar.setSelected(false);
            }
        } else {
            darAlerta(true, "Inserta un nombre");
        }
    }

    @FXML
    void eliminarPermiso() {
        Permiso permiso = tvPermisos.getSelectionModel().getSelectedItem();
        if (permiso != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de continuar con esta acción?, los cambios serán irreversibles");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            // Verificar si el usuario confirmó la acción
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                String query = "DELETE FROM permisos WHERE id_modulo = ? AND id_rol = ?";

                try {
                    Connection conexion = this.getConnection();
                    PreparedStatement preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.setString(1, permiso.getIdModulo() + "");
                    preparedStatement.setString(2, permiso.getIdRol() + "");
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    darAlerta(false, e.getLocalizedMessage());
                }
                tvPermisos.setItems(dameListaPermisos());
                darMensaje("Permiso eliminado");
            }
        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
    }

    @FXML
    void insertarTrabajan() {
        Empleado emp = new Empleado();
        Sucursal suc = new Sucursal();
        if (cbDNITrabajan.getValue() != null && cbIDSucursalTrabajan.getValue() != null && dpInicioTrabajan.getValue() != null && dpFinTrabajan.getValue() != null) {
            String query = "INSERT INTO trabajan values (?,?,?,?)";

            emp = (Empleado) cbDNITrabajan.getValue();
            suc = (Sucursal) cbIDSucursalTrabajan.getValue();
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, emp.getDni());
                preparedStatement.setString(2, suc.getIdSucursal() + "");
                preparedStatement.setString(3, dpInicioTrabajan.getValue().format(DateTimeFormatter.ISO_DATE));
                preparedStatement.setString(4, dpFinTrabajan.getValue().format(DateTimeFormatter.ISO_DATE));
                preparedStatement.executeUpdate();
                darMensaje("Periodo de trabajo insertado");

                tvTrabajan.setItems(dameListaTrabajan());
                cbDNITrabajan.setValue(null);
                cbIDSucursalTrabajan.setValue(null);
                dpInicioTrabajan.setValue(null);
                dpFinTrabajan.setValue(null);
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }
        } else {
            darAlerta(true, "Falta algún campo");
        }
    }

    @FXML
    void actualizarTrabajan() {
        Empleado emp = new Empleado();
        Sucursal suc = new Sucursal();
        if (cbDNITrabajan.getValue() != null && cbIDSucursalTrabajan.getValue() != null && dpInicioTrabajan.getValue() != null && dpFinTrabajan.getValue() != null) {
            String query = "UPDATE trabajan set fecha_fin = ? WHERE dni_empleado = ? AND id_sucursal = ? AND fecha_ini = ?";

            emp = (Empleado) cbDNITrabajan.getValue();
            suc = (Sucursal) cbIDSucursalTrabajan.getValue();
            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, dpFinTrabajan.getValue().format(DateTimeFormatter.ISO_DATE));
                preparedStatement.setString(2, emp.getDni());
                preparedStatement.setString(3, suc.getIdSucursal() + "");
                preparedStatement.setString(4, dpInicioTrabajan.getValue().format(DateTimeFormatter.ISO_DATE));
                preparedStatement.executeUpdate();
                darMensaje("Periodo de trabajo actualizado");

                tvTrabajan.setItems(dameListaTrabajan());
                cbDNITrabajan.setValue(null);
                cbIDSucursalTrabajan.setValue(null);
                dpInicioTrabajan.setValue(null);
                dpFinTrabajan.setValue(null);
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }
        } else {
            darAlerta(true, "Falta algún campo");
        }
    }

    @FXML
    void eliminarTrabajan() {
        Trabajan trabajan = tvTrabajan.getSelectionModel().getSelectedItem();
        if (trabajan != null) {
            String query = "DELETE FROM trabajan WHERE dni_empleado = ? AND id_sucursal = ? AND fecha_ini = ?";

            try {
                Connection conexion = this.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setString(1, trabajan.getDni() + "");
                preparedStatement.setString(2, trabajan.getIdSucursal() + "");
                preparedStatement.setString(3, trabajan.getFechaInicio());
                preparedStatement.executeUpdate();
                tvTrabajan.setItems(dameListaTrabajan());
                darMensaje("Periodo de trabajo eliminado");
            } catch (SQLException e) {
                darAlerta(false, e.getLocalizedMessage());
            }

        } else {
            darAlerta(true, "Debes seleccionar un elemento de la tabla");
        }
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

    public ObservableList<Usuario> dameListaUsuarios(String permiso) {
        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "";
            if (permiso.contains("*")) {
                try {
                    query = "SELECT * FROM usuarios, personas where usuarios.dni_usuario = personas.dni AND usuarios.dni_usuario = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, persona.getDni());
                    ResultSet resultado = preparedStatement.executeQuery();
                    Usuario usuario;
                    while (resultado.next()) { //Se usan los identificadores propios en la BBDD

                        usuario = new Usuario(resultado.getString("personas.dni"), resultado.getString("nombre"), resultado.getString("apellidos"), resultado.getString("correo"), resultado.getInt("telefono"), resultado.getString("contrasena"), resultado.getInt("rol"), resultado.getString("usuario"));

                        listaUsuarios.add(usuario);
                    }
                } catch (SQLException e) {
                }
            } else {
                query = "SELECT * FROM usuarios, personas where usuarios.dni_usuario = personas.dni";
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

    public ObservableList<Reserva> dameListaReservas(String permiso) {
        ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "";
            if (permiso.contains("*")) {
                query = "SELECT * FROM reservas WHERE dni = ?";
                
                try {

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, persona.getDni());
                    ResultSet resultado = preparedStatement.executeQuery();
                    System.out.println(preparedStatement);
                    Reserva res;
                    while (resultado.next()) {
                        res = new Reserva(resultado.getInt("id_reserva"), resultado.getInt("id_pista"), resultado.getString("dni"), resultado.getString("hora_inicio"), resultado.getFloat("duracion"), resultado.getString("fecha"), resultado.getFloat("precio_reserva"));
                        System.out.println(res);
                        listaReservas.add(res);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getLocalizedMessage());
                }

            } else {
                query = "SELECT * FROM reservas";
                Statement st;
                ResultSet rs;

                try {
                    st = connection.createStatement();
                    rs = st.executeQuery(query);
                    Reserva reserva;
                    while (rs.next()) { //Se usan los identificadores propios en la BBDD

                        reserva = new Reserva(rs.getInt("id_reserva"), rs.getInt("id_pista"), rs.getString("dni"), rs.getString("hora_inicio"), rs.getFloat("duracion"), rs.getString("fecha"), rs.getFloat("precio_reserva"));

                        listaReservas.add(reserva);
                    }
                } catch (SQLException e) {
                }
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

                    mantenimiento = new Mantenimiento(rs.getInt("id_mantenimiento"), rs.getInt("id_pista"), rs.getString("dni"), rs.getString("tipo_trabajo"), rs.getFloat("duracion"), rs.getString("fecha_ini"));

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

                    trabajan = new Trabajan(rs.getString("dni_empleado"), rs.getInt("id_sucursal"), rs.getString("fecha_ini"), rs.getString("fecha_fin"));

                    listaTrabajan.add(trabajan);
                }
            } catch (SQLException e) {
            }
            return listaTrabajan;
        }
        return null;
    }

    public void darMensaje(String texto) {
        message.setText(texto);
    }

    public void darAlerta(Boolean tipo, String mensaje) {
        if (tipo) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Ha habido un error de conexión o de la base de datos, error: " + mensaje);
            alert.show();
        }
    }

    public ButtonType darConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();

        ButtonType resultado = alert.showAndWait().orElse(ButtonType.CANCEL);

        return resultado;
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
        darMensaje("Bienvenido a Sportify");

        ObservableList<Sucursal> listaS = dameListaSucursal();

        //Los campos han de coincidir con los campos del objeto Sucursal
        tcIDSucursal.setCellValueFactory(new PropertyValueFactory<Sucursal, Integer>("idSucursal"));
        tcCiudad.setCellValueFactory(new PropertyValueFactory<Sucursal, String>("ciudad"));
        tcCodPos.setCellValueFactory(new PropertyValueFactory<Sucursal, Integer>("codigoPostal"));
        tcDireccion.setCellValueFactory(new PropertyValueFactory<Sucursal, String>("direccion"));
        tcTelSucu.setCellValueFactory(new PropertyValueFactory<Sucursal, Integer>("telefono"));

        tvSucursal.setItems(listaS);
        cbSucursalPista.setItems(listaS);
        cbIDSucursalTrabajan.setItems(listaS);
        ObservableList<Pista> listaP = dameListaPista();

        //Los campos han de coincidir con los campos del objeto Libros
        tcIdPista.setCellValueFactory(new PropertyValueFactory<Pista, Integer>("idPista"));
        tcPrecioH.setCellValueFactory(new PropertyValueFactory<Pista, Integer>("precioHora"));
        tcActividad.setCellValueFactory(new PropertyValueFactory<Pista, String>("actividad"));

        tvPistas.setItems(listaP);
        cbIdPistaManteni.setItems(listaP);
        cbIdPistaReserva.setItems(listaP);
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

        ObservableList<Usuario> listaUsuarios = dameListaUsuarios(sacarPermiso(8, persona.getRol()));

        //Los campos han de coincidir con los campos del objeto Libros
        tcDNIUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("dni"));
        tcNombreUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        tcApellidosUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellidos"));
        tcCorreoUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        tcTelUsu.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("telefono"));
        tcUsu.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usuario"));
        cbDNIReserva.setItems(listaUsuarios);
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
        cbDNITrabajan.setItems(listaEmpleados);
        ObservableList<Reserva> listaReservas = dameListaReservas(sacarPermiso(11, persona.getRol()));

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

        cbBuscarSucursales.getItems().addAll("id_sucursal", "ciudad", "codigo_postal", "direccion", "telefono");
        cbBuscarReservas.getItems().addAll("id_pista", "dni", "hora_inicio", "duracion", "fecha", "precio_reserva");
        cbBuscarPistas.getItems().addAll("id_pista", "precioHora", "actividad", "id_sucursal");
        cbBuscarUsuarios.getItems().addAll("dni", "nombre", "apellidos", "correo", "telefono", "usuario");
        cbBuscarEmpleados.getItems().addAll("dni", "nombre", "apellidos", "correo", "telefono", "sueldo");
        cbBuscarMantienen.getItems().addAll("dni", "id_pista", "tipo_trabajo", "fecha_ini", "duracion");
        cbBuscarTrabajan.getItems().addAll("dni_empleado", "id_sucursal", "fecha_ini", "fecha_fin");
        cbBuscarPermisos.getItems().addAll("id_rol", "id_modulo", "permisos");

        contextMenuRol.setAutoHide(true);
        itemRol.setOnAction(event -> {
            //int selectedItem = tablaCasas.getSelectionModel().getSelectedItem().getIdCasa();
            eliminarRol();
        });
        contextMenuRol.getItems().add(itemRol);
        tvRol.setContextMenu(contextMenuRol);

        tvRol.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // This is a double click
                cargarRol();
            }
        });

        contextMenuSucursal.setAutoHide(true);
        itemSucursal.setOnAction(event -> {
            //int selectedItem = tablaCasas.getSelectionModel().getSelectedItem().getIdCasa();
            eliminarSucursal();
        });
        contextMenuSucursal.getItems().add(itemSucursal);
        tvSucursal.setContextMenu(contextMenuSucursal);

        tvSucursal.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // This is a double click
                cargarSucursal();
            }
        });

        contextMenuReserva.setAutoHide(true);
        itemReserva.setOnAction(event -> {
            //int selectedItem = tablaCasas.getSelectionModel().getSelectedItem().getIdCasa();
            eliminarReserva();
        });
        contextMenuReserva.getItems().add(itemReserva);
        tvReservas.setContextMenu(contextMenuReserva);

        tvReservas.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // This is a double click
                cargarReserva();
            }
        });

        contextMenuPista.setAutoHide(true);
        itemPista.setOnAction(event -> {
            //int selectedItem = tablaCasas.getSelectionModel().getSelectedItem().getIdCasa();
            eliminarPista();
        });
        contextMenuPista.getItems().add(itemPista);
        tvPistas.setContextMenu(contextMenuPista);

        tvPistas.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // This is a double click
                cargarPista();
            }
        });

        contextMenuUsuario.setAutoHide(true);
        itemUsuario.setOnAction(event -> {
            //int selectedItem = tablaCasas.getSelectionModel().getSelectedItem().getIdCasa();
            eliminarUsuario();
        });
        contextMenuUsuario.getItems().add(itemUsuario);
        tvUsuarios.setContextMenu(contextMenuUsuario);

        tvUsuarios.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // This is a double click
                cargarUsuario();
            }
        });

        contextMenuEmpleado.setAutoHide(true);
        itemEmpleado.setOnAction(event -> {
            //int selectedItem = tablaCasas.getSelectionModel().getSelectedItem().getIdCasa();
            eliminarEmple();
        });
        contextMenuEmpleado.getItems().add(itemEmpleado);
        tvEmpleados.setContextMenu(contextMenuEmpleado);

        tvEmpleados.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // This is a double click
                cargarEmple();
            }
        });

        contextMenuMantenimiento.setAutoHide(true);
        itemMantenimiento.setOnAction(event -> {
            //int selectedItem = tablaCasas.getSelectionModel().getSelectedItem().getIdCasa();
            eliminarManteni();
        });
        contextMenuMantenimiento.getItems().add(itemMantenimiento);
        tvMantenimiento.setContextMenu(contextMenuMantenimiento);

        tvMantenimiento.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // This is a double click
                cargarManteni();
            }
        });

        contextMenuTrabajan.setAutoHide(true);
        itemTrabajan.setOnAction(event -> {
            //int selectedItem = tablaCasas.getSelectionModel().getSelectedItem().getIdCasa();
            eliminarTrabajan();
        });
        contextMenuTrabajan.getItems().add(itemTrabajan);
        tvTrabajan.setContextMenu(contextMenuTrabajan);

        tvTrabajan.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // This is a double click
                cargarTrabajan();
            }
        });

        contextMenuPermiso.setAutoHide(true);
        itemPermiso.setOnAction(event -> {
            //int selectedItem = tablaCasas.getSelectionModel().getSelectedItem().getIdCasa();
            eliminarPermiso();
        });
        contextMenuPermiso.getItems().add(itemPermiso);
        tvPermisos.setContextMenu(contextMenuPermiso);

        tvPermisos.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // This is a double click
                cargarPermiso();
            }
        });

        System.out.println("Id rol: " + persona.getRol());
        System.out.println("Permiso: " + sacarPermiso(1, persona.getRol()));
        if (!sacarPermiso(1, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabSucursal);
        }
        if (!sacarPermiso(2, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabRoles);
        }
        if (!sacarPermiso(3, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabModulos);
        }
        if (!sacarPermiso(4, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabPermisos);
        }
        if (!sacarPermiso(6, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabEmpleados);
        }
        if (!sacarPermiso(7, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabTrabajan);
        }
        if (!sacarPermiso(8, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabUsuarios);
        }
        if (!sacarPermiso(9, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabPistas);
        }
        if (!sacarPermiso(10, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabMantenimiento);
        }
        if (!sacarPermiso(11, persona.getRol()).contains("R")) {
            tbGeneral.getTabs().remove(tabReservas);
        }

    }

    public String sacarPermiso(int modulo, int rol) {
        String retorno = null;
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM permisos WHERE id_rol = ? AND id_modulo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rol);
            preparedStatement.setInt(2, modulo);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {
                retorno = resultado.getString("permisos");
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return retorno;
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
