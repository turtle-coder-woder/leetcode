public class WordSearch {
    Integer row, col;

    public boolean exist(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        char[] words = word.toCharArray();

        int[][] boardMask = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (words[0] == board[i][j] && boardMask[i][j] == 0) {
                    if (traverse(board, words, boardMask, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    private boolean traverse(char[][] board, char[] word, int[][] boardMask, int r, int c, int k) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return false;
        }

        if (board[r][c] == word[k] && boardMask[r][c] == 0) {
            if (k == word.length - 1) {
                return true;
            }
            boardMask[r][c] = 1;
            if (traverse(board, word, boardMask, r + 1, c, k + 1) ||
                    traverse(board, word, boardMask, r, c + 1, k + 1) ||
                    traverse(board, word, boardMask, r - 1, c, k + 1) ||
                    traverse(board, word, boardMask, r, c - 1, k + 1)) {
                return true;

            }
            boardMask[r][c] = 0;
        }


        return false;
    }


}
