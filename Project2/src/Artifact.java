
public class Artifact 
{
	String name;
	String description;
	String touch;
	boolean state; //Not sure why this is here but it certainly doesn't hurt. ¯\_(ツ)_/¯
	Artifact (String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	String examine()
	{
		return description;  //Describes the object when its not a special artifact.
	}
	
	String touch()
	{
		return "Touching the " + name + " doesn't appear to do anything."; //If its not a special artifact this is always true.
	}
}
