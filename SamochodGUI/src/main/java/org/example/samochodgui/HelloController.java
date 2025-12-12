package org.example.samochodgui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import symulator.Samochod;
import symulator.Silnik;
import symulator.SkrzyniaBiegow;
import symulator.Sprzeglo;
import symulator.Pozycja;

public class HelloController {
    //MAPA
    @FXML private AnchorPane mapPane;
    @FXML private ImageView carImageView;
    @FXML private Label carLabel;

    //MENU
    @FXML private MenuBar menuBar;
    @FXML private MenuItem closeMenuItem;
    @FXML private MenuItem deleteMenuItem;
    //TOOLBAR
    @FXML private ComboBox<String> carComboBox;
    @FXML private Button addCarButton;
    @FXML private Button deleteCarButton;
    //Samochod
    @FXML private TextField modelTextField;
    @FXML private TextField plateTextField;
    @FXML private TextField weightTextField;
    @FXML private TextField speedTextField;

    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private Button carExtraButton;
    //Skrzynia Biegow
    @FXML private TextField gearboxNameTextField;
    @FXML private TextField gearboxPriceTextField;
    @FXML private TextField gearboxWeightTextField;
    @FXML private TextField gearboxGearTextField;

    @FXML private Button increaseGearButton;
    @FXML private Button decreaseGearButton;

    //Silnik
    @FXML private TextField engineNameTextField;
    @FXML private TextField enginePriceTextField;
    @FXML private TextField engineWeightTextField;
    @FXML private TextField engineRpmTextField;

    @FXML private Button increaseRpmButton;
    @FXML private Button decreaseRpmButton;

    //Sprzeglo
    @FXML private TextField clutchNameTextField;
    @FXML private TextField clutchPriceTextField;
    @FXML private TextField clutchWeightTextField;
    @FXML private TextField clutchStateTextField;

    @FXML private Button pressClutchButton;
    @FXML private Button releaseClutchButton;

    private Samochod samochod;
    //tworzenie samochodu
    public void initialize() {
        Silnik silnik = new Silnik("Silnik 1.9 TDI", 150.0, 5000.0, 5500);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("Skrzynia manualna", 50.0,2500.0, 6);
        Sprzeglo sprzeglo = new Sprzeglo("Sprzeglo", 18.0, 600.0);
        Pozycja pozycja = new Pozycja(0,0);

        samochod = new Samochod("TSZ28312", 160, silnik, skrzynia, sprzeglo, pozycja);

        carComboBox.getItems().add("Samochod 1");
        carComboBox.getSelectionModel().selectFirst();

        System.out.println("Samochód gotowy!");
        refreshCarData();

    }
    private void refreshCarData() {
        modelTextField.setText("Volkswagen");
        plateTextField.setText("TSZ28312");
        weightTextField.setText(String.valueOf(samochod.getWaga()));
        speedTextField.setText("0");
        gearboxNameTextField.setText(samochod.getSkrzynia().getNazwa());
        engineNameTextField.setText(samochod.getSilnik().getNazwa());
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    //metody do obslugi zdarzen
    @FXML
    public void onStopButton() {
        samochod.wylacz();
        engineRpmTextField.setText("0");
    }
    @FXML
    public void onStartButton() {
        samochod.wlacz();
        engineRpmTextField.setText(String.valueOf(samochod.getSilnik().getObroty()));
    }
    @FXML
    public void onCarExtraButton() {
        System.out.println("Dod przycisk wcisniety!");
    }
    @FXML
    public void onIncreaseGearButton() {
        samochod.getSkrzynia().zwiekszBieg();
        gearboxGearTextField.setText(String.valueOf(samochod.getSkrzynia().getAktBieg()));
    }
    @FXML
    public void onDecreaseGearButton() {
        samochod.getSkrzynia().zmniejszBieg();
        gearboxGearTextField.setText(String.valueOf(samochod.getSkrzynia().getAktBieg()));
    }
    @FXML
    public void onIncreaseRpmButton() {
        samochod.getSilnik().zwiekszObroty();
        engineRpmTextField.setText(String.valueOf(samochod.getSilnik().getObroty()));
    }
    @FXML
    public void onDecreaseRpmButton() {
        samochod.getSilnik().zmniejszObroty();
        engineRpmTextField.setText(String.valueOf(samochod.getSilnik().getObroty()));
    }
    @FXML
    public void onPressClutchButton() {
        samochod.getSprzeglo().wcisnij();
        clutchStateTextField.setText("Wciśnięte");
    }
    @FXML
    public void onReleaseClutchButton() {
        samochod.getSprzeglo().zwolnij();
        clutchStateTextField.setText("Zwolnione");
    }
}
