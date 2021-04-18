package zuul.pkg_command;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Handles the test command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class TestCommand extends Command {
    public TestCommand() {
        super("test");
    }

    /**
     * Tests the program with command from a test file.
     *
     * @param pGameEngine    The game engine.
     * @param pPlayer    The player using the command.
     * @param pInterface The user interface used by the game.
     */
    @Override
    public void execute(GameEngine pGameEngine, Player pPlayer, UserInterface pInterface) {
        if (!this.hasSecondWord()) {
            pInterface.println("Quel fichier doit-être chargé ?");
            return;
        }

        Path vPath = Paths.get("./test/", this.getSecondWord());

        Scanner vScanner;
        try {
            vScanner = new Scanner(vPath.toFile());
        } catch (FileNotFoundException pError) {
            pInterface.println("Le fichier de test demandé est introuvable.");
            return;
        }

        while (vScanner.hasNextLine()) {
            String vNext = vScanner.nextLine();

            pInterface.printf("[TEST] > %s", vNext);
            pInterface.println();

            pGameEngine.processCommand(vNext, true);
        }
    }

}
