package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXPasswordField txt_pass;

    @FXML
    private JFXButton btn_forgot;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_sign;

    @FXML
    private JFXCheckBox cb_remember;

    @FXML
    private JFXButton btn_login;

    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXButton btn_member;

    private static String userName;
    
    
    Connection connect=null;
    PreparedStatement sorguIfadesi = null;
    ResultSet getirilen=null;
    String sql;
    
    public loginController() {
    	connect = dataBaseUtil.connect();
    }
    @FXML
    void btn_sign_clicked(ActionEvent event) {
    	try {
        	Stage window = new Stage();
    		AnchorPane mainPane = (AnchorPane) FXMLLoader.load(getClass().getResource("signForm.fxml"));
    		Scene mainScene = new Scene(mainPane,310,400);
    		window.setScene(mainScene);
    		window.show();
        	}catch(Exception e) {
        		System.out.println(e.getMessage().toString());
        	}
    }

  

    @FXML
    void btn_member_clicked(ActionEvent event) {

    }

   

    @FXML
    void btn_back_clicked(ActionEvent event) {

    }

   
    @FXML
    void btn_login_clicked(ActionEvent event) {
    	sql="select * from users where user_name=? and user_pass=?";
    	
    	try {
    		sorguIfadesi=connect.prepareStatement(sql);
    		sorguIfadesi.setString(1, txt_username.getText());
    		sorguIfadesi.setString(2, txt_pass.getText());
    		ResultSet getirilen=sorguIfadesi.executeQuery();
    		
    		if(getirilen.next()) {
    			setUserName(txt_username.getText());
    			Stage window = new Stage();
    			AnchorPane mainPane = (AnchorPane) FXMLLoader.load(getClass().getResource("mainForm.fxml"));
    			Scene mainScene = new Scene(mainPane,700,400);
    			window.setScene(mainScene);
    			window.show();
    		}
    		else {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Login Failed");
    			alert.setHeaderText("Username or password incorrect !");
    			alert.showAndWait();
    		
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

 

    @FXML
    void initialize() {
        assert txt_pass != null : "fx:id=\"txt_pass\" was not injected: check your FXML file 'loginForm.fxml'.";
        assert btn_forgot != null : "fx:id=\"btn_forgot\" was not injected: check your FXML file 'loginForm.fxml'.";
        assert btn_back != null : "fx:id=\"btn_back\" was not injected: check your FXML file 'loginForm.fxml'.";
        assert btn_sign != null : "fx:id=\"btn_sign\" was not injected: check your FXML file 'loginForm.fxml'.";
        assert cb_remember != null : "fx:id=\"cb_remember\" was not injected: check your FXML file 'loginForm.fxml'.";
        assert btn_login != null : "fx:id=\"btn_login\" was not injected: check your FXML file 'loginForm.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'loginForm.fxml'.";
        assert btn_member != null : "fx:id=\"btn_member\" was not injected: check your FXML file 'loginForm.fxml'.";

    }
	public static String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		loginController.userName = userName;
	}
}
