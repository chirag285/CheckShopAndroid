package com.example.checkshop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class CustomerListProductAdapter extends ArrayAdapter<RowItemCustomer> {
    public SparseBooleanArray mCheckStates;
    Context context;
    private static int selectedIndex;
    private int selectedColor = Color.parseColor("#1b1b1b");

    public CustomerListProductAdapter(Context context, int resourceId,
                                      List<RowItemCustomer> items) {
        super(context, resourceId, items);
        this.context = context;
        mCheckStates = new SparseBooleanArray(items.size());
        //selectedIndex = -1;

    }


    public static void setSelectedIndex(int ind) {
        selectedIndex = ind;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItemCustomer rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitemcustomer, null);
            holder = new ViewHolder();
            holder.productname = convertView.findViewById(R.id.productname);
            //holder.avilability = convertView.findViewById(R.id.avilability);
            holder.produxtdis = convertView.findViewById(R.id.produxtdis);
            holder.cost = convertView.findViewById(R.id.cost);
            holder.avi = convertView.findViewById(R.id.avi);
           // holder.vname = convertView.findViewById(R.id.vname);
            holder.im_language = convertView.findViewById(R.id.im_language);

            convertView.setTag(holder);

        }
        else
            holder = (ViewHolder) convertView.getTag();
        holder.productname.setText(rowItem.getTitle());
        //holder.avi.setText(rowItem.getAvailability());
        holder.produxtdis.setText(rowItem.getDesc());
        holder.cost.setText(rowItem.getCost());
       // holder.vname.setText(rowItem.getCompany()+" "+":"+" "+rowItem.getName());
       /* final ImagePopup imagePopup = new ImagePopup(context);
        imagePopup.setWindowHeight(200); // Optional
        imagePopup.setWindowWidth(200); // Optional
        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
        imagePopup.setFullScreen(true); // Optional
        imagePopup.setHideCloseIcon(true);  // Optional
        imagePopup.setImageOnClickClose(true);  // Optional
        if(!rowItem.getContactPhoto().equalsIgnoreCase(null))
        {
            Uri uri = Uri.parse(rowItem.getContactPhoto());*/
       // Uri uri = Uri.parse(rowItem.getContactPhoto());
           // Picasso.with(context).load(uri).resize(70, 70).into(holder.im_language);
        if(position==0) {
            holder.im_language.setImageResource(R.drawable.s1);
        }
        else if(position==1) {
            holder.im_language.setImageResource(R.drawable.s2);
        }

        else if(position==2) {
            holder.im_language.setImageResource(R.drawable.s3);
        }

        else if(position==3) {
            holder.im_language.setImageResource(R.drawable.s4);
        }

        else if(position==4) {
            holder.im_language.setImageResource(R.drawable.s5);
        }
        else if(position==5) {
            holder.im_language.setImageResource(R.drawable.s6);
        }
        else if(position==6) {
            holder.im_language.setImageResource(R.drawable.s7);
        }
        else if(position==7) {
            holder.im_language.setImageResource(R.drawable.s8);
        }
        return convertView;
    }

    /*private view holder class*/
    private class ViewHolder {
        private TextView productname, avilability, produxtdis,cost,avi,vname,holidaystotal;
        ImageView im_language;
        CheckBox checkBox;
    }
}
