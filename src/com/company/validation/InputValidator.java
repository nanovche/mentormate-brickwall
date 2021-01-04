package com.company.validation;

import com.company.exceptions.BrickSpansMoreThanTwoRowsOrColsException;
import com.company.exceptions.DimensionOutOfBoundsException;
import com.company.exceptions.ExceptionMessagesUtilityClass;
import com.company.exceptions.GivenDimensionsDoNotCorrespondToInputRowsAndColsException;

import static com.company.exceptions.ExceptionMessagesUtilityClass.*;

public class InputValidator {

    //validates dimension requirements
    public void validateDimensions(int rows, int cols) throws DimensionOutOfBoundsException {

        if((rows < 2 || rows > 100) || (cols < 2 || cols > 100)){
            throw new DimensionOutOfBoundsException(DIMENSION_OUT_OF_REQUIREMENT);
        }
    }

    //validates correspondence between given dimensions and actual array dimensions
    public void validateCorrespondenceBetweenDimensionsAndActualArrayDimensions(int rows, int cols, int[][] firstLayerOfBricks) throws GivenDimensionsDoNotCorrespondToInputRowsAndColsException {

        if((rows != firstLayerOfBricks.length) || (cols != firstLayerOfBricks[0].length)){
            throw new GivenDimensionsDoNotCorrespondToInputRowsAndColsException(INPUT_VALUES_D0_NOT_CORRESPOND_TO_GIVEN_DIMENSIONS);
        }
    }

    //validates row- and colspan to not exceed 3
    public void validateBrickSpan(int[][] firstLayerOfBricks) throws BrickSpansMoreThanTwoRowsOrColsException {

        //checks rowspan
        for (int[] firstLayerOfBrick : firstLayerOfBricks) {

            for (int col = 0; col < firstLayerOfBricks[0].length - 2; col++) {

                if (firstLayerOfBrick[col] == firstLayerOfBrick[col + 1] &&
                        firstLayerOfBrick[col] == firstLayerOfBrick[col + 2]) {

                    throw new BrickSpansMoreThanTwoRowsOrColsException(BRICKS_SPAN_THREE_ROWS_OR_COLS);
                }
            }
        }

        //checks colspan
        for (int col = 0; col < firstLayerOfBricks[0].length; col++) {

            for (int row = 0; row < firstLayerOfBricks.length - 2; row++) {

                if(firstLayerOfBricks[row][col] == firstLayerOfBricks[row + 1][col] &&
                   firstLayerOfBricks[row][col] == firstLayerOfBricks[row + 2][col]){

                    throw new BrickSpansMoreThanTwoRowsOrColsException(BRICKS_SPAN_THREE_ROWS_OR_COLS);
                }
            }
        }
    }
}
