package com.androidjson.sqlitelogin_androidjsoncom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.sqlitelogin_androidjsoncom.database.DatabaseManagerUser;
import com.androidjson.sqlitelogin_androidjsoncom.entity.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DashboardActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DatabaseManagerUser databaseManagerUser;
    private User itemUser;
    private String ident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Bundle b = getIntent().getExtras();

        ident = b.getString("IDENT");

        databaseManagerUser= new DatabaseManagerUser(getApplicationContext());
        itemUser = databaseManagerUser.getUser(ident); // find the register user in the databse

        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);

        ((TextView) header.findViewById(R.id.textname)).setText(itemUser.getName());
        ((TextView) header.findViewById(R.id.textemail)).setText(itemUser.getemail());

        Bitmap bitmapsinfoto = BitmapFactory.decodeResource(getResources(),R.drawable.imagen);
        RoundedBitmapDrawable roundedBitmapDrawablesinfoto = RoundedBitmapDrawableFactory.create(getResources(), bitmapsinfoto);
        roundedBitmapDrawablesinfoto.setCircular(true);

        ((ImageView) header.findViewById(R.id.imageView)).setImageDrawable(roundedBitmapDrawablesinfoto);

        if(itemUser.getBytes()!=null){
            byte[] foodImage = itemUser.getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);

            ((ImageView) header.findViewById(R.id.imageView)).setImageBitmap(bitmap);

            Bitmap bitmap2 = ((BitmapDrawable)((ImageView) header.findViewById(R.id.imageView)).getDrawable()).getBitmap();
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
            roundedBitmapDrawable.setCircular(true);

            ((ImageView) header.findViewById(R.id.imageView)).setImageDrawable(roundedBitmapDrawable);
        }








        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
             DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_send , R.id.nav_video, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* if(item.getItemId()==R.id.action_Intro)
        {
            //Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
           Intent i= new Intent(this,IntroActivity.class);
            startActivity(i);
        }*/
        if(item.getItemId()==R.id.action_Logout)
        {
            finish();
            Toast.makeText(DashboardActivity.this,"Log Out Successfully", Toast.LENGTH_LONG).show();
        }

    return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
