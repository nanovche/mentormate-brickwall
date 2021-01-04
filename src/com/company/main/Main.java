package com.company.main;

import com.company.exceptions.BrickSpansMoreThanTwoRowsOrColsException;
import com.company.exceptions.DimensionOutOfBoundsException;
import com.company.exceptions.GivenDimensionsDoNotCorrespondToInputRowsAndColsException;
import com.company.printer.Printer;
import com.company.reader.InputReader;
import com.company.solver.Solver;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws DimensionOutOfBoundsException, GivenDimensionsDoNotCorrespondToInputRowsAndColsException, BrickSpansMoreThanTwoRowsOrColsException {

        InputReader inputReader = new InputReader(new Scanner(System.in));

        //zeroeth element contains width
        //first element contains length
        int[] dimensions = inputReader.readDimensions();
        int[][] brickWall = inputReader.readBrickWall(dimensions[0], dimensions[1]);
        inputReader.closeScanner();

        System.out.println("First layer: ");
        //initalizes printer
        Printer printer = new Printer();
        printer.printBrickWall(brickWall);

        //solver object -> does the computation
        Solver solver = new Solver();
        int[][] newLayer = solver.calculateNewLayer(brickWall);

        //printing solution
        System.out.println("Second Layer:");
        printer.printBrickWall(newLayer);

    }

}
