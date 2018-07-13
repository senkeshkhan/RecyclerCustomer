package esec.recycler.com.recyclercustomer.adapter;


import android.graphics.Color;
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
import esec.recycler.com.recyclercustomer.model.InboxModel;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.MyViewHolder> {

    private List<InboxModel> statementList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_address, txt_time,txt_points, txt_mobile,txt_distance;
        LinearLayout llay_inbox;
        public ImageView im_bin;

        public LinearLayout ln_statement_head;

        public MyViewHolder(View view) {
            super(view);
            txt_address = (TextView) view.findViewById(R.id.txt_address);
            txt_time = (TextView)view.findViewById(R.id.txt_time);
            txt_distance = (TextView)view.findViewById(R.id.txt_distance);
            llay_inbox= (LinearLayout) view.findViewById(R.id.llay_inbox);
            im_bin = (ImageView)view.findViewById(R.id.im_bin);

            //ln_statement_head = (LinearLayout)view.findViewById(R.id.ln_statement_head);

        }
    }

    public InboxAdapter(List<InboxModel> statementList) {
        this.statementList = statementList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inbox, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InboxModel statement_content = statementList.get(position);

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

        if(position>=3)
        {
            holder.llay_inbox.setBackgroundColor(Color.parseColor("#FFC1BFBF"));
        }

        holder.txt_address.setText(Html.fromHtml("<font color='#009fdf'> Product Name:</font> Aqua Water Bottle"));

        holder.txt_time.setText(Html.fromHtml("<font color='#9ec54c'> 3 min ago</font> "));

        holder.txt_distance.setText(Html.fromHtml("<font color='#009fdf'> Deposited Location:</font> No, 123, Franklins Road, California, USA."));


        holder.im_bin.setImageResource(Integer.valueOf(statement_content.getTitle()));


    }

    @Override
    public int getItemCount() {
        return statementList.size();
    }
}