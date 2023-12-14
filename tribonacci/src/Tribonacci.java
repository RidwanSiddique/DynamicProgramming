import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Tribonacci {
    public static int trib(int n) {
        return trib(n, new HashMap<>());
    }

    public static int trib(int n, HashMap<Integer, Integer> memo) {
        if (n == 0 ) {
            return n;
        }
       else if(n == 1){
            return 0;
        }
        else if(n == 2){
            return 1;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = trib(n - 1, memo) + trib(n - 2, memo) + trib(n - 3, memo);
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

                        int actualOutput = trib(input);

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