package me.flippr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
public class BrowsePictureActivity extends Activity {

    // this is the action code we use in our intent, 
    // this way we know we're looking at the response from our own action
    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    public final static String NAME = "com.example.myfirstapp.MESSAGE";
    public final static String COMPANY = "com.example.myfirstapp.MESSAGE";
    public final static String TITLE = "com.example.myfirstapp.MESSAGE";
    public final static String LOCATION = "com.example.myfirstapp.MESSAGE";
	public final static String PICTURE = "com.example.myfirstapp.MESSAGE";
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        ((Button) findViewById(R.id.button1))
                .setOnClickListener(new OnClickListener() {

                    public void onClick(View arg0) {

                        // in onCreate or any event where your want the user to
                        // select a file
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,
                                "Select Picture"), SELECT_PICTURE);
                    }
                });
        Uri uri = Uri.parse(selectedImagePath);
        ((ImageView) findViewById(R.id.chooseImage)).setImageURI(uri);
    }
    public void Submit(View view){
    	Intent intent = new Intent(this, MyCardActivity.class);
    	Bundle extras = new Bundle();
    	EditText editText1 = (EditText) findViewById(R.id.editText1);
    	EditText editText2 = (EditText) findViewById(R.id.editText2);
    	String name = editText1.getText().toString() + " " + editText2.getText().toString();
    	extras.putString(NAME, name);
    	
    	EditText editText3 = (EditText) findViewById(R.id.editText3);
    	String company = editText3.getText().toString();
    	extras.putString(COMPANY, company);
    	
    	EditText editText4 = (EditText) findViewById(R.id.editText4);
    	String title = editText4.getText().toString();
    	extras.putString(TITLE, title);
    	
    	EditText editText5 = (EditText) findViewById(R.id.editText5);
    	String location = editText4.getText().toString();
    	extras.putString(LOCATION, location);
    	
    	extras.putString(PICTURE, selectedImagePath);
    	intent.putExtras(extras);
    	startActivity(intent);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
            // just some safety built in 
            if( uri == null ) {
                // TODO perform some logging or show user feedback
                return null;
            }
            // try to retrieve the image from the media store first
            // this will only work for images selected from gallery
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = managedQuery(uri, projection, null, null, null);
            if( cursor != null ){
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
            // this is our fallback here
            return uri.getPath();
    }

}