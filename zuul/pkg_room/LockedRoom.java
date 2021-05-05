package zuul.pkg_room;

import zuul.pkg_game.Player;
import zuul.pkg_item.Item;

/**
 * Represents a closed room accessible only under a certain condition.
 */
public class LockedRoom extends Room {
    /**
     * The item key.
     */
    private Item aKey;

    /**
     * Creates a new locked room.
     *
     * @param pName        Name of the room.
     * @param pDescription Description of the room.
     */
    public LockedRoom(String pName, String pDescription) {
        super(pName, pDescription);
    }

    /**
     * Checks if the player can unlock the room.
     *
     * @param pPlayer Player who want to access the room.
     * @return If the player can access the room.
     */
    public boolean check(final Player pPlayer) {
        return pPlayer.getItem(this.aKey.getName()) != null;
    }

    /**
     * Sets the item which will act as the key.
     *
     * @param pItem The item key.
     */
    public void setKeyItem(final Item pItem) {
        this.aKey = pItem;
    }
}
