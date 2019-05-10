package com.example.test;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.*;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    TextView    text1;
    TextView    text2;
    Button      button1;
    String      svtext1 =   "sv_text1";
    String      svtext2 =   "sv_text2";
    String      text1Val;
    String      text2Val;
    TextView    url;
    ArrayList<Items> items_list = new ArrayList<Items>();
    DrawerLayout mLayout;
    //Toolbar fragment_toolbar;


    public static int test_static;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);

        if (savedInstanceState != null) {
            if (savedInstanceState.getString(svtext1) != null){
                text1.setText(savedInstanceState.getString(svtext1));
            }
            if (savedInstanceState.getString(svtext2) != null) {
                text2.setText(savedInstanceState.getString(svtext2));
            }
        }

        //button click using onclicklistener
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                text1.setText("Updated Text1");
                text1Val = "Updated Text1";
                //Toast toast_msg = new Toast(getApplicationContext());
                //toast_msg.setGravity(Gravity.TOP,0,0);
                //toast_msg.setview(layout);
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_test), LENGTH_LONG).show();
            }
        } );

        //use of intents
        url = findViewById(R.id.url);
        url.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent_url = new Intent(Intent.ACTION_VIEW, Uri.parse(url.getText().toString()));
                startActivity(intent_url);
            }
        });

        System.out.println("*********************Static *******************" +Items.test_static);

        items_list.add(new Items("Android", "Samsung Note 8"));
        items_list.add(new Items("Android", "Pixel 3"));
        items_list.add(new Items("Apple", "Iphone XR"));
        items_list.add(new Items("Apple", "Iphone XS"));
        items_list.add(new Items("Android", "Samsung S10e"));
        items_list.add(new Items("Apple", "Iphone 8"));


        // static variables/methods can be directly accessed without creating objects
        System.out.println("*********************Static *******************" +Items.test_static);

        ItemsAdapter adapter    = new ItemsAdapter(this,items_list);
        ListView list_main           = findViewById(R.id.list);
        list_main.setAdapter(adapter);

        list_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"this is a sample toast",Toast.LENGTH_SHORT).show();
            }
        });

        /*
        ArrayList<String> simpleitems = new ArrayList<String>();
        simpleitems.add("android");
        simpleitems.add("apple");
        simpleitems.add("note8");
        simpleitems.add("note8");
        simpleitems.add("note8");
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, simpleitems);
        list.setAdapter(itemsAdapter);
        */

        mLayout = findViewById(R.id.fragment_drawer);

        //create toolbar and set the Action Bar
        Toolbar fragment_toolbar = findViewById(R.id.fragment_toolbar);
        setSupportActionBar(fragment_toolbar);
        ActionBar actionbar = getSupportActionBar();

        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);

        //create Fragment and fragment transaction to commit the fragment
        Fragment frag1 = new Fragment_test1();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, frag1);
        ft.commit();

        NavigationView navigationView = findViewById(R.id.nav_beaches);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // show selected item in the drawer and display the selected fragment
                        menuItem.setChecked(true);
                        //displaySelectedScreen(menuItem.getItemId());
                        Fragment frag1 = new Fragment_test1();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_frame, frag1);
                        ft.commit();

                        mLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
    }

    @Override
    //open the navigation drawer when menu icon is clicked instead of the swipe from the left
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //button click method call referenced in the XML layout
    public void button2Click(View v){
        text2.setText("Updated Text2");
        text2Val = "Updated Text2";
    }

    //save and restore the state of variables during device rotations
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(svtext1,text1Val);
        outState.putString(svtext2, text2Val);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text1Val = savedInstanceState.getString(svtext1);
        text2Val = savedInstanceState.getString(svtext2);
    }

    //same method name can be given for click and perform diff function for each ID
    /*
    public void onButtonClick(View v){
        switch (v.getId()){
            case R.id.button2:
                text2.setText("Updated Text2 onbuttonclick");
                break;
        }
    }

    */
}
