
public class Artifact implements Touchable
{
	String name;
	String description;
	String touch;
	boolean state;
	Artifact (String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	String examine()
	{
		return description;  //Describes the object when its not a special artifact.
	}
	
	public String touch()
	{
		return "Touching the " + name + " doesn't appear to do anything."; //If its not a special artifact this is always true.
	}
	
	boolean state() //Had to put this here see below.
	{
		return state;
	}
	void setState() //Had to put this here if I want GCPUApp to be able to access SpecialArtifacts setState
	{
		//Which is why it does absolutely nothing.
	}
}
