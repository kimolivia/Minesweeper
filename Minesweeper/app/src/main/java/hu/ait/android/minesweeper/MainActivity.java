package hu.ait.android.minesweeper;

import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutContent;
    private MinesweeperView MinesweepView;
    private Chronometer timer;
    private long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnReset = (Button) findViewById(R.id.btnReset);
        MinesweepView = (MinesweeperView) findViewById(R.id.MinesweeperView);
        timer = (Chronometer) findViewById(R.id.btnTimer);
        timer.start();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.setBase(SystemClock.elapsedRealtime());
                startTime = 0;
                timer.start();
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
            }
        });
        }

    public boolean isFlagChecked(){
        ToggleButton flagged = (ToggleButton) findViewById(R.id.btnToggle);
        return flagged.isChecked();
    }

    public void gameOver(){
        timer.stop();
        Snackbar.make(MinesweepView, R.string.gameOver, Snackbar.LENGTH_LONG).show();

    }

    public void gameWon(){
        Snackbar.make(MinesweepView, R.string.gameWon, Snackbar.LENGTH_LONG).show();

    }


    }
