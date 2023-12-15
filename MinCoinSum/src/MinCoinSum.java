import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MinCoinSum {

        public static int minCoin(int target, List<Integer> coins){
            return minCoin(target, coins, new HashMap<>());
        }
        public static int minCoin(int target, List<Integer> coins, HashMap<Integer, Integer> memo){

            if(target == 0){
                return 0;
            }
            if(target < 0){
                return -1;
            }
            if(memo.containsKey(target)){
                return memo.get(target);
            }
            int minimumCoin = -1;
            for (int coin : coins){
                int subResult = target - coin;
                int currentSum = minCoin(subResult, coins, memo);
                if( currentSum != -1){
                    int numCoinSum = currentSum + 1;
                    if(numCoinSum<minimumCoin || minimumCoin == -1){
                        minimumCoin = numCoinSum;

                    }
                }
            }
            memo.put(target, minimumCoin);
            return minimumCoin;
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
                            List<Integer> coins = new ArrayList<>();

                            // Read the rest of the integers into the list
                            while (inputScanner.hasNextInt()) {
                                int coin = inputScanner.nextInt();
                                coins.add(coin);
                            }
                            int expectedOutput = outputScanner.nextInt();

                            int actualOutput = minCoin(target, coins);

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
