package com.example.root.coffeebuzz;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends ListActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            ListView listDrinks=getListView();
            ArrayAdapter<Drinks> listAdapter=new ArrayAdapter<Drinks>(this,
                    android.R.layout.simple_list_item_1,
                    Drinks.drinks
            );
            listDrinks.setAdapter(listAdapter);
        }
    public void onListItemClick(ListView listView, View itemView, int position, long id){
        Intent intent=new Intent(DrinkCategoryActivity.this,DrinkActivity.class);
        startActivity(intent);
    }

    }


