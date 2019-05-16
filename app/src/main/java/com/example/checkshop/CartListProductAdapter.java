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

import java.util.List;


public class CartListProductAdapter extends ArrayAdapter<RowItemCart> {
    public SparseBooleanArray mCheckStates;
    Context context;
    private static int selectedIndex;
    private int selectedColor = Color.parseColor("#1b1b1b");

    public CartListProductAdapter(Context context, int resourceId,
                                  List<RowItemCart> items) {
        super(context, resourceId, items);
        this.context = context;
        mCheckStates = new SparseBooleanArray(items.size());
        //selectedIndex = -1;

    }



    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItemCart rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cart_item, null);
            holder = new ViewHolder();
            holder.productname = convertView.findViewById(R.id.cartItemName);
            holder.cost = convertView.findViewById(R.id.cartItemPrice);
           // holder.im_language = convertView.findViewById(R.id.cartItemIcon);
           holder.TextViewID = convertView.findViewById(R.id.TextViewID);
            //holder.cartItemTotal = convertView.findViewById(R.id.cartItemTotal);
            convertView.setTag(holder);

        }
        else
            holder = (ViewHolder) convertView.getTag();
        holder.productname.setText(rowItem.getTitle());
        holder.TextViewID.setText(rowItem.getIndex());
        //String x = "x " + String.valueOf(5);
        //holder.cartItemQuantity.setText(x);
        holder.cost.setText("Rs"+" "+rowItem.getCost());
        /*Picasso.with(context).load(uri).resize(70, 70).into(holder.im_language);*/
        int itemPrice=0;

            itemPrice = Integer.valueOf((rowItem.getCost()));
            itemPrice = itemPrice;

        //holder.cartItemTotal.setText(""+itemPrice);
        /*if (position == selectedIndex) {
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.teal));
        }
        else {
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.white));
        }*/


        return convertView;
    }

    /*private view holder class*/
    private class ViewHolder {
        private TextView productname, TextViewID, cartItemTotal,cost,avi,vname,holidaystotal;
        ImageView im_language;
        CheckBox checkBox;
    }
}
