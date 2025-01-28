package dev.brunob.algovisualizer;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AlgorithmVisualizer extends Canvas {

    private int[] array;
    private volatile boolean isSorting = false;
    private volatile int sortedCount;

    public AlgorithmVisualizer(int width, int height) {
        super(width, height);
        array = new int[width];
        for (int i = 0; i < width; i++) {
            array[i] = (int) (Math.random() * height);
        }
    }
    public void drawArray(int highlight1, int highlight2, int highlight3, int target) {
        Platform.runLater(() -> {
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());

            for (int i = 0; i < array.length; i++) {
                // Color base
                Color color = Color.BLACK;

                // Lógica de colores
                if (i == highlight1 || i == highlight3) {
                    color = Color.BLUE; // Límites low/high
                } else if (i == highlight2) {
                    color = Color.RED;  // Punto medio
                } else if (array[i] == target) {
                    color = Color.GREEN; // Objetivo
                }

                gc.setFill(color);
                gc.fillRect(i, getHeight() - array[i], 1, array[i]);
            }
        });
    }

    public void drawArray() {
        Platform.runLater(() -> {
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());
            for (int i = 0; i < array.length; i++) {
                gc.setFill(Color.BLACK);
                gc.fillRect(i, getHeight() - array[i], 1, array[i]);
            }
        });
    }

    private void highlightIndices(int low, int mid, int high, int target) {
        drawArray(low, mid, high, target);
    }
    private void highlightFound(int index) {
        Platform.runLater(() -> {
            GraphicsContext gc = getGraphicsContext2D();
            gc.setFill(Color.GREEN);
            gc.fillRect(index, getHeight() - array[index], 1, array[index]);
        });
    }

    public void resetArray() {
        if (!isSorting) {
            sortedCount = 0; // Reiniciar contador
            int width = (int) getWidth();
            int height = (int) getHeight();
            array = new int[width];
            for (int i = 0; i < width; i++) {
                array[i] = (int) (Math.random() * height);
            }
        }
    }

    public void bubbleSort() {
        new Thread(() -> {
            isSorting = true;
            int updateInterval = 5; // Actualizar cada 5 intercambios
            int updateCounter = 0;

            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;

                        if (++updateCounter % updateInterval == 0) {
                            drawArray();
                            try {
                                Thread.sleep(1); // Reducir el sleep
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
            drawArray();
            // Ultima actualizacion para ver el resultado final
            isSorting = false;
        }).start();
    }

    public void insertionSort() {
        new Thread(() -> {
            isSorting = true;
            int updateInterval = 5;
            int updateCounter = 0;

            for (int i = 1; i < array.length; i++) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;

                    if (++updateCounter % updateInterval == 0) {
                        drawArray();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                array[j + 1] = key;
            }
            drawArray();
            isSorting = false;
        }).start();
    }
    private void insertionSortSilent() {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public void binarySearch(){
        new Thread(() -> {
            isSorting = true;

            // Primero ordenar el arreglo (requisito para búsqueda binaria)
            insertionSortSilent(); // Metodo sin visualización para ordenar primero

            int target = array[(int)(Math.random() * array.length)]; // Valor aleatorio del arreglo
            int low = 0;
            int high = array.length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                // Resaltar elementos importantes
                highlightIndices(low, mid, high, target);

                try { Thread.sleep(500); } catch (InterruptedException e) { break; }

                if (array[mid] == target) {
                    highlightFound(mid); // Resaltar encontrado
                    break;
                } else if (array[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            isSorting = false;
        }).start();
    }

    public void selectionSort(){
        new Thread(() -> {
            isSorting = true;
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
                    drawArray();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            drawArray();
            isSorting = false;
        }).start();
    }

    public void quickSort(){
        new Thread(() -> {
            isSorting = true;
            int updateInterval = 5;
            int updateCounter = 0;

            int n = array.length;
            for (int i = 1; i < n; ++i) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;

                    if (++updateCounter % updateInterval == 0) {
                        drawArray();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                array[j + 1] = key;
            }
            drawArray();
            isSorting = false;
        }).start();
    }

    public void mergeSort(){
        new Thread(() -> {
            isSorting = true;
            int updateInterval = 5;
            int updateCounter = 0;

            int n = array.length;
            for (int i = 1; i < n; ++i) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;

                    if (++updateCounter % updateInterval == 0) {
                        drawArray();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                array[j + 1] = key;
            }
            drawArray();
            isSorting = false;
        }).start();
    }

    public void linearSearch() {
        new Thread(() -> {
            isSorting = true;
            int target = array[(int)(Math.random() * array.length)]; // Objetivo aleatorio

            for (int i = 0; i < array.length; i++) {
                // Resaltar el elemento actual y el objetivo
                highlightCurrentAndTarget(i, target);

                try { Thread.sleep(10); } // Más rápido para búsqueda lineal
                catch (InterruptedException e) { break; }

                if (array[i] == target) {
                    highlightFound(i);
                    break;
                }
            }
            isSorting = false;
        }).start();
    }

    private void highlightCurrentAndTarget(int currentIndex, int targetValue) {
        Platform.runLater(() -> {
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());

            for (int j = 0; j < array.length; j++) {
                if (j == currentIndex) {
                    gc.setFill(Color.RED); // Elemento actual
                } else if (array[j] == targetValue) {
                    gc.setFill(Color.GREEN); // Objetivo
                } else {
                    gc.setFill(Color.BLACK); // Resto
                }
                gc.fillRect(j, getHeight() - array[j], 1, array[j]);
            }
        });
    }

    private void heapify(int heapSize, int rootIndex) {
        int largest = rootIndex;
        int leftChild = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;

        if (leftChild < heapSize && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        if (rightChild < heapSize && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        if (largest != rootIndex) {
            int swap = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = swap;

            drawArrayWithHighlights(rootIndex, largest); // Resaltar nodos del heap
            try { Thread.sleep(5); } catch (InterruptedException e) { return; }

            heapify(heapSize, largest);
        }
    }

    public void heapSort() {
        new Thread(() -> {
            isSorting = true;
            sortedCount = 0; // Inicializar contador
            int n = array.length;
            // Construir el max heap
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(n, i);
            }
            // Extraer elementos del heap
            for (int i = n - 1; i > 0; i--) {
                // Intercambiar raíz con último elemento
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;

                sortedCount++; // Actualizar contador de ordenados

                drawArrayWithHighlights(i, 0);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    break;
                }

                heapify(i, 0);
            }
            sortedCount = array.length; // Marcar todos como ordenados al final
            drawArray();
            isSorting = false;
        }).start();
    }
    private void drawArrayWithHighlights(int highlight1, int highlight2) {
        Platform.runLater(() -> {
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());

            for (int i = 0; i < array.length; i++) {
                // Elementos ordenados (verde)
                if (i >= array.length - sortedCount) {
                    gc.setFill(Color.GREEN);
                }
                // Elementos siendo procesados (rojo)
                else if (i == highlight1 || i == highlight2) {
                    gc.setFill(Color.RED);
                }
                // Resto (negro)
                else {
                    gc.setFill(Color.BLACK);
                }
                gc.fillRect(i, getHeight() - array[i], 1, array[i]);
            }
        });
    }
}
