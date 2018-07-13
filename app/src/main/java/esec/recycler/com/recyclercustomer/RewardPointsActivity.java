package esec.recycler.com.recyclercustomer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


import esec.recycler.com.recyclercustomer.countdown.CountdownTimer;
import esec.recycler.com.recyclercustomer.ui.home.HomeActivity;
import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class RewardPointsActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    //https://stackoverflow.com/questions/9741300/charts-for-android








    ArrayList<Entry> entries;





    Toolbar toolbar;


    Button btn_redeem;

    PieChart pieChart1;


    LinearLayout  ln_spentpoints,ln_availablepoints,ln_availablepointsblue;

    TextView txt_points,txt_totalpoints;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RewardPointsActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_points);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Reward Points");
         pieChart1 = (PieChart) findViewById(R.id.pieChartView);

        ln_spentpoints = (LinearLayout) findViewById(R.id.ln_spentpoints);
        ln_availablepoints = (LinearLayout) findViewById(R.id.ln_availablepoints);
        ln_availablepointsblue = (LinearLayout) findViewById(R.id.ln_availablepointsblue);


        txt_points =(TextView)findViewById(R.id.txt_points);


        txt_totalpoints =(TextView)findViewById(R.id.txt_totalpoints);



        ln_availablepoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  txt_points.setText("1250");
            }
        });


        //what about status of my appraisal?pls tell me
        ln_availablepointsblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  txt_points.setText("1250");
            }
        });


      //  pieChartView = (PieChartView) findViewById(R.id.pieChartView);


       // pieChart1.setHoleRadius(30f);


        pieChart1.setUsePercentValues(true);
/*

        SpannableStringBuilder sb = new SpannableStringBuilder(str1 + str2);
        ForegroundColorSpan fcs1 = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan fcs2 = new ForegroundColorSpan(Color.GREEN);
        sb.setSpan(fcs1, 0, str1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(fcs2, str1.length(), str1.length() + str2.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        pieChart1.setCenterText(sb);
        pieChart1.setCenterTextSize(20f);
*/











        ArrayList<Entry> yvalues = new ArrayList<Entry>();



        yvalues.add(new Entry(50f, 0));
        yvalues.add(new Entry(25f, 1));
        yvalues.add(new Entry(25f, 2));


        PieDataSet dataSet = new PieDataSet(yvalues, "");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("");
        xVals.add("");
        xVals.add("");


        PieData data = new PieData(xVals, dataSet);



        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart1.setData(data);
        pieChart1.setDescription("");
      //  pieChart1.setDescription("This is Pie Chart");
       // pieChart1.setDrawHoleEnabled(true);
        //pieChart1.setTransparentCircleRadius(58f);

        pieChart1.setHoleRadius(78f);
        dataSet.setColors(new int[] { Color.parseColor("#EE5F61"),Color.parseColor("#CA84DA"), Color.parseColor("#52B6F2")});

     //   data.setValueTextSize(13f);
        data.setDrawValues(false);
        //data.setValueTextColor(Color.DKGRAY);

        pieChart1.setOnChartValueSelectedListener(this);

        pieChart1.animateXY(1400, 1400);
        pieChart1.setDrawSliceText(false);
        btn_redeem=(Button)findViewById(R.id.btn_redeem);
        pieChart1.getLegend().setEnabled(false);

        btn_redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(RewardPointsActivity.this,RedeemCommingSoon.class);
                startActivity(i);
            }
        });

       // set(pieView);
    }
    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {


        System.out.println("VAL SELECTED"+h+"dataset"+h.getXIndex());


        if(h.getXIndex()==0){


            txt_points.setText("2500");
            txt_totalpoints.setText("TOTAL POINTS");
        }

        if(h.getXIndex()==1){
            txt_points.setText("1250");
            txt_totalpoints.setText("SPENT POINTS");
        }

        if(h.getXIndex()==2){
            txt_points.setText("1250");
            txt_totalpoints.setText("AVAILABLE POINTS");
        }

       /* if(dataSetIndex != 0){
            txt_points.setText("5000");
        }*/


        if (e == null)
            return;

       // Toast.makeText(getApplicationContext(),""+e.getVal(),Toast.LENGTH_SHORT);
      /* // Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);*/
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    /*private void set(PieView pieView) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        pieHelperArrayList.add(new PieHelper(35, Color.RED));
        pieHelperArrayList.add(new PieHelper(65, Color.rgb(102,204,0)));

        pieView.setDate(pieHelperArrayList);
        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            @Override
            public void onPieClick(int index) {
                if (index != PieView.NO_SELECTED_INDEX) {
                   // textView.setText(index + " selected");
                } else {
                   // textView.setText("No selected pie");
                }
            }
        });
        pieView.selectedPie(2);
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