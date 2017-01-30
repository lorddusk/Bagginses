package nl.lang2619.bagginses.helpers;

/**
 * Created by Alex on 27/01/2017.
 */
public enum BagType {

    TIER1(3, 5, 44, 19, "t1"),
    TIER2(3, 9, 8, 19, "t2"),
    TIER3(4, 9, 8, 19, "t3"),
    VOID(1, 1, 80, 37, "void"),
    ENDER(0, 0, 0, 0, "ender");

    BagType(int rows, int columns, int bagStartX, int bagStartY, String tierCode) {
        this.rows = rows;
        this.columns = columns;
        this.bagStartX = bagStartX;
        this.bagStartY = bagStartY;
        this.tierCode = tierCode;
    }

    private int rows;
    private int columns;
    private int bagStartX;
    private int bagStartY;
    private String tierCode;

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

    public String getTierCode() {
        return tierCode;
    }
}
