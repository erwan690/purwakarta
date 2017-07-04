package purwakarta.kota.kuliner;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import purwakarta.kota.kuliner.helper.SessionManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        session.setKeyId("5","Kuliner Purwakarta");
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.content_frame, new WarungFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        //moveTaskToBack(false);
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(item.getItemId());

        if (id == R.id.nav_tjeplak) {
            // Handle the camera action
        }
        return true;
    }

    private void displaySelectedScreen(int itemId) {
        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                session.setKeyId("5","Kuliner Purwakarta");
                fragment = new WarungFragment();
                break;

            case R.id.nav_sate:
                fragment = new KategoriFragment();
                break;
            case R.id.nav_menong:
                session.setKeyId("75","Galeri Menong");
                Intent menongIntent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(menongIntent);
                break;

            case R.id.nav_simping:
                session.setKeyId("78","Simping Kaum");
                Intent simpingIntent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(simpingIntent);
                break;

            case R.id.nav_peuyeum:
                session.setKeyId("76","Peuyeum Bendul");
                Intent peuyeumIntent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(peuyeumIntent);
                break;

            case R.id.nav_tjeplak:
                session.setKeyId("79","Tjeplak Kuliner");
                Intent tjeplakIntent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(tjeplakIntent);
                break;

            case R.id.nav_pujasera:
                session.setKeyId("77","Pujasera Srikandi");
                Intent pujaseraIntent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(pujaseraIntent);
                break;
            case R.id.nav_warung_terdekat:
                Intent warterIntent = new Intent(MainActivity.this, WarterActivity.class);
                startActivity(warterIntent);
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}
