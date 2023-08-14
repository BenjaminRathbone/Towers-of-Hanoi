//Programmer: Ben Rathbone
//CS 145
//Date: 8-5-23
//Assignment: Lab 5 - Towers of Hanoi
//Purpose: A program that solves the Tower of Hanoi puzzle in the fewest possible moves.
//         The number of disks can be set in a field, but the default is 4.

import java.util.*;

public class TowersOfHanoi
{
   //---fields---
   static int disks = 4;   //# of disks
   static int pause = 500; //time to pause between moves (milliseconds)
   //pegs
   static Stack<Integer> Apeg;
   static Stack<Integer> Bpeg;
   static Stack<Integer> Cpeg;
   static Map<Character,Stack<Integer>> pegs;
   static int count; //int to keep track of # of moves 
   
   //main method
   //initializes fields and fills starting peg with disks
   //calls the move method to solve the puzzle
   public static void main(String[] args) throws InterruptedException
   {
      //initialize pegs
      Apeg = new Stack<>();
      Bpeg = new Stack<>();
      Cpeg = new Stack<>();
      
      //puts the pegs into a map
      pegs = new HashMap<Character,Stack<Integer>>();
      pegs.put('A', Apeg);
      pegs.put('B', Bpeg);
      pegs.put('C', Cpeg);
      
      //initialize count
      count = 1;
      
      //fills starting peg with disks
      for (int i = disks; i > 0; i--)
      {
         Apeg.add(i);
      }
      
      //prints title and starting position
      System.out.println("_________________");
      System.out.println("|TOWERS OF HANOI|");
      System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
      System.out.println("Starting position:");
      printTowers(); //calls the printTowers method
      Thread.sleep(pause); //pause
      
      //calls the move method to solve the puzzle
      move(disks, 'A', 'C', 'B');
      System.out.print("All done!");
   }//end of main method
   
   //recursive method that moves disks from starting peg to destination peg
   //accepts n number of disks
   //accepts start, destination, and auxiliary peg
   public static void move(int n, char start, char dest, char aux) throws InterruptedException
   {
      if (n == 1) //base case
      {
         //move the disk from the start peg to the dest peg
         System.out.println(count + ". Move a disk from peg " + start + " to peg " + dest + ".");
         pegs.get(dest).push(pegs.get(start).pop()); //moves the disk from start peg to dest peg
         count++; //increases count variable
         printTowers(); //calls the printTowers method
         Thread.sleep(pause); //pause
      }
      else //recursive case
      {
         //move n-1 disks from the start peg to the aux peg, using the dest peg as the aux
         move(n - 1, start, aux, dest);
         
         //move the nth disk from the start peg to the dest peg
         System.out.println(count + ". Move a disk from peg " + start + " to peg " + dest + ".");
         pegs.get(dest).push(pegs.get(start).pop()); //moves the disk from start peg to dest peg
         count++; //increases count variable
         printTowers(); //calls the printTowers method
         Thread.sleep(pause); //pause
         
         //move n-1 disks from the aux peg to the dest peg, using the start peg as the aux
         move(n - 1, aux, dest, start);
      }
   }//end of move method
   
   //prints the puzzle in its current state
   public static void printTowers()
   {
      System.out.println("A: " + Apeg);
      System.out.println("B: " + Bpeg);
      System.out.println("C: " + Cpeg);
      System.out.println();
   }//end of printTowers method
}//end of program