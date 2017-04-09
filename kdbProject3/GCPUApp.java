import java.util.Scanner;
import java.io.*;

public class GCPUApp 
{
	int row;
	int col;
	public static void main (String Arg[])
	{
		int row = 0;   //Dropping you off in the box canyon
		int col = 3;
		boolean quit = false; //Giving you the option to quit
		boolean save = false; //Allowing you to save
		boolean validInput = false; //Initializing a boolean to check if you entered in one of 10 valid commands.
		boolean quitloop = false; //I put quit in a while loop so i need to initialize that.
		boolean addArtifact = false;
		Map map = new Map(); //Initializing the map
		Backpack pack = new Backpack(); //Initializing the backpack
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
			
			if(input.equals("take")) //6th valid command
			{
				validInput = true;
				if(pack.setArtifact(map.rooms[row][col].contents) == true)
				{
					System.out.println("You grab the " + map.rooms[row][col].contents.name + ".");
					map.rooms[row][col].contents = null;
					input = "";
				}
				else
				{
					System.out.println("Your pack is full you need to drop something first.");
					input = "";
				}
			} //Take if
			
			if(input.equals("drop")) //7th valid command
			{
				validInput = true;
				if(map.rooms[row][col].contents != null)
				{ //Can't believe this took me two hours ಠ_ಠ 
					System.out.println("You pick up the " + map.rooms[row][col].contents.name + ".");
					pack.setArtifactTemp(pack.removeArtifact(0));  //Sets the artifact you don't want to temporary storage (Which has no limits)
					pack.setArtifact(map.rooms[row][col].contents); //Puts the artifact you DO want into your backpack
					map.rooms[row][col].contents = null; //Sets the room to null for no particular reason other than that it seamed like a good idea
					map.rooms[row][col].contents = pack.removeTempArtifact(0); //Puts the artifact you don't want back on the map.
					System.out.println("You drop the " + map.rooms[row][col].contents.name + ".");
				}
				if(map.rooms[row][col].contents == null)
				{
					map.rooms[row][col].contents = pack.removeArtifact(0);
					System.out.println("You drop the " + map.rooms[row][col].contents.name + ".");
				}
			} //Drop if
			
			if(input.equals("inventory")) //8th valid command
			{
				validInput = true;
				System.out.println(pack.getInventory()); //Shows you your inventory
			}//Inventory if
			
			if(input.equals("help")) //9th valid command
			{
				validInput = true;
				System.out.println("You can enter a direction such as n,s,e,w,nw..."); //What commands exist in a nice soothing customer service voice.
				System.out.println("Or one of the following commands:"); //The grammar is probably awful.
				System.out.println("'Look' To see whats going on around you.");
				System.out.println("'Examine' Allows you to study an item more carefully.");
				System.out.println("'Touch' Has your character touch an item.");
				System.out.println("'Take' Grabs an item and puts it your backpack.");
				System.out.println("'Drop' Drops an item from your backpack.");
				System.out.println("'Inventory' Displays your items.");
				System.out.println("'Save' Saves your game.");
				System.out.println("'Restore' Restores an old save.");
				System.out.println("'Quit' Allows you to exit the game safely.");
			} //Help if
			
			while (input.equals("save") || save == true) //10th Valid command
			{
				validInput = true;
				save = true; //Keeping you in the loop
				try
				{
					String fileName = "/home/kyle/GCPU2.txt"; //I thought about putting this in a more comical directory but decided against it.
					System.out.println("Where would you like it saved?"); //Soothing customer service voice.
					System.out.print("> ");
					input = scan.nextLine();
					fileName = input;
					
					File file = new File(fileName); //Creates the user designated file
					FileWriter fw = new FileWriter(file);
					BufferedWriter buffer = new BufferedWriter (fw); //Prepares it for the horrors ahead.
					PrintWriter pw = new PrintWriter(buffer);
					
					// Save starting location
					pw.println("StartLocation" + "=" + row + "," + col);
					
					// Save room contents
					for(int i = 0; i <= 3; i++)
					{
						if (map.rooms[0][i].contents != null)
							pw.println("Artifact" + "=" + map.rooms[0][i].contents.name + "," + 0 + "," + i);
						if (i < 3 && map.rooms[1][i].contents != null)
							pw.println("Artifact" + "=" + map.rooms[1][i].contents.name + "," + 1 + "," + i);
						if (map.rooms[2][i].contents != null)
							pw.println("Artifact" + "=" + map.rooms[2][i].contents.name + "," + 2 + "," + i);
						if (i < 2 && map.rooms[3][i].contents != null)
							pw.println("Artifact" + "=" + map.rooms[3][i].contents.name + "," + 3 + "," + i);
					}
					
					pw.println("Inventory=" + pack.getInventory()); //Save contents of backpack
					
					// Save states of special artifacts. I chose to have it only save the state if its on sense they're off by default. ¯\_(ツ)_/¯ 
					for(int i = 0; i <=3; i++) //The states are also kept as booleans because I'm lazy.
					{
						if (map.rooms[0][i].contents != null && map.rooms[0][i].contents.state() != false)
							pw.println("SpecialArtifact" + "=" + map.rooms[0][i].contents.name + "," + map.rooms[0][i].contents.state());
						if (i < 3 && map.rooms[1][i].contents != null && map.rooms[1][i].contents.state() != false)
							pw.println("SpecialArtifact" + "=" + map.rooms[1][i].contents.name + "," + map.rooms[1][i].contents.state()); //Stupid i < 3 kept me up all night, NullPointers are THE worst ಠ_ಠ 
						if (map.rooms[2][i].contents != null && map.rooms[2][i].contents.state() != false)
							pw.println("SpecialArtifact" + "=" + map.rooms[2][i].contents.name + "," + map.rooms[2][i].contents.state());
						if (i < 2 && map.rooms[3][i].contents != null && map.rooms[3][i].contents.state() != false)
							pw.println("SpecialArtifact" + "=" + map.rooms[3][i].contents.name + "," + map.rooms[3][i].contents.state());
					}

					// Close buffer and display confirmation message
					buffer.close();      			
					System.out.println("Adventure saved.");
					save = false; //pushing you out of the loop
					input = "";
					
				}

				catch (Exception e)
				{
					System.out.println(e.getMessage());
					System.out.println(e.getClass().getName()); //I'm leaving this here in case you get 'null' as the message. For example a Null Pointer Exception just shows up as null and that could mean literally anything.
				}												//Seriously so far I've had 5 completely different reasons for it being null, only one of which was an actual NullPointer Exception. ಠ_ಠ 
			} //Save if
			
			if (input.equals("restore")) //11th Valid command
			{
				validInput = true;
				try
	    		{
	   			String fileName = "/home/kyle/GCPU2.txt"; //How come it doesn't accept ~ as a shortcut to /home/user/ ?
	   			System.out.println("Where is your save?");
				System.out.print("> ");
				input = scan.nextLine();
				fileName = input;
				
	   			File file = new File(fileName);
	    		FileReader reader = new FileReader(file);
	    		BufferedReader buffer = new BufferedReader(reader);
	    			
	    		// Erase the map of existing artifacts
	    		map.erase();
	    			
	    		// Begin reading text file
	    		String fileInput = buffer.readLine();
	    		while (fileInput != null)
	    		{
	    			String[] data = fileInput.split("=|,");
	    			String key = data[0];
	    			if (key.equals("StartLocation"))
	    			{
	    				row = Integer.parseInt(data[1]);
	    				col = Integer.parseInt(data[2]);
	    			}
	    			if (key.equals("Artifact"))
	    			{
					// Restore artifact location
	    				map.name = data[1];
	    				for(int i = 0; i <= map.name.length(); i++) //Not entirely sure why I had to do this.
	    				{
	    					int artifactRow = Integer.parseInt(data[2]);
		    				int artifactCol = Integer.parseInt(data[3]);
		    				map.rooms[artifactRow][artifactCol].contents = map.restore();
	    				}
	    			} 
	    			else
	    			if (key.equals("Inventory"))
	    			{
	    				for(int i = 1; i <= data.length - 1; i++) //Or this.
	    				{
	    				map.name = data[i];
	    				pack.setArtifact(map.restore());
	    				}
	    				// Restore backpack contents
	    			}
	    			else
	    			if (key.equals("SpecialArtifact"))
	    			{
	    				map.name = data[1];
	    				System.out.println(data[1]); //Why is it that I didn't have to write a for loop here? I had to for the other two?
	    				map.restore().touch(); //I cheated and just asked touch. Sidenote this should allow for the user to change true to false in the savefile.
	    				// Restore states of special artifacts
	    			}
	    			fileInput = buffer.readLine();
	    			}
	    			buffer.close();
	    			reader.close();	
	    		}
	    		catch (Exception e)
	    		{
	    			System.out.println(e.getMessage());
	    			System.out.println(e.getClass());
	    		}
			} //Restore If
			
			if(!validInput)
				System.out.println("\nI don't understand that word '" + input + "'"); // a /n for line break and spits your input back out at ya
			if(!input.equals("quit") && quit == false)
				System.out.print("> "); 
		}//While Loop
		scan.close(); //Closes scanner cause eclipse wont shut up about it ಠ_ಠ 
	}//Main
}//GCPUApp
