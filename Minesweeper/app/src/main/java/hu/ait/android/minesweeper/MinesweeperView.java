package hu.ait.android.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by oliviakim on 9/24/17.
 */


public class MinesweeperView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintBomb;
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

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,getWidth(),getHeight(), paintBg);

        drawGameArea(canvas);

        // drawContents(canvas);

    }

    private void drawContents(Canvas canvas) {
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



        }
        return true;
    }


}

