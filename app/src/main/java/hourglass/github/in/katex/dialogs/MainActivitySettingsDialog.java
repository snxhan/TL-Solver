package hourglass.github.in.katex.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import hourglass.github.in.katex.activities.MainActivity;

public class MainActivitySettingsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String [] dp_array = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Current decimal places: " + MainActivity.loadDP())
                .setItems(dp_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        MainActivity.saveDP(String.valueOf(dp_array[which]));
                        Toast.makeText(getActivity(), "Updated to " + String.valueOf(dp_array[which]) + " decimal places", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
