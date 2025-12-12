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
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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
    @FXML private ComboBox<Samochod> carComboBox;
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
    //lista samochodow
    private static ObservableList<Samochod> samochody =
            FXCollections.observableArrayList();

    //tworzenie samochodu
    public void initialize() {
        Silnik silnik = new Silnik("Silnik 1.9 TDI", 150.0, 5000.0, 5500);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("Skrzynia manualna", 50.0,2500.0, 6);
        Sprzeglo sprzeglo = new Sprzeglo("Sprzeglo", 18.0, 600.0);
        Pozycja pozycja = new Pozycja(0,0);

        samochod = new Samochod("Volkswagen","TSZ28312", 160, silnik, skrzynia, sprzeglo, pozycja);

        System.out.println("Samochód gotowy!");
        refresh();

        Image carImage = new Image(
                getClass().getResource("/org/example/samochodgui/car_image.png").toExternalForm()
        );

        carImageView.setImage(carImage);
        carImageView.setFitWidth(100);
        carImageView.setFitHeight(100);
        carImageView.setTranslateX(0);
        carImageView.setTranslateY(0);

        carComboBox.setItems(samochody);
        carComboBox.setOnAction(e -> {
            samochod = carComboBox.getValue();
            refresh();
        });
    }
    private void refresh() {
        if (samochod == null) return;

        // Samochod
        modelTextField.setText(samochod.getModel());
        plateTextField.setText(samochod.getNrRejest());
        weightTextField.setText(String.valueOf(samochod.getWaga()));
        speedTextField.setText(String.valueOf(samochod.getAktPredkosc()));

        // Skrzynia
        gearboxNameTextField.setText(samochod.getSkrzynia().getNazwa());
        gearboxPriceTextField.setText(String.valueOf(samochod.getSkrzynia().getCena()));
        gearboxWeightTextField.setText(String.valueOf(samochod.getSkrzynia().getWaga()));
        gearboxGearTextField.setText(String.valueOf(samochod.getSkrzynia().getAktBieg()));

        // Silnik
        engineNameTextField.setText(samochod.getSilnik().getNazwa());
        enginePriceTextField.setText(String.valueOf(samochod.getSilnik().getCena()));
        engineWeightTextField.setText(String.valueOf(samochod.getSilnik().getWaga()));
        engineRpmTextField.setText(String.valueOf(samochod.getSilnik().getObroty()));

        // Sprzeglo
        clutchNameTextField.setText(samochod.getSprzeglo().getNazwa());
        clutchPriceTextField.setText(String.valueOf(samochod.getSprzeglo().getCena()));
        clutchWeightTextField.setText(String.valueOf(samochod.getSprzeglo().getWaga()));
        clutchStateTextField.setText(
                samochod.getSprzeglo().czyWcisniete() ? "Wciśnięte" : "Zwolnione"
        );
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    //metody do obslugi zdarzen
    @FXML
    public void onStopButton(ActionEvent actionEvent) {
        samochod.wylacz();
        refresh();
    }
    @FXML
    public void onStartButton(ActionEvent actionEvent) {
        samochod.wlacz();
        refresh();
    }
    @FXML
    public void onCarExtraButton(ActionEvent actionEvent) {
        System.out.println("Dod przycisk wcisniety!");
        refresh();
    }
    @FXML
    public void onIncreaseGearButton(ActionEvent actionEvent) {
        System.out.println("Zwieksz bieg");
        samochod.getSkrzynia().zwiekszBieg();
        refresh();
    }
    @FXML
    public void onDecreaseGearButton(ActionEvent actionEvent) {
        System.out.println("Zmniejsz bieg");
        samochod.getSkrzynia().zmniejszBieg();
        refresh();
    }
    @FXML
    public void onIncreaseRpmButton(ActionEvent actionEvent) {
        System.out.println("Dodaj gazu");
        samochod.getSilnik().zwiekszObroty();
        refresh();
    }
    @FXML
    public void onDecreaseRpmButton(ActionEvent actionEvent) {
        System.out.println("Odejmij gazu");
        samochod.getSilnik().zmniejszObroty();
        refresh();
    }
    @FXML
    public void onPressClutchButton(ActionEvent actionEvent) {
        samochod.getSprzeglo().wcisnij();
        refresh();
    }
    @FXML
    public void onReleaseClutchButton(ActionEvent actionEvent) {
        samochod.getSprzeglo().zwolnij();
        refresh();
    }
    public static void addCarToList(String model,String registration,double weight,int speed) {
        Silnik silnik = new Silnik("Domyślny silnik", weight * 0.4, 3000, 6000);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("Domyślna skrzynia", weight * 0.2, 2000, 5);
        Sprzeglo sprzeglo = new Sprzeglo("Domyślne sprzęgło", weight * 0.1, 1000);
        Pozycja pozycja = new Pozycja(0, 0);

        Samochod nowy = new Samochod(model,registration,speed,silnik,skrzynia,sprzeglo,pozycja);
        samochody.add(nowy);
    }
    public void openAddCarWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("DodajSamochod.fxml")
        );

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Dodaj nowy samochód");
        stage.show();
    }

}
