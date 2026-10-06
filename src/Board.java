public class Board {
    long[][] pieces;
    long board;

    final int PAWN = 0;
    final int KNIGHT = 1;
    final int BISHOP = 2;
    final int ROOK = 3;
    final int QUEEN = 4;
    final int KING = 5;
    final int WHITE = 0;
    final int BLACK = 1;

    public Board() {
        initBoard();
    }

    public void initBoard() {
        long[][] pieces = new long[2][6];
        pieces[WHITE][PAWN] = 0x000000000000FF00L;
        pieces[BLACK][PAWN] = 0x00FF000000000000L;
        pieces[WHITE][KNIGHT] = 0x0000000000000042L;
        pieces[BLACK][KNIGHT] = 0x4200000000000000L;
        pieces[WHITE][BISHOP] = 0x0000000000000024L;
        pieces[BLACK][BISHOP] = 0x2400000000000000L;
        pieces[WHITE][ROOK] = 0x0000000000000081L;
        pieces[BLACK][ROOK] = 0x8100000000000000L;
        pieces[WHITE][QUEEN] = 0x0000000000000008L;
        pieces[BLACK][QUEEN] = 0x0800000000000000L;
        pieces[WHITE][KING] = 0x0000000000000010L;
        pieces[BLACK][KING] = 0x1000000000000000L;

        updateBoard();

        this.pieces = pieces;
        this.board = board;
    }

    public void movePiece(String from, String to) {
        int fromSqr = getSqrNotation(from);
        int toSqr = getSqrNotation(to);

        Piece piece = getPiece(fromSqr);
        Piece removedPiece = getPiece(toSqr);
        setPiece(toSqr, removedPiece, piece);
        setPiece(fromSqr, piece, null);

        updateBoard();

    }

    public int getSqrNotation(String letterNotation) {
        if(letterNotation.length() != 2) return -1;
        int sqrNotation = 0;

        sqrNotation += ((letterNotation.charAt(0) - 'a') * 8);
        sqrNotation += letterNotation.charAt(1) - '1';
        return sqrNotation;
    }

    public Piece getPiece(int sqr) {
        if(!isOccupied(this.board, sqr)) return null;

        for(int i = PAWN; i <= KING; i++) {
            if(isOccupied(pieces[WHITE][i], sqr)) return new Piece(i, WHITE, sqr);
            if(isOccupied(pieces[BLACK][i], sqr)) return new Piece(i, BLACK, sqr);
        }
        return null;
    }

    public void setPiece(int sqr, Piece oldPiece, Piece newPiece) {
        long piece = 0;
        if(oldPiece != null) {
            piece = ~(0x1L << sqr);
            pieces[oldPiece.getColor()][oldPiece.getType()] &= piece;
        }
        if(newPiece != null) {
            piece = 0x1L << sqr;
            pieces[newPiece.getColor()][newPiece.getType()] |= piece;
            newPiece.setSqr(sqr);
        }
    }

    public boolean isOccupied(long board, int sqr) {
        return (((board << sqr) & 1L) != 0);
    }

    public void updateBoard() {
        long board = 0x0000000000000000L;
        for(long[] color : pieces) { //INIT board to merge all piece boards
            for(long pieceBoard: color) {
                board |= pieceBoard;
            }
        }
        this.board = board;
    }
}
