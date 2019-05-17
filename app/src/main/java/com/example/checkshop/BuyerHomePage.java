package com.example.checkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class BuyerHomePage extends AppCompatActivity {

    /*  GridView grid;

      String[] web = {
              "Google",
              "Github",
              "Instagram",
              "Facebook",
              "Flickr",
              "Pinterest",
              "Quora",
              "Twitter"

      } ;
      int[] imageId = {
              R.drawable.1,
              R.drawable.s2,
              R.drawable.3,
              R.drawable.4,
              R.drawable.5,
              R.drawable.6,
              R.drawable.7,
              R.drawable.8
      };
  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home_page);

       /* grid = findViewById(R.id.grid);

        custom_grid_view adapter = new custom_grid_view(BuyerHomePage.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(BuyerHomePage.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
    }*/
    }
}