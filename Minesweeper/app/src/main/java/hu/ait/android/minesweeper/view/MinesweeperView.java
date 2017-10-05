package hu.ait.android.minesweeper.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import hu.ait.android.minesweeper.MinesweeperModel;
import hu.ait.android.minesweeper.R;
import hu.ait.android.minesweeper.data.MainActivity;


/**
 * Created by oliviakim on 9/24/17.
 */


public class MinesweeperView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintNumber;
    private Paint paintLosingCoord;
    private Paint paintRevealed;
    private boolean gameOver = false;
    private int numOfMinesLeft = 3;
    private int gameOverX = -1;
    private int gameOverY = -1;

    private Bitmap bmFlag;
    private Bitmap bmMine;


    public MinesweeperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.DKGRAY);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStrokeWidth(5);
        paintLine.setStyle(Paint.Style.STROKE);

        paintNumber = new Paint();
        paintNumber.setColor(Color.GREEN);
        paintNumber.setStrokeWidth(5);
        paintNumber.setTextSize(110);
        paintNumber.setStyle(Paint.Style.STROKE);

        paintRevealed = new Paint();
        paintRevealed.setColor(Color.YELLOW);
        paintRevealed.setStrokeWidth(5);
        paintRevealed.setTextSize(110);
        paintRevealed.setStyle(Paint.Style.STROKE);

        paintLosingCoord = new Paint();
        paintLosingCoord.setColor(Color.LTGRAY);
        paintLosingCoord.setStrokeWidth(5);
        paintLosingCoord.setStyle(Paint.Style.STROKE);

        bmFlag = BitmapFactory.decodeResource(getResources(), R.drawable.flag);
        bmMine = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,getWidth(),getHeight(), paintBg);

        drawGameArea(canvas);

        drawContents(canvas);

    }

    private void drawContents(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int numOfMines = MinesweeperModel.getInstance().getBombNumber(i, j);
                Rect coordFlag = new Rect(
                        i * getWidth() / 5,
                        j * getHeight() / 5,
                        i * getWidth() / 5 + 110,
                        j * getHeight() / 5 + 110
                );
                Rect coordMine = new Rect(
                        i * getWidth() / 5,
                        j * getHeight() / 5,
                        i * getWidth() / 5 + 117,
                        j * getHeight() / 5 + 115
                );

                drawOnGameLosingCoord(canvas);

                if (!gameOver) {

                    drawIfGameNotOver(canvas, i, j, numOfMines, coordFlag);

                } else{
                        if (MinesweeperModel.getInstance().getFieldContent(i, j).isAlreadyClicked()){

                            drawClickedWhenGameOver(canvas, i, j, numOfMines, coordFlag, coordMine);

                        } else {

                            revealUnclickedWhenGameOver(canvas, i, j, numOfMines, coordFlag, coordMine);

                        } if (numOfMinesLeft != 0) {
                            ((MainActivity) getContext()).gameOver();
                        } else {
                            ((MainActivity) getContext()).gameWon();
                        }
                    }
                }
            }
        }

    private void drawOnGameLosingCoord(Canvas canvas) {
        if(gameOverX != -1 && gameOverY != -1) {
            canvas.drawLine(gameOverX * getWidth() / 5 , gameOverY * getHeight() / 5,
                    (gameOverX + 1) * getWidth() / 5 ,
                    (gameOverY + 1) * getHeight() / 5 , paintLosingCoord);

            canvas.drawLine((gameOverX + 1) * getWidth() / 5 , gameOverY * getHeight() / 5 ,
                    gameOverX * getWidth() / 5 , (gameOverY + 1) * getHeight() / 5 , paintLosingCoord);

        }
    }

    private void revealUnclickedWhenGameOver(Canvas canvas, int i, int j, int numOfMines, Rect coordFlag, Rect coordMine) {
        if (MinesweeperModel.getInstance().getFieldContent(i, j).isFlag()) {
            canvas.drawBitmap (bmFlag, null, coordFlag, null);

        } else if (MinesweeperModel.getInstance().getFieldContent(i, j).isMine()) {

            canvas.drawBitmap (bmMine, null, coordMine, null );

        } else {
            canvas.drawText(
                    String.valueOf(numOfMines),
                    i * getWidth() / 5 + 20,
                    (j + 1) * getHeight() / 5 - 20,
                    paintRevealed
            );
        }
    }

    private void drawClickedWhenGameOver(Canvas canvas, int i, int j, int numOfMines, Rect coordFlag, Rect coordMine) {
        if (MinesweeperModel.getInstance().getFieldContent(i, j).isFlag()) {

            canvas.drawBitmap (bmFlag, null, coordFlag, null );

        } else if (MinesweeperModel.getInstance().getFieldContent(i, j).isMine()) {
            canvas.drawBitmap (bmMine, null, coordMine, null );

        } else {
            canvas.drawText(
                    String.valueOf(numOfMines),
                    i * getWidth() / 5 + 20,
                    (j + 1) * getHeight() / 5 -20,
                    paintNumber
            );
        }
    }

    private void drawIfGameNotOver(Canvas canvas, int i, int j, int numOfMines, Rect coordFlag) {
        if (MinesweeperModel.getInstance().getFieldContent(i, j).isAlreadyClicked()) {
            if (MinesweeperModel.getInstance().getFieldContent(i, j).isFlag()) {
                canvas.drawBitmap (bmFlag, null, coordFlag, null );
            } else {
                canvas.drawText(
                        String.valueOf(numOfMines),
                        i * getWidth() / 5 + 20,
                        (j + 1) * getHeight() / 5 - 20,
                        paintNumber
                );
            }
        }
    }

    private void drawGameArea(Canvas canvas) {

        canvas.drawRect(0,0,getWidth(),getHeight(), paintLine);

        canvas.drawLine(getWidth() / 5, 0, getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(2 * getWidth() / 5, 0, 2 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(3 * getWidth() / 5, 0, 3 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(4 * getWidth() / 5, 0, 4 * getWidth() / 5, getHeight(), paintLine);

        canvas.drawLine(0, getHeight() / 5, getWidth(), getHeight() / 5, paintLine);
        canvas.drawLine(0, 2 * getHeight() / 5, getWidth(), 2 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 3 * getHeight() / 5, getWidth(), 3 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 4 * getHeight() / 5, getWidth(), 4 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 5 * getHeight() / 5, getWidth(), 5 * (getHeight() / 5), paintLine);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int tX = ((int) event.getX()) / (getWidth() / 5);
            int tY = ((int) event.getY()) / (getHeight() / 5);

            if (!MinesweeperModel.getInstance().getFieldContent(tX, tY).isAlreadyClicked() && !gameOver){

                if (((MainActivity) getContext()).isFlagChecked()){
                    checkGameOverInFlagMode(tX, tY);

                } else {
                    if (MinesweeperModel.getInstance().getFieldContent(tX, tY).isMine()){
                        gameOver = true;
                        gameOverX = tX;
                        gameOverY = tY;
                        MinesweeperModel.getInstance().getFieldContent(tX, tY).setIsAlreadyClicked(true);
                    }
                }

                MinesweeperModel.getInstance().getFieldContent(tX, tY).setIsAlreadyClicked(true);

            }
                invalidate();
                return true;
            }
        return super.onTouchEvent(event);
    }

    private void checkGameOverInFlagMode(int tX, int tY) {
        MinesweeperModel.getInstance().getFieldContent(tX, tY).setFlag(true);
        if (!MinesweeperModel.getInstance().getFieldContent(tX, tY).isMine()){
            gameOver = true;
            gameOverX = tX;
            gameOverY = tY;
        } else {
            numOfMinesLeft--;
            if (numOfMinesLeft == 0){
                gameOver = true;
            }
        }
    }

    public void clearBoard(){
       MinesweeperModel.getInstance().resetGame();
       gameOver = false;
       numOfMinesLeft = 3;
       gameOverX = -1;
       gameOverY = -1;
       invalidate();
   }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }
}


