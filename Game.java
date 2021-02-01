public class Game {
    private final Parser aParser;
    private Room aCurrentRoom;

    /**
     * The Zuul game.
     */
    public Game() {
        this.aParser = new Parser();
        this.createRooms();
    }

    /**
     * Creates the necessary rooms for the game.
     */
    private void createRooms() {
        Room office = new Room("Bureau de Murphy Law");
        Room car = new Room("Voiture de Murphy Law");

        office.setExit("east", car);

        this.aCurrentRoom = office;
    }

    /**
     * The main entry point of the game.
     */
    public void play() {
        this.printWelcome();

        boolean vFinished = false;
        while (!vFinished) {
            Command vCommand = this.aParser.getCommand();
            vFinished = this.processCommand(vCommand);
        }

        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Prints a small welcome text.
     */
    private void printWelcome() {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.\n");

        this.printLocationInfo();
    }

    /**
     * Process a Command object.
     *
     * @param pCommand the command to be processed.
     * @return if the program should quit.
     * @see Command
     */
    private boolean processCommand(final Command pCommand) {
        if (pCommand.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        switch (pCommand.getCommandWord()) {
            case "help":
                this.printHelp();
                return false;

            case "quit":
                return this.quit(pCommand);

            case "go":
                this.goRoom(pCommand);
                return false;

            case "look":
                this.look();
                return false;

            default:
                System.out.println("I don't know what you mean...");
                return false;
        }
    }

    /**
     * Handles the quit command.
     *
     * @param pCommand the quit command to be processed.
     * @return if the program should quit.
     */
    private boolean quit(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            System.out.println("Quit what ??");
            return false;
        }

        return true;
    }

    /**
     * Prints a little help text.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println("Your command words are:");
        System.out.println("\tgo quit help");
    }

    /**
     * Handles the look command.
     */
    private void look() {
        System.out.println(this.aCurrentRoom.getLongDescription());
    }

    /**
     * Changes the current room.
     *
     * @param pCommand the go command to be processed.
     */
    private void goRoom(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            System.out.println("Go where ?");

            return;
        }

        Room vNextRoom = this.aCurrentRoom.getExit(pCommand.getSecondWord());
        if (vNextRoom == null) System.out.println("There is no door !");
        else this.aCurrentRoom = vNextRoom;

        this.printLocationInfo();
    }

    /**
     * Prints the location informations.
     */
    private void printLocationInfo() {
        System.out.println(this.aCurrentRoom.getLongDescription());
    }

} // Game
