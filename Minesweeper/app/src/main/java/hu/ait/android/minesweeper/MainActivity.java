package hu.ait.android.minesweeper;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    Button btnReset = (Button) findViewById(R.id.btnReset);
    final MinesweeperView MinesweepView = (MinesweeperView) findViewById(R.id.MinesweeperView);


        }
}
