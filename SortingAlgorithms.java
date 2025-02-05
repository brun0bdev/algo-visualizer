package dev.brunob.algovisualizer.algorithms;

import dev.brunob.algovisualizer.AlgorithmVisualizer;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class SortingAlgorithms {
    private AlgorithmVisualizer visualizer;

    public SortingAlgorithms(AlgorithmVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void bubbleSort(Button resetButton) {
        new Thread(() -> {
            Platform.runLater(() -> resetButton.setDisable(true));
            visualizer.setSorting(true);
            int[] array = visualizer.getArray();
            int updateInterval = 5;
            int updateCounter = 0;

            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;

                        if (++updateCounter % updateInterval == 0) {
                            visualizer.drawArray();
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
            visualizer.drawArray();
            Platform.runLater(() -> resetButton.setDisable(false));
            visualizer.setSorting(false);
        }).start();
    }

    public void insertionSort(Button resetButton){
        new Thread(() -> {
            Platform.runLater(() -> resetButton.setDisable(true));
            visualizer.setSorting(true);
            int[] array = visualizer.getArray();
            int updateInterval = 5;
            int updateCounter = 0;

            for (int i = 1; i < array.length; i++) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;

                    if (++updateCounter % updateInterval == 0) {
                        visualizer.drawArray();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                array[j + 1] = key;
            }
            visualizer.drawArray();
            Platform.runLater(() -> resetButton.setDisable(false));
            visualizer.setSorting(false);
        }).start();
    }

    public void selectionSort(Button resetButton){
        new Thread(() -> {
            Platform.runLater(() -> resetButton.setDisable(true));
            visualizer.setSorting(true);
            int[] array = visualizer.getArray();
            int updateInterval = 5;
            int updateCounter = 0;
            int n = array.length;
            for (int i = 0; i < n-1; i++) {
                int min_idx = i;
                for (int j = i+1; j < n; j++)
                    if (array[j] < array[min_idx])
                        min_idx = j;
                int temp = array[min_idx];
                array[min_idx] = array[i];
                array[i] = temp;

                if (++updateCounter % updateInterval == 0) {
                    visualizer.drawArray();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            visualizer.drawArray();
            Platform.runLater(() -> resetButton.setDisable(false));
            visualizer.setSorting(false);
        }).start();
    }

}