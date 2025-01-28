package dev.brunob.algovisualizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SortingVisualizer extends Application {

    private AlgorithmVisualizer visualizer;

    @Override
    public void start(Stage primaryStage) {
        visualizer = new AlgorithmVisualizer(800, 600);

        ComboBox<String> algorithmSelector = new ComboBox<>();
        algorithmSelector.getItems().addAll("Bubble Sort", "Insertion Sort", "Selection Sort", "Quick Sort", "Merge Sort","Heap Sort","Binary Search", "Linear Search");
        algorithmSelector.setValue("Bubble Sort");

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            String selectedAlgorithm = algorithmSelector.getValue();
            if ("Bubble Sort".equals(selectedAlgorithm)) {
                visualizer.bubbleSort();
            } else if ("Insertion Sort".equals(selectedAlgorithm)) {
                visualizer.insertionSort();
            } else if("Binary Search".equals(selectedAlgorithm)){
                visualizer.binarySearch();
            } else if ("Selection Sort".equals(selectedAlgorithm)) {
                visualizer.selectionSort();
            } else if("Quick Sort".equals(selectedAlgorithm)){
                visualizer.quickSort();
            } else if ("Merge Sort".equals(selectedAlgorithm)) {
                visualizer.mergeSort();
            } else if("Linear Search".equals(selectedAlgorithm)){
                visualizer.linearSearch();
            } else if("Heap Sort".equals(selectedAlgorithm)){
                visualizer.heapSort();
            }
        });

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> {
            visualizer.resetArray();
            visualizer.drawArray();
        });

        // Contenedor horizontal para los botones
        HBox buttonContainer = new HBox(10); // Espacio de 10px entre botones
        buttonContainer.getChildren().addAll(startButton, resetButton);

        // Layout principal (agregando el contenedor de botones)
        VBox root = new VBox(10, algorithmSelector, buttonContainer, visualizer);
        Scene scene = new Scene(root, 800, 650);

        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}