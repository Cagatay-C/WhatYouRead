package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class booksController {
	int idm = 3;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btn_add;

    @FXML
    private TableView<Books> table_view;

    @FXML
    private TableColumn<Books, Integer> clm_id;

    @FXML
    private TableColumn<Books,String> clm_author;

    @FXML
    private JFXTextField txt_book;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private TableColumn<Books, String> clm_book;

    @FXML
    private JFXTextField txt_author;

    @FXML
    private JFXButton btn_update;
    
    String selected = "";
    
    String sql="";
    Connection connect=null;
    PreparedStatement sorguIfadesi = null;
    ResultSet getirilen = null;
    
    public booksController() {
    	connect = dataBaseUtil.connect();
    }
    
    
    @FXML
    void btn_update_clicked(ActionEvent event) {
    	sql="update books set book_name=?,author_name=? where book_id=?";
    	try {
			sorguIfadesi=connect.prepareStatement(sql);
			sorguIfadesi.setString(1, txt_book.getText());
			sorguIfadesi.setString(2, txt_author.getText());
			sorguIfadesi.setInt(3, idm);
			sorguIfadesi.executeUpdate();
			
			getValues(table_view);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    	
    }

    @FXML
    void btn_add_clicked(ActionEvent event) {
    	sql = "insert into books(book_name,author_name) values(?,?)";
    	try {
			sorguIfadesi = connect.prepareStatement(sql);
			sorguIfadesi.setString(1,txt_book.getText());
			sorguIfadesi.setString(2, txt_author.getText());
			sorguIfadesi.execute();
			getValues(table_view);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void btn_delete_clicked(ActionEvent event) {
    	sql = "delete from books where book_name=? and author_name=?";
    	try {
    		sorguIfadesi = connect.prepareStatement(sql);
    		sorguIfadesi.setString(1,txt_book.getText());
    		sorguIfadesi.setString(2, txt_author.getText());
    		sorguIfadesi.executeUpdate();
    		getValues(table_view);
    	}catch(Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    						
    }
    
    public void getValues(TableView<Books> table) throws SQLException {
    	sql = "select * from books";
    	ObservableList<Books> booksList = FXCollections.observableArrayList();
    	try {
    		sorguIfadesi = connect.prepareStatement(sql);
    		ResultSet getirilen = sorguIfadesi.executeQuery();
    	
    		while(getirilen.next()) {
    			booksList.add(new Books(getirilen.getInt("book_id"),getirilen.getString("book_name"),getirilen.getString("author_name")));
    			
    		}
    		clm_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
    		clm_book.setCellValueFactory(new PropertyValueFactory<>("book_name"));
    		clm_author.setCellValueFactory(new PropertyValueFactory<>("author_name"));
    		table_view.setItems(booksList);
    	}catch(Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    	
    }
    

    @FXML
    void initialize() {
    	try {
			getValues(table_view);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
