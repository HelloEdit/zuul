import java.util.HashMap;

/**
 * The main class of the Zuul project.
 */
public class Game {
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
        Room car = this.initRoom("voiture", "c'est la voiture de Murphy Law. Pratique pour aller là où vous voulez");
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

        System.out.println("Merci d'avoir joué. Au revoir.");
    }

    /**
     * Prints a small welcome text.
     */
    private void printWelcome() {
        System.out.println("\n██████  ██       █████  ███    ██ ███████ ████████     ██     ██  █████  ██████  ███████ \n" +
                "██   ██ ██      ██   ██ ████   ██ ██         ██        ██     ██ ██   ██ ██   ██ ██      \n" +
                "██████  ██      ███████ ██ ██  ██ █████      ██        ██  █  ██ ███████ ██████  ███████ \n" +
                "██      ██      ██   ██ ██  ██ ██ ██         ██        ██ ███ ██ ██   ██ ██   ██      ██ \n" +
                "██      ███████ ██   ██ ██   ████ ███████    ██         ███ ███  ██   ██ ██   ██ ███████ \n\n");

        System.out.println("Planet Wars est un jeu d'aventure de science fiction incroyable");
        System.out.println("Pour accéder à l'aide, taper \"help\"\n\n");

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
            System.out.println("Je ne sais pas ce qu'est cette commande...");
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
        System.out.println("Rien à inspecter ici.");
    }

    /**
     * Handles the quit command.
     *
     * @param pCommand the quit command to be processed.
     * @return if the program should quit.
     */
    private boolean quit(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            System.out.println("Quitter quoi ??");
            return false;
        }

        return true;
    }

    /**
     * Prints a little help text.
     */
    private void printHelp() {
        System.out.println("Vous êtes l'élu qui va rétablir l'équilibre de la galaxie.");
        System.out.println("Vous devez trouver le crystal sacré pour cela.");
        System.out.println("Les commandes disponibles sont les suivantes :");
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
            System.out.println("Aller où ?");

            return;
        }

        Room vNextRoom = this.aCurrentRoom.getExit(pCommand.getSecondWord());
        if (vNextRoom == null) System.out.println("Cette direction est inconnue...\n");
        else this.aCurrentRoom = vNextRoom;

        this.printLocationInfo();
    }

    /**
     * Prints the location informations.
     */
    private void printLocationInfo() {
        System.out.println(this.aCurrentRoom.getLongDescription());
    }
}
