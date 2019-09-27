package com.example.iffath.style_omega.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.iffath.style_omega.Fragment.ContactUs;
import com.example.iffath.style_omega.Fragment.DeliveryLocation;
import com.example.iffath.style_omega.Fragment.Detailed_item;
import com.example.iffath.style_omega.Fragment.History;
import com.example.iffath.style_omega.Fragment.Payment;
import com.example.iffath.style_omega.Fragment.Profile;
import com.example.iffath.style_omega.Fragment.TypeHome;
import com.example.iffath.style_omega.Fragment.ViewCart;
import com.example.iffath.style_omega.Fragment.home_page;
import com.example.iffath.style_omega.Fragment.searchResults;
import com.example.iffath.style_omega.Model.SingletonProduct;
import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;


public class home extends AppCompatActivity {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private NavigationView navDrawer;
    private View navigationHeader;
    private searchResults result;
    SearchView mSearchView;
    TextView nametxt;
    String username;
    SharedPreferences sharedPreferences;
    public static String loggedUser= null;

    public home() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Select Type");
        //display home fragment as the default for the home screen
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.display_screen, home_page.getInstance(),"Home")
                    .commit();
        }

        //retrieve logged user's username
          sharedPreferences = getSharedPreferences(Launcher.keyPreference, Context.MODE_PRIVATE);
          long id = sharedPreferences.getLong("user",0);
          User user = User.findById(User.class,id);
          username = user.getUsername();
          loggedUser = username;

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
        nametxt = navigationHeader.findViewById(R.id.navigation_user);
        nametxt.setText(username); //set username in the navigation header
        setupDrawerContent(navDrawer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    //method which doesn't change activity when user clicks back button when the drawer is open
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            FragmentManager manager = getSupportFragmentManager();
            if(manager.getBackStackEntryCount() > 0) {
                super.onBackPressed();
            }
            Fragment currentFragment = manager.findFragmentById(R.id.display_screen);
            if(currentFragment instanceof home_page){
                navDrawer.getMenu().getItem(0).setChecked(true);
            }
            else if(currentFragment instanceof History){
                navDrawer.getMenu().getItem(3).setChecked(true);
            }
            else if(currentFragment instanceof ContactUs){
                navDrawer.getMenu().getItem(4).setChecked(true);
            }
            else if(currentFragment instanceof DeliveryLocation){
                navDrawer.getMenu().getItem(2).setChecked(true);
            }
            else if(currentFragment instanceof Payment){
                navDrawer.getMenu().getItem(2).setChecked(true);
            }
            else if(currentFragment instanceof ViewCart){
                navDrawer.getMenu().getItem(2).setChecked(true);
            }
            else if(currentFragment instanceof searchResults){
                navDrawer.getMenu().getItem(0).setChecked(true);
            }
            else if(currentFragment instanceof Detailed_item) {
                navDrawer.getMenu().getItem(0).setChecked(true);
            }
            else if(currentFragment instanceof Profile) {
                navDrawer.getMenu().getItem(1).setChecked(true);
            }
            else if(currentFragment instanceof TypeHome) {
                navDrawer.getMenu().getItem(0).setChecked(true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        else if(item.getItemId() == R.id.search_icon){
            mSearchView = (SearchView) item.getActionView();
            mSearchView.setQueryHint("Search");
            mSearchView.setSubmitButtonEnabled(true);
            if(result == null){
                result = new searchResults();
            }

            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    result.setMyAdapterFilter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if(!newText.isEmpty()) {
                        result.setMyAdapterFilter(newText);
                    }
                    return false;
                }
            });
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.display_screen, result,"Home")
                    .addToBackStack("Home")
                    .commit();

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
        int id = menuItem.getItemId(); //id of the menu item selected
        switch(id){
            case R.id.nav_homepage:
                Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
                fragment = new home_page();
                break;
            case R.id.nav_history:
                Toast.makeText(this,"History",Toast.LENGTH_SHORT).show();
                fragment = new History();
                break;
            case R.id.nav_viewCart:
                Toast.makeText(this,"My Cart",Toast.LENGTH_SHORT).show();
                fragment = new ViewCart();
                break;

            case R.id.nav_editprofile:
                Toast.makeText(this,"View Profile",Toast.LENGTH_SHORT).show();
                fragment = new Profile();
                break;

            case R.id.nav_contact:
                Toast.makeText(this,"Contact Us",Toast.LENGTH_SHORT).show();
                fragment = new ContactUs();
                break;

            case R.id.nav_logout:
                Toast.makeText(this,"Logged Out",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                break;

                default:
                    fragment = new home_page();
        }


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.display_screen, fragment,"Home")
                .addToBackStack("Home")
                .commit();

        menuItem.setChecked(true); //this highlights to show what the current fragment is

        setTitle(menuItem.getTitle()); //the selected option title is displayed on the toolbar

        drawer.closeDrawer(GravityCompat.START); //close the drawer

    }

}
