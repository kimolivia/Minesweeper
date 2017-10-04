package hu.ait.android.minesweeper;

import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutContent;
    private int x;

    private MinesweeperView MinesweepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnReset = (Button) findViewById(R.id.btnReset);
        //final MinesweeperView MinesweepView = (MinesweeperView) findViewById(R.id.MinesweeperView);
        MinesweepView = (MinesweeperView) findViewById(R.id.MinesweeperView);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MinesweepView.clearBoard();
            }
        });


        final ToggleButton flagged = (ToggleButton) findViewById(R.id.btnToggle);
        flagged.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean flagModeOn) {
                if(flagModeOn){
                    flagged.setChecked(true);
                }
                else{
                    flagged.setChecked(false);
                }
                //Snackbar.make(MinesweepView, String.valueOf(flagged.isChecked()), Snackbar.LENGTH_LONG).show();
            }
        });
        }

    public boolean isFlagChecked(){
        ToggleButton flagged = (ToggleButton) findViewById(R.id.btnToggle);
        return flagged.isChecked();
    }

    public void gameOver(){
        Snackbar.make(MinesweepView, "Game Over", Snackbar.LENGTH_LONG).show();

    }

    public void gameWon(){
        Snackbar.make(MinesweepView, "You Won!", Snackbar.LENGTH_LONG).show();

    }


    }
