public class SetMatrixZero {
    public void setZeroes(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        boolean doesTopRowHasZero = false;
        boolean doesTopColHasZero = false;
        //read 1 row only
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) {
                    doesTopRowHasZero = true;
                    break;
                }
            }
        }

        //read 1 col only
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < r; j++) {
                if (matrix[j][i] == 0) {
                    doesTopColHasZero = true;
                    break;
                }
            }
        }


        //read full matrix
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }


        //read 1 row again for setting 0
        for (int i = 0; i < 1; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] == 0) {
                    //fill column
                    for (int k = 0; k < r; k++) {
                        matrix[k][j] = 0;
                    }
                }
            }
        }

        //read 1 col again for setting 0
        for (int i = 0; i < 1; i++) {
            for (int j = 1; j < r; j++) {
                if (matrix[j][i] == 0) {
                    //fill row
                    for (int k = 0; k < c; k++) {
                        matrix[j][k] = 0;
                    }
                }
            }
        }


        //final case for row and col each
        //read 1 row only
        if (doesTopRowHasZero) {
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < c; j++) {
                    matrix[i][j] = 0;
                }
            }
        }


        //read 1 col only
        if (doesTopColHasZero) {
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < r; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        return;
    }
}
