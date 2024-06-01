package controlador;



import Modelo.Persona;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class controladorLogin implements Initializable{
    private controladorAdmin cvfc;
    @FXML
    private Button btnEntrar;

    @FXML
    private PasswordField pfContrasena;

    @FXML
    private TextField tfDNI;

    @FXML
    void btnEntrar() {
        String dni = tfDNI.getText();
        String contra = pfContrasena.getText();
        
        String cadena = existeUsuario(dni,contra);
        
        switch (cadena){
            case "OK":
                Persona per = sacarPersona(dni);
        
                if (per != null) {
                    try {
                    // Cargar el nuevo contenido desde el archivo FXML
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/vistaAdministrador1.fxml"));
                    Pane nuevoContenidoPane = loader.load();

                    cvfc = loader.getController();
                    this.cvfc.enviaAControlador(per);
                    cvfc.setControladorEnlace(this);

                    // Crear una nueva ventana para mostrar el contenido
                    Stage nuevoContenidoStage = new Stage();
                    nuevoContenidoStage.setTitle("Sportify");
                    nuevoContenidoStage.getIcons().add(new Image("/Imagenes/Miscelanea/tenis.png"));
                    nuevoContenidoStage.initModality(Modality.WINDOW_MODAL);

                    // Configurar la escena con el contenido cargado
                    Scene nuevoContenidoScene = new Scene(nuevoContenidoPane);

                    nuevoContenidoStage.setScene(nuevoContenidoScene);

                    // Mostrar la nueva ventana
                    nuevoContenidoStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }else{
                    System.out.println("Ha ocurrido un problema");
                }
                break;
            case "WRONGPASS" :
                System.out.println("Contraseña incorrecta");
                break;
            case "NOUSER":
                System.out.println("El usuario no existe");
        }

        
        
        System.out.println(existeUsuario(dni,contra));
    }

    public Persona sacarPersona(String dni){
        Persona retorno = null;
            try {
                Connection connection = getConnection();
                String query = "SELECT * FROM personas WHERE dni = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, dni);
                ResultSet resultado = preparedStatement.executeQuery();

                if (resultado.next()) {

                    //Rellenar datos de persona para pasarla al siguiente controlador
                    Persona p = new Persona();
                    p.setDni(dni);
                    p.setNombre(resultado.getString("nombre"));
                    p.setApellidos(resultado.getString("apellidos"));
                    p.setCorreo(resultado.getString("correo"));
                    p.setTelefono(resultado.getInt("telefono"));
                    p.setPassword(resultado.getString("contrasena"));
                    p.setRol(resultado.getInt("rol"));

                    retorno = p;
                }
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        return retorno;
    }
    
    public String existeUsuario(String dni, String pass) {
        String retorno = null;
            try {
                Connection connection = getConnection();
                String query = "SELECT contrasena FROM personas WHERE dni = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, dni);
                ResultSet resultado = preparedStatement.executeQuery();

                if (resultado.next()) {
                     System.out.println(resultado.getString("contrasena").toString().equals(pass));
                    //String comp = resultado.getString("contrasena").toString();
                    if(resultado.getString("contrasena").toString().equals(pass)){
                         retorno = "OK";
                     }else{
                         retorno = "WRONGPASS";
                     }
                } else {
                    retorno = "NOUSER";
                }
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        return retorno;
    }
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tfDNI.setText("21027174E");
       pfContrasena.setText("abcd");
    }

}
