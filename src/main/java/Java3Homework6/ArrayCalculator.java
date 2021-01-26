package Java3Homework6;

public class ArrayCalculator {
    public static int[] array1(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr.length == 0)
                throw new RuntimeException("Массив должен содержать хотя бы один элемент");
            if (arr[i] == 4) {
                return Arrays.copyOfRange(arr, i + 1, arr.length);
            }
        }
        throw new RuntimeException("Массив не содержит 4");
    }

    public static boolean arrayContainsNumbers(int[] arr) {
        boolean number1 = false;
        boolean number4 = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 1 && arr[i] != 4) {
                return false;
            }
            if (arr[i] == 1) {
                number1 = true;
            }
            if (arr[i] == 4) {
                number4 = true;
            }
        }
        return number1 && number4;
    }
}
