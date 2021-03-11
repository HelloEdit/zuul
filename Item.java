public class Item {
    /**
     * The description of the item.
     */
    private String aDescription;

    /**
     * Weight of an item. 0 if the item has no weight.
     */
    private int aWeight;

    /**
     * Creates a new item with a description and a weight.
     *
     * @param pDescription description of the item.
     * @param pWeight      weight of the item.
     */
    public Item(final String pDescription, final int pWeight) {
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }

    /**
     * Creates a new item with a description and no weight.
     *
     * @param pDescription description of the item.
     */
    public Item(final String pDescription) {
        this(pDescription, 0);
    }

    /**
     * Gets the item's description.
     *
     * @return the item's description.
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * Gets the item's weight
     *
     * @return the item's weight.
     */
    public int getWeight() {
        return this.aWeight;
    }
}