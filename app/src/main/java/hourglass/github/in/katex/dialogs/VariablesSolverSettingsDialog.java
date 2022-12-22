package hourglass.github.in.katex.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import hourglass.github.in.katex.R;
import hourglass.github.in.katex.activities.VariablesSolver;
import hourglass.github.in.katex.activities.VariablesSolverSettings;

public class VariablesSolverSettingsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String [] dp_array = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Current decimal places: " + VariablesSolver.loadDP())
                .setItems(dp_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        VariablesSolver.saveDP(String.valueOf(dp_array[which]));
                        Toast.makeText(getActivity(), "Updated to " + String.valueOf(dp_array[which]) + " decimal places", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
