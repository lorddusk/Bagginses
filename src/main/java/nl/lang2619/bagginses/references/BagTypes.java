package nl.lang2619.bagginses.references;

/**
 * Created by Tim on 4/12/2015.
 */
public enum BagTypes {
    TIER1(3, 5, 44, 19),
    TIER2(3, 5, 8, 19),
    VOID(1, 1, 80, 37),
    ENDER(0, 0, 0, 0);

    BagTypes(int rows, int columns, int startX, int startY) {
        this.rows = rows;
        this.columns = columns;
        this.startX = startX;
        this.startY = startY;
    }

    private int rows;
    private int columns;
    private int startX;
    private int startY;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getSize() {
        return columns * rows;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}
