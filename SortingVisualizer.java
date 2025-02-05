package dev.brunob.algovisualizer;

import dev.brunob.algovisualizer.algorithms.SearchAlgorithms;
import dev.brunob.algovisualizer.algorithms.SortingAlgorithms;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SortingVisualizer extends Application {

    private AlgorithmVisualizer visualizer;
    private SortingAlgorithms sortingAlgorithms;
    private SearchAlgorithms searchAlgorithms;

    @Override
    public void start(Stage primaryStage) {
        visualizer = new AlgorithmVisualizer(800, 600);
        sortingAlgorithms = new SortingAlgorithms(visualizer);
        searchAlgorithms = new SearchAlgorithms(visualizer);

        ComboBox<String> algorithmSelector = new ComboBox<>();
        algorithmSelector.getItems().addAll("Bubble Sort", "Insertion Sort", "Selection Sort", "Quick Sort", "Merge Sort","Heap Sort","Binary Search", "Linear Search", "Jump Search");
        algorithmSelector.setValue("Bubble Sort");

        Button startButton = new Button("Start");
        Button resetButton = new Button("Reset");
        startButton.setOnAction(e -> {
            String selectedAlgorithm = algorithmSelector.getValue();
            if ("Bubble Sort".equals(selectedAlgorithm)) {
                sortingAlgorithms.bubbleSort(resetButton);
            } else if ("Insertion Sort".equals(selectedAlgorithm)) {
                sortingAlgorithms.insertionSort(resetButton);
            } else if ("Selection Sort".equals(selectedAlgorithm)) {
                sortingAlgorithms.selectionSort(resetButton);
            } else if("Quick Sort".equals(selectedAlgorithm)){
                //sortingAlgorithms.quickSort(resetButton);
            } else if ("Merge Sort".equals(selectedAlgorithm)) {
                //sortingAlgorithms.mergeSort(resetButton);
            } else if("Heap Sort".equals(selectedAlgorithm)){
                //sortingAlgorithms.heapSort(resetButton);
            } else if("Binary Search".equals(selectedAlgorithm)) {
                searchAlgorithms.binarySearch(resetButton);
            } else if ("Linear Search".equals(selectedAlgorithm)) {
                searchAlgorithms.linearSearch(resetButton);
            }
        });


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