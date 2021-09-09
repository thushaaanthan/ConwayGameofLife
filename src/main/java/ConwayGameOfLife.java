public class ConwayGameOfLife {

    public static void main(String[] args) {

        int [][] listOfInput = {{5,5},{6,5},{7,5},{5,6},{6,6},{7,6}};

        if(listOfInput.length>0) {
            int rows = 200;
            int columns = 200;
            int[][] currentGeneration = new int[rows][columns];
            for (int i = 0; i < listOfInput.length; i++) {
                currentGeneration[listOfInput[i][0]][listOfInput[i][1]] = 1;
            }
            System.out.println("Input");
            outputResult(currentGeneration);
            System.out.println("Output of the next 100 state:");
            NextGeneration nextGeneration = new NextGeneration(rows, columns);
            for (int i = 0; i < 100; i++) {
                int[][] nextGen = nextGeneration.nextState(currentGeneration);
                System.out.print((i + 1) + ": ");
                outputResult(nextGen);
                currentGeneration = nextGen;
            }
        } else {
            System.out.println("Input: {{}}");
            System.out.println("Output of the next 100 state:");
            System.out.println("1:[]");
        }
    }

    /**
     * This method helps to print the generation
     * @param generation the array set.
     */
    private static void outputResult(int[][] generation) {
        for (int i = 0; i < generation.length; i++) {
            for (int j = 0; j < generation[i].length; j++) {
                if (generation[i][j] != 0) {
                    System.out.print("[" + i + "," + j + "]");
                }
            }
        }
        System.out.println();
    }
}
