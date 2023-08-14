package easyCollection;

import java.util.HashMap;
import java.util.Map;

public class isValidSoduko {
    public boolean isValidSudoku(char[][] board) {

        return checkAllCols(board) && checkAllRows(board) && checkInnerBoxes(board);
    }

    private boolean checkInnerBoxes(char[][] board) {
        int offset, ilimit, jlimit;
        offset = ilimit = jlimit = 3;

        for (int i = 0; i < ilimit && i < 9; i += offset, ilimit += offset) {
            for (int j = 0; j < jlimit && j < 9; j += offset, jlimit += offset) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int ii = i; ii < i+3; ii++) {
                    for (int jj = j; jj < j+3; jj++) {
                        int key = board[ii][jj];
                        if (key == '.') {
                            continue;
                        }
                        if (map.getOrDefault(key, 0) > 0) {
                            return false;
                        }
                        map.put(key, 1);
                    }
                }
            }
        }
        return true;
    }

    private boolean checkAllRows(char[][] board) {
        for (int j = 0; j < 9; j++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < 9; i++) {
                int key = board[i][j];
                if (key == '.') {
                    continue;
                }
                if (map.getOrDefault(key, 0) > 0) {
                    return false;
                }
                map.put(key, 1);
            }
        }
        return true;
    }

    private boolean checkAllCols(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < 9; j++) {
                int key = board[i][j];
                if (key == '.') {
                    continue;
                }
                if (map.getOrDefault(key, 0) > 0) {
                    return false;
                }
                map.put(key, 1);
            }
        }
        return true;
    }
}
