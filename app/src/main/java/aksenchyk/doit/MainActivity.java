package aksenchyk.doit;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

import aksenchyk.doit.navigation_views_fragments.ProgectsFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;



    private FrameLayout mMainFrame;

    //Fragments

    private ProgectsFragment progectsFragment;


    private FirebaseFirestore mFirestore;

    private Button mButtonAddNewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        mMainFrame = (FrameLayout) findViewById(R.id.content_frame);


        progectsFragment = new ProgectsFragment();

        setFragment(progectsFragment);
        setTitle(getString(R.string.menu_item_inbox));




        mFirestore = FirebaseFirestore.getInstance();

        mButtonAddNewList = (Button) findViewById(R.id.buttonAddNewList);


        mButtonAddNewList.setOnClickListener(this);



        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );




        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here




                        switch (menuItem.getItemId()) {
                            case R.id.nav_inbox:
                                setTitle(getString(R.string.menu_item_inbox));

                                return true;
                            case R.id.nav_today:
                                setTitle(getString(R.string.menu_item_today));

                                return true;
                            case R.id.nav_plans:
                                setTitle(getString(R.string.menu_item_plans));

                                return true;
                            case R.id.nav_someday:
                                setTitle(getString(R.string.menu_item_someday));
                                setFragment(progectsFragment);
                                return true;
                            case R.id.nav_journal:
                                setTitle(getString(R.string.menu_item_journal));

                                return true;

                            case R.id.nav_search:
                                setTitle(getString(R.string.menu_item_search));

                                return true;

                            case R.id.nav_settings:
                                setTitle(getString(R.string.menu_group_settings));

                                return true;

                            default:
                                setTitle(menuItem.getTitle());
                                setFragment(progectsFragment);

                                return true;
                        }


                        //return true;
                    }
                });

        mNavigationView.setItemIconTintList(null);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");
        String userName = intent.getStringExtra("userName");


        TextView emailTextView  = (TextView) findViewById(R.id.emailTextView);
        TextView nameTextView  = (TextView) findViewById(R.id.nameTextView);

        emailTextView.setText(userEmail);
        nameTextView.setText(userName);



    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.buttonAddNewList: {
                Menu menu = mNavigationView.getMenu();
              // menu.removeGroup(R.id.nav_group_projects);

                menu.add(R.id.nav_group_projects,101 , 0, "Item1" + new Date()).setIcon(R.drawable.ic_75_percent);



            }
            break;
        }
    }


}
