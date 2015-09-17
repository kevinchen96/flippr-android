package me.flippr.utilities;

import me.flippr.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


public class NewCardDialog extends DialogFragment {
	
	public EditText name;
	public EditText position;
	public EditText company;
	public EditText location;
	public DatePicker date;
	
	public interface NoticeDialogListener {
        public void onDialogPositiveClick(NewCardDialog dialog);
        public void onDialogNegativeClick(NewCardDialog dialog);
    }
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    View view = inflater.inflate(R.layout.dialog_card, null);
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(view)
	    // Add action buttons
	           .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	        	   public void onClick(DialogInterface dialog, int id) {
                       // Send the positive button event back to the host activity
                       mListener.onDialogPositiveClick(NewCardDialog.this);
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // Send the negative button event back to the host activity
                       mListener.onDialogNegativeClick(NewCardDialog.this);
                   }
               });
	    name = (EditText) view.findViewById(R.id.name);
	    position = (EditText) view.findViewById(R.id.position);
	    location = (EditText) view.findViewById(R.id.location);
	    company = (EditText) view.findViewById(R.id.company);
	    date = (DatePicker) view.findViewById(R.id.date);
	    return builder.create();
	}
	
	// Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;
    
    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
