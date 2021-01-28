package Game;

import Piece.Guard;
import ui.Modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard extends JFrame implements MouseListener {

    public static final int TILE_SIDE_COUNT = 5;

    private Piece[][] pieceCollection;
    private Piece selectedPiece;

    public GameBoard() {

        this.pieceCollection = new Piece[TILE_SIDE_COUNT][TILE_SIDE_COUNT];






    public GameBoard() {

            this.pieceCollection = new Piece[TILE_SIDE_COUNT][TILE_SIDE_COUNT];

            // Yellow



            this.pieceCollection[0][0] = (new Guard(0, 0, Color.YELLOW));
            this.pieceCollection[0][1] = (new Guard(0, 1, Color.YELLOW));
            this.pieceCollection[0][2] = (new Guard(1, 2, Color.YELLOW));
            this.pieceCollection[0][3] = (new Guard(0, 3, Color.YELLOW));
            this.pieceCollection[0][4] = (new Guard(0, 4, Color.YELLOW));

            //Green
            this.pieceCollection[4][0] = (new Guard(4, 0, Color.GREEN));
            this.pieceCollection[4][1] = (new Guard(4, 1, Color.GREEN));
            this.pieceCollection[4][2] = (new Guard(4, 2, Color.GREEN));
            this.pieceCollection[4][3] = (new Guard(4, 3, Color.GREEN));
            this.pieceCollection[4][4] = (new Guard(4, 4, Color.GREEN));

            this.setSize(500,500);
            this.setVisible(true);
            this.setDefaultCloseOperation(3);
            this.addMouseListener(this);

        }




    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        for(int row = 0; row < 5; row++);
          for(int col = 0; col < 5; col++);



            }

    @Override
    public void mouseClicked(MouseEvent e) {

        int row = this.getBoardDimentionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimentionBasedOnCoordinates(e.getX());

        // check if piece is already selected
        if(this.selectedPiece != null) {


            Piece p = this.selectedPiece;

            if(p.isMoveValid(row, col)) {

                movePiece(row, col, p);
                this.repaint();
                return;
            }
        }
        else {
            // new Modal(this, "Внимание", "Невалиден ход, по дъската");
            Modal.render(this, "Внимание", "Невалиден ход, по дъската");
            return;
        }
        // * move

        // check if piece is available
        if(this.hasBoardPiece(row, col)) {
            this.selectedPiece = this.getBoardPiece(row, col);
        }
    }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {

                this.renderGameTile(g, row, col);
                this.renderGamePiece(g, row, col);
            }
        }
    }

    private void movePiece(int row, int col, Piece p) {
        // 1. Get the original coordinates of the selected piece
        int initialRow = p.getRow();
        int initialCol = p.getCol();

        // 2. Move the piece to trhe new coordinates
        p.move(row, col);

        // 3. Swap the reference to the selected piece from the original coordinates
        // TODO: Abstraction of piece collection
        this.pieceCollection[p.getRow()][p.getCol()] = this.selectedPiece;
        this.pieceCollection[initialRow][initialCol] = null;

        // 4. Remove reference to selected piece
        // TODO: Abstraction of selected piece access
        this.selectedPiece = null;
    }

    private Color getTileColor(int row, int col) {

        boolean isRowEven  = (row % 2 == 0);
        boolean isRowOdd   = !isRowEven;
        boolean isColEven  = (col % 2 == 0);
        boolean isColOdd   = !isColEven;

        if(isRowEven && isColEven   ) return Color.GREEN;
        if(isRowEven && isColOdd    ) return Color.YELLOW;
        if(isRowOdd  && isColEven   ) return Color.YELLOW;

        return Color.GREEN;
    }


    private void renderGameTile(Graphics g, int row, int col) {

        Color tileColor = this.getTileColor(row, col);
        GameTile tile = new GameTile(row, col, tileColor);
        tile.render(g);
    }

    private Piece getBoardPiece(int row, int col) {
        return this.pieceCollection[row][col];
    }

    private boolean hasBoardPiece(int row, int col) {
        return this.getBoardPiece(row, col) != null;
    }

    private void renderGamePiece(Graphics g, int row, int col) {

        if(this.hasBoardPiece(row, col)) {

            Piece p = this.getBoardPiece(row, col);
            p.render(g);
        }
    }

    private int getBoardDimentionBasedOnCoordinates(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }


    }






