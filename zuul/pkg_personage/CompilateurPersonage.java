package zuul.pkg_personage;

import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

public class CompilateurPersonage extends Personage {
    public CompilateurPersonage() {
        super("Compilateur", "le compilateur java !");
    }

    @Override
    public void dialog(GameEngine pEngine, UserInterface pInterface, Player pPlayer) {
        pInterface.println("Félicitation ! Vous avez trouvé le compilateur Java !");

        pEngine.win();
    }
}
