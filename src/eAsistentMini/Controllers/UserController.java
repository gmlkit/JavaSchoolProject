package eAsistentMini.Controllers;

import eAsistentMini.Logic.Conn;
import eAsistentMini.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public TableView gradesTable;
    static private int userId=new Main().getCurrentUser();
    //TABLE VIEW AND DATA
    private ObservableList<ObservableList> data;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buildData();
    }
    public void buildData(){
        Connection c ;
        data = FXCollections.observableArrayList();
        try{
            c = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
            Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
            Statement st = conn.createStatement();

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT u.ime,u.priimek,pr.ime,o.ocena FROM starsi s \n" +
                    "INNER JOIN ucenci u ON u.stars_id=s.id \n" +
                    "INNER JOIN ucenci_predmeti up ON up.ucenec_id=u.id \n" +
                    "INNER JOIN predmeti pr ON up.predmet_id=pr.id \n" +
                    "INNER JOIN ocene o ON o.ucenec_id=up.id\n" +
                    "WHERE s.id=?";
            PreparedStatement psql =c.prepareStatement(SQL);
            psql.setInt(1,userId);
//            psql.setInt(2,prId);
            //ResultSet
            ResultSet rs = psql.executeQuery();

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                gradesTable.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            gradesTable.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}