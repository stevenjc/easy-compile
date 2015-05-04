//Author:Steven Colon
//This program allows for a quicker way to compile and run java programs.
//Limitations: Cannot be used to compile a java file that requires arguments from the command line

import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class commandScript{
	//Compiles the file using created process
	public static void compileFile(Process compileProc) throws IOException, InterruptedException{
		int status = compileProc.waitFor();  //Status code to detect any errors
		BufferedReader stdError = new BufferedReader(new InputStreamReader(compileProc.getErrorStream()));
		
		String error = stdError.readLine();
		if(status != 0){  //Error has occurred
			System.out.println("Compile has failed");
			while(error != null){ //Prints out any errors
				System.out.println(error);
			}
			System.exit(status); //Exits program
		}
	}
	//Runs the file using created process
	public static void runFile(Process runProc) throws IOException, InterruptedException{
		int status = runProc.waitFor();
		BufferedReader stdError = new BufferedReader(new InputStreamReader(runProc.getErrorStream()));
			
		String error = stdError.readLine();
		
		if(status != 0){
			System.out.println(error);
			System.exit(status);
		}
	}
	
	public static void main (String[] args) throws IOException, InterruptedException{
		Scanner input = new Scanner(System.in);
		String file = args[0];	//Reads file name from command line
		String command1 = "javac " + file +".java"; //Command to compile java file
		String command2 = "java " + file;	//Command to run java file
		Process compileProc = Runtime.getRuntime().exec(command1); //Process to compile code
		compileFile(compileProc);
		Process runProc = Runtime.getRuntime().exec(command2); //Process to run code
		runFile(runProc);
		
		System.out.println("\nProgram has ended");
	}
}