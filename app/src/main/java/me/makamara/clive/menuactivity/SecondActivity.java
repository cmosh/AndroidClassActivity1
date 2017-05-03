package me.makamara.clive.menuactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> toDelete;
    private ArrayList<String> animals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        toDelete = new ArrayList<>();
        animals = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.animals_array)));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, animals);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);



        // Assign adapter to ListView
        // Defined Array values to show in ListView
//        registerForContextMenu(listView);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new  AbsListView.MultiChoiceModeListener() {




            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    toDelete.add(adapter.getItem(position));
                } else {
                    toDelete.remove(adapter.getItem(position));
                }
            }

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.menu_context, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        for (String item2 : toDelete) {
                            animals.remove(item2);
                        }
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }



            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {
                toDelete.clear();

            }


        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Long press the "+ itemValue+" for context menu", Toast.LENGTH_LONG)
                        .show();

            ;}});

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_context, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_refresh:
                Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
                animals.clear();
                animals.addAll(Arrays.asList(getResources().getStringArray(R.array.animals_array)));
                adapter.notifyDataSetChanged();
                break;
            case R.id.action_back:
                finish();
                break;
            default:


        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {


        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Talk to the beast");
        menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "SMS");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Call"){
            Toast.makeText(getApplicationContext(),"calling...",Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="SMS"){
            Toast.makeText(getApplicationContext(),"sending sms...",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

}
