import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Fibonacci {
    public static int fib(int n) {
        return fib(n, new HashMap<>());
    }

    public static int fib(int n, HashMap<Integer, Integer> memo) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = fib(n - 1, memo) + fib(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        File folder = new File("../test");
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.endsWith(".in")) {
                    String inputFileName = fileName;
                    String outputFileName = fileName.replace(".in", ".out");
                    String inputFilePath = "../test/" + inputFileName;
                    String outputFilePath = "../test/" + outputFileName;

                    try {
                        Scanner inputScanner = new Scanner(new File(inputFilePath));
                        Scanner outputScanner = new Scanner(new File(outputFilePath));

                        int input = inputScanner.nextInt();
                        int expectedOutput = outputScanner.nextInt();

                        int actualOutput = fib(input);

                        if (actualOutput == expectedOutput) {
                            System.out.println(inputFileName + " PASSED");
                        } else {
                            System.out.println(inputFileName + " FAILED");
                            System.out.println("Expected Output: " + expectedOutput);
                            System.out.println("Actual Output: " + actualOutput);
                        }

                        inputScanner.close();
                        outputScanner.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}