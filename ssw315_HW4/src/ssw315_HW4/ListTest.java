package ssw315_HW4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class ListTest{
	ListTest(){
	}
	
	public class Rectangle{
		
		private double side1;
		private double side2;
		
		/** Constructs a default Rectangle object */
		public Rectangle() {
			this.side1 = 0.0;
			this.side2 = 0.0;
		}
		
		/** Constructs a Rectangle object with input values side1 and side2 */
		public Rectangle(double side1, double side2) throws IllegalArgumentException{
			if (side1 < 0.0 || side2 < 0.0) throw new IllegalArgumentException("Invalid input!");
			this.side1 = side1;
			this.side2 = side2;
		}
		
		/** Returns side1 */
		public double getSideOne() {
			return this.side1;
		}
		
		/** Returns side1 */
		public double getSideTwo() {
			return this.side2;
		}
		
		/** Sets side1 to new value */
		public void setSideOne(double side1) throws IllegalArgumentException{
			if (side1 < 0.0) throw new IllegalArgumentException("Invalid input!");
			this.side1 = side1;
		}
		
		/** Sets side1 to new value */
		public void setSideTwo(double side2) throws IllegalArgumentException{
			if (side2 < 0.0) throw new IllegalArgumentException("Invalid input!");
			this.side2 = side2;
		}
		
		/** Returns a String representation of the Rectangle object */
		public String toString() {
			return "side 1: " + side1 + ", side 2: " + side2 + ".";
		}
	}
	
	private List<Rectangle> testRec = new ArrayList<Rectangle>();
	private List<List<Rectangle>> dupes = new ArrayList<List<Rectangle>>();
	
	public void addRec() {
		testRec.add(new Rectangle(0.1, 3));//
		testRec.add(new Rectangle(0.5, 4));
		testRec.add(new Rectangle(0.2, 6));
		testRec.add(new Rectangle(0.1, 7));
		testRec.add(new Rectangle(1.1, 3));
		testRec.add(new Rectangle(0.7, 8));
		testRec.add(new Rectangle(0.4, 4));   ///
		testRec.add(new Rectangle(0.1, 3));//
		testRec.add(new Rectangle(0.4, 4));   ///
		testRec.add(new Rectangle(0.1, 3));//
	}
	
	public void testing() {
		//int[] nonDupe = new int
		for (int i = 0; i < testRec.size(); i++) {//goes through every item in list
			//if (i == 5) {
			//	testRec.remove(i);
			//}
			System.out.println(testRec.get(i));
			//for (int j = 0; j < testRec.size(); j++) {
				
			//}	
		}
		return;
	}
	
	public void findDupes() { //IT FUCKING WORKS
		//int[] nonDupe = new int
		for (int i = 0; i < testRec.size(); i++) {//goes through every item in list
			boolean there = false;
			List<Rectangle> duplicate = new ArrayList<Rectangle>();
			//System.out.println();
			//System.out.println(testRec.get(i));
			//System.out.println();
			Rectangle r1 = testRec.get(i);
			for (int j = 0; j < testRec.size(); j++) {
				Rectangle r2 = testRec.get(j);
				//System.out.println(testRec.get(j));
				//System.out.println(testRec.get(i).getSideOne() == testRec.get(j) && i != j);
				if (r1.getSideOne() == r2.getSideOne() && r1.getSideTwo() == r2.getSideTwo() && i != j) {
					System.out.println(testRec.get(i) + " " + i);
					System.out.println(testRec.get(j) + " " + j);
					System.out.println();
					there = true;
					duplicate.add(testRec.get(j));
					testRec.remove(j);
				}
			}
			if (there == true) {
				duplicate.add(testRec.get(i));
				dupes.add(duplicate);
			}
		}
		return;
	}
	
	public void print() {
		for (int i = 0; i < dupes.size(); i++) {
			//System.out.println(dupes.get(i));
			for (int j = 0; j < dupes.get(i).size(); j++) {
				System.out.println(dupes.get(i).get(j));
				System.out.println("i: " + i + ", j: " + j);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		ListTest test = new ListTest();
		test.addRec();
		//test.testing();
		test.findDupes();
		test.print();
	}
	
	
}
