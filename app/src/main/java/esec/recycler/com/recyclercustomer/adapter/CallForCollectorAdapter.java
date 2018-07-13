package esec.recycler.com.recyclercustomer.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.model.CollectingCentersModel;


public class CallForCollectorAdapter extends RecyclerView.Adapter<CallForCollectorAdapter.MyViewHolder> {

    private List<CollectingCentersModel> statementList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_address, txt_zipcode, txt_mobile,txt_distance;

        public ImageView im_bin;

        public LinearLayout ln_statement_head;

        public MyViewHolder(View view) {
            super(view);
            txt_address = (TextView) view.findViewById(R.id.txt_address);
            txt_zipcode = (TextView)view.findViewById(R.id.txt_zipcode);
            txt_mobile = (TextView)view.findViewById(R.id.txt_mobile);

            txt_distance = (TextView)view.findViewById(R.id.txt_distance);

            im_bin = (ImageView)view.findViewById(R.id.im_bin);
            //ln_statement_head = (LinearLayout)view.findViewById(R.id.ln_statement_head);
        }
    }

    public CallForCollectorAdapter(List<CollectingCentersModel> statementList) {
        this.statementList = statementList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_call_forcollector, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CollectingCentersModel statement_content = statementList.get(position);

       /* if(!statement_content.getStatement_date().equalsIgnoreCase(""))
        {
            holder.ln_statement_head.setVisibility(View.VISIBLE);

            holder.txt_statement_date.setText(statement_content.getStatement_date());

        }
        else
        {
            holder.ln_statement_head.setVisibility(View.GONE);
        }*/

        /*holder.txt_title.setText(statement_content.getTitle());
        holder.txt_description.setText(statement_content.getDescription());
        holder.txt_value.setText(statement_content.getValue());*/

        holder.txt_address.setText(Html.fromHtml("<font color='#009fdf'> ADDRESS:</font> No, 123, Franklins Road, California, USA."));

        holder.txt_zipcode.setText(Html.fromHtml("<font color='#009fdf'> ZIPCODE:</font> 342256"));

        holder.txt_mobile.setText(Html.fromHtml("<font color='#009fdf'> MOBILE:</font> +606305566777"));

        holder.txt_distance.setText(Html.fromHtml("<font color='#9ec54c'> DISTANCE:</font> 2KMS"));

        holder.im_bin.setImageResource(Integer.valueOf(statement_content.getTitle()));

    }

    @Override
    public int getItemCount() {
        return statementList.size();
    }
}