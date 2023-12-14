import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SumPossible {
    public static boolean sumPossible(int target, List<Integer> numbers){
        return sumPossible(target, numbers, new HashMap<>());
    }
    public static boolean sumPossible(int target, List<Integer> numbers, HashMap<Integer, Boolean> memo){
        if(target == 0){
            return true;
        }
        if(target < 0){
            return false;
        }
        if(memo.containsKey(target)){
            return memo.get(target);
        }
        for (int num : numbers){
            int subResult = target - num;
            if(sumPossible(subResult, numbers, memo)){
                memo.put(target, true);
                return true;
            }
        }
        memo.put(target, false);
        return false;
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

                        int target = inputScanner.nextInt();

                        // Create a list to store the remaining integers
                        List<Integer> numbers = new ArrayList<>();

                        // Read the rest of the integers into the list
                        while (inputScanner.hasNextInt()) {
                            int number = inputScanner.nextInt();
                            numbers.add(number);
                        }
                        boolean expectedOutput = outputScanner.nextBoolean();

                        boolean actualOutput = sumPossible(target, numbers);

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
