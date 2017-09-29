package hu.ait.android.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by oliviakim on 9/24/17.
 */


public class MinesweeperView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintBomb;
    private Paint paintInside;
    private Paint paintNumber;
    private Paint paintFlag;

    public MinesweeperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // Style for different graphics

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStrokeWidth(5);
        paintLine.setStyle(Paint.Style.STROKE);

        paintInside = new Paint();
        paintInside.setColor(Color.RED);
        paintLine.setStrokeWidth(5);
        paintLine.setStyle(Paint.Style.STROKE);

        paintBomb = new Paint();
        paintBomb.setColor(Color.RED);
        paintBomb.setStrokeWidth(5);
        paintBomb.setStyle(Paint.Style.STROKE);

        paintNumber = new Paint();
        paintNumber.setColor(Color.RED);
        paintNumber.setStrokeWidth(5);
        paintNumber.setTextSize(120);
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


    private void drawContents(Canvas canvas) {
        MinesweeperModel field = MinesweeperModel.getInstance();
        field.fillMatrix();
        field.setBombLocations();
        field.setAllBombNumbers();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(MinesweeperModel.getInstance().getFieldContent(i,j).isMine()){
                    canvas.drawText("M",
                            i*getWidth()/5, (j + 1)*getHeight()/5,
                            paintNumber);
                } else {
                    int numOfMines = field.getBombNumber(i, j);
                    canvas.drawText(String.valueOf(numOfMines),
                            i * getWidth() / 5, (j + 1) * getHeight() / 5,
                            paintNumber);
                }

                /*
                canvas.drawLine(i * getWidth() / 5 , j * getHeight() / 5,
                        (i + 1) * getWidth() / 5 ,
                        (j + 1) * getHeight() / 5 , paintBomb);

                canvas.drawLine((i + 1) * getWidth() / 5 , j * getHeight() / 5 ,
                        i * getWidth() / 5 , (j + 1) * getHeight() / 5 , paintBomb);

                Log.d("drew text", "?");
                */
            }
                /*
                if (MinesweeperModel.getInstance().getFieldContent(i,j).isMine()) {
                    canvas.drawLine(i * getWidth() / 5 , j * getHeight() / 5,
                            (i + 1) * getWidth() / 5 ,
                            (j + 1) * getHeight() / 5 , paintBomb);

                    canvas.drawLine((i + 1) * getWidth() / 5 , j * getHeight() / 5 ,
                            i * getWidth() / 5 , (j + 1) * getHeight() / 5 , paintBomb);

                }

                }

            }
            */
        }
    }



/*
    private void drawContents(Canvas canvas) {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(MinesweeperModel.getInstance().getFieldContent(i,j).isMine()){

                    canvas.drawLine(i * getWidth() / 5 , j * getHeight() / 5,
                            (i + 1) * getWidth() / 5 ,
                            (j + 1) * getHeight() / 5 , paintBomb);

                    canvas.drawLine((i + 1) * getWidth() / 5 , j * getHeight() / 5 ,
                            i * getWidth() / 5 , (j + 1) * getHeight() / 5 , paintBomb);

                }
            }
        }
    }
*/



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int tX = ((int) event.getX()) / (getWidth() / 5);
            int tY = ((int) event.getY()) / (getHeight() / 5);


        }
        return true;
    }

    // maybe a set board function?

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }
}


