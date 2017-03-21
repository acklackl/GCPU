class Artifact implements Touchable {
    //artifact object has name and description properties, initialize them, also has movable boolean
    String name, description;
    boolean state;
    boolean movable;

    //constructor for artifact
    Artifact(String name, String description, boolean movable) {
        this.name = name;
        this.description = description;
        this.movable = movable;
    }

    //examine method returns description of artifact
    public String examine() {
        return this.description;
    }

    //touch method returns string
    public String touch() {
        return "Touching the " + this.name + " doesn't appear to do anything.";
    }
}

