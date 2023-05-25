import java.util.Random;
public class Main {
    // Создание массива из случайной последовательности 10000 целых чисел в программе
    public static class RandomGenerator {
        public static int[] generateArray(int size) {
            int[] array = new int[size];
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt();
            }
            return array;
        }
        public static int[] generateRandomIndices(int size, int[] array) {
            Random random = new Random();
            int[] randomIndexes = new int[size];
            for (int i = 0; i < size; i++) {
                randomIndexes[i] = random.nextInt(array.length);
            }
            return randomIndexes;
        }
    }
    public static void main(String[] args) {

// Добавление чисел, замеряя время работы и количество операций для каждого добавления
        FenwickTree fenwickTree = new FenwickTree(10000);
        int[] array = RandomGenerator.generateArray(10000);
        long totalTime = 0;
        long totalOperations = 0;
        for (int i = 0; i < array.length; i++) {
            long startTime = System.nanoTime();
            fenwickTree.update(i + 1, array[i]);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            totalTime += elapsedTime;
            totalOperations += i + 1;
        }
        double averageTime = (double) totalTime / array.length;
        double averageOperations = (double) totalOperations / array.length;
        System.out.println("Average insertion time: " + averageTime + " ns");
        System.out.println("Average insertion operations: " + averageOperations);

// Поиск случайно выбранных 100 элементов, замеряя время работы и количество операций для каждого поиска:
        int[] randomIndices = RandomGenerator.generateRandomIndices(100, array);
        totalTime = 0;
        totalOperations = 0;
        for (int index : randomIndices) {
            long startTime = System.nanoTime();
            int result = fenwickTree.query(index + 1);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            totalTime += elapsedTime;
            totalOperations += index + 1;
        }
        averageTime = (double) totalTime / randomIndices.length;
        averageOperations = (double) totalOperations / randomIndices.length;
        System.out.println("Average search time: " + averageTime + " ns");
        System.out.println("Average search operations: " + averageOperations);

//Удаление случайно выбранных 1000 элементов, замеряя время работы и количество операций для каждого удаления
        int[] randomIndices1 = RandomGenerator.generateRandomIndices(1000, array);
        totalTime = 0;
        totalOperations = 0;
        for (int index : randomIndices1) {
            long startTime = System.nanoTime();
            fenwickTree.update(index + 1, - array[index]);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            totalTime += elapsedTime;
            totalOperations += index + 1;
        }
        averageTime = (double) totalTime / randomIndices1.length;
        averageOperations = (double) totalOperations / randomIndices1.length;
        System.out.println("Average deletion time: " + averageTime + " ns");
        System.out.println("Average deletion operations: " + averageOperations);
    }
}