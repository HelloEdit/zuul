package zuul.pkg_command;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_personage.Personage;
import zuul.pkg_room.Room;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the talk command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class TalkCommand extends Command {
    public TalkCommand() {
        super("talk");
    }

    /**
     * Talks to a personage.
     *
     * @param pGameEngine The game engine.
     * @param pPlayer     The player using the command.
     * @param pInterface  The user interface used by the game.
     * @throws Exception If something goes wrong.
     */
    @Override
    public void execute(GameEngine pGameEngine, Player pPlayer, UserInterface pInterface) throws Exception {
        Room vRoom = pPlayer.getRoom();

        if (!this.hasSecondWord()) {
            pInterface.println("Vous devez spécifier à qui parler.");
            pInterface.println(vRoom.getPersonagesDescription());
            return;
        }

        Personage vPerso = vRoom.getPersonage(this.getSecondWord());
        if (vPerso == null) pInterface.println("Ce personnage n'est pas là.");
        else vPerso.dialog(pGameEngine, pInterface, pPlayer);
    }
}
