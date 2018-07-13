package esec.recycler.com.recyclercustomer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;



import esec.recycler.com.recyclercustomer.countdown.CountdownTimer;
import esec.recycler.com.recyclercustomer.ui.home.HomeActivity;


public class ReferFriendActivity extends AppCompatActivity {
    //https://stackoverflow.com/questions/9741300/charts-for-android
    TextView txt_resendbtn, txt_countdown;
    //PinEntryEditText pinEntry;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private CountdownTimer countdownTimer;
    private int SECONDS = 30;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ReferFriendActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_friend);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}