package com.example.lab_4.gui;

import com.example.lab_4.core.DataProcessor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowApp extends Application {
    @Override
    public void start(Stage stage) {
        TextArea area1 = new TextArea();
        area1.setPromptText("Первый список (каждое число с новой строки)");
        TextArea area2 = new TextArea();
        area2.setPromptText("Второй список (каждое число с новой строки)");
        Button button = new Button("Объединить и отсортировать");
        ListView<String> listView = new ListView<>();

        button.setOnAction(e -> {
            String[] arr1 = area1.getText().split("\\r?\\n");
            String[] arr2 = area2.getText().split("\\r?\\n");
            DataProcessor processor = new DataProcessor();
            String[] res = processor.processPipeline(arr1, arr2);
            listView.getItems().clear();
            listView.getItems().addAll(res);
        });

        VBox root = new VBox(10,
                new Label("Первый список:"), area1,
                new Label("Второй список:"), area2,
                button,
                new Label("Результат (отсортированный):"), listView
        );
        root.setStyle("-fx-padding: 15;");
        Scene scene = new Scene(root, 600, 700);
        stage.setTitle("Вариант 4 – два списка");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}