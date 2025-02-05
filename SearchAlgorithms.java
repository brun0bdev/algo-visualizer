package dev.brunob.algovisualizer.algorithms;

import dev.brunob.algovisualizer.AlgorithmVisualizer;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class SearchAlgorithms {
    private AlgorithmVisualizer visualizer;

    public SearchAlgorithms(AlgorithmVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void binarySearch(Button resetButton) {
        new Thread(() -> {
            visualizer.setSorting(true);
            Platform.runLater(() -> resetButton.setDisable(true));
            int[] array = visualizer.getArray();
            visualizer.insertionSortSilent();

            int target = array[(int) (Math.random() * array.length)];
            int low = 0;
            int high = array.length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                visualizer.highlightIndices(low, mid, high, target);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }

                if (array[mid] == target) {
                    visualizer.highlightFound(mid);
                    break;
                } else if (array[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            visualizer.setSorting(false);
        }).start();
    }

    public void linearSearch(Button resetButton) {
        new Thread(() -> {
            Platform.runLater(() -> resetButton.setDisable(true));
            visualizer.setSorting(true);
            int[] array = visualizer.getArray();

            int target = array[(int) (Math.random() * array.length)];

            for (int i = 0; i < array.length; i++) {
                visualizer.highlightCurrentAndTarget(i, target);

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    break;
                }

                if (array[i] == target) {
                    visualizer.highlightFound(i);
                    break;
                }
            }
            Platform.runLater(() -> resetButton.setDisable(false));
            visualizer.setSorting(false);
        }).start();
    }
}