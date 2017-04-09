import java.util.Scanner;

public class GCPUApp 
{
	public static void main (String Arg[])
	{
		int row = 0;   //Dropping you off in the box canyon
		int col = 3;
		boolean quit = false; //Giving you the option to quit
		Map map = new Map(); //Initializing the map
		Scanner scan = new Scanner(System.in); //Starting up the scanner
		
		System.out.println("Welcome to the Great Cal Poly Underground!"); //Welcome message
		System.out.println(map.rooms[row][col].name); //Where you are
		System.out.println(map.rooms[row][col].rmDescription); //What it looks like
		System.out.println("There's an opening to the " + map.rooms[row][col].getExits()); //Where you can go
		System.out.print("> "); //input box
		String input =""; //initializing input string otherwise the whole thing don't work
		
		while (!input.equals("quit") && quit == false) //so if you type in quit your out of the loop, if you type y afterwards your also out of the loop
		{
			input = scan.nextLine().toLowerCase(); //converts your input into lower-case.
			boolean validInput = false; //Initializing a boolean to check if you entered in one of 5 valid commands.
			boolean quitloop = false; //I put quit in a while loop so i need to initialize that.
			
			//Went with this cause it looks nicer and less confusing. ಠ_ಠ
			if(input.equals("n") ||
			   input.equals("e") ||
			   input.equals("w") ||
			   input.equals("s") ||
			   input.equals("ne")||
			   input.equals("nw")||
			   input.equals("se")||
			   input.equals("sw"))
			{
				validInput = true; //So at the end it doesn't spit out "I don't understand that word 'input'"
				if(map.rooms[row][col].isValidExit(input)) //Is the exit valid?
				{
					if(input.contains("n")) //Sense it already checks that its valid all i need to do is see if it contains what I'm looking for.
						col--;
					if(input.contains("s"))
						col++;
					if(input.contains("e"))
						row++;
					if(input.contains("w"))
						row--;
					System.out.println("You have entered " + map.rooms[row][col].name); //Tells room to ask map what the name of that room is. (Room keeps track of what room your in but doesn't know what that room is).
					System.out.println(map.rooms[row][col].rmDescription); //Same thing but for what the description is.
					System.out.println("There's an opening to the " + map.rooms[row][col].getExits()); //Asks room for the exits.
				} //exit if
				else
					System.out.println("You can't go that way!"); //You gave me an invalid exit
			}//Direction if
			
			if(input.equals("look")) //2nd valid command
			{
				validInput = true;
				System.out.println(map.rooms[row][col].name);
				System.out.println(map.rooms[row][col].rmDescription);
				System.out.println("There's an opening to the " + map.rooms[row][col].getExits());
				System.out.println(map.rooms[row][col].look()); //Asks room to ask map whats inside the room. (Same reasoning as before).
			}
			
			if(input.equals("examine")) //3rd valid command
			{
				validInput = true;
				System.out.println(map.rooms[row][col].contents.examine()); //Asks room to ask map to ask specialartifact to ask artifact to ask map again what the artifact is/does.
				
			}
			
			if(input.equals("touch")) //4th valid command
			{
				validInput = true;
				System.out.println(map.rooms[row][col].contents.touch()); //Asks room to ask ... to switch the artifact state if possible and then output what that did. Usually a whole lot of nothing.
			}
			while (input.equals("quit") && quit == false) //5th valid command
			{
				validInput = true;
				System.out.println("Do you wish to leave the Underground? (Y/N)");
				System.out.print("> ");
				while(input.equals("quit") && quit == false && quitloop == false) //I can't remember why i did this other than it wouldn't work if i did it any of the other ways i tried. It would just spit out an infinite loop of ">"'s and Do you want to quit?
				{
					input = scan.nextLine().toLowerCase();
					if(input.equals("y"))
					{
						quit = true; //Gets you out of all three while loops
						System.out.println("Thank you for visiting the Great CalPoly Underground!");
					}
					if(input.equals("n"))
					{
						quitloop = true; // Gets you out of two while loops. The one that handles quit and the one that asks are you sure?
						input = "";
					}
				} //That while loop with two &&'s
			} //The input = quit loop
			if(!validInput)
				System.out.println("\nI don't understand that word '" + input + "'"); // a /n for line break and spits your input back out at ya
			
			System.out.print("> "); //This is the last thing processed by the first while loop so it makes sense to put the input carrot back
		}//While Loop
		scan.close(); //Closes scanner cause eclipse wont shut up about it ಠ_ಠ
	}//Main
}//GCPUApp
