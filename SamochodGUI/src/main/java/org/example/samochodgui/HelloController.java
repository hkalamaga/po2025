package org.example.samochodgui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import symulator.*;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Platform;
import javafx.animation.AnimationTimer;

public class HelloController implements Listener {
    //mapa
    @FXML private AnchorPane mapPane;
    @FXML private ImageView carImageView;
    @FXML private Label carLabel;

    //menu
    @FXML private MenuBar menuBar;
    @FXML private MenuItem closeMenuItem;
    @FXML private MenuItem deleteMenuItem;
    //toolbar
    @FXML private ComboBox<Samochod> carComboBox;
    @FXML private Button addCarButton;
    @FXML private Button deleteCarButton;
    //samochod
    @FXML private TextField modelTextField;
    @FXML private TextField plateTextField;
    @FXML private TextField weightTextField;
    @FXML private TextField speedTextField;

    @FXML private Button startButton;
    @FXML private Button stopButton;
    //skrzynia Biegow
    @FXML private TextField gearboxNameTextField;
    @FXML private TextField gearboxPriceTextField;
    @FXML private TextField gearboxWeightTextField;
    @FXML private TextField gearboxGearTextField;

    @FXML private Button increaseGearButton;
    @FXML private Button decreaseGearButton;

    //silnik
    @FXML private TextField engineNameTextField;
    @FXML private TextField enginePriceTextField;
    @FXML private TextField engineWeightTextField;
    @FXML private TextField engineRpmTextField;

    @FXML private Button increaseRpmButton;
    @FXML private Button decreaseRpmButton;

    //sprzeglo
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
        samochod.addListener(this);
        samochody.add(samochod);
        System.out.println("Samochod gotowy!");
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
            if (samochod != null) {
                samochod.removeListener(this);
            }

            samochod = carComboBox.getValue();

            if (samochod != null) {
                samochod.addListener(this);
            }

