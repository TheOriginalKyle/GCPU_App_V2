import java.lang.reflect.Array;
import java.util.*;

public class Backpack 
{
	private ArrayList <Artifact> contents = new ArrayList <Artifact> ();
	private ArrayList <Artifact> tempContents = new ArrayList <Artifact>(); //I made another ArrayList in case you hit the limit but don't want to put multiple items in a room.
	public final int limit = 3;
	
	public boolean setArtifact(Artifact anyArtifact) //Puts the artifact in your backpack
	{
		if(contents.size() < limit)
		{
			return contents.add(anyArtifact);
		}
		else
			return false;
		
	}
	public boolean setArtifactTemp(Artifact anyArtifact) //Puts the artifact in temporary storage
	{
		return tempContents.add(anyArtifact);
	}
	
	public Artifact removeArtifact(int index) //Removes it.
	{
		return contents.remove(index);
	}
	public Artifact removeTempArtifact(int index) //Removes it.
	{
		return tempContents.remove(index);
	}
	
	public String getInventory() //Tells you what's in your inventory. I kept it as just the name to make making a save file easier.
	{
		String message = "";
		for (int index=0; index<contents.size(); index++)
		{
			message += contents.get(index).name;
			if(contents.size() > 1)
				message += ",";
		}
		return message;
	}
}
