package hourglass.github.in.katex.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import hourglass.github.in.katex.R;
import hourglass.github.in.katex.dialogs.VariablesSolverSettingsDialog;

public class VariablesSolverSettings extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variablessolver_settings);
        String [] settingsArray = new String[]{"Set answer decimal places", ""};
        ArrayAdapter settingsAdapter = new ArrayAdapter(this, R.layout.activity_variablessolver_settings_listview, settingsArray);
        ListView settingsListView = (ListView) findViewById(R.id.variablesSolverSettingsList);
        settingsListView.setAdapter(settingsAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Variables Solver Settings");
        // Back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        settingsListView.setOnItemClickListener((parent, view, position, id) -> {
//                String entry = (String) parent.getItemAtPosition(position);
            // if set decimal places is selected
            if(position == 0) {
                // Open dialog
                VariablesSolverSettingsDialog dialog = new VariablesSolverSettingsDialog();
                dialog.show(getFragmentManager(), "example");

            }else
                Toast.makeText(getApplicationContext(), "not available yet!!", Toast.LENGTH_SHORT).show();
//                settingsAdapter.clear();
//                settingsAdapter.addAll(settingsArray);
//                settingsAdapter.notifyDataSetChanged();
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
//        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent a = new Intent(this,VariablesSolver.class);
//                this.startActivity(a);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
