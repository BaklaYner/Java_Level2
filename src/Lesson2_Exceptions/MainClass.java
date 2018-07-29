package Lesson2_Exceptions;

public class MainClass {
    private static String test = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0";
    private static String test2 = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 a";
    private static String test3 = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0 0";
    private static String test4 = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0\n1 2 3 4";
    public static void main(String[] args) {
        double res = 0;
        try {
            res = matrixHalfSum(test);
            System.out.println(res);
        } catch (WrongArraySizeException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    private static double matrixHalfSum(String source) throws WrongArraySizeException {
        String[] temp = source.split("\n");
        if (temp.length != 4)
            throw new WrongArraySizeException("Size of entered matrix should be 4x4");

        String[][] matrix = new String[temp.length][];
        for (int i = 0; i < temp.length; i++) {
            matrix[i] = temp[i].split(" ");
            if (matrix[i].length != 4)
                throw new WrongArraySizeException("Size of entered matrix should be 4x4");
        }

        double sum = 0;
        for (String[] line : matrix) {
            for (String number : line) {
                sum += Integer.parseInt(number);
            }
        }

        return sum / 2;
    }

}