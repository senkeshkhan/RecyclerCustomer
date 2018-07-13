package esec.recycler.com.recyclercustomer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import esec.recycler.com.recyclercustomer.adapter.CollectingCentersAdapter;
import esec.recycler.com.recyclercustomer.model.CollectingCentersModel;
import esec.recycler.com.recyclercustomer.ui.home.HomeActivity;


public class CollectingCentersActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    CollectingCentersAdapter mAdapter;

    private List<CollectingCentersModel> statementlist = new ArrayList<>();


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CollectingCentersActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collecting_centers);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Collection Centers");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CollectingCentersAdapter(statementlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

    }

    private void prepareMovieData() {
        CollectingCentersModel statement_model = new CollectingCentersModel("Today", String.valueOf(R.mipmap.red_bin), "123456789", "KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new CollectingCentersModel("", String.valueOf(R.mipmap.yellow_bin), "123456789", "KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new CollectingCentersModel("", String.valueOf(R.mipmap.blue_bin), "123456789", "KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new CollectingCentersModel("Yesterday", String.valueOf(R.mipmap.green_bin), "123456789", "KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new CollectingCentersModel("", String.valueOf(R.mipmap.red_bin), "123456789", "KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new CollectingCentersModel("", String.valueOf(R.mipmap.yellow_bin), "123456789", "KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new CollectingCentersModel("05-03-2018", String.valueOf(R.mipmap.blue_bin), "123456789", "KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new CollectingCentersModel("", String.valueOf(R.mipmap.green_bin), "123456789", "KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new CollectingCentersModel("", String.valueOf(R.mipmap.red_bin), "123456789", "KES 12,345,69.00");
        statementlist.add(statement_model);

        mAdapter.notifyDataSetChanged();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

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
