package com.gokousei.fgo_tools;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ServantAdapter extends RecyclerView.Adapter {
    private LayoutInflater mInflater;
    private static Context mContext;
    private String[] servant;

    public ServantAdapter(Context context) {
        servant = new ServantData(context.getResources()).getServantData();
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DefaultViewHolder(mInflater.inflate(R.layout.servant_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DefaultViewHolder) holder).textView.setText(servant[position]);
        ((DefaultViewHolder) holder).imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return servant.length;
    }

    private static class DefaultViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private LinearLayout linearLayout;

        public DefaultViewHolder(final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.servant_item_textView);
            imageView = itemView.findViewById(R.id.servant_item_imageView);
            linearLayout = itemView.findViewById(R.id.servant_item);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(itemView.getContext(), textView.getText(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(itemView.getContext(), ServantInfoActivity.class);
                    intent.putExtra("servant_name",textView.getText());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
