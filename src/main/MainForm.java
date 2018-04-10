/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Branko
 */
public class MainForm extends Form {

    @FXML
    private ListView<Covek> lista1;
    @FXML
    private TextField txtime;
    @FXML
    private TextField txtprezime;
    @FXML
    private TextField txtmail;
    @FXML
    private Button btndodaj;
    @FXML
    private Button btnpopuni;
    @FXML
    private TextField txtpretraga;
    @FXML
    private Button btnpretraga;
    @FXML
    private Label lbpretraga;
    @FXML
    private RadioButton rpoimenu;
    @FXML
    private RadioButton rpoprezimenu;
    @FXML
    private ToggleGroup grupa;
    @FXML
    private Button btnbrisi;
    @FXML
    private Label lblk;
    

    @FXML
    private void dodajcoveka(ActionEvent event) throws SQLException {

        Connection conn = null;
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kontakti?autoReconnect=true&useSSL=false", "root", "123456");
        String query = "Insert into covek(ime,prezime,mail) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, txtime.getText());
        ps.setString(2, txtprezime.getText());
        ps.setString(3, txtmail.getText());
        ps.execute();
        conn.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("dodali ste coveka");
        alert.showAndWait();
        popunilistu();

    }

    @FXML
    public void popunilistu() throws SQLException {
        ArrayList<Covek> lista2 = new ArrayList<Covek>();
        Connection conn = null;

        conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kontakti?autoReconnect=true&useSSL=false", "root", "123456");
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("Select rednibroj_id,ime,prezime,mail from covek");
        Covek c= null;
        while (rs.next()) {
            c=new Covek(rs.getInt("rednibroj_id"), rs.getString("ime"), rs.getString("prezime"),rs.getString("mail"));
           lista2.add(c);        
           }
           
        ObservableList<Covek> lista = FXCollections.observableList(lista2);
       
        lista1=new ListView<Covek>(lista);
        String ljudi= "";
        for(int i=0;i<lista2.size();i++){
            ljudi +=lista2.get(i).getIme();
        }
        lblk.setText(ljudi);
        //lista1.setItems(lista);
        lista1=new ListView<Covek>(lista);
        conn.close();
    }

    private void popunilistu2(ActionEvent event) throws SQLException {

        popunilistu();
    }

    private void pretraga2() throws SQLException {

        ArrayList<String> lista2 = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement s = null;
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kontakti?autoReconnect=true&useSSL=false", "root", "123456");
        if (rpoimenu.isSelected()) {
            s = conn.prepareStatement("Select ime,prezime from covek where ime=?");
        }
        if (rpoprezimenu.isSelected()) {
            s = conn.prepareStatement("Select ime,prezime from covek where prezime=?");
        }
        s.setString(1, txtpretraga.getText());
        ResultSet rs = s.executeQuery();
        while (rs.next()) {
            lista2.add(rs.getString("ime") + " " + rs.getString("prezime"));

        }
        ObservableList<String> lista = FXCollections.observableList(lista2);
      //  lista1.setItems(lista);

        conn.close();

    }

    private void poimenuclicked(ActionEvent event) {

        lbpretraga.setText("Pretraga po imenu");

    }

    private void poprezimenuclicked(ActionEvent event) {

        lbpretraga.setText("Pretraga po prezimenu");
    }

    @FXML
    public void pretraga(ActionEvent event) throws SQLException {
        pretraga2();
    }

    @FXML
    private void pretraga(MouseEvent event) {
    }

    @FXML
    private void brisicoveka(ActionEvent event) {
        
       lblk.setText(lista1.getSelectionModel().getSelectedItem().toString());
    }

}
