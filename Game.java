import java.util.HashMap;

public class Game {
    private final Parser aParser;
    private final HashMap<String, Room> aAllRooms;
    private Room aCurrentRoom;

    /**
     * Creates the game and initialise its internal map.
     */
    public Game() {
        this.aParser = new Parser();
        this.aAllRooms = new HashMap<>();

        this.createRooms();
    }

    /**
     * Creates the necessary rooms for the game.
     */
    private void createRooms() {
        Room office = this.initRoom("bureau", "c'est le bureau de Murphy Law");
        Room car = this.initRoom("voiture", "c'est la voiture de Murphy Law. Pratique pour aller là où vous voulez !");
        Room esiee = this.initRoom("ESIEE", "c'est la salle où vous avez lancé ce jeu");
        Room greatStair = this.initRoom("grand escalier", "l'escalier principal de Buckingham Palace");
        Room reception = this.initRoom("salle de réception", "la salle de réception de Buckingham Palace");
        Room apartments = this.initRoom("appartements", "ce sont les appartements de la Reine");
        Room kitchen = this.initRoom("cuisine", "la cuisine de Buckingham Palace");
        Room cave = this.initRoom("cave", "une cave de stockage d'objets sous Buckingham Palace");

        office.setExit("east", car);

        car.setExit("esiee", esiee)
                .setExit("buckingham", greatStair);

        greatStair.setExit("west", reception);

        reception.setExit("up", apartments)
                .setExit("west", kitchen)
                .setExit("east", greatStair);

        kitchen.setExit("down", cave)
                .setExit("east", reception);

        cave.setExit("up", kitchen);

        kitchen.setExit("east", reception);

        apartments.setExit("down", reception);

        this.aCurrentRoom = office;
    }

    /**
     * Initializes a new room and store it.
     *
     * @param pName        the name of the room.
     * @param pDescription the description of the room.
     * @return the created room.
     */
    private Room initRoom(final String pName, final String pDescription) {
        Room vCurrentRoom = new Room(pName, pDescription);
        this.aAllRooms.put(pName, vCurrentRoom);

        return vCurrentRoom;
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

            case "inspect":
                this.inspect(pCommand);
                return false;

            // On néglige la branche "default" ici car ons ait que si on arrive à l'instruction du
            // switch, le "commandWord" est valide, c'est donc forcément un des 5 cas ci dessus.
        }

        return false;
    }

    /**
     * Handles the inspect command.
     *
     * @param pCommand the inspect command to be processed.
     */
    private void inspect(Command pCommand) {
        System.out.println("Nothing to inspect here.");
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
        System.out.println("\t" + this.aParser.getCommands());
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
        if (vNextRoom == null) System.out.println("Unknown direction");
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
