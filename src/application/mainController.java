package application;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class mainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lab_books;

    @FXML
    private Label lab_newbook;

    @FXML
    private ListView<String> list_books;

    @FXML
    private ListView<String> list_cur;

    @FXML
    private JFXButton btn_tocurrent;

    @FXML
    private JFXButton btn_edit;

    @FXML
    private ListView<String> list_want;

    @FXML
    private JFXButton btn_tofinish;

    @FXML
    private ImageView img_book;

    @FXML
    private JFXButton btn_wantoread;

    @FXML
    private ListView<String> list_finish;
    
    String user = loginController.getUserName();
    

    Connection connect = null;
    PreparedStatement sorguIfadesi = null;
    ResultSet getirilen = null;
    String sql;
    String sql2;
    public mainController() {
    	connect = dataBaseUtil.connect();
    }

   
   

    @FXML
    void btn_wantoread_clicked(ActionEvent event) {
    	sql="insert into want_read(user_name, book_name) values (?,?)";
    	int index = list_books.getSelectionModel().getSelectedIndex();
    	String bookName=list_books.getItems().get(index);
    	list_want.getItems().add(bookName);
    	try {
			sorguIfadesi=connect.prepareStatement(sql);
			sorguIfadesi.setString(1, user);
			sorguIfadesi.setString(2, bookName);
			sorguIfadesi.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }

    

    @FXML
    void btn_adtofinished_clicked(ActionEvent event) {
    	sql="insert into finish_read(user_name, book_name) values (?,?)";
    	int index = list_cur.getSelectionModel().getSelectedIndex();
    	String bookName=list_cur.getItems().get(index);
    	list_finish.getItems().add(bookName);
    	list_cur.getItems().remove(index);
    	try {
			sorguIfadesi=connect.prepareStatement(sql);
			sorguIfadesi.setString(1, user);
			sorguIfadesi.setString(2, bookName);
			sorguIfadesi.execute();
			
			sql2="delete from cur_read where book_name=? and user_name=?";
			sorguIfadesi=connect.prepareStatement(sql2);
			sorguIfadesi.setString(1, bookName);
			sorguIfadesi.setString(2, user);
			sorguIfadesi.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

   

    @FXML
    void btn_tocurrent_clicked(ActionEvent event) {
    	sql="insert into cur_read(user_name, book_name) values (?,?)";
    	int index = list_want.getSelectionModel().getSelectedIndex();
    	String bookName=list_want.getItems().get(index);
    	list_cur.getItems().add(bookName);
    	list_want.getItems().remove(index);
    	try {
			sorguIfadesi=connect.prepareStatement(sql);
			sorguIfadesi.setString(1, user);
			sorguIfadesi.setString(2, bookName);
			sorguIfadesi.execute();
			
			sql2="delete from want_read where book_name=? and user_name=?";
			sorguIfadesi=connect.prepareStatement(sql2);
			sorguIfadesi.setString(1, bookName);
			sorguIfadesi.setString(2, user);
			sorguIfadesi.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void btn_edit_clicked(ActionEvent event) {
    	try {
    	Stage window = new Stage();
		AnchorPane mainPane = (AnchorPane) FXMLLoader.load(getClass().getResource("booksForm.fxml"));
		Scene mainScene = new Scene(mainPane,510,400);
		window.setScene(mainScene);
		window.show();
    	}catch(Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    	}
    public void getToListBooks() {
    	sql = "select book_name from books";
    	try {
			sorguIfadesi=connect.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				list_books.getItems().add(getirilen.getString("book_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void getToWantBooks() {
    	sql = "select book_name from want_read where user_name=?";
    	try {
    		sorguIfadesi=connect.prepareStatement(sql);
    		sorguIfadesi.setString(1, user);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				list_want.getItems().add(getirilen.getString("book_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void getToCurBooks() { 
    sql = "select book_name from cur_read where user_name=?";
	try {
		sorguIfadesi=connect.prepareStatement(sql);
		sorguIfadesi.setString(1, user);
		ResultSet getirilen = sorguIfadesi.executeQuery();
		while(getirilen.next()) {
			list_cur.getItems().add(getirilen.getString("book_name"));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    public void getToFinBooks() {
    	sql = "select book_name from finish_read where user_name=?";
    	try {
    		sorguIfadesi=connect.prepareStatement(sql);
    		sorguIfadesi.setString(1, user);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				list_finish.getItems().add(getirilen.getString("book_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @FXML
    void initialize() {
        getToListBooks();
        getToWantBooks();
        getToCurBooks();
        getToFinBooks();
    }
}
