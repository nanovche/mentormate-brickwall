package com.company.solver;

public class Solver {

    int[][] newLayerOfBricks;

    public int[][]  calculateNewLayer(int[][] bricks){

        //vars to hold width and length of matrix for better readability
        int width = bricks.length;
        int length = bricks[0].length;

        //initializing next layer of bricks with same size as previous one
        newLayerOfBricks = new int[width][length];

        //total number of bricks is half the area of the layer
        int numberOfBricks = width * length / 2;

        //array to hold the numbers for bricks
        int[] brickNumbers = new int[numberOfBricks];

        //initializing it
        for (int i = 0; i < numberOfBricks; i++) {
            brickNumbers[i] = i + 1;
        }

        // index to keep track of the current brick added
        int indexOfNewBrick = 0;


        //algorithm: whenever two adjacent variables are different we can add a new brick on these indices
        //zeroes are left in the array whenever was not possible to add a brick rowwise -> will be filled out
        //columnwise
        for (int row = 0; row < width; row++) {

            for (int col = 0; col < length - 1; col++) {
                if(bricks[row][col] != bricks[row][col + 1]){
                    newLayerOfBricks[row][col] = brickNumbers[indexOfNewBrick];
                    newLayerOfBricks[row][col + 1] = brickNumbers[indexOfNewBrick++];
                    col++; // col is incremented when brick is added to skip the comparison of already taken place
                }
            }
        }


        //here are filled the remaining zeroes
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < length; col++) {
                if(newLayerOfBricks[row][col] == 0){

                    int[] indicesOfAdjacentZero = findAdjacentZeroes(newLayerOfBricks, row, col);
                    int rowOfNewZero = indicesOfAdjacentZero[0];
                    int colOfNewZero = indicesOfAdjacentZero[1];

                    newLayerOfBricks[row][col] = brickNumbers[indexOfNewBrick];
                    newLayerOfBricks[rowOfNewZero][colOfNewZero] = brickNumbers[indexOfNewBrick++];
                }
            }
        }

        return newLayerOfBricks;
    }

    //finds the other slot of the already empty one
    private int[] findAdjacentZeroes(int[][] newLayerOfBricks, int row, int col){

        //zeroeth element is row of adjacent zero
        //first element is col of adjacent zero
        //array to hold the indices
        int[] indicesOfAdjacentZero = new int[2];

        //it searches for adjacent zeroes(the adjacent slot for halfbrick)
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if(Math.abs(r) != Math.abs(c)){ // absolute values is used to avoid diagonals
                    if(isCoordinateInBounds(row + r, col + c)){
                        if(newLayerOfBricks[row + r][col + c] == 0){
                            indicesOfAdjacentZero[0] = row + r;
                            indicesOfAdjacentZero[1] = col + c;
                            break;
                        }
                    }
                }
            }
        }
        return indicesOfAdjacentZero;
    }

    //checks to see if coordinate is in bounds
    private boolean isCoordinateInBounds(int row, int col){

        return (row >= 0 && row < newLayerOfBricks.length) &&
                (col >= 0 && col < newLayerOfBricks[0].length);
    }
}
