package zuul.pkg_personage;

import zuul.Utils;
import zuul.pkg_game.GameEngine;
import zuul.pkg_game.Player;
import zuul.pkg_item.Cookie;
import zuul.pkg_item.Item;
import zuul.pkg_ui.UserInterface;

/**
 * Represents a technician in the game.
 */
public class TechnicianPersonage extends MovingPersonage {
    /**
     * Dialogues for the technician looking for his screwdriver.
     */
    private static final String[] dialogs_searching = {
            "Mon dieu mais je n'ai pas le temps de vous parler ! J'ai perdu mon tournevis...",
            "Vous n'auriez pas vu un tournevis ? Mais attendez, vous devriez être à votre poste vous non ?",
            "Mais vous êtes qui ? Où est votre uniforme ? Et plus important, où est mon tournevis !",
    };
    /**
     * Dialogs after the technician found his screwdriver.
     */
    private static final String[] dialog_after = {
            "Encore merci mais mai tenant déguerpissez, j'ai du travail. Beaucoup.",
            "Bon, maintenant je dois me remettre au travail"
    };
    /**
     * Indicates if the technician has found his screwdriver
     */
    private boolean aFoundScrewdriver;

    /**
     * Creates a new technician personage.
     */
    public TechnicianPersonage() {
        super("technicien", "un ingénieur spécialisé en physique nucléaire et en Java 25");

        this.aFoundScrewdriver = false;
    }

    @Override
    public void dialog(GameEngine pEngine, UserInterface pInterface, Player pPlayer) {
        if (this.aFoundScrewdriver) {
            pInterface.println("[technicien] " + Utils.randomElement(dialog_after));
            return;
        }

        Item vItem = pPlayer.getItem("tournevis");

        if (vItem == null) {
            pInterface.println("[Technicien] " + Utils.randomElement(dialogs_searching));
            return;
        }

        pInterface.printf("[technicien] Ho merci beaucoup pour le tournevis ! Tenez, ce petit quelque chose pour vous remercier.");
        pPlayer.deleteItem("tournevis");
        pPlayer.addItem(new Cookie("chocolat", "un chocolat d'une marque inconnue"));

        this.aFoundScrewdriver = true;
    }
}
