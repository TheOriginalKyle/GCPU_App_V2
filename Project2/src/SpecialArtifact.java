
public class SpecialArtifact extends Artifact
{
	String specialDescription;
	String description;
	String examineResult;
	String touchOn;
	String touchOff;
	boolean state = false; //Again not sure why this is here but it certainly doesn't hurt.
	
	SpecialArtifact(String name, String description, String specialDescription, String touchOn, String touchOff)
	{
		super(name, specialDescription);
		this.specialDescription = specialDescription;
		this.description = description; //Took me the longest time to figure out that examine() won't work without this one stupid line. ಠ_ಠ
		this.touchOn = touchOn;
		this.touchOff = touchOff;
	}
	String examine()
	{
		
		if(this.state == false) //Asking what the touch state is (false = off)
			examineResult = description; //It sets examineResult string equal to the description as if it was a regular artifact.
		if(this.state == true)
			examineResult = specialDescription;
		return examineResult; //returns its results
	}
	String touch()
	{
		String touch = ""; //Not sure why this is here examine() sure worked without it...
		
		if(this.state == false) //If touch is currently off you'll probably want the it turned on message. Its kind of counter-intuitive. :/
			touch = touchOn;
		if(this.state == true)
			touch = touchOff;
		this.state = !state; //Flips the state to the opposite of whatever it was. Its also why the if's are counter-intuitive. 
		return touch; //Returns the corresponding message.
	}
}
