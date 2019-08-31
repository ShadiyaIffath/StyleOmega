package com.example.iffath.style_omega.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.iffath.style_omega.Fragment.ViewCart;
import com.example.iffath.style_omega.Fragment.home_page;
import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;
import butterknife.ButterKnife;

public class home extends AppCompatActivity {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    FragmentManager fragmentManager;
    private NavigationView navDrawer;
    private View navigationHeader;
    TextView nametxt;
    String username;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //display home fragment as the default for the home screen
          //homeFragment(); //fail
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.display_screen, home_page.getInstance())
                    .commit();
        }

        //retrieve logged user's username
          sharedPreferences = getSharedPreferences(Launcher.keyPreference, Context.MODE_PRIVATE);
          username = sharedPreferences.getString("username",null);

        // The custom toolbar replaces the action bar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.home_drawer); //drawer state is settled
        drawerToggle = setupDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        //navigation view is settled along with the header text field value
        navDrawer = findViewById(R.id.navigationHome);
        navigationHeader = navDrawer.getHeaderView(0);
        nametxt = ButterKnife.findById(navigationHeader,R.id.navigation_user);
        nametxt.setText(username); //set username in the navigation header
        setupDrawerContent(navDrawer);
    }

    private void homeFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.display_screen, home_page.getInstance())
                .commit();
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    //method which doesn't change activity when user clicks back button when the drawer is open
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(@NotNull NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NotNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(@NotNull MenuItem menuItem) {       //fragment navigation click event logic

        Fragment fragment = null;
        Class fragmentLogic;
        int id =menuItem.getItemId(); //id of the menu item selected

        switch(id){
            case R.id.nav_homepage:
                Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
                //fragmentLogic = home_page.class;
                break;
//            case R.id.nav_history:
//                fragmentLogic = History.class;
//                break;
            case R.id.nav_viewCart:
                Toast.makeText(this,"My Cart",Toast.LENGTH_SHORT).show();
                fragmentLogic = ViewCart.class;
                break;

            case R.id.nav_logout:
                Toast.makeText(this,"Logged Out",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                Intent intent = new Intent(this,Launcher.class);
                startActivity(intent);

                default:
                    fragmentLogic = home_page.class;
        }
        try {
            //fragment = (Fragment) fragmentLogic.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.display_screen, fragment);
//        fragmentTransaction.commit();//replaces existing fragment with the selected menu item

        menuItem.setChecked(true); //this highlights to show what the current fragment is

        setTitle(menuItem.getTitle()); //the selected option title is displayed on the toolbar

        drawer.closeDrawer(GravityCompat.START); //close the drawer

    }



}
