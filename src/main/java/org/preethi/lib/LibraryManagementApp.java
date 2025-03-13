package org.preethi.lib;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryManagementApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Image image = new Image(getClass().getResource("/org/preethi/lib/title.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(800);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/preethi/lib/library-view.fxml"));
        VBox root = fxmlLoader.load();

        root.getChildren().add(0, imageView);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/org/preethi/lib/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
