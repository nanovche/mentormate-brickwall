package com.company.reader;

import com.company.exceptions.BrickSpansMoreThanTwoRowsOrColsException;
import com.company.exceptions.DimensionOutOfBoundsException;
import com.company.exceptions.GivenDimensionsDoNotCorrespondToInputRowsAndColsException;
import com.company.validation.InputValidator;

import java.util.Scanner;

public class InputReader {

    //scanner instance variable to read input
    private final Scanner scanner;
    //has-a relationship
    private final InputValidator validator;

    //constructor assigns scanner from client
    public InputReader(Scanner scanner) {
        this.scanner = scanner;
        validator = new InputValidator();
    }

    //reads width and length from standard input
    public int[] readDimensions() throws DimensionOutOfBoundsException {

        //zeroeth element contains width
        //first element contains length
        int[] dimensions = new int[2];

        dimensions[0] = scanner.nextInt();
        dimensions[1] = scanner.nextInt();

        validator.validateDimensions(dimensions[0], dimensions[1]);

        return dimensions;
    }

    //reads brickWall each separate line as a string, then splits by interval and
    //parses each string digit into int.
    //fills the input into two dimensional array
    public int[][] readBrickWall(int rows, int cols) throws GivenDimensionsDoNotCorrespondToInputRowsAndColsException, BrickSpansMoreThanTwoRowsOrColsException {

        scanner.nextLine();

        int[][] bricks = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            //string containing each separate line of input
            String currentLine = scanner.nextLine();
            //string array containing each separate digit(from the currentLine) as a string
            String[] digits = currentLine.split(" ");
            for (int col = 0; col < cols; col++) {
                bricks[row][col] = Integer.parseInt(digits[col]);
            }
        }

        validator.validateCorrespondenceBetweenDimensionsAndActualArrayDimensions(rows, cols, bricks);
        validator.validateBrickSpan(bricks);

        return bricks;
    }

    //closes Scanner
    public void closeScanner(){
        scanner.close();
    }

}
