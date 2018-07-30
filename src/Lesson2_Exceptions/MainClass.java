package Lesson2_Exceptions;

public class MainClass {
    private static String test = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0";
    private static String test2 = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 a";
    private static String test3 = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0 0";
    private static String test4 = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0\n1 2 3 4";

    public static void main(String[] args) {
        try {
            double res = matrixHalfSum(parseMatrix(test));
            System.out.println(res);
        } catch (WrongArraySizeException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String[][] parseMatrix(String source) throws WrongArraySizeException {
        String[] temp = source.split("\n");
        if (temp.length != 4)
            throw new WrongArraySizeException("Size of entered matrix should be 4x4. There are "
                    + temp.length + " rows in entered matrix:\n" + source);

        String[][] matrix = new String[temp.length][];
        for (int i = 0; i < temp.length; i++) {
            matrix[i] = temp[i].split(" ");
            if (matrix[i].length != 4)
                throw new WrongArraySizeException("Size of entered matrix should be 4x4. There are "
                        + matrix[i].length + " elements in row " + i + ":\n" + source);
        }
        return matrix;
    }

    private static double matrixHalfSum(String[][] matrix) {
        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += Integer.parseInt(matrix[i][j]);
            }
        }
        return sum / 2;
    }

}