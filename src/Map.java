import java.util.ArrayList;

class Map {

    //3d array with 2 floors, 4 rows, and 4 columns
    Room rooms[][][] = new Room[2][4][4];
    public ArrayList<Artifact> allArtifacts = new ArrayList<>();

    Map() {
        //Library and its artifacts
        Room library = new Room("Library", "Sunlight is streaming in through the glass windows.", "south", "east",
                false, false);
        Artifact sign = new Artifact("sign", "The sign reads: No food or drinks in the library.", true);
        Artifact book = new Artifact("book", "The cover of the book says: How to Fail Java by Steven Jobs.", true);
        library.addArtifact(sign);
        library.addArtifact(book);
        this.allArtifacts.add(sign);
        this.allArtifacts.add(book);

        //Pointy Building and its artifacts
        Room pointyBuilding = new Room("Pointy Building", "There is a pointy thing on top of this building.",
                "southeast", false, false);
        SpecialArtifact chair = new SpecialArtifact("chair", "This chair is rocking back and forth.", true);
        chair.descriptions("The chair begins flying!", "The chair is floating in the air!", "The chair landed.");
        SpecialArtifact telescope = new SpecialArtifact("telescope", "This telescope is large.", true);
        telescope.descriptions("You looked into the telescope!", "You see beautiful stars.",
                "You stopped watching the stars.");
        pointyBuilding.addArtifact(chair);
        pointyBuilding.addArtifact(telescope);
        this.allArtifacts.add(chair);
        this.allArtifacts.add(telescope);

        //BRIC Gym and its artifacts
        Room bricGym = new Room("BRIC Gym", "There are people exercising and training.", "west",
                "east", "southeast", false, false);
        Artifact dumbbell = new Artifact("dumbbell", "The label on the dumbbell reads: 100lbs.", false);
        Artifact medicineBall = new Artifact("medicine ball", "The medicine ball is very heavy.", true);
        bricGym.addArtifact(dumbbell);
        bricGym.addArtifact(medicineBall);
        this.allArtifacts.add(dumbbell);
        this.allArtifacts.add(medicineBall);

        //Starbucks and its artifacts
        Room starbucks = new Room("Starbucks", "The smell of coffee fills this room.", "west", "northwest",
                false, false);
        Artifact coffeeCup = new Artifact("coffee cup", "The cup puts out a strong coffee smell.", true);
        SpecialArtifact coffeeMaker = new SpecialArtifact("coffee maker",
                "The coffee maker brews a hot cup of coffee.", false);
        coffeeMaker.descriptions("You turned it on!", "Coffee is spilling!", "You turned the coffee maker off.");
        starbucks.addArtifact(coffeeCup);
        starbucks.addArtifact(coffeeMaker);
        this.allArtifacts.add(coffeeCup);
        this.allArtifacts.add(coffeeMaker);

        //Locker Room and its artifacts, has stairs going up
        Room lockerRoom = new Room("Locker Room", "You see men and women inside this locker room.", "north", "south",
                true, false);
        Artifact towel = new Artifact("towel", "The towel feels a little wet.", true);
        Artifact underwear = new Artifact("underwear", "The underwear smells fresh and clean.", true);
        lockerRoom.addArtifact(towel);
        lockerRoom.addArtifact(underwear);
        this.allArtifacts.add(towel);
        this.allArtifacts.add(underwear);

        //Class Room and its artifacts
        Room classRoom = new Room("Classroom", "You see an old table covered with papers near the front of the room.",
                "north", "east", false, false);
        Artifact exam = new Artifact("exam", "CIS 234 Final Exam... The rest appears unreadable due to a lack"
                + " of printer toner", false);
        classRoom.addArtifact(exam);
        this.allArtifacts.add(exam);

        //Game Room and its artifacts, has stairs going up
        Room gameRoom = new Room("Game Room", "This is a room where you can relieve your stress by playing games",
                "east", "west", true, false);
        Artifact poolStick = new Artifact("pool stick", "The pool stick looks warped and uneven.", true);
        SpecialArtifact pinballGame = new SpecialArtifact("pinball game", "There is a slot for coins.", false);
        pinballGame.descriptions("You inserted a coin!", "You are playing pinball!", "You lost the game.");
        SpecialArtifact pacmanGame = new SpecialArtifact("pacman game", "A familiar tune is playing.", false);
        pacmanGame.descriptions("You started the game!", "You are now playing Pacman, trying to avoid the ghosts.",
                "You turned off the game out of frustration.");
        gameRoom.addArtifact(poolStick);
        gameRoom.addArtifact(pinballGame);
        gameRoom.addArtifact(pacmanGame);
        this.allArtifacts.add(poolStick);
        this.allArtifacts.add(pinballGame);
        this.allArtifacts.add(pacmanGame);

        //Counseling Office and its artifacts, has stairs going up
        Room counselingOffice = new Room("Counseling Office", "You see several delirious students frantically screaming",
                "north", true, false);

        //Judicial Affairs Office and its artifacts
        Room judicialAffairsOffice = new Room("Judicial Affairs Office",
                "You hear a loud whipping sound followed by crying.", "north", "east", "west", false, false);
        Artifact whip = new Artifact("whip", "The whip is embedded with an inscription that reads: Cheating Hurts.", true);
        judicialAffairsOffice.addArtifact(whip);
        this.allArtifacts.add(whip);

        //Student Center and its artifacts, has stairs going down
        Room studentCenter = new Room("Student Center", "You see students working hard on their homework.",
                "southwest", "southeast", false, true);
        SpecialArtifact computer = new SpecialArtifact("computer", "The computer is off.", true);
        computer.descriptions("You turned on the computer!", "You see a blue screen.", "You turned off the computer.");
        studentCenter.addArtifact(computer);
        this.allArtifacts.add(computer);

        //Career Center and its artifacts, has stairs going down
        Room careerCenter = new Room("Career Center", "This room is for students who want a job.", "northeast",
                false, true);
        Artifact resume = new Artifact("resume", "The resume seems to be very organized and professional.", true);
        careerCenter.addArtifact(resume);
        this.allArtifacts.add(resume);

        //Horse Center and its artifacts, has stairs going down
        Room horseCenter = new Room("Horse Center", "Horse are preparing for the next race.", "northwest", false,
                true);
        Artifact saddle = new Artifact("saddle", "The saddle looks sturdy and stable.", true);
        horseCenter.addArtifact(saddle);
        this.allArtifacts.add(saddle);

        //Kellogg Mansion and its artifacts
        Room kelloggMansion = new Room("Kellogg Mansion", "This is the former home of William Kellogg.", "east", false,
                false);
        Artifact picture = new Artifact("picture", "The picture bears an inscription that reads:" +
                " W. K. Kellogg. He appears to be holding a box of Corn Flakes.", true);
        kelloggMansion.addArtifact(picture);
        this.allArtifacts.add(picture);

        //Box Canyon and its artifacts
        Room boxCanyon = new Room("Box Canyon", "This looks like the Voorhis Ecological Reserve."
                + " A cavernous opening in the canyon wall.", "north", false, false);
        SpecialArtifact paper = new SpecialArtifact("paper", "The paper appears blank.", true);
        paper.descriptions("The paper begins to glow...", "The paper reads: Welcome to the Great Cal Poly Underground",
                "The writing fades.");
        Artifact billyBronco = new Artifact("Billy Bronco", "A horse-like statue made of valuable diamonds.", false);
        boxCanyon.addArtifact(paper);
        boxCanyon.addArtifact(billyBronco);
        this.allArtifacts.add(paper);
        this.allArtifacts.add(billyBronco);

        //Rose Garden and its artifacts
        Room roseGarden = new Room("Rose Garden", "You are standing in the middle of a beautiful rose garden.",
                "south", "west", "northeast", false, false);
        Artifact gazebo = new Artifact("gazebo", "The small plaque on the structure reads: Enjoy the" +
                " garden!", false);
        roseGarden.addArtifact(gazebo);
        this.allArtifacts.add(gazebo);

        //Los Olivos and its artifacts
        Room losOlivos = new Room("Los Olivos", "The aroma of hamburgers and pizza wafts through the air.",
                "north", "west", "east", false, false);
        Artifact lunch = new Artifact("lunch", "The lunch appears to be a hamburger, french fries, and" +
                " some kind of soda.", true);
        losOlivos.addArtifact(lunch);
        this.allArtifacts.add(lunch);

        //mystery room
        Room mysteryRoom = new Room("Mystery Room", "You will now be warped into a random room!", "N/A", false, false);

        //empty room with no exits
        Room empty = new Room("Empty Room", "This room is empty.", "N/A", false, false);

        //empty room with a west exit
        Room empty1 = new Room("Empty Room", "This room is empty.", "west", false, false);

        //empty room with a south exit
        Room empty2 = new Room("Empty Room", "This room is empty.", "south", false, false);

        //first floor
        rooms[0][0][0] = library;
        rooms[0][0][1] = pointyBuilding;
        rooms[0][0][2] = bricGym;
        rooms[0][0][3] = empty1;
        rooms[0][1][0] = classRoom;
        rooms[0][1][1] = roseGarden;
        rooms[0][1][2] = starbucks;
        rooms[0][1][3] = mysteryRoom;
        rooms[0][2][0] = kelloggMansion;
        rooms[0][2][1] = losOlivos;
        rooms[0][2][2] = lockerRoom;
        rooms[0][2][3] = empty2;
        rooms[0][3][0] = boxCanyon;
        rooms[0][3][1] = gameRoom;
        rooms[0][3][2] = judicialAffairsOffice;
        rooms[0][3][3] = counselingOffice;

        //second floor
        rooms[1][0][0] = empty;
        rooms[1][0][1] = empty;
        rooms[1][0][2] = empty;
        rooms[1][0][3] = empty;
        rooms[1][1][0] = empty;
        rooms[1][1][1] = empty;
        rooms[1][1][2] = empty;
        rooms[1][1][3] = empty;
        rooms[1][2][0] = empty;
        rooms[1][2][1] = empty;
        rooms[1][2][2] = studentCenter;
        rooms[1][2][3] = empty;
        rooms[1][3][0] = empty;
        rooms[1][3][1] = careerCenter;
        rooms[1][3][2] = empty;
        rooms[1][3][3] = horseCenter;
    }

    //method that clears all rooms of artifacts
    public void clear() {
        for (int f = 0; f < 2; f++) {
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    this.rooms[f][r][c].artifacts.clear();
                }
            }
        }
    }
}
