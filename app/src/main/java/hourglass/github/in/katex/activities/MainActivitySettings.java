package hourglass.github.in.katex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import hourglass.github.in.katex.R;
import hourglass.github.in.katex.dialogs.MainActivitySettingsDialog;
import hourglass.github.in.katex.dialogs.VariablesSolverSettingsDialog;

public class MainActivitySettings extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity_settings);
        String[] settingsArray = new String[]{"Set decimal places", "Set rounding mode"};
        ArrayAdapter settingsAdapter = new ArrayAdapter(this, R.layout.activity_mainactivity_settings_listview, settingsArray);
        ListView settingsListView = (ListView) findViewById(R.id.mainActivitySettingsList);
        settingsListView.setAdapter(settingsAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("App Settings");
        // Back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        settingsListView.setOnItemClickListener((parent, view, position, id) -> {
//                String entry = (String) parent.getItemAtPosition(position);
            // if set decimal places is selected
            if (position == 0) {
                // Open dialog
//                VariablesSolverSettingsDialog dialog = new VariablesSolverSettingsDialog();
                MainActivitySettingsDialog dialog = new MainActivitySettingsDialog();
                dialog.show(getFragmentManager(), "example");
            } else
                Toast.makeText(getApplicationContext(), "not available yet!!", Toast.LENGTH_SHORT).show();
//                settingsAdapter.clear();
//                settingsAdapter.addAll(settingsArray);
//                settingsAdapter.notifyDataSetChanged();
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent a = new Intent(this, MainActivity.class);
//                this.startActivity(a);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
