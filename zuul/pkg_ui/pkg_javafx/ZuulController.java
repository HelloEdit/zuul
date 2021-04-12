package zuul.pkg_ui.pkg_javafx;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import zuul.pkg_game.Engine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * This class is the controller of our JavaFX view.
 * It allows to make the link between the graphical interface and the pkg_game logic.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 * @see zuul.pkg_ui.UserInterface
 */
public class ZuulController implements UserInterface {
    @FXML
    private ImageView placeImage;

    @FXML
    private TextField inputField;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label actualWeightText;

    @FXML
    private Label maxWeightText;

    @FXML
    private TextArea logArea;

    @FXML
    private ListView<Item> inventoryList;

    /**
     * Game engine of the Zuul project.
     */
    private Engine engine;

    /**
     * Prints a string.
     *
     * @param s the String to be printed
     */
    @Override
    public void print(String s) {
        this.logArea.appendText(s);
    }

    /**
     * Terminates the current line by writing the line separator string. The line
     * separator string is defined by the system property line.separator, and
     * is not necessarily a single newline character ('\n').
     */
    @Override
    public void println() {
        this.logArea.appendText(System.lineSeparator());
    }

    /**
     * Prints a String and then terminate the line.
     *
     * @param pInput The text to be printed.
     */
    @Override
    public void println(final String pInput) {
        this.logArea.appendText(pInput);
        this.println();
    }

    /**
     * A convenience method to write a formatted string to the interface
     * using the specified format string and arguments.
     *
     * @param pFormat A format string as described in Format string syntax
     * @param pArgs   Arguments referenced by the format specifiers in the format string.
     */
    @Override
    public void printf(final String pFormat, final Object... pArgs) {
        this.logArea.appendText(String.format(pFormat, pArgs));
    }

    /**
     * Causes the JavaFX application to terminate.
     */
    @Override
    public void close() {
        Platform.exit();
    }

    /**
     * Disables the GUI.
     */
    @Override
    public void disable() {
        this.inputField.setDisable(true);
        this.inventoryList.setDisable(true);
    }

    /**
     * Initializes the interface.
     * Called to initialize a controller after its root element has been completely processed.
     * <p>
     * This methode SHOULD NOT be called.
     */
    @FXML
    private void initialize() {
        this.engine = new Engine();
        this.engine.setInterface(this);

        Player vPlayer = this.engine.getPlayer();

        // bind the current location of the player to the interface
        // update the related properties

        StringBinding vLocation = Bindings.createStringBinding(
                () -> vPlayer.getRoom().getName(),
                vPlayer.getObservableRoom()
        );

        StringBinding vDescription = Bindings.createStringBinding(
                () -> vPlayer.getRoom().getDescription(),
                vPlayer.getObservableRoom()
        );

        ObjectBinding<Image> vImage = Bindings.createObjectBinding(
                () -> new Image("/assets/" + vPlayer.getRoom().getImage()),
                vPlayer.getObservableRoom()
        );

        this.placeImage.imageProperty().bind(vImage);
        this.locationLabel.textProperty().bind(vLocation);
        this.descriptionLabel.textProperty().bind(vDescription);

        // bind the player's inventory weight and max weight

        this.actualWeightText.textProperty().bind(vPlayer.getObservableWeight().asString());
        this.maxWeightText.textProperty().bind(vPlayer.getObservableMaxWeight().asString());

        this.inventoryList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Item pItem, boolean pEmpty) {
                super.updateItem(pItem, pEmpty);

                if (pItem == null || pEmpty) {
                    setText(null);
                } else {
                    setText(pItem.getName());
                }
            }
        });

        // bind inventory to the ListView
        ObservableMap<String, Item> vItemList = vPlayer.getObservableInventory();
        ObservableList<Item> vItems = FXCollections.observableArrayList();

        // load items already present in the inventory in the list
        vItems.addAll(vPlayer.getInventory().getObservableItems().values());
        this.inventoryList.setItems(vItems);

        // listen Map inventory changes
        MapChangeListener<String, Item> inventoryListener = (event) -> {
            if (event.wasAdded()) vItems.add(event.getValueAdded());
            if (event.wasRemoved()) vItems.remove(event.getValueRemoved());
        };

        vItemList.addListener(inventoryListener);
    }

    /**
     * Handles the pkg_command input in the text field.
     */
    @FXML
    private void onInput() {
        String vInput = inputField.getText().strip();
        inputField.clear();

        if (vInput.isEmpty()) return;

        this.engine.processCommand(vInput, false);
    }

    /**
     * Handles the pkg_item utilization.
     */
    @FXML
    private void onItemUse() {
        Item vItem = inventoryList.getSelectionModel().getSelectedItem();

        try {
            vItem.use(this.engine, this.engine.getPlayer(), this.engine.getInterface());
        } catch (Exception pError) {
            this.engine.getInterface().println(pError.getMessage());
        }

    }

    /**
     * Handles the pkg_item dropping.
     */
    @FXML
    private void onItemDrop() {
        Item vItem = inventoryList.getSelectionModel().getSelectedItem();
        Player vPlayer = this.engine.getPlayer();

        try {
            vPlayer.dropItem(vItem.getName());
        } catch (Item.CannotManageItemException pError) {
            // we ignore the error
        }
    }

    /**
     * Handles the image clicked.
     */
    @FXML
    public void onImageClicked() {
        Stage vStage = new Stage();
        vStage.setTitle("Image");

        Image vImage = this.placeImage.getImage();
        ImageView vView = new ImageView(vImage);

        BorderPane vPane = new BorderPane();
        vPane.setCenter(vView);

        Scene vScene = new Scene(vPane, vImage.getWidth(), vImage.getHeight());
        vStage.setScene(vScene);

        vStage.initModality(Modality.WINDOW_MODAL);
        vStage.initOwner(this.placeImage.getScene().getWindow());
        vStage.setResizable(false);

        vStage.showAndWait();
    }
}
