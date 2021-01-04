package com.company.printer;

public class Printer {

    //prints result
    public void printBrickWall(int[][] bricks){

        for (int[] brick : bricks) {
            System.out.println("*".repeat(5 * bricks[0].length));
            System.out.print("*");
            for (int col = 0; col < bricks[0].length - 1; col++) {
                System.out.printf("%-4d", brick[col]);
                if(brick[col] == brick[col + 1]){
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }
                if(col == bricks[0].length - 2){
                    System.out.printf("%-4d", brick[col + 1]);
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        System.out.println("*".repeat(5 * bricks[0].length));

    }
}
