package nl.lang2619.bagginses.references;

/**
 * Created by Tim on 4/12/2015.
 */
public enum BagTypes {
    TIER1(3, 5, 44, 19),
    TIER2(3, 9, 8, 19),
    TIER3(4, 9, 8, 19),
    VOID(1, 1, 80, 37),
    ENDER(0, 0, 0, 0);

    BagTypes(int rows, int columns, int bagStartX, int bagStartY) {
        this.rows = rows;
        this.columns = columns;
        this.bagStartX = bagStartX;
        this.bagStartY = bagStartY;
    }

    private int rows;
    private int columns;
    private int bagStartX;
    private int bagStartY;
    private int invStartX;
    private int invStartY;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getSize() {
        return columns * rows;
    }

    public int getBagStartX() {
        return bagStartX;
    }

    public int getBagStartY() {
        return bagStartY;
    }
}
