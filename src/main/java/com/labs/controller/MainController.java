package com.labs.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.labs.exception.FileSizeException;
import com.labs.exception.InvalidInputException;
import com.labs.helper.IdComparator;
import com.labs.helper.LetterComparator;
import com.labs.helper.NumberComparator;
import com.labs.helper.RegionComparator;
import com.labs.model.CarNumber;
import com.labs.service.CarPlateService;
import com.labs.service.FileService;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.io.File;
import java.util.Collection;
import java.util.stream.Collectors;


public class MainController {

    private ObservableList<CarNumber> allCarPLates = FXCollections.observableArrayList();
    private ObservableList<CarNumber> coolCarPlates = FXCollections.observableArrayList();
    private ListProperty<CarNumber> listProperty = new SimpleListProperty<>();

    private CarPlateService carPlateService = new CarPlateService();
    private FileService fileService = new FileService();

    @FXML
    private JFXTextField numberField;

    @FXML
    private JFXButton addNumberButton;

    @FXML
    private JFXListView<CarNumber> numberList;

    @FXML
    private JFXButton showCoolPlatesButton;

    @FXML
    private JFXButton sortByRegionButton;

    @FXML
    private JFXButton sortByNumbersButton;

    @FXML
    private JFXButton sortByLettersButton;

    @FXML
    private JFXButton deletePlateButton;
    @FXML
    private JFXButton sortByInitialOrderButton;

    @FXML
    public void initialize() {
        listProperty.setValue(allCarPLates);
        showCoolPlatesButton.disableProperty().bind(Bindings.isEmpty(listProperty));
        sortByInitialOrderButton.disableProperty().bind(Bindings.isEmpty(allCarPLates));
        sortByLettersButton.disableProperty().bind(Bindings.isEmpty(allCarPLates));
        sortByNumbersButton.disableProperty().bind(Bindings.isEmpty(allCarPLates));
        sortByRegionButton.disableProperty().bind(Bindings.isEmpty(allCarPLates));
        numberList.itemsProperty().bindBidirectional(listProperty);
        coolCarPlates.addListener((ListChangeListener<? super CarNumber>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    allCarPLates.removeAll(change.getRemoved());
                    allCarPLates.addAll(change.getAddedSubList());
                }
            }
        });
        addNumberButton.disableProperty().bind(Bindings.isEmpty(numberField.textProperty()));
        numberList.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->
                deletePlateButton.setDisable(newValue == null)));
    }


    @FXML
    void addNumber(ActionEvent event) {
        addNewPlate(numberField.getText());
        numberField.clear();
    }

    @FXML
    void deletePlate(ActionEvent event) {
        numberList.getItems().removeAll(numberList.getSelectionModel().getSelectedItems());
        System.out.println("1");
    }

    @FXML
    void showCoolPlates(ActionEvent event) {
        Collection<CarNumber> cool = carPlateService.getCoolNumbers(allCarPLates);
        coolCarPlates.setAll(cool);
        listProperty.setValue(coolCarPlates);
        System.out.println("s");
    }

    @FXML
    void sortByLetters(ActionEvent event) {
        numberList.getItems().sort(new LetterComparator());
    }

    @FXML
    void sortByNumbers(ActionEvent event) {
        numberList.getItems().sort(new NumberComparator());
    }

    @FXML
    void sortByRegion(ActionEvent event) {
        numberList.getItems().sort(new RegionComparator());
    }

    @FXML
    void sortByInitialOrder(ActionEvent actionEvent) {
        allCarPLates.sort(new IdComparator());
        listProperty.setValue(allCarPLates);
    }

    @FXML
    void openFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        try {
            fileService.readFile(file).forEach(this::addNewPlate);
        } catch (FileSizeException | InvalidInputException e) {
            showErrorAlert("Invalid file", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void saveFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);
        fileService.writeFile(file, allCarPLates.stream().map(CarNumber::toString).collect(Collectors.toList()));
    }

    @FXML
    void closeApplication(ActionEvent actionEvent) {
        Platform.exit();
    }

    private void addNewPlate(String carNumber) throws InvalidInputException {
        CarNumber newNumber = new CarNumber(carNumber);
        if (allCarPLates.contains(newNumber)) {
            showErrorAlert("Invalid input", "Number already exists in list");
        } else {
            allCarPLates.add(newNumber);
        }
        listProperty.setValue(allCarPLates);
    }

    private void showErrorAlert(String headerText, String bodyText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(bodyText);
        alert.show();
    }
}
