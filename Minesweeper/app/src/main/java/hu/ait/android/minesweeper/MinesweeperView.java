package hu.ait.android.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private int numberOfMinesLeft = 3;
    private boolean gameOver = false;


    public MinesweeperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
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
                Log.d("here", "0");

                if (gameOver) {
                    Log.d("here", "1");
                    ((MainActivity) getContext()).gameOver();

                }
                if (MinesweeperModel.getInstance().getFieldContent(i, j).isAlreadyClicked()) {
                    Log.d("here", "2");
                    if (MinesweeperModel.getInstance().getFieldContent(i, j).isFlag()) {
                        Log.d("here", "3");

                        if (!MinesweeperModel.getInstance().getFieldContent(i, j).isMine()) {
                            Log.d("here", "4");
                            canvas.drawText("F",
                                    i * getWidth() / 5, (j + 1) * getHeight() / 5,
                                    paintBomb);
                            gameOver = true;

                        } else {
                            Log.d("here", "5");
                            canvas.drawText("F",
                                    i * getWidth() / 5, (j + 1) * getHeight() / 5,
                                    paintFlag);
                        }
                    } else if (MinesweeperModel.getInstance().getFieldContent(i, j).isMine()) {
                        Log.d("here", "6");
                        canvas.drawText("M", i * getWidth() / 5, (j + 1) * getHeight() / 5, paintEndBomb);
                        gameOver = true;

                    } else {
                        Log.d("here", "7");
                        canvas.drawText(String.valueOf(numOfMines),
                                i * getWidth() / 5,
                                (j + 1) * getHeight() / 5,
                                paintNumber);

                    }
                }
            }
        }
    }




/*
                if (numberOfMinesLeft != 0 || !gameOver) {
                    if (MinesweeperModel.getInstance().getFieldContent(i, j).isAlreadyClicked()) {
                        if (MinesweeperModel.getInstance().getFieldContent(i, j).isFlag()) {
                            if (!MinesweeperModel.getInstance().getFieldContent(i, j).isMine()) {
                                gameOver = true;
                                canvas.drawText("M",
                                        i * getWidth() / 5, (j + 1) * getHeight() / 5,
                                        paintFlag);
                            } else {
                                canvas.drawText("F",
                                        i * getWidth() / 5, (j + 1) * getHeight() / 5,
                                        paintFlag);
                            }

                        } else if (MinesweeperModel.getInstance().getFieldContent(i, j).isMine()) {

                            gameOver = true;

                            canvas.drawText("M", i * getWidth() / 5, (j + 1) * getHeight() / 5, paintBomb);

                        } else {
                            canvas.drawText(String.valueOf(numOfMines),
                                    i * getWidth() / 5,
                                    (j + 1) * getHeight() / 5,
                                    paintNumber);
                        }
                    }

                }
                */




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

            if(((MainActivity) getContext()).isFlagChecked() &&
                    !MinesweeperModel.getInstance().getFieldContent(tX,tY).isAlreadyClicked()) {
                MinesweeperModel.getInstance().getFieldContent(tX,tY).setFlag(true);
            }

            MinesweeperModel.getInstance().getFieldContent(tX,tY).setIsAlreadyClicked(true);


            invalidate();

            return true;
        }
        return super.onTouchEvent(event);

    }


   public void clearBoard(){
       MinesweeperModel.getInstance().resetGame();
       gameOver = false;
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


