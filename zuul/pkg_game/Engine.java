package zuul.pkg_game;

import zuul.pkg_command.Command;
import zuul.pkg_command.Parser;
import zuul.pkg_item.Beamer;
import zuul.pkg_item.Cookie;
import zuul.pkg_room.Room;
import zuul.pkg_room.RoomRandomizer;
import zuul.pkg_room.TransporterRoom;
import zuul.pkg_ui.UserInterface;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * This main class creates the necessary implementation objects and starts the game.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class Engine {
    /**
     * All the rooms created for the game.
     */
    private final RoomRandomizer aRandomizer;

    /**
     * The player playing the game.
     */
    private final Player aPlayer;

    /**
     * Graphical user interface to be used for the game.
     */
    private UserInterface aInterface;

    /**
     * Creates a new engine for the game.
     */
    public Engine() {
        this.aRandomizer = new RoomRandomizer();
        this.aPlayer = new Player();

        this.createRooms();
    }

    /**
     * Creates the rooms required for the game.
     */
    private void createRooms() {
        // Murphy Law Universe
        Room vOffice = new Room("bureau", "C'est le bureau de Murphy Law");
        vOffice.addItem("loupe", "une simple loupe");
        vOffice.addItem(new Beamer());

        Room vCar = new Room("voiture", "C'est la voiture de Murphy Law. Pratique pour vous déplacer");
        vCar.addItem("clé", "d'une utilité assez évidente");

        // Buckingham Palace Universe
        Room vGreatStair = new Room("grand escalier", "L'escalier principal de Buckingham Palace");
        vGreatStair.addItem("statue", "Une statue imposante de la reine", -1);

        Room vReception = new Room("salle de réception", "Salle où, manifestement, on reçoit des gens importants");
        vReception.addItem("cendrier", "C'est un beau cendrier, vraiment");

        Room vTerrace = new Room("terrasse", "terrasse donnant sur le jardin de Buckingham Palace");
        vTerrace.addItem("jumelles", "les jumelles sont en effet pratique pour observer loin");

        Room vAppartements = new Room("appartements", "les appartements de la reine");
        vAppartements.addItem("bijoux", "des boucles d'oreilles en or, évidemment");

        Room vKitchen = new Room("cuisine", "les cuisine du palais");
        vKitchen.addItem(new Cookie());

        Room vCave = new Room("cave", "une cave de stockage bien caché");
        vCave.addItem("caisses", "une caisse scellé");
        vCave.addItem("tableau", "un vieux tableau poussiéreux, représentant un semblant de bataille");

        // Star Trek Universe

        this.aRandomizer.addAll();
        Room vTeleporter = new TransporterRoom(this.aRandomizer);

        // ESIEE Universe
        Room vEsiee = new Room("salle de l'ESIEE", "mais... c'est là où vous avez lancé ce jeu");
        vEsiee.addItem("livre", "un livre intitulé \"Objects First with Java\"");

        // Setting up the "paths" between rooms

        vEsiee.setExit("east", vCar);

        vOffice.setExit("east", vCar);

        vCar.setExit("bureau", vOffice);
        vCar.setExit("esiee", vEsiee);
        vCar.setExit("buckingham", vGreatStair);

        vGreatStair.setExit("east", vTerrace);
        vGreatStair.setExit("south", vReception);

        vTerrace.setExit("west", vGreatStair);

        vReception.setExit("north", vGreatStair);
        vReception.setExit("top", vAppartements);
        vReception.setExit("south", vKitchen);

        vAppartements.setExit("down", vReception);

        vKitchen.setExit("north", vReception);
        vKitchen.setExit("down", vCave);

        vCave.setExit("voiture", vCar);

        // sets the first room where the game begins
        this.aPlayer.setCurrentRoom(vOffice);
    }

    /**
     * Shows the welcome text for the game.
     */
    private void welcome() {
        this.aInterface.println("\n\t______  _                      _                                \n" +
                "\t| ___ \\| |                    | |                               \n" +
                "\t| |_/ /| |  __ _  _ __    ___ | |_  __      __  __ _  _ __  ___ \n" +
                "\t|  __/ | | / _` || '_ \\  / _ \\| __| \\ \\ /\\ / / / _` || '__|/ __|\n" +
                "\t| |    | || (_| || | | ||  __/| |_   \\ V  V / | (_| || |   \\__ \\\n" +
                "\t\\_|    |_| \\__,_||_| |_| \\___| \\__|   \\_/\\_/   \\__,_||_|   |___/\n\n"
        );

        this.printLocationInfo();
        this.aInterface.println();
    }

    /**
     * Processes the command entered by the user.
     *
     * @param pInput    Command to be processed.
     * @param pTestMode true if the game is in test mode, false otherwise.
     */
    public void processCommand(final String pInput, final boolean pTestMode) {
        Command vCommand = Parser.parseCommand(pInput, pTestMode);

        // while writing my commands, I noticed that many of them needed the player instance, the game engine
        // and/or the interface used. So instead of repeating the getters I preferred to put the different
        // elements as parameters of the `execute` method.

        try {
            vCommand.execute(this, this.aPlayer, this.aInterface);
        } catch (Timer.TimerLimitException pError) {
            // here we are in the case where the game cannot continue because a timer has expired
            this.aInterface.printf(pError.getMessage());
            this.aInterface.disable();
        } catch (Exception pError) {
            // a non-fatal error occurred.
            this.aInterface.println(pError.getMessage());
        }

        this.aInterface.println();
    }

    /**
     * Prints the location informations.
     */
    public void printLocationInfo() {
        String vText = this.aPlayer.getRoomDescription();

        this.aInterface.println(vText);
    }

    /**
     * Gets the player object.
     *
     * @return The player object.
     */
    public Player getPlayer() {
        return this.aPlayer;
    }

    /**
     * Gets the user interface used by the engine.
     *
     * @return The user interface used.
     */
    public UserInterface getInterface() {
        return this.aInterface;
    }

    /**
     * Sets the used graphical user interface for the game.
     *
     * @param pInterface The user interface to be used.
     */
    public void setInterface(final UserInterface pInterface) {
        this.aInterface = pInterface;

        this.welcome();
    }
}
