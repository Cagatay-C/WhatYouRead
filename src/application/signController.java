package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class signController {

	Connection connect = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen =null;
	String sql = "";
	
	public signController() {
		connect = dataBaseUtil.connect();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXPasswordField txt_password;

    @FXML
    private JFXButton btn_sign;

    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXTextField txt_email;

    @FXML
    void btn_clicked_sign(ActionEvent event) {
    	sql = "insert into users(user_name,user_pass,user_email) values(?,?,?)";
    	try {
			sorguIfadesi = connect.prepareStatement(sql);
			sorguIfadesi.setString(1,txt_username.getText());
			sorguIfadesi.setString(2, txt_password.getText());
			sorguIfadesi.setString(3, txt_email.getText());
			sorguIfadesi.execute();
			System.out.println("Confirmed!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    }
   

    @FXML
    void initialize() {
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'signForm.fxml'.";
        assert btn_sign != null : "fx:id=\"btn_sign\" was not injected: check your FXML file 'signForm.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'signForm.fxml'.";
        assert txt_email != null : "fx:id=\"txt_email\" was not injected: check your FXML file 'signForm.fxml'.";

    }
}
