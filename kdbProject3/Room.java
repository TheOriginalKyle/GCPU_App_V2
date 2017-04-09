
public class Room 
{
	//Instance variable
	String name;
	String [] exits;
	String rmDescription;
	Artifact contents;
	boolean state; //I forgot why this is here pretty sure it doesn't need to be here. But it was while I was trying to get examine to work right.
	
	Room (String name) //Approach 2
	{
		this.name = name;
	}
	
	Room(String name, String[] exits, String rmDescription) //Approach 3
	{
		this (name); //Uses approach 2 instead of repeating "this.name = name;"
		this.exits = exits;
		this.rmDescription = rmDescription;
		
	}
	
	String getExits()
	{
		String getExit = "";
		for(int index = 0; index < exits.length; index++) //Goes through the list of exits and adds to string getExit the non abbreviated version.
		{
			if (exits[index].equals("n"))
				getExit = getExit + ("North, "); //Could've added an if to remove commas but...
			if(exits[index].equals("e"))
				getExit = getExit + ("East, ");
			if(exits[index].equals("w"))
				getExit = getExit + ("West, ");
			if(exits[index].equals("s"))
				getExit = getExit + ("South, ");
			if(exits[index].equals("nw"))
				getExit = getExit + ("Northwest, ");
			if(exits[index].equals("ne"))
				getExit = getExit + ("Northeast, ");
			if(exits[index].equals("sw"))
				getExit = getExit + ("Southwest, ");
			if(exits[index].equals("se"))
				getExit = getExit + ("Southeast, ");
				
		}
		return getExit; //returns a non-abbreviated string
	}
	
	String look()
	{
		if(contents == null) //Checks if there's nothing here which is true for at least one room.
			System.out.print ("There's nothing here");
		if(contents != null)
			System.out.print("There is a " + contents.name + " here."); //Otherwise it lists the room contents
		return "";
	}
	
	
	boolean isValidExit(String requestedExit)
	{
		boolean result = false;
		int index = 0;
		while (index < exits.length && result == false) //Just runs through and checks your input if it is a valid exit
		{
			if (exits[index].equals(requestedExit))
				result = true;
			else
				index++;
		}
		
		
		return result;
	}
}