            refresh();
        });

        mapPane.setOnMouseClicked(event -> {
            if (samochod == null) {
                pokazBlad("Nie wybrano samochodu!");
                return;
            }

            double x = event.getX();
            double y = event.getY();
            System.out.println("Pozycja: x=" + x + ", y=" + y);
            Pozycja nowaPozycja = new Pozycja(x, y);
            samochod.jedzDo(nowaPozycja);
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
                samochod.getSprzeglo().czyWcisniete() ? "Wcisniete" : "Zwolnione"
        );
        Platform.runLater(() -> {
            carImageView.setTranslateX(samochod.getPozycja().getX());
            carImageView.setTranslateY(samochod.getPozycja().getY());
        });

        walidujStanJazdy();
        if (samochod.getSprzeglo().czyWcisniete()) {
            clutchStateTextField.setStyle("-fx-background-color: #fff3b0;");
        } else {
            clutchStateTextField.setStyle("");
        }

    }

    @FXML
    private Label welcomeText;

    //metody do obslugi zdarzen
    @FXML
    public void onStopButton(ActionEvent actionEvent) {
        if (samochod == null) {
            pokazBlad("Brak samochodu do wylączenia!");
            return;
        }
        samochod.wylacz();
        refresh();
    }
    @FXML
    public void onStartButton(ActionEvent actionEvent) {
        if (samochod == null) {
            pokazBlad("Brak samochodu do uruchomienia!");
            return;
        }
        samochod.wlacz();
        refresh();
    }
    @FXML
    public void onIncreaseGearButton(ActionEvent actionEvent) {
        if (samochod == null) {
            pokazBlad("Brak samochodu- nie mozna zmienic biegu!");
            return;
        }
        try {
            samochod.getSkrzynia().zwiekszBieg();
            refresh();
        } catch (SamochodException e) {
            pokazBlad(e.getMessage());
        }
    }

    @FXML
    public void onDecreaseGearButton(ActionEvent actionEvent) {
        if (samochod == null) {
            pokazBlad("Brak samochodu – nie mozna zmienic biegu!");
            return;
        }
        try {
            samochod.getSkrzynia().zmniejszBieg();
            refresh();
        } catch (SamochodException e) {
            pokazBlad(e.getMessage());
        }
    }
    @FXML
    public void onIncreaseRpmButton(ActionEvent actionEvent) {
        try {
            samochod.getSilnik().zwiekszObroty();
            refresh();
        } catch (SamochodException e) {
            pokazBlad(e.getMessage());
        }
    }
    @FXML
    public void onDecreaseRpmButton(ActionEvent actionEvent) {
        if (samochod == null) {
            pokazBlad("Brak samochodu – nie mozna odjac gazu!");
            return;
        }
        try {
            samochod.getSilnik().zmniejszObroty();
            refresh();
        } catch (SamochodException e) {
            pokazBlad(e.getMessage());
        }
    }
    @FXML
    public void onPressClutchButton(ActionEvent actionEvent) {
        if (samochod == null) {
            pokazBlad("Brak samochodu – nie mozna wcisnac sprzegla!");
            return;
        }
        try {
            samochod.getSprzeglo().wcisnij();
            refresh();
        } catch (SamochodException e) {
            pokazBlad(e.getMessage());
        }
    }
    @FXML
    public void onReleaseClutchButton(ActionEvent actionEvent) {
        if (samochod == null) {
            pokazBlad("Brak samochodu – nie mozna zwolnic sprzegla!");
            return;
        }
        try {
            samochod.getSprzeglo().zwolnij();
            refresh();
        } catch (SamochodException e) {
            pokazBlad(e.getMessage());
        }
    }
    public static void addCarToList(String model,String registration,double weight,int speed) {
        Silnik silnik = new Silnik("Domyslny silnik", weight * 0.4, 3000, 6000);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("Domyslna skrzynia", weight * 0.2, 2000, 5);
        Sprzeglo sprzeglo = new Sprzeglo("Domyslne sprzeglo", weight * 0.1, 1000);
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
        stage.setTitle("Dodaj nowy samochod");
        stage.show();
    }
    @FXML
    public void onUsunSamochod(ActionEvent actionEvent) {
        if (samochod == null) return;

        samochody.remove(samochod);

        if (!samochody.isEmpty()) {
            samochod = samochody.get(0);
            carComboBox.getSelectionModel().selectFirst();
        } else {
            samochod = null;
            carComboBox.getSelectionModel().clearSelection();
        }

        refresh();
    }
    public void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Blad");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
    @Override
    public void update() {
        Platform.runLater(this::refresh);
    }
    private boolean obrotyZaDuze() {
        int bieg = samochod.getSkrzynia().getAktBieg();
        int obroty = samochod.getSilnik().getObroty();

        if (bieg == 0) return false;

        int max;
        switch (bieg) {
            case 1 -> max = 2500;
            case 2 -> max = 3000;
            case 3 -> max = 3500;
            case 4 -> max = 4000;
            case 5 -> max = 4500;
            default -> max = 5500;
        }
        return obroty > max;
    }
    private boolean biegZaDuzyDoPredkosci() {
        int bieg = samochod.getSkrzynia().getAktBieg();
        double v = samochod.getAktPredkosc();

        return switch (bieg) {
            case 1 -> v > 25;
            case 2 -> v > 45;
            case 3 -> v > 70;
            case 4 -> v > 100;
            case 5 -> v > 140;
            default -> false;
        };
    }
    private void walidujStanJazdy() {
        int bieg = samochod.getSkrzynia().getAktBieg();

        if (bieg == 0) {
            // luz
            gearboxGearTextField.setStyle("-fx-background-color: #e0e0e0;");
            engineRpmTextField.setStyle("-fx-background-color: #e0e0e0;");
            return;
        }

        if (obrotyZaDuze()) {
            gearboxGearTextField.setStyle("-fx-background-color: #ffcccc;");
            engineRpmTextField.setStyle("-fx-background-color: #ffcccc;");
            System.out.println("Za wysokie obroty – zmien bieg!");
            return;
        }

        if (biegZaDuzyDoPredkosci()) {
            gearboxGearTextField.setStyle("-fx-background-color: #ffcccc;");
            engineRpmTextField.setStyle("-fx-background-color: #ffcccc;");
            System.out.println("Bieg za duzy do predkosci!");
            return;
        }
        gearboxGearTextField.setStyle("-fx-background-color: #ccffcc;");
        engineRpmTextField.setStyle("-fx-background-color: #ccffcc;");
    }






}

