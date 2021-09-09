public class NextGeneration {

    private int[][] currentState;
    private int rows;
    private int columns;

    public NextGeneration(int row, int column) {

        this.currentState = new int[row][column];
        this.rows = row;
        this.columns = column;
    }

    public int[][] getCurrentState() {
        return currentState;
    }

    /**
     * This method will calculate the next state of the current generation.
     *
     * @param currentState the current state matrix.
     * @return next state result.
     */
    public int[][] nextState(int[][] currentState) {

        this.currentState = currentState;
        int[][] nextState = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                nextState[i][j] = this.cellNextGenerationState(i, j);
            }
        }
        return nextState;
    }

    /**
     * This method helps to find the generation change of the particular cell in the next state.
     *
     * @param cellRow    vertical position of the cell
     * @param cellColumn horizontal position of the cell
     * @return the generation change of the particular cell in the next state.
     */
    private int cellNextGenerationState(int cellRow, int cellColumn) {

        int aliveNeighbors = this.liveNeighbours(cellRow, cellColumn);
        if ((currentState[cellRow][cellColumn] == 1) && (aliveNeighbors == 3 || aliveNeighbors == 2)) {
            //Any live cell with two or three live neighbours lives on to the next generation.
            return 1;
        } else if ((currentState[cellRow][cellColumn] == 1) && aliveNeighbors > 3) {
            //Any live cell with more than three live neighbours dies, as if by overpopulation.
            return 0;
        } else if ((currentState[cellRow][cellColumn] == 0) && aliveNeighbors == 3) {
            // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method helps to calculate the count of the live neighbours.
     *
     * @param cellRow    vertical position of the cell.
     * @param cellColumn horizontal position of the cell.
     * @return count of the live neighbours.
     */
    private int liveNeighbours(int cellRow, int cellColumn) {

        int liveNeighboursCount = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (cellRow + x < 0 || cellRow + x > (currentState.length - 1) || y + cellColumn < 0 ||
                        y + cellColumn > (currentState[0].length - 1)) {
                    continue;
                }
                liveNeighboursCount += currentState[cellRow + x][y + cellColumn];
            }
        }
        liveNeighboursCount -= currentState[cellRow][cellColumn];
        return liveNeighboursCount;
    }
}
