import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class GCPUApp {

    //initialize variables
    private static Scanner scan = new Scanner(System.in);
    private static int floor = 0;
    private static int row = 3;
    private static int col = 0;
    private static Map map = new Map();
    //a boolean that determines whether a room is reintroduced or not
    private static boolean exitExist = true;
    private static boolean quit = false;
    private static boolean stop;
    private static String input, interaction;
    private static Backpack backpack = new Backpack();
    private static boolean fullBackpack;
    private static Artifact take, drop;
    private static ArrayList<String> saveArtifacts = new ArrayList<>();
    static ArrayList<String> saveSpecialArtifacts = new ArrayList<>();
    private static Random rand = new Random();
    static String[] read;

    //main method
    public static void main(String[] args) {

        //out-of-bounds string
        String noWay = "You can't go that way.";

        //save file
        String output;

        //help string
        String help = "List of available commands\n" +
                "--------------------------\n" +
                "---------\n" +
                "movement:\n" +
                "---------\n" +
                "n = go north\n" +
                "s = go south\n" +
                "e = go east\n" +
                "w = go west\n" +
                "ne = go northeast\n" +
                "nw = go northwest\n" +
                "se = go southeast\n" +
                "sw = go southwest\n" +
                "up = go upstairs\n" +
                "down = go downstairs\n" +
                "-------------\n" +
                "interactions:\n" +
                "-------------\n" +
                "look = show description, exits, artifacts\n" +
                "examine (artifact) = examine desired artifact\n" +
                "touch (artifact) = touch desired artifact\n" +
                "take (artifact) = put desired artifact into your backpack\n" +
                "drop (artifact) = drop desired artifact from backpack and take another artifact\n" +
                "-------------\n" +
                "menu options:\n" +
                "-------------\n" +
                "inventory = display all artifacts in your backpack\n" +
                "save = save your progress to desired text file\n" +
                "restore = restore your progress from saved text file\n" +
                "help = list of available commands\n" +
                "quit = leave the Great Cal Poly Underground";

        //begin user dialogue
        System.out.println("Welcome to the Great Cal Poly Underground!\n");

        //loop
        while (!quit) {

            //test if user entered a new room that's not a mystery room
            if (exitExist && !map.rooms[floor][row][col].name.equals("Mystery Room")) {

                //welcome user to room, print out it's description, exits, stairs
                try {
                    System.out.println("You have entered the " + map.rooms[floor][row][col].name + "\n"
                            + map.rooms[floor][row][col].description + "\n" + "There is an opening to the "
                            + map.rooms[floor][row][col].getExits() +
                            map.rooms[floor][row][col].getStairs());
                }

                //catch an exception if user went out of bounds
                catch (ArrayIndexOutOfBoundsException i) {

                    //print out the string contained in noWay
                    System.out.println(noWay);

                    // reset user's original position if user went out of bounds
                    if (row > 2) {
                        row--;
                    }
                    else if (row < 0) {
                        row++;
                    }
                    if (col > 2) {
                        col--;
                    }
                    else if (col < 0) {
                        col++;
                    }
                    if (floor > 1) {
                        floor--;
                    }
                    else if (floor < 0) {
                        floor++;
                    }
                }
            }

            //test if it's a mystery room, warp user to another non-empty random room
            else if (map.rooms[floor][row][col].name.equals("Mystery Room")) {
                System.out.println("You have entered the Mystery Room!" +
                        "\nYou will now be warped to a random room!\n");
                while (map.rooms[floor][row][col].name.equals("Empty Room")
                        || map.rooms[floor][row][col].name.equals("Mystery Room")) {

                    //set random row/col/floor
                    row = rand.nextInt(3);
                    col = rand.nextInt(3);
                    floor = rand.nextInt(2);
                }

                //go back to beginning of loop in order to welcome user to new room
                continue;
            }

            //initialize stop boolean, take user input and change to lowercase
            stop = false;
            System.out.print(">");
            input = scan.nextLine().toLowerCase();
            System.out.println();

            //if user inputs quit, confirm they want to quit
            if (input.equals("quit")) {
                label:
                while (!input.equals("n") || !input.equals("y")) {
                    System.out.print("Do you wish to leave the Underground? (Y/N) >");
                    exitExist = false;
                    input = scan.nextLine().toLowerCase();
                    switch (input) {
                        case "y":
                            quit = true;
                            break label;
                        case "n":
                            System.out.println("You didn't leave the Underground.");
                            break label;
                        default:
                            System.out.println("Please type Y or N.");
                            break;
                    }
                }
                System.out.print("\n");
            }

            //if user wants to go east, move them east if there is an exit there
            else if (input.equals("e")) {
                input = "east";
                if (map.rooms[floor][row][col].isValidExit(input)) {
                    col++;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                    exitExist = false;
                }
            }

            //if user wants to go west, move them west if there is an exit there
            else if (input.equals("w")) {
                input = "west";
                if (map.rooms[floor][row][col].isValidExit(input)) {
                    col--;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                    exitExist = false;
                }
            }

            //if user wants to go west, move them west if there is an exit there
            else if (input.equals("n")) {
                input = "north";
                if (map.rooms[floor][row][col].isValidExit(input)) {
                    row--;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                    exitExist = false;
                }
            }

            //if user wants to go south, move user south if there is an exit
            else if (input.equals("s")) {
                input = "south";
                if (map.rooms[floor][row][col].isValidExit(input)) {
                    row++;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                    exitExist = false;
                }
            }

            //if user wants to go northwest, move user northwest if there is an exit
            else if (input.equals("nw")) {
                input = "northwest";
                if (map.rooms[floor][row][col].isValidExit(input)) {
                    row--;
                    col--;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                    exitExist = false;
                }
            }

            //if user wants to go southwest, move user southwest if there is an exit
            else if (input.equals("sw")) {
                input = "southwest";
                if (map.rooms[floor][row][col].isValidExit(input)) {
                    row++;
                    col--;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                    exitExist = false;
                }
            }

            //if user wants to go northeast, move user northeast if there is an exit
            else if (input.equals("ne")) {
                input = "northeast";
                if (map.rooms[floor][row][col].isValidExit(input)) {
                    row--;
                    col++;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                    exitExist = false;
                }
            }

            //if user wants to go southeast, move user southeast if there is an exit
            else if (input.equals("se")) {
                input = "southeast";
                if (map.rooms[floor][row][col].isValidExit(input)) {
                    row++;
                    col++;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                    exitExist = false;
                }
            }

            //move user upstairs if there are stairs going up in the room
            else if (input.equals("up")) {
                if (map.rooms[floor][row][col].stairsUp) {
                    System.out.println("You are going up...");
                    floor++;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                }
            }

            //move user downstairs if there are stairs going down
            else if (input.equals("down")) {
                if (map.rooms[floor][row][col].stairsDown) {
                    System.out.println("You are going down...");
                    floor--;
                    exitExist = true;
                }
                else {
                    System.out.println(noWay);
                }
            }

            //if user looks, call the look method of the room
            else if (input.equals("look")) {
                map.rooms[floor][row][col].look();
                exitExist = false;
            }

            //if user types "examine" or "examine object" call the interaction method from this class
            else if (input.length() >= 7 && input.substring(0, 7).equals("examine")) {
                interaction(8, "Examine", "examining");
            }

            //if user types "touch" or "touch object" call the interaction method from this class
            else if (input.length() >= 5 && input.substring(0, 5).equals("touch")) {
                interaction(6, "Touch", "touching");
            }

            //if user types "take" or "take object" call the interaction method from this class
            else if (input.length() >= 4 && input.substring(0, 4).equals("take")) {
                interaction(5, "Take", "taking");
            }

            //if user types "drop" or "drop object" call the interaction method from this class
            else if (input.length() >= 4 && input.substring(0, 4).equals("drop")) {
                if (!backpack.empty()) {
                    interaction(5, "Drop", "dropping");
                }
                else if (backpack.empty()) {
                    System.out.println("Your backpack is empty!");
                    exitExist = false;
                }
            }

            //if user wants to check backpack inventory
            else if (input.equals("inventory")) {
                backpack.inventory();
                exitExist = false;
            }

            //ask user file they want to save
            else if (input.equals("save")) {
                System.out.print("Enter file to save >");
                output = scan.nextLine();
                Save(output, floor, row, col, backpack.inventorySave());
                exitExist = false;
            }

            //ask user which file they would like to restore
            else if (input.equals("restore")) {
                Restore();
            }

            //provide list of commands
            else if (input.equals("help")) {
                System.out.println(help);
                exitExist = false;
            }

            //invalid inputs are not accepted
            else {
                System.out.println("I don't understand the word " + "\"" + input + "\".");
                exitExist = false;
            }
        }

        //print this out if user quits (if quit = true)
        System.out.print("Thank you for visiting the Great Cal Poly Underground.");
    }

    //private method interaction, with integer and 2 strings as arguments
    private static void interaction(int substring, String type1, String type2) {

        //boolean to check whether an artifact is movable
        boolean movable = true;

        //check if room is not empty by calling emptyCheck method of room
        if (map.rooms[floor][row][col].emptyCheck() && !(type1.equals("Drop"))) {
            System.out.println("There is nothing to " + type1.toLowerCase() + "...");
        }

        else {

            //try to take an object user typed as the input
            try {
                input = input.substring(substring);
            }

            //if user didn't enter an object, list objects in room and ask user which one they want
            catch (StringIndexOutOfBoundsException e) {
                System.out.println(type1 + " what?");
                System.out.println("Type one of the following objects or \"stop\" to stop " + type2 + ":");

                //if user didn't type drop print out artifacts in room
                if (!type1.equals("Drop")) {
                    map.rooms[floor][row][col].printArtifacts();
                }

                //if user did type drop print backpack inventory
                else {
                    backpack.printInventory();
                }

                //ask user which item they want to interact with
                System.out.print(">");
                input = scan.nextLine();
                System.out.println();

                //if user wants to stop interacting
                if (input.equalsIgnoreCase("stop")) {
                    stop = true;
                }
            }

            //let user know they stopped interacting, if they typed stop
            if (stop) {
                System.out.println("You have stopped " + type2 + ".");
            }

            //touch, examine, touch, or drop will do different things
            else {
                switch (type1) {
                    case "Touch":
                        take = null;
                        drop = null;
                        interaction = map.rooms[floor][row][col].touch(input);
                        break;

                    case "Examine":
                        take = null;
                        drop = null;
                        interaction = map.rooms[floor][row][col].examine(input);
                        break;

                    case "Take":
                        drop = null;
                        interaction = null;
                        take = map.rooms[floor][row][col].take(input);

                        if (take != null) {
                            if (take.movable) {
                                map.rooms[floor][row][col].artifacts.remove(take);
                                fullBackpack = backpack.setArtifact(take);
                            }
                            else {
                                take = null;
                                movable = false;
                            }
                        }
                        break;

                    case "Drop":
                        /*boolean empty = true;

                        if (!map.rooms[floor][row][col].artifacts.isEmpty()) {
                                empty = false;
                            }

                        if (backpack.empty()) {
                                empty = true;
                            }*/

                        take = null;//if you want to swap, get rid of this line
                        interaction = null;
                        drop = backpack.getArtifact(input);

                        if (drop != null) {
                                backpack.removeArtifact(drop);
                                map.rooms[floor][row][col].drop(drop);
                            }

                        /*if (!empty && !(drop == null)) {
                            do {
                                take = map.rooms[floor][row][col].artifacts.get
                                        (rand.nextInt(map.rooms[floor][row][col].artifacts.size()));
                            }
                            while (take == drop);
                            if (take != null) {
                                backpack.setArtifact(take);
                                map.rooms[floor][row][col].artifacts.remove(take);
                            }
                        }

                        else {
                            take = null;
                        }*/
                        break;
                }

                //perform actions based on user's desired command
                if (interaction != null) {
                    System.out.println(interaction);
                }

                else if (drop != null) {
                    System.out.println("[" + drop.name + "] dropped");

                    /*if (take != null) {
                            System.out.println("[" + take.name + "] taken");
                    }*/
                }

                //if object is movable
                else if (take != null && movable) {
                    if (fullBackpack) {
                        System.out.println("[" + take.name + "] taken");
                    }
                    else {
                        System.out.println("Your backpack is full!");
                    }
                }

                //if object is immovable
                else if (take == null && !movable) {
                    System.out.println("It can't be moved... nice try.");
                }

                //if object isn't in backpack, let user know
                else if (type1.equals("Drop")) {
                    System.out.println("There is no " + input + " in your backpack.");
                }

                //if object isn't there, let user know
                else {
                    System.out.println("There is no " + input + " here.");
                }
            }
        }

        exitExist = false;
    }

    //method to save all artifacts, special artifacts and their states into ArrayLists
    private static void saveArtifacts() {
        for (int f = 0; f < 2; f++) {
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    for (Artifact a : map.rooms[f][r][c].artifacts) {
                        if (!saveArtifacts.contains(a.name + "," + f + "," + r + "," + c)) {
                            saveArtifacts.add(a.name + "," + f + "," + r + "," + c);
                        }
                        if (a.getClass() == SpecialArtifact.class) {
                            if (a.state) {
                                saveSpecialArtifacts.add(a.name + "," + "on");
                            }
                            else if (!a.state) {
                                saveSpecialArtifacts.add(a.name + "," + "off");
                            }
                        }
                    }
                    backpack.state();
                }
            }
        }
    }

    //method to save all progress onto a txt file
    private static void Save(String out, int floor, int row, int col, String inventory) {

        String[] checkName = out.split("\\.");

        while (!checkName[checkName.length - 1].equalsIgnoreCase("txt")) {
            System.out.print("Please enter a .txt file >");
            out = scan.nextLine();
            checkName = out.split("\\.");
        }

        File output = new File(out);
        FileWriter writer;

        boolean overwrite = true;
        boolean overWriteLoop = true;
        String overWriteResponse;

        if (output.exists()) {
            System.out.print("The file already exists. Overwrite (Y/N)? >");
            while (overWriteLoop) {
                overWriteResponse = scan.nextLine();

                if (overWriteResponse.equalsIgnoreCase("Y")) {
                    overwrite = true;
                    overWriteLoop = false;
                }
                else if (overWriteResponse.equalsIgnoreCase("N")) {
                    overwrite = false;
                    overWriteLoop = false;
                }
                else {
                    System.out.print("Please enter Y or N >");
                }
            }
        }

        //if user wanted to overwrite, or if filename is free to be used
        if (overwrite) {
            try {
                writer = new FileWriter(output);
                BufferedWriter buf = new BufferedWriter(writer);

                buf.write("StartLocation=" + floor + "," + row + "," + col);
                buf.newLine();

                saveArtifacts();

                for (String saveArtifact : saveArtifacts) {
                    buf.write("Artifact=" + saveArtifact);
                    buf.newLine();
                }

                buf.write("Inventory=" + inventory);
                buf.newLine();

                for (String saveSpecialArtifact : saveSpecialArtifacts) {
                    buf.write("SpecialArtifact=" + saveSpecialArtifact);
                    buf.newLine();
                }

                buf.flush();
                buf.close();

                System.out.println("Save complete.");
            }
            catch (IOException e) {
                System.out.println("There was an error saving the file.");
            }
        }
        else if (!overwrite) {
            System.out.println("Your file wasn't overwritten.");
        }
    }

    //method to restore all progress by reading text file
    private static void Restore() {
        listFiles();
        System.out.print(">");
        String restore = scan.nextLine();
        System.out.println();

        map.clear();
        backpack.clear();

        try {
            FileReader reader = new FileReader(restore);
            BufferedReader buf = new BufferedReader(reader);
            String line;

            while ((line = buf.readLine()) != null) {
                read = line.split("=|,");
                switch (read[0]) {
                    case "StartLocation":
                        floor = Integer.parseInt(read[1]);
                        row = Integer.parseInt(read[2]);
                        col = Integer.parseInt(read[3]);
                        break;

                    case "Artifact":
                        for (Artifact i : map.allArtifacts) {
                            if (i.name.equals(read[1])) {
                                map.rooms[Integer.parseInt(read[2])]
                                        [Integer.parseInt(read[3])]
                                        [Integer.parseInt(read[4])].artifacts.add(i);
                                break;
                            }
                        }
                        break;

                    case "Inventory":
                        if (!read[1].equals("empty")) {
                            for (Artifact i : map.allArtifacts) {
                                for (int e = 1; e < read.length; e++) {
                                    if (i.name.equals(read[e])) {
                                        backpack.setArtifact(i);
                                    }
                                }
                            }
                        }
                        break;

                    case "SpecialArtifact":
                        for (int f = 0; f < 2; f++) {
                            for (int r = 0; r < 4; r++) {
                                for (int c = 0; c < 4; c++) {

                                    for (Artifact i : map.rooms[f][r][c].artifacts) {
                                        if (i.name.equals(read[1])) {
                                            if (read[2].equals("on")) {
                                                i.state = true;
                                                break;
                                            }
                                            else if (read[2].equals("off")) {
                                                i.state = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        backpack.backpackState();
                        break;
                }
            }
            buf.close();
            exitExist = true;
        }
        catch (FileNotFoundException e) {
            System.out.println("The file you typed in wasn't found.");
            exitExist = false;
        }
        catch (IOException e) {
            System.out.println("There was an error.");
            exitExist = false;
        }

    }

    //method that lists all the txt files available in the default working directory where txt files are written to
    private static void listFiles() {
        String workingDir = System.getProperty("user.dir");
        File dir = new File(workingDir);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        if ((files != null ? files.length : 0) != 0) {
            System.out.println("Which file would you like to restore (or type the location of your save file):");
            for (File i : files) {
                System.out.println("-" + i.getName());
            }
        }

        else {
            System.out.println("No save file found in default location. Please enter the location of the save file:");
        }
    }
}

//do you want drop to swap