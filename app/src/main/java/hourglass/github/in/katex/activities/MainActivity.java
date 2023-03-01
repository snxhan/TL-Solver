package hourglass.github.in.katex.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import hourglass.github.in.katex.R;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    // Shared Preferences
    static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialViews();;

        // Shared Preferences
        sharedPreferences = getSharedPreferences("appSettings", Context.MODE_PRIVATE);

    }

    // Menu button in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainactivity_menu, menu);
        return true;
    }

    // Options Menu
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent a = new Intent(this,MainActivity.class);
                this.startActivity(a);
                return true;
            case R.id.mainActivity_settings:
                Intent b = new Intent(this,MainActivitySettings.class);
                this.startActivity(b);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // Shared Preferences - decimal places (dp)
    public static void saveDP(String new_dp) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dp", new_dp);
        editor.apply();
    }
    // Shared Preferences - decimal places (dp)
    public static String loadDP() {
        // 3 as default
        return sharedPreferences.getString("dp", "3");
    }

    // animation for back button pressed NOT USED
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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

    public void formulaListView(View view) {
        Log.d(TAG,"Complex Number View clicked");
        Intent intent = new Intent(getApplicationContext(),FormulaList.class);
        startActivity(intent);
    }
}