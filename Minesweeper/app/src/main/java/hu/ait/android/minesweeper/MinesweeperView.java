package hu.ait.android.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import hu.ait.android.minesweeper.MinesweeperModel;

/**
 * Created by oliviakim on 9/24/17.
 */


public class MinesweeperView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintBomb;
    private Paint paintNumber;
    private Paint paintEndBomb;
    private Paint paintFlag;
    private Paint paintRevealed;
    private boolean gameOver = false;
    private int numOfMinesLeft = 3;

   // private Bitmap bmFlag;


    public MinesweeperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.DKGRAY);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStrokeWidth(5);
        paintLine.setStyle(Paint.Style.STROKE);

        paintBomb = new Paint();
        paintBomb.setColor(Color.RED);
        paintBomb.setStrokeWidth(5);
        paintBomb.setTextSize(120);
        paintBomb.setStyle(Paint.Style.STROKE);

        paintEndBomb = new Paint();
        paintEndBomb.setColor(Color.RED);
        paintEndBomb.setStrokeWidth(10);
        paintEndBomb.setTextSize(150);

        paintFlag = new Paint();
        paintFlag.setColor(Color.YELLOW);
        paintFlag.setStrokeWidth(5);
        paintFlag.setTextSize(100);
        paintFlag.setStyle(Paint.Style.STROKE);

        paintNumber = new Paint();
        paintNumber.setColor(Color.GREEN);
        paintNumber.setStrokeWidth(5);
        paintNumber.setTextSize(110);
        paintNumber.setStyle(Paint.Style.STROKE);

        paintRevealed = new Paint();
        paintRevealed.setColor(Color.BLUE);
        paintRevealed.setStrokeWidth(5);
        paintRevealed.setTextSize(110);
        paintRevealed.setStyle(Paint.Style.STROKE);

        //bmFlag = BitmapFactory.decodeResource(getResources(), R.drawable.flag);
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

                if (!gameOver) {

                        if (MinesweeperModel.getInstance().getFieldContent(i, j).isAlreadyClicked()) {

                            if (MinesweeperModel.getInstance().getFieldContent(i, j).isFlag()) {

                                canvas.drawText(
                                        "F",
                                        i * getWidth() / 5,
                                        (j + 1) * getHeight() / 5,
                                        paintFlag
                                );


                            } else {

                                canvas.drawText(
                                        String.valueOf(numOfMines),
                                        i * getWidth() / 5,
                                        (j + 1) * getHeight() / 5,
                                        paintNumber
                                );
                            }
                        }

                    } else{
                        if (MinesweeperModel.getInstance().getFieldContent(i, j).isAlreadyClicked()) {
                            if (MinesweeperModel.getInstance().getFieldContent(i, j).isFlag()) {
                                canvas.drawText(
                                        "F",
                                        i * getWidth() / 5,
                                        (j + 1) * getHeight() / 5,
                                        paintFlag
                                );
                            } else if (MinesweeperModel.getInstance().getFieldContent(i, j).isMine()) {
                                canvas.drawText(
                                        "M",
                                        i * getWidth() / 5,
                                        (j + 1) * getHeight() / 5,
                                        paintEndBomb
                                );
                            } else {
                                canvas.drawText(
                                        String.valueOf(numOfMines),
                                        i * getWidth() / 5,
                                        (j + 1) * getHeight() / 5,
                                        paintNumber
                                );

                            }
                        } else {

                            if (MinesweeperModel.getInstance().getFieldContent(i, j).isFlag()) {
                                canvas.drawText(
                                        "F",
                                        i * getWidth() / 5,
                                        (j + 1) * getHeight() / 5,
                                        paintRevealed
                                );
                            } else if (MinesweeperModel.getInstance().getFieldContent(i, j).isMine()) {
                                canvas.drawText(
                                        "M",
                                        i * getWidth() / 5,
                                        (j + 1) * getHeight() / 5,
                                        paintRevealed
                                );
                            } else {
                                canvas.drawText(
                                        String.valueOf(numOfMines),
                                        i * getWidth() / 5,
                                        (j + 1) * getHeight() / 5,
                                        paintRevealed
                                );

                            }

                        }
                        if (numOfMinesLeft != 0) {
                            ((MainActivity) getContext()).gameOver();
                        } else {
                            ((MainActivity) getContext()).gameWon();
                        }
                    }
                }
            }
        }

    private void drawGameArea(Canvas canvas) {

        canvas.drawRect(0,0,getWidth(),getHeight(), paintLine);

        //vertical lines
        canvas.drawLine(getWidth() / 5, 0, getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(2 * getWidth() / 5, 0, 2 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(3 * getWidth() / 5, 0, 3 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(4 * getWidth() / 5, 0, 4 * getWidth() / 5, getHeight(), paintLine);

        //horizontal lines
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
                    MinesweeperModel.getInstance().getFieldContent(tX, tY).setFlag(true);
                    if (!MinesweeperModel.getInstance().getFieldContent(tX, tY).isMine()){
                        gameOver = true;

                } else {
                        numOfMinesLeft--;
                        if (numOfMinesLeft == 0){
                            gameOver = true;
                        }
                }
                } else {
                    if (MinesweeperModel.getInstance().getFieldContent(tX, tY).isMine()){
                        gameOver = true;
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


   public void clearBoard(){
       MinesweeperModel.getInstance().resetGame();
       gameOver = false;
       numOfMinesLeft = 3;
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


