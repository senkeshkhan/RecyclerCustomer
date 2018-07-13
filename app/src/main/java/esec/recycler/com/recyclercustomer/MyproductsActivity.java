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

import esec.recycler.com.recyclercustomer.adapter.MyProductsAdapter;
import esec.recycler.com.recyclercustomer.model.MyProductsModel;
import esec.recycler.com.recyclercustomer.ui.home.HomeActivity;


public class MyproductsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MyProductsAdapter mAdapter;

    private List<MyProductsModel> statementlist = new ArrayList<>();
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MyproductsActivity.class);
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
        getSupportActionBar().setTitle("My Products");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MyProductsAdapter(statementlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

    }


    private void prepareMovieData() {
        MyProductsModel statement_model = new MyProductsModel("Today", String.valueOf(R.mipmap.bottles), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new MyProductsModel("", String.valueOf(R.mipmap.pro_icon1), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new MyProductsModel("", String.valueOf(R.mipmap.pro_icon2), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new MyProductsModel("", String.valueOf(R.mipmap.bottles), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new MyProductsModel("", String.valueOf(R.mipmap.pro_icon1), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new MyProductsModel("", String.valueOf(R.mipmap.pro_icon2), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new MyProductsModel("", String.valueOf(R.mipmap.bottles), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new MyProductsModel("", String.valueOf(R.mipmap.pro_icon1), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);

        statement_model = new MyProductsModel("", String.valueOf(R.mipmap.pro_icon2), "123456789","KES 12,345,69.00");
        statementlist.add(statement_model);


        mAdapter.notifyDataSetChanged();
    }


  /*  @Override
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
