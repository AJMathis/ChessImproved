public class Piece {
    int type;
    int color;
    int sqr;

    public Piece() {
        this.type = -1;
        this.color = -1;
        this.sqr = -1;
    }

    public Piece(int type, int color, int sqr) {
        this.type = type;
        this.color = color;
        this.sqr = sqr;
    }

    public int getType() {
        return this.type;
    }
    public int getColor() {
        return this.color;
    }
    public int getSqr() {
        return this.sqr;
    }

    public void setSqr(int sqr) {
        this.sqr = sqr;
    }
}
