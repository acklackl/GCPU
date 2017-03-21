public class SpecialArtifact extends Artifact {

    //initialize setup
    private boolean state = false;
    private String onDescription;
    private String offDescription;
    private String touchOnDescription;
    private String touchOffDescription;

    //child of Artifact, reference to parent
    SpecialArtifact(String name, String description, boolean movable) {
        super(name, description, movable);
    }

    //override examine method to return description based on state
    public String examine() {
        if (this.state) {
            return this.onDescription;
        }
        else {
            return this.offDescription;
        }
    }

    //override touch method to change state and return touchOn/touchOff description
    public String touch() {
        if (this.state) {
            this.state = false;
            return this.touchOffDescription;
        }
        else {
            this.state = true;
            return this.touchOnDescription;
        }
    }

    //method to simplify assigning all 4 descriptions a Special Artifact requires
    void descriptions(String touchOnDescription, String onDescription,
                      String touchOffDescription) {
        this.offDescription = this.description;
        this.touchOnDescription = touchOnDescription;
        this.onDescription = onDescription;
        this.touchOffDescription = touchOffDescription;
    }
}