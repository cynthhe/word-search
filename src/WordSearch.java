/*
 * WordSearch.java
 * Author: Cynthia He
 * Submission Date: November 30, 2018
 *
 * Purpose: This program generates a 2D array (word puzzle) where the objective is to
 * find all of the dictionary words located in the array.
 *
 * Statement of Academic Honesty:
 *
 * The following code represents my own work. I have neither
 * received nor given inappropriate assistance. I have not copied
 * or modified code from any source other than the course webpage
 * or the course textbook. I recognize that any unauthorized
 * assistance or plagiarism will be handled in accordance with
 * the University of Georgia's Academic Honesty Policy and the
 * policies of this course. I recognize that my work is based
 * on an assignment created by the Department of Computer
 * Science at the University of Georgia. Any publishing
 * or posting of source code for this project is strictly
 * prohibited unless you have written consent from the Department
 * of Computer Science at the University of Georgia.
 */

import java.util.Scanner;

public class WordSearch {

    public static void main(String[] args) {
        long seed;
        WordBoard board;

        Scanner scanner = new Scanner(System.in); // create new Scanner object
        seed = promptUserForSeed(scanner); // pass scanner to promptUserForSeed()
        board = findWords(10,10, seed); // pass to findWords()
        System.out.println("\n" + board); // print board
        System.out.println("\n" + board.getSeed()); // get seed
        findWords(10, 10, seed).checkAnswers(); // use checkAnswers()
    }

    public static long promptUserForSeed(Scanner input) {
        System.out.println("Enter the seed for a 10x10 board:"); // print prompt to input seed
        long seed = input.nextLong(); // read input
        return seed; // return value
    }

    public static WordBoard findWords(int rows, int cols, long seed) {
        WordBoard board = new WordBoard(rows, cols, seed); // create new WordBoard object
        char[][] puzzle = board.getBoard();
        boolean charMatch = true;
        // takes ordered pair for highlightWord()
        int firstPairRow = 0;
        int firstPairCol = 0;
        int lastPairRow = 0;
        int lastPairCol = 0;
        // searches through each word
        for (String word : board.getDictionary()) {
            // horizontal loop
            for(int i = 0; i < puzzle.length; i++) {
                charMatch = false;
                int count = 0; // reset counter
                // horizontal left -> right
                for(int j = 0; j < puzzle[0].length; j++) {
                    char wordChar = puzzle[i][j]; // takes char at that specific point on the board
                    // compares word char and index of the dictionary word (determined by counter)
                    if(wordChar == word.charAt(count)) {
                        charMatch = true;
                        // takes first coordinates of the word
                        if(count == 0 && charMatch == true) {
                            firstPairRow = i;
                            firstPairCol = j;
                        }
                        count++; // increment count to find next char of dictionary word
                    }
                    // resets search back to the first char of the dictionary word
                    else {
                        charMatch = false;
                        count = 0;
                    }
                    // takes last coordinates of the dictionary word
                    if (count == word.length() && charMatch == true) {
                        lastPairRow = i;
                        lastPairCol = j;
                        board.highlightWord(firstPairRow, firstPairCol, lastPairRow, lastPairCol);
                        break;
                    }
                }
                // horizontal right -> left
                count = 0;
                for(int j2 = puzzle[0].length - 1; j2 >= 0; j2--) {
                    char wordChar = puzzle[i][j2];
                    if(wordChar == word.charAt(count)) {
                        charMatch = true;
                        if(count == 0 && charMatch == true) {
                            firstPairRow = i;
                            firstPairCol = j2;
                        }
                        count++;
                    }
                    else {
                        charMatch = false;
                        count = 0;
                    }
                    if (count == word.length() && charMatch == true) {
                        lastPairRow = i;
                        lastPairCol = j2;
                        board.highlightWord(firstPairRow, firstPairCol, lastPairRow, lastPairCol);
                        break;
                    }
                }
            }
            // vertical loop - where the column is at
            for(int j = 0; j < puzzle[0].length; j++) {
                charMatch = false;
                int count = 0;
                // vertical up -> bottom
                for(int i = 0; i < puzzle.length; i++) {
                    char wordChar = puzzle[i][j];
                    if(wordChar == word.charAt(count)) {
                        charMatch = true;
                        if(count == 0 && charMatch == true) {
                            firstPairRow = i;
                            firstPairCol = j;
                        }
                        count++;
                    }
                    else {
                        charMatch = false;
                        count = 0;
                    }
                    if (count == word.length() && charMatch == true) {
                        lastPairRow = i;
                        lastPairCol = j;
                        board.highlightWord(firstPairRow, firstPairCol, lastPairRow, lastPairCol);
                        break;
                    }
                }
                // vertical bottom -> up
                count = 0;
                for(int i2 = puzzle[0].length - 1; i2 >= 0; i2--) {
                    char wordChar = puzzle[i2][j];
                    if(wordChar == word.charAt(count)) {
                        charMatch = true;
                        if(count == 0 && charMatch == true) {
                            firstPairRow = i2;
                            firstPairCol = j;
                        }
                        count++;
                    }
                    else {
                        charMatch = false;
                        count = 0;
                    }
                    if (count == word.length() && charMatch == true) {
                        lastPairRow = i2;
                        lastPairCol = j;
                        board.highlightWord(firstPairRow, firstPairCol, lastPairRow, lastPairCol);
                        break;
                    }
                }
            }
        }
        return board; // return WordBoard object
    }

}
