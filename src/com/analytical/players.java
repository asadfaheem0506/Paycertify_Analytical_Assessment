package com.analytical;

import java.util.ArrayList;
import java.util.Scanner;

public class players {

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter horizontal rows of Multi-Dimension Array: ");
        int horizontal = in.nextInt();

        System.out.println("Enter vertical rows of Multi-Dimension Array: ");
        int vertical = in.nextInt();

        int numElementsMulti = horizontal * vertical;
        System.out.println("Number of Elements in Multi-Dimension Array: "+numElementsMulti);

        int[][] multi = new int[horizontal][vertical];

        for (int i = 0; i < multi.length; i++) {
            int horVal = i+1;
            for (int b = 0; b < multi[i].length; b++) {
                int verVal = b+1;
                String strVal = "" + horVal + verVal;
                multi[i][b] = Integer.parseInt(strVal);
            }
        }

        for (int i = 0; i < multi.length; i++) {
            for (int b = 0; b < multi[i].length; b++) {
                System.out.print(multi[i][b] + " ");
            }
            System.out.println();
        }

        System.out.println("Enter Number of Players to be occupied: ");
        int numPlayers = in.nextInt();
        int result = 0;

        int h = 0;
        int v = 0;
        int numElem = 1;
        while (numElem <= numElementsMulti) {
            System.out.println("Checking combination possible for element "+ numElem + " of total " + numElementsMulti + " elements");
            ArrayList<Integer> leftElems = new ArrayList<Integer>();
            for (int i = 0; i < multi.length; i++) {
                for (int b = 0; b < multi[i].length; b++) {
                    leftElems.add(multi[i][b]);
                }
            }

            int counter = 0;
            int elem = multi[h][v];
            counter++;
            System.out.println("Element Selected: "+elem);

            eliminateValues(multi, horizontal, vertical, elem, leftElems);
            System.out.print("Elements left: ");
            for (Integer leftElem : leftElems)
                System.out.print(leftElem + " ");
            System.out.println();

            while(leftElems.size() > 0) {
                counter++;
                elem = leftElems.get(0);
                System.out.println("Element Selected: "+elem);
                eliminateValues(multi, horizontal, vertical, elem, leftElems);
                System.out.print("Elements left: ");
                for (Integer leftElem : leftElems)
                    System.out.print(leftElem+ " ");
                System.out.println();

                if (counter == numPlayers) {
                    result++;
                    break;
                }
            }

            if (numElem % vertical == 0)
                h++;

            if (numElem % vertical != 0)
                v++;
            else
                v = 0;

            System.out.println("Combinations so far: "+result);
            numElem++;
        }

        System.out.println("Number of possible combinations to position players on grid: "+result);
    }

    public static void eliminateValues(int[][] multi, int horizontal, int vertical,
                                       int selectedElem, ArrayList<Integer> leftElems) {
        leftElems.remove(Integer.valueOf(selectedElem));

        //eliminate Horizontal elements of selected Element
        for (int i=0; i<vertical; i++)
            removeHorizontalRight(selectedElem, vertical, leftElems);
        for (int i=0; i<vertical; i++)
            removeHorizontalLeft(selectedElem, vertical, leftElems);

        //eliminate Vertical elements of selected Element
        for (int i=0; i<horizontal; i++)
            removeVerticalDown(selectedElem, horizontal, leftElems);
        for (int i=0; i<horizontal; i++)
            removeVerticalUp(selectedElem, horizontal, leftElems);

        //eliminate Diagonal elements of selected Element
        for (int i=0; i<horizontal; i++)
            removeTopLeftDiagonal(selectedElem, horizontal, leftElems);
        for (int i=0; i<horizontal; i++)
            removeBottomLeftDiagonal(selectedElem, horizontal, leftElems);
        for (int i=0; i<vertical; i++)
            removeTopRightDiagonal(selectedElem, vertical, leftElems);
        for (int i=0; i<vertical; i++)
            removeBottomRightDiagonal(selectedElem, vertical, leftElems);
    }

    public static void removeHorizontalRight(int selectedElem, int horizontal, ArrayList<Integer> leftElems) {
        int hRemoveElem = selectedElem;
        for (int i=0; i<horizontal; i++) {
            if (leftElems.contains(hRemoveElem))
                leftElems.remove(Integer.valueOf(hRemoveElem));
            hRemoveElem++;
        }
    }

    public static void removeHorizontalLeft(int selectedElem, int horizontal, ArrayList<Integer> leftElems) {
        int hRemoveElem = selectedElem;
        for (int i=0; i<horizontal; i++) {
            if (leftElems.contains(hRemoveElem))
                leftElems.remove(Integer.valueOf(hRemoveElem));
            hRemoveElem--;
        }
    }

    public static void removeVerticalDown(int selectedElem, int vertical, ArrayList<Integer> leftElems) {
        int vRemoveElem = selectedElem;
        for (int i=0; i<vertical; i++) {
            if (leftElems.contains(vRemoveElem))
                leftElems.remove(Integer.valueOf(vRemoveElem));
            vRemoveElem = vRemoveElem+10;
        }
    }

    public static void removeVerticalUp(int selectedElem, int vertical, ArrayList<Integer> leftElems) {
        int vRemoveElem = selectedElem;
        for (int i=0; i<vertical; i++) {
            if (leftElems.contains(vRemoveElem))
                leftElems.remove(Integer.valueOf(vRemoveElem));
            vRemoveElem = vRemoveElem-10;
        }
    }

    public static void removeTopLeftDiagonal(int selectedElem, int horizontal, ArrayList<Integer> leftElems) {
        int removeElem = selectedElem;
        for (int i=0; i<horizontal; i++) {
            if (leftElems.contains(removeElem))
                leftElems.remove(Integer.valueOf(removeElem));
            removeElem = removeElem+11;
        }
    }

    public static void removeBottomLeftDiagonal(int selectedElem, int horizontal, ArrayList<Integer> leftElems) {
        int removeElem = selectedElem;
        for (int i=0; i<horizontal; i++) {
            if (leftElems.contains(removeElem))
                leftElems.remove(Integer.valueOf(removeElem));
            removeElem = removeElem-9;
        }
    }

    public static void removeTopRightDiagonal(int selectedElem, int horizontal, ArrayList<Integer> leftElems) {
        int removeElem = selectedElem;
        for (int i=0; i<horizontal; i++) {
            if (leftElems.contains(removeElem))
                leftElems.remove(Integer.valueOf(removeElem));
            removeElem = removeElem+9;
        }
    }

    public static void removeBottomRightDiagonal(int selectedElem, int horizontal, ArrayList<Integer> leftElems) {
        int removeElem = selectedElem;
        for (int i=0; i<horizontal; i++) {
            if (leftElems.contains(removeElem))
                leftElems.remove(Integer.valueOf(removeElem));
            removeElem = removeElem-11;
        }
    }
}