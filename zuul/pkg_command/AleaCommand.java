package zuul.pkg_command;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_room.Room;
import zuul.pkg_room.TransporterRoom;
import zuul.pkg_ui.UserInterface;

/**
 * Handles the alea command.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class AleaCommand extends Command {
    public AleaCommand() {
        super("alea", true);
    }

    /**
     * Forces the exit of the current transporter room.
     *
     * @param pGameEngine The game engine.
     * @param pPlayer     The player using the command.
     * @param pInterface  The user interface used by the game.
     * @throws UnsupportedOperationException If player is not using this command in a transporter room.
     */
    @Override
    public void execute(GameEngine pGameEngine, Player pPlayer, UserInterface pInterface) throws UnsupportedOperationException {
        Room vRoom = pPlayer.getRoom();

        if (!(vRoom instanceof TransporterRoom)) {
            throw new UnsupportedOperationException("alea ne peut être utilisé uniquement que dans un transporter.");
        }

        TransporterRoom vTransporter = (TransporterRoom) vRoom;

        if (!this.hasSecondWord()) {
            vTransporter.setForcedExit(null);

            pInterface.printf("La prochaine sortie de %s sera aléatoire !");
            pInterface.println();

            return;
        }

        vTransporter.setForcedExit(this.getSecondWord());

        pInterface.printf("Sortie de %s mise sur %s.", vRoom.getName(), this.getSecondWord());
        pInterface.println();
    }
}
