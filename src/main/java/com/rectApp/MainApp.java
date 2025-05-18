package com.rectApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Основной класс JavaFX-приложения.
 * Загружает интерфейс, подключает контроллер.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        Scene scene = new Scene(root, 800, 550);
        stage.setTitle("RectApp");
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(600);
        stage.show();

        // Защита от некорректного ресайза
        new StageResizeProtector(
                stage,
                controller.getDrawPane(),
                controller.getRectangle(),
                1
        );

    }

    public static void main(String[] args) {
        launch(args);
    }
}
