package zuul.pkg_personage;

import zuul.pkg_game.Player;
import zuul.pkg_ui.UserInterface;

import java.util.Random;

public class TechnicianPersonage extends MovingPersonage {
    private static String[] DIALOGS = {
            "Mon dieu mais je n'ai pas le temps de vous parler !",
            "Vous devriez être à votre poste vous non ?",
            "Mais vous êtes qui ? Où est votre uniforme ?",
            "Java 22 était quand même bien mieux... On en fait plus des langages comme ça."
    };

    /**
     * Creates a new technician personage.
     */
    public TechnicianPersonage() {
        super("technicien", "un ingénieur spécialisé en physique nucléaire et en Java 25");
    }

    @Override
    public void dialog(UserInterface pInterface, Player pPlayer) {
        int index = new Random().nextInt(DIALOGS.length);
        pInterface.println("[Technicien] " + DIALOGS[index]);
    }
}
