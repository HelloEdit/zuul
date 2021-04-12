package zuul.pkg_ui.pkg_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is responsible for initializing the JavaFX thread as well as
 * correctly setting up the main pkg_game window and launching the controller.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class JavaFX extends Application {
    public static void play() {
        JavaFX.launch();
    }

    /**
     * Starts the JavaFX interface.
     * <p>
     * This methode SHOULD NOT be called.
     *
     * @param primaryStage primary Stage is constructed by the platform.
     * @throws Exception if something go wrong.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(JavaFX.class.getResource("zuul.fxml"));
        primaryStage.setScene(new Scene(root, 1050, 500));

        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(1050);
        primaryStage.setTitle("Planet Wars");
        primaryStage.centerOnScreen();

        primaryStage.show();
    }
}