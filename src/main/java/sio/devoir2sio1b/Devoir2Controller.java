package sio.devoir2sio1b;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sio.devoir2sio1b.Model.Action;
import sio.devoir2sio1b.Model.Trader;
import sio.devoir2sio1b.Services.ServicesAction;
import sio.devoir2sio1b.Services.ServicesTrader;
import sio.devoir2sio1b.Tools.ConnexionBDD;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.SplittableRandom;

public class Devoir2Controller implements Initializable {

    @FXML
    private TableColumn tcMontantAchatAction;
    @FXML
    private TableColumn tcNumeroTrader;
    @FXML
    private TableView<Trader> tvTraders;
    @FXML
    private TableColumn tcNumeroAction;
    @FXML
    private TableColumn tcNomAction;
    @FXML
    private TableColumn tcQuantiteAction;
    @FXML
    private TableColumn tcNomTrader;
    @FXML
    private TableView<Action> tvActions;
    @FXML
    private TextField txtMontantTotalPortefeuille;
    @FXML
    private TextField txtNomAction;
    @FXML
    private TextField txtQuantiteAction;
    @FXML
    private Button btnVendre;
    ServicesTrader servicesTrader;
    ConnexionBDD maCnx;
    ServicesAction servicesAction;
    int idTrader;
    int idAction;
    int quantite;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // A vous de jouer
        try {
            maCnx = new ConnexionBDD();
            servicesAction = new ServicesAction();
            servicesTrader = new ServicesTrader();
            tcNomTrader.setCellValueFactory(new PropertyValueFactory<>("nomTrader"));
            tcNumeroTrader.setCellValueFactory(new PropertyValueFactory<>("idTrader"));
            tvTraders.setItems(FXCollections.observableList(servicesTrader.getAllTraders()));

            tcNomAction.setCellValueFactory(new PropertyValueFactory<>("nomAction"));
            tcNumeroAction.setCellValueFactory(new PropertyValueFactory<>("idAction"));
            tcQuantiteAction.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tcMontantAchatAction.setCellValueFactory(new PropertyValueFactory<>("montantAchat"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void tvTradersClicked(Event event) throws SQLException
    {
        // A vous de jouer
        idTrader = tvTraders.getSelectionModel().getSelectedItem().getIdTrader();
        tvActions.setItems(FXCollections.observableList(servicesAction.getAllActionsByTrader(idTrader)));
        txtMontantTotalPortefeuille.setText(String.valueOf(servicesTrader.getMontantTotalPortefeuille(idTrader)));
    }

    @FXML
    public void btnVendreClicked(Event event) throws SQLException
    {

        // A vous de jouer
        quantite=Integer.parseInt(txtQuantiteAction.getText());
        idAction = tvActions.getSelectionModel().getSelectedItem().getIdAction();


        if (tvActions.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Choix de l'action");
            alert.setContentText("Veuillez sélectionner une action");
            alert.showAndWait();
        }
        else if (txtQuantiteAction.getText() == "" || txtQuantiteAction.getText() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Choix de la quantité");
            alert.setContentText("Veuillez sélectionner une quantité");
            alert.showAndWait();
        }
//        else if (Integer.parseInt(txtQuantiteAction.getText()) > Integer.parseInt(tcQuantiteAction.getText()))
//        {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("Erreur de saisie");
//            alert.setContentText("Vous ne pouvez pas vendre plus que ce vous possédez");
//            alert.showAndWait();
//        }
        else if (Integer.parseInt(txtQuantiteAction.getText()) < tvActions.getSelectionModel().getSelectedItem().getQuantite())
        {
            servicesAction.updateQuantite(idTrader, idAction, quantite);
            tvActions.setItems(FXCollections.observableList(servicesAction.getAllActionsByTrader(idTrader)));
            txtMontantTotalPortefeuille.setText(String.valueOf(servicesTrader.getMontantTotalPortefeuille(idTrader)));
            tvActions.refresh();

        }
        else if (Integer.parseInt(txtQuantiteAction.getText()) == tvActions.getSelectionModel().getSelectedItem().getQuantite())
        {
            servicesAction.deleteAction(idTrader, idAction);
            tvActions.setItems(FXCollections.observableList(servicesAction.getAllActionsByTrader(idTrader)));
            txtMontantTotalPortefeuille.setText(String.valueOf(servicesTrader.getMontantTotalPortefeuille(idTrader)));
            tvActions.refresh();
        }


    }

    @FXML
    public void tvActionsClicked(Event event)
    {
        // A vous de jouer
        txtNomAction.setText(tvActions.getSelectionModel().getSelectedItem().getNomAction());
        txtQuantiteAction.setText(String.valueOf(tvActions.getSelectionModel().getSelectedItem().getQuantite()));
    }
}