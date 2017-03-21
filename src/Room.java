import java.util.ArrayList;

class Room {

    //instance variables
    String name;
    String description;
    private String exit1, exit2, exit3, examine, touch;
    private Artifact take;
    //up and down stairs
    boolean stairsUp, stairsDown;
    //Arraylist for multiple artifacts
    ArrayList<Artifact> artifacts = new ArrayList<>();

    //constructor for room with one exit
    Room(String name, String description, String exit1, boolean stairsUp, boolean stairsDown) {
        this.name = name;
        this.description = description;
        this.exit1 = exit1;
        this.stairsUp = stairsUp;
        this.stairsDown = stairsDown;
    }

    //constructor for room with two exits (overload)
    Room(String name, String description, String exit1, String exit2, boolean stairsUp, boolean stairsDown) {
        this.name = name;
        this.description = description;
        this.exit1 = exit1;
        this.exit2 = exit2;
        this.stairsUp = stairsUp;
        this.stairsDown = stairsDown;
    }

    //constructor for room with three exits (overload)
    Room(String name, String description, String exit1, String exit2, String exit3, boolean stairsUp, boolean stairsDown) {
        this.name = name;
        this.description = description;
        this.exit1 = exit1;
        this.exit2 = exit2;
        this.exit3 = exit3;
        this.stairsUp = stairsUp;
        this.stairsDown = stairsDown;
    }

    //a method that allows adding of artifacts to an array of artifacts in a room
    ArrayList<Artifact> addArtifact(Artifact artifact) {
        artifacts.add(artifact);
        return artifacts;
    }

    //list out all artifacts available in the room by looping through array
    private void allArtifacts() {
        for (Artifact i : this.artifacts) {
            System.out.println("There is a(n) " + i.name + " here.");
        }
    }

    //when user examines, loop through array, find the artifact desired, and return examine method from artifact
    String examine(String artifact) {
        for (Artifact i : this.artifacts) {
            if (i.name.equalsIgnoreCase(artifact)) {
                examine = i.examine();
                break;
            }
            else {
                examine = null;
            }
        }
        return examine;
    }

    //when user touches, loop through array, find the artifact desired, and return touch method from artifact
    String touch(String artifact) {
        for (Artifact i : this.artifacts) {
            if (i.name.equalsIgnoreCase(artifact)) {
                touch = i.touch();
                break;
            }
            else {
                touch = null;
            }
        }
        return touch;
    }

    Artifact take(String artifact) {
        for (Artifact i : this.artifacts) {
            if (i.name.equalsIgnoreCase(artifact)) {
                take = i;
                break;
            }
            else {
                take = null;
            }
        }
        return take;
    }

    void drop(Artifact artifact) {
        this.artifacts.add(artifact);
    }


    //list out all artifacts in a list for user to choose from
    void printArtifacts() {
        for (Artifact i : this.artifacts) {
            System.out.println("-" + i.name);
        }
    }

    //check whether room has artifacts or not
    boolean emptyCheck() {
        return this.artifacts.isEmpty();
    }

    //returns where exits are available in a room
    String getExits() {
        if (this.exit2 != null) {
            if (this.exit3 != null) {
                return this.exit1 + ", " + this.exit2 + ", and " + this.exit3 + ".";
            }
            else {
                return this.exit1 + " and " + this.exit2 + ".";
            }
        }
        else {
            return this.exit1 + ".";
        }
    }

    //returns stairs if there are stairs
    String getStairs() {
        if (stairsUp) {
            return "\nThere appear to be stairs going up.";
        }
        else if (stairsDown) {
            return "\nThere appear to be stairs going down.";
        }
        else {
            return "";
        }
    }

    //displays room description, exits, and artifacts
    void look() {
        System.out.println(this.name + "\n"
                + this.description + "\n" + "There is an opening to the " + getExits() +
                getStairs());
        this.allArtifacts();
    }

    //check if the users requested exit is a valid one
    boolean isValidExit(String requestedExit) {
        return requestedExit.equals(this.exit1) || requestedExit.equals(this.exit2) ||
                requestedExit.equals(this.exit3);
    }
}
