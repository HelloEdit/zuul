import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class creates all rooms, creates the parser and starts
 * the game.  It also evaluates and executes the commands that
 * the parser returns.
 */
public class GameEngine {
    /**
     * The parser instance to get commands.
     */
    private final Parser aParser;

    /**
     * All rooms created for the game.
     */
    private final HashMap<String, Room> aAllRooms;

    /**
     * The current room where the player is.
     */
    private Room aCurrentRoom;

    /**
     * The current room where the player is.
     */
    private Room aPreviousRoom;

    /**
     * The graphical user interface of the game.
     */
    private UserInterface aGui;

    /**
     * Creates the game and initialise its internal map.
     */
    public GameEngine() {
        this.aParser = new Parser();
        this.aAllRooms = new HashMap<>();

        this.createRooms();
    }

    /**
     * Set a new graphical user interface for the game
     */
    public void setGUI(final UserInterface pUserInterface) {
        this.aGui = pUserInterface;
        if (this.aCurrentRoom.getImageName() != null)
            this.aGui.showImage(this.aCurrentRoom.getImageName());

        this.printWelcome();
    }

    /**
     * Prints a small welcome text.
     */
    private void printWelcome() {
        this.aGui.println("Planet Wars est un jeu d'aventure de science fiction incroyable");
        this.aGui.println("Pour accéder à l'aide, taper \"help\"\n\n");

        this.printLocationInfo();
    }

    /**
     * Creates the necessary rooms for the game.
     */
    private void createRooms() {
        Room office = this.initRoom("bureau", "c'est le bureau de Murphy Law")
                .addItem("loupe", 1);

        Room car = this.initRoom("voiture", "c'est la voiture de Murphy Law. Pratique pour aller là où vous voulez")
                .addItem("clés de voiture", 1);

        Room esiee = this.initRoom("ESIEE", "c'est la salle où vous avez lancé ce jeu")
                .addItem("livre \"Objects First with Java\"", 1);

        Room greatStair = this.initRoom("grand escalier", "l'escalier principal de Buckingham Palace")
                .addItem("statue de la reine", 100);

        Room reception = this.initRoom("salle de réception", "la salle de réception de Buckingham Palace")
                .addItem("cendrier", 1);

        Room apartments = this.initRoom("appartements", "ce sont les appartements de la Reine")
                .addItem("boucles d'oreilles", 1);

        Room kitchen = this.initRoom("cuisine", "la cuisine de Buckingham Palace")
                .addItem("oignons", 1);

        Room cave = this.initRoom("cave", "une cave de stockage d'objets sous Buckingham Palace")
                .addItem("épée", 2);

        // Setting up the exits
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
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(final String pCommandLine) {
        this.aGui.println("> " + pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);

        if (vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }

        switch (vCommand.getCommandWord()) {
            case "help":
                this.printHelp();
                break;

            case "quit":
                if (this.quit(vCommand)) this.endGame();
                break;

            case "go":
                this.go(vCommand);
                break;

            case "look":
                this.look(vCommand);
                break;

            case "back":
                this.back(vCommand);
                break;

            case "inspect":
                this.inspect(vCommand);
                break;

            // On néglige la branche "default" ici car on sait que si on arrive à l'instruction du
            // switch, le "commandWord" est valide, c'est donc forcément un des 5 cas ci dessus.
        }
    }

    /**
     * GOes back to the latest room.
     *
     * @param vCommand the command to be processed.
     */
    private void back(Command vCommand) {
        if (vCommand.hasSecondWord()) {
            this.aGui.println("retourner où ??");
            return;
        }

        if (this.aPreviousRoom == null) {
            this.aGui.println("Aucune salle dans laquelle retourner.");
            return;
        }

        this.changeRoom(this.aPreviousRoom);
    }

    /**
     * Prints a little help text.
     */
    private void printHelp() {
        this.aGui.println("Vous êtes l'élu qui va rétablir l'équilibre de la galaxie.");
        this.aGui.println("Vous devez trouver le crystal sacré pour cela.");
        this.aGui.println("Les commandes disponibles sont les suivantes :");
        this.aGui.println("\t" + this.aParser.getCommands());
    }

    /**
     * Handles the quit command.
     *
     * @param pCommand the quit command to be processed.
     * @return true if the program should quit, false otherwise.
     */
    private boolean quit(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("Quitter quoi ??");
            return false;
        }

        return true;
    }

    /**
     * Changes the current room.
     *
     * @param pCommand the go command to be processed.
     */
    private void go(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Aller où ?");

            return;
        }

        Room vNextRoom = this.aCurrentRoom.getExit(pCommand.getSecondWord());
        if (vNextRoom == null) {
            this.aGui.println("Cette direction est inconnue...\n");
            return;
        }

        this.changeRoom(vNextRoom);
    }

    /**
     * Handles the look command.
     */
    private void look(final Command pCommand) {
        String vToDisplay;

        if (pCommand.hasSecondWord()) {
            Item vItem = this.aCurrentRoom.getItems().get(pCommand.getSecondWord());
            vToDisplay = vItem != null
                    ? vItem.getLongDescription()
                    : "Objet inconnu. Rien a afficher.\n";
        }
        else {
            vToDisplay = this.aCurrentRoom.getLongDescription();
        }

        this.aGui.println(vToDisplay);
    }

    /**
     * Changes the current room.
     *
     * @param vRoom the room to be used.
     */
    private void changeRoom(Room vRoom) {
        this.aPreviousRoom = this.aCurrentRoom;
        this.aCurrentRoom = vRoom;

        if (vRoom.getImageName() != null)
            this.aGui.showImage(vRoom.getImageName());


        this.printLocationInfo();
    }

    /**
     * Handles the inspect command.
     *
     * @param pCommand the inspect command to be processed.
     */
    private void inspect(Command pCommand) {
        this.aGui.println("Rien à inspecter ici.");
    }

    /**
     * End the game by gratefully closing it.
     */
    private void endGame() {
        this.aGui.println("Merci d'avoir joué.  Au revoir.");
        this.aGui.enable(false);
    }

    /**
     * Prints the location informations.
     */
    private void printLocationInfo() {
        this.aGui.println(this.aCurrentRoom.getLongDescription());
    }
}
