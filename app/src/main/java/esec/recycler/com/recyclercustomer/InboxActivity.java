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

import esec.recycler.com.recyclercustomer.adapter.InboxAdapter;
import esec.recycler.com.recyclercustomer.model.InboxModel;
import esec.recycler.com.recyclercustomer.ui.home.HomeActivity;


public class InboxActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    InboxAdapter mAdapter;

    private List<InboxModel> statementlist = new ArrayList<>();




    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, InboxActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inbox");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new InboxAdapter(statementlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

    }


    private void prepareMovieData() {
        InboxModel statement_model = new InboxModel("Today", String.valueOf(R.mipmap.open_msg), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new InboxModel("", String.valueOf(R.mipmap.open_msg), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new InboxModel("", String.valueOf(R.mipmap.open_msg), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new InboxModel("", String.valueOf(R.mipmap.new_msg), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new InboxModel("", String.valueOf(R.mipmap.new_msg), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new InboxModel("", String.valueOf(R.mipmap.new_msg), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new InboxModel("", String.valueOf(R.mipmap.new_msg), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new InboxModel("", String.valueOf(R.mipmap.new_msg), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new InboxModel("", String.valueOf(R.mipmap.new_msg), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        mAdapter.notifyDataSetChanged();
    }


    /*@Override
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
