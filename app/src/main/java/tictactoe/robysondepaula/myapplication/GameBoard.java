package tictactoe.robysondepaula.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class GameBoard extends View {


    private final int boardColor;
    private final int xColor;
    private final int OColor;
    private final int winColor;

    private boolean winLine = false;

    private final Paint paintingColor = new Paint();

    private final GameCode game;

    private int cellsSize = getWidth() / 3;


    public GameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new GameCode();

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GameBoard, 0, 0);

        try {
            boardColor = array.getInteger(R.styleable.GameBoard_boardColor, 0);
            xColor = array.getInteger(R.styleable.GameBoard_boardColor, 0);
            OColor = array.getInteger(R.styleable.GameBoard_boardColor, 0);
            winColor = array.getInteger(R.styleable.GameBoard_boardColor, 0);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int boardDimensions = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellsSize = boardDimensions / 3;

        setMeasuredDimension(boardDimensions, boardDimensions);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paintingColor.setStyle(Paint.Style.STROKE);
        paintingColor.setAntiAlias(true);

        drawingGameBoard(canvas);

        drawMarkers(canvas);


    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();


        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            int row = (int) Math.ceil(y / cellsSize);
            int col = (int) Math.ceil(x / cellsSize);

            if(!winLine){

                if(game.updateGameBoard(row, col)){
                    invalidate();

                    if(game.winner()){
                        winLine =true;
                        invalidate();
                    }

                    if(game.getPlayer() % 2 == 0 ) {
                        game.setPlayer(game.getPlayer()-1);
                    }
                    else{
                        game.setPlayer(game.getPlayer()+1);
                    }
                }
            }



            invalidate();

            return true;
        }

        return false;
    }




    private void drawingGameBoard(Canvas canvas){
        paintingColor.setColor(boardColor);
        paintingColor.setStrokeWidth(16);

        for(int cells=1; cells<3; cells++){
            canvas.drawLine(cellsSize* cells, 0, cellsSize* cells, canvas.getWidth(), paintingColor);
        }
        for(int rows=1; rows<3; rows++){
            canvas.drawLine(0, cellsSize* rows, canvas.getWidth(), cellsSize*rows, paintingColor);

        }

    }

    private void drawMarkers(Canvas canvas){
        for (int row=0; row<3; row++){
            for(int col=0; col<3; col++){
                if(game.getGameBoard() [row] [col] != 0){
                    if(game.getGameBoard()[row][col] ==1){
                        xTurnDraw(canvas, row, col);
                    }
                    else{
                        oTurnDraw(canvas, row, col);
                    }
                }

            }
        }
    }




    private void xTurnDraw(Canvas canvas, int row, int col) {
        paintingColor.setColor(xColor);

        canvas.drawLine((float) ((col + 1) * cellsSize - cellsSize *0.2), (float) (row * cellsSize + cellsSize *0.2),(float) (col * cellsSize + cellsSize *0.2),(float) ((row + 1) * cellsSize -cellsSize * 0.2), paintingColor);

        canvas.drawLine((float) (col * cellsSize + cellsSize *0.2), (float) (row * cellsSize + cellsSize*0.2),(float)  ((col + 1) * cellsSize - cellsSize *0.2), (float)  ((row +1) * cellsSize - cellsSize*0.2), paintingColor);
    }
    private void oTurnDraw(Canvas canvas, int row, int col) {
        paintingColor.setColor(OColor);

        canvas.drawOval((float) (col*cellsSize + cellsSize*0.2), (float) (row*cellsSize + cellsSize*0.2), (float) ((col*cellsSize + cellsSize) -cellsSize*0.2),(float) ((row*cellsSize + cellsSize) - cellsSize*0.2) , paintingColor);

    }

    
    public void settingGameUp(Button playAgain, Button home, TextView playerDisplay, String [] names){
        game.settingPlayAgainBtn(playAgain);
        game.settingHomeBtn(home);
        game.settingPlayerTurn(playerDisplay);
        game.setPlayerNames(names);

    }

    public void resetGame(){
        game.resetGame();
        winLine = false;
    }


}
