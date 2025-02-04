package dev.brunob.algovisualizer.algorithms;

import dev.brunob.algovisualizer.AlgorithmVisualizer;

public class SortingAlgorithms {
    private int[] array;
    private AlgorithmVisualizer visualizer;

    public SortingAlgorithms(int[] array, AlgorithmVisualizer visualizer) {
        this.array = array;
        this.visualizer = visualizer;
    }

    public void bubbleSort() {
        new Thread(() -> {
            visualizer.setSorting(true);
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
            visualizer.setSorting(false);
        }).start();
    }

    public void insertionSort(){
        new Thread(() -> {
            visualizer.setSorting(true);
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
            visualizer.setSorting(false);
        }).start();
    }
}
