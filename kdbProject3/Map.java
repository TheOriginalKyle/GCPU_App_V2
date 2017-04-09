
public class Map 
{
	//Instance Variable
	Room rooms[][] = new Room[4][4];
	Artifact sign, exam, picture, paper, coffee, gazebo, lunch, bussiness_plan, report, beer, candle, beer_can; //You can skip to the bottom if you're looking for changes since project 2.
	String name;
	int artifactRow;
	int artifactCol;
	//Constructor
	Map()
	{
		//I organized this by columns in the array and columns are labeled in my provided map.
		//Column  0
		Room library = new Room("University Library", new String[]{"s","e"}, "There's a lot of books in here.");
		Room classRm = new Room("Class Room", new String[]{"n","e"}, "You see an old table covered with papers near the front of the room.");
		Room kellogg = new Room("Kellogg Mansion", new String[]{"e"}, "This is the former home of William Kellogg.");
		Room canyon = new Room("Box Canyon", new String[]{"n"},"This looks like the Voorhis Ecological Reserve.\n"
				+ "A cavernous opening in the canyon wall lies just ahead of you.");
		
		//Column 1
		Room starbucks = new Room("Starbucks", new String[]{"w", "e", "se"}, "You see a line of college students and a lot of coffee.");
		Room roseGarden = new Room("Rose Garden", new String[]{"s", "w"}, "You are standing in the middle of a beautiful rose garden.");
		Room losOlivos = new Room("Los Olivos", new String[]{"n", "w"}, "The aroma of hamburgers and pizza wafts through the air.");
		
		//Column 2
		Room engineering = new Room("Engineering Building", new String[]{"s", "w", "e", "se"}, "Who would've thought stress had a smell.");
		Room bussiness = new Room("Bussiness Building", new String[]{"n", "nw", "ne", "s"}, "A bunch of enterprising students attempt to sell you a box of rocks.");
		Room brew_works = new Room("Brew Works", new String[]{"n", "s", "ne"}, "You see a lot of students and a lot of beer.");
		Room village = new Room("Village", new String[]{"n"}, "A Resident Advisor warns you about the dangers of candles.");
		
		//Column 3
		Room dorms = new Room("Dorms", new String[]{"s", "w", "sw"}, "Its oddly quiet in here. The clock says its before noon.");
		Room cla = new Room("CLA", new String[]{"w"}, "You see a long pointed building.");
		
		
		//Artifacts are also handled this way but I put the contents of the room underneath the artifact thats present in it. (Confusing yes them time savings tho.)
		//Column 0
		sign = new Artifact("Sign", "The sign reads: No food or drinks in the library");
		library.contents = sign;
		exam = new Artifact("Exam", "CIS 234 Final Exam... The rest appears unreadable due to a lack of printer toner.");
		classRm.contents = exam;
		picture = new Artifact("Picture", "The picture bears an inscription that reads:\n"
													+ "'W. K. Kellogg'. He appears to be holding a box of Corn Flakes.");
		kellogg.contents = picture;
		paper = new SpecialArtifact("Paper", "The paper appears blank", "The paper reads: Welcome to the Great Cal Poly Underground!", false, 
													"The paper begins to glow...", "The writing fades.");
		canyon.contents = paper;
		
		//Column 1
		coffee = new Artifact("Coffee", "A small container of energy usually used for projects.");
		starbucks.contents = coffee;
		gazebo = new Artifact("Gazebo", "The small plaque on the structure reads: Enjoy the garden!");
		roseGarden.contents = gazebo;
		lunch = new Artifact("Lunch", "The lunch appears to be a hamburger, french fries, and some kind of soda.");
		losOlivos.contents = lunch;
		
		//Column 2
		report = new SpecialArtifact("Report", "Structural integrety of the CLA... The rest appears unreadable due to a lack of interest",
													"Detailed instructions on how to destroy the CLA building.", false, "New information is revealed", "The writing fades");
		engineering.contents = report;
		bussiness_plan = new Artifact("Bussiness Plan", "It appears to be a plan that if executed incorrectly would result in war between the US and Peru.");
		bussiness.contents = bussiness_plan;
		beer = new SpecialArtifact("Beer", "Beer is the nectar of the gods.", "You begin to wonder how your ex is doing.", false, 
													"You drink the beer...", "The beer is empty.");
		brew_works.contents = beer;
		candle = new SpecialArtifact("Candle", "Candles aren't allowed in the village.", "The candle is lit. And the Displinary coordinater wants to speak with you.", false, 
														"You light the candle.", "You blow out the candle.");
		village.contents = candle;
		
		//Column 3
		beer_can = new Artifact("Beer can", "Its just an empty beer can.");
		dorms.contents = beer_can;
		
		//Room map in array form.
		rooms[0][0] = library;
		rooms[0][1] = classRm;
		rooms[0][2] = kellogg;
		rooms[0][3] = canyon;
		
		rooms[1][0] = starbucks;
		rooms[1][1] = roseGarden;
		rooms[1][2] = losOlivos;
		
		rooms[2][0] = engineering;
		rooms[2][1] = bussiness;
		rooms[2][2] = brew_works;
		rooms[2][3] = village;
		
		rooms[3][0] = dorms;
		rooms[3][1] = cla;
	}
	
	void erase() //Erases everything by setting it all to null
    {
		for(int i = 0; i < 4; i++)
		{
			rooms[0][i].contents = null;
			if(i < 3)
				rooms[1][i].contents = null;
			rooms[2][i].contents = null;
			if(i < 2)
				rooms[3][i].contents = null;
		}
    }
	
	Artifact restore()//Doesn't actually restore anything just converts the strings to Artifacts. Everything else is handled by GCPUApp
	{
			if (name.equalsIgnoreCase("sign")) //I have no idea why I had to do this. Like java just freaked out and kept using the last else. Which is bad sense thats null and null breaks things.
			{
				return sign;
			}
			else 
			{
				if(name.equalsIgnoreCase("exam"))
				{
					return exam;
				}
				else
				{
					if (name.equalsIgnoreCase("picture"))
					{
						return picture;
					}
					else
					{
						if (name.equalsIgnoreCase("Paper"))
							return paper;
						else
						{
							if (name.equalsIgnoreCase("coffee"))
							{
								return coffee;
							}
							else
							{
								if (name.equalsIgnoreCase("gazebo"))
								{
									return gazebo;
								}
								else
								{
									if (name.equalsIgnoreCase("lunch"))
									{
										return lunch;
									}
									else
									{
										if (name.equalsIgnoreCase("bussiness plan"))
										{
											return bussiness_plan;
										}
										else
										{
											if (name.equalsIgnoreCase("report"))
											{
												return report;
											}
											else
											{
												if (name.equalsIgnoreCase("beer"))
												{
													return beer;
												}
												else
												{
													if (name.equalsIgnoreCase("candle"))
													{
														return candle;
													}
													else
													{
														if (name.equalsIgnoreCase("beer can"))
														{
															return beer_can;
														}
														else
														{
															return null;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
	}
}
