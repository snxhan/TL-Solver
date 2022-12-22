package hourglass.github.in.katex.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import hourglass.github.in.katex.R;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialViews();;
    }

    private void setInitialViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Transmission Line Solver");
    }

    public void recyclerViewClick(View view) {
        Log.d(TAG,"Using Mathview in Recyclerview Clicked");
        Intent intent = new Intent(getApplicationContext(),MathViewListActivity.class);
        startActivity(intent);
    }

    public void layoutViewClick(View view) {
        Log.d(TAG,"Using MathView in Layout Clicked");
        Intent intent = new Intent(getApplicationContext(),MathviewInLayoutActivity.class);
        startActivity(intent);
    }

    public void addingAtRuntime(View view) {
        Log.d(TAG,"Adding Mathview at runtime Clicked");
        Intent intent = new Intent(getApplicationContext(),MathViewAdditionAtRuntime.class);
        startActivity(intent);
    }

    public void testCalculation(View view) {
        Log.d(TAG,"What is this?");
        Intent intent = new Intent(getApplicationContext(),MathViewTestCalculationActivity.class);
        startActivity(intent);
//        setContentView(R.layout.activity_testcalculation);
    }

    public void testRecyclerView(View view) {
        Log.d(TAG,"What is this?");
        Intent intent = new Intent(getApplicationContext(),MathViewRecyclerView.class);
        startActivity(intent);
    }

    public void complexNumberView(View view) {
        Log.d(TAG,"Complex Number View clicked");
        Intent intent = new Intent(getApplicationContext(),ComplexNumber.class);
        startActivity(intent);
    }

    public void variablesSolverView(View view) {
        Log.d(TAG,"Complex Number View clicked");
        Intent intent = new Intent(getApplicationContext(),VariablesSolver.class);
        startActivity(intent);
    }
}