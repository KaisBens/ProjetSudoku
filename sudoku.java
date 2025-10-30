import java.util.Random;

public class sudoku {
    private static final int SIZE = 9;
    private static final int EMPTY_CELL = 0;
    private int[][] board;
    private Random random;

    public sudoku() {
        board = new int[SIZE][SIZE];
        random = new Random();
    }

    public void generate() {
        if (solve(0, 0)) {
            fillRemaining();
        } else {
            System.out.println("Impossible de générer un Sudoku valide.");
        }
    }

    private boolean solve(int row, int col) {
        if (row == SIZE - 1 && col == SIZE) {
            return true;
        }

        if (col == SIZE) {
            row++;
            col = 0;
        }

        if (board[row][col] != EMPTY_CELL) {
            return solve(row, col + 1);
        }

        for (int num = 1; num <= SIZE; num++) {
            if (isValid(row, col, num)) {
                board[row][col] = num;
                if (solve(row, col + 1)) {
                    return true;
                }
                board[row][col] = EMPTY_CELL;
            }
        }

        return false;
    }

    private boolean isValid(int row, int col, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num || board[x][col] == num || board[(row - row % 3) + x / 3][(col - col % 3) + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    private void fillRemaining() {
        fillRemaining(0, 0);
    }

    private boolean fillRemaining(int row, int col) {
        if (row == SIZE - 1 && col == SIZE) {
            return true;
        }

        if (col == SIZE) {
            row++;
            col = 0;
        }

        if (board[row][col] == EMPTY_CELL) {
            int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            for (int i = 0; i < SIZE; i++) {
                int index = random.nextInt(SIZE - i) + i;
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            }

            for (int num : nums) {
                if (isValid(row, col, num)) {
                    board[row][col] = num;
                    if (fillRemaining(row, col + 1)) {
                        return true;
                    }
                    board[row][col] = EMPTY_CELL;
                }
            }
            return false;
        }
        return fillRemaining(row, col + 1);
    }

    public void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        sudoku generator = new sudoku();
        generator.generate();
        generator.print();
    }
}
