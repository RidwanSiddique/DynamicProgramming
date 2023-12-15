import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CountPath {
    public static int countPath(List<List<String>> grid){
        return countPath(0, 0, grid, new HashMap<>());
    }
    public static int countPath(int row, int col, List<List<String>> grid, HashMap<List<Integer>, Integer> memo){
        if(row == grid.size() || col == grid.get(0).size()){
            return 0;
        }
        if(grid.get(row).get(col) == "X"){
            return 0;
        }
        if(row == grid.size() - 1 && col == grid.get(0).size() - 1){
            return 1;
        }
        List<Integer> pos = Arrays.asList(row, col);
        if(memo.containsKey(pos)){
            memo.get(pos);
        }
        int resultPath =  countPath(row + 1, col, grid, memo) + countPath(row, col + 1, grid, memo);
        memo.put(pos, resultPath);
        return resultPath;
    }
    public static void main(String[] args){

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

                        List<List<String>> grid = new ArrayList<>();
                        while (inputScanner.hasNextLine()) {
                            String line = inputScanner.nextLine();
                            List<String> row = Arrays.asList(line.split(" "));
                            grid.add(row);
                        }
                        int expectedOutput = outputScanner.nextInt();

                        int actualOutput = countPath(grid);

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
