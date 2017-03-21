import java.util.ArrayList;

class Backpack {

    //initialize variables
    private Artifact artifact;
    private ArrayList<Artifact> contents = new ArrayList<>();
    private final int LIMIT = 3;

    //method that adds artifacts to backpack if limit has not yet been reached
    boolean setArtifact(Artifact artifact) {
        if (this.contents.size() >= this.LIMIT) {
            return false;
        }
        else {
            this.contents.add(artifact);
            return true;
        }
    }

    //method that clears the backpack
    void clear() {
        this.contents.clear();
    }

    //checks the state of SpecialArtifacts inside backpack and returns its state, adds it to ArrayList in main class
    void state() {

        for (Artifact a : this.contents) {
            if (a.getClass() == SpecialArtifact.class) {
                if (a.state) {
                    GCPUApp.saveSpecialArtifacts.add(a.name + "," + "on");
                }

                else if (!a.state) {
                    GCPUApp.saveSpecialArtifacts.add(a.name + "," + "off");
                }
            }
        }
    }

    //returns true if backpack is empty
    boolean empty() {
        return this.contents.isEmpty();
    }

    //displays all items inside backpack, or if backpack is empty
    void inventory() {
        if (this.empty()) {
            System.out.println("Your backpack is empty.");
        }
        for (Artifact i : this.contents) {
            System.out.println("You have a " + i.name + ".");
        }
    }

    //a string listing items inside backpack
    String inventorySave() {
        if (contents.size() == 1) {
            return this.contents.get(0).name;
        }
        else if (contents.size() == 2) {
            return this.contents.get(0).name + "," + this.contents.get(1).name;
        }
        else if (contents.size() == 3) {
            return this.contents.get(0).name + "," + this.contents.get(1).name + "," + this.contents.get(2).name;
        }
        else {
            return "empty";
        }
    }

    //method that allows retrieval of items from backpack
    Artifact getArtifact(String i) {
        for (Artifact a : this.contents) {
            if (a.name.equalsIgnoreCase(i)) {
                artifact = a;
                break;
            }
            else artifact = null;
        }
        return artifact;
    }

    //method that removes an artifact from backpack
    Artifact removeArtifact(Artifact i) {
        this.contents.remove(i);
        return i;
    }

    //method that prints out all inventory inside backpack
    void printInventory() {
        for (Artifact i : this.contents) {
            System.out.println("-" + i.name);
        }
    }

    //method that saves special artifacts that are inside the backpacks states onto save file
    void backpackState() {
        for (Artifact i : this.contents) {
            if (i.name.equals(GCPUApp.read[1])) {
                if (GCPUApp.read[2].equals("on")) {
                    i.state = true;
                    break;
                } else if (GCPUApp.read[2].equals("off")) {
                    i.state = false;
                    break;
                }
            }
        }
    }
}
