package me.makamara.clive.menuactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_two:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.action_three:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
            case R.id.action_four:
                startActivity(new Intent(this, FourthActivity.class));
                break;
            case R.id.action_five:
                startActivity(new Intent(this, FifthActivity.class));
                break;
        }

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }




}
