
public class SpecialArtifact extends Artifact
{
	String specialDescription;
	String description;
	String examineResult;
	String touchOn;
	String touchOff;
	boolean state;
	boolean setState;
	
	SpecialArtifact(String name, String description, String specialDescription, boolean state, String touchOn, String touchOff) //Added a boolean here so you can easily tell the state. All of them are false be default.
	{
		super(name, specialDescription);
		this.specialDescription = specialDescription;
		this.description = description; //Took me the longest time to figure out that examine() won't work without this one stupid line. ಠ_ಠ
		this.state = state;
		this.touchOn = touchOn;
		this.touchOff = touchOff;
	}
	void setState() //Just flips the state.
	{
		state = !state;
	}
	
	boolean state() //Tells you what the state is.
	{
		return state;
	}
	
	String examine()
	{
		
		if(state == false) //Asking what the touch state is (false = off)
			examineResult = description; //It sets examineResult string equal to the description as if it was a regular artifact.
		if(state == true)
			examineResult = specialDescription;
		return examineResult; //returns its results
	}
	
	public String touch()
	{
		String touch = ""; //Not sure why this is here examine() sure worked without it...
		
		if(state == false) //If touch is currently off you'll probably want the it turned on message. Its kind of counter-intuitive. :/
			touch = touchOn;
		if(state == true)
			touch = touchOff;
		state = !state; //Flips the state to the opposite of whatever it was. Its also why the if's are counter-intuitive. 
		return touch; //Returns the corresponding message.
	}
}
