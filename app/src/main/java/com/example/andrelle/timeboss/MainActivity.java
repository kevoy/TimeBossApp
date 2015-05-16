package com.example.andrelle.timeboss;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    Button addTaskBtn, addActivityBtn,timeTableTab, tasksViewTab, activitiesViewTab, settingsTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTaskBtn = (Button) findViewById(R.id.new_task_btn);
        addTaskBtn.setOnClickListener(new addTaskListener());

        timeTableTab = (Button) findViewById(R.id.timetable_tab);
        timeTableTab.setOnClickListener(new TimetableViewListener());

        tasksViewTab = (Button) findViewById(R.id.tasks_tab);
        tasksViewTab.setOnClickListener(new TasksViewListener());

        loadHomeActivity();


    }
    public void loadTasksActivity(){
        LinearLayout mainContainer = (LinearLayout) findViewById(R.id.main_container);
        mainContainer.removeViews(1, mainContainer.getChildCount() - 1);
        for(int i=0; i<8; i++) {
            LinearLayout task_btn = (LinearLayout) getLayoutInflater().inflate(R.layout.task_btn_layout, null);
            mainContainer.addView(task_btn);
            TextView time = (TextView) task_btn.findViewById(R.id.task_btn_name);
            time.setText("Name"+i);

        }

    }
    public void addTaskToTable(LinearLayout table, String name,String hour, String day, String color){

        LinearLayout row = (LinearLayout) getLayoutInflater().inflate(R.layout.timetable_row_layout,null);
        if(table.findViewWithTag(hour.replace(" ", "").replace(".", ""))!=null){
            row = (LinearLayout) table.findViewWithTag(hour.replace(" ", "").replace(".", ""));

        }

        TextView hour_field = (TextView) row.findViewById(R.id.hour);
        hour_field.setText(hour);
        if(name!=null){
            TextView day_field;
            if(day.equals("monday")){
                day_field = (TextView) row.findViewById(R.id.monday);

            }else if(day.equals("tuesday")){
                day_field = (TextView) row.findViewById(R.id.tuesday);

            }else if(day.equals("wednesday")){
                day_field = (TextView) row.findViewById(R.id.wednesday);

            }else if(day.equals("thursday")){
                day_field = (TextView) row.findViewById(R.id.thursday);

            }else{
                day_field = (TextView) row.findViewById(R.id.friday);

            }
            day_field.setText(name);
            day_field.setBackgroundColor(Color.parseColor(color));
        }
        if(table.findViewWithTag(hour.replace(" ", "").replace(".", ""))==null) {
            row.setTag(hour.replace(" ", "").replace(".", ""));
            table.addView(row);
        }
    }
    public LinearLayout getTimetable(){
        LinearLayout timetable = (LinearLayout) getLayoutInflater().inflate(R.layout.timetable_view,null);
        timetable.removeViews(2, timetable.getChildCount()-2);

        addTaskToTable(timetable, "EDTK 2025", "6 a.m", "tuesday", "#ED6556");
        addTaskToTable(timetable, "EDTK 3004", "6 a.m", "friday", "#ED6556");
        addTaskToTable(timetable, null, "7 a.m", "wednesday", "#ED6556");
        addTaskToTable(timetable, "Science", "8 a.m", "monday", "#ED6556");
        addTaskToTable(timetable, "Bio 2025", "8 a.m", "wednesday", "#ED6556");
        addTaskToTable(timetable, "food 3004", "8 a.m", "tuesday", "#ED6556");

        addTaskToTable(timetable, "EDTK 2025", "9 a.m", "tuesday", "#ED6556");
        addTaskToTable(timetable, "EDTK 3004", "9 a.m", "monday", "#ED6556");
        addTaskToTable(timetable, null, "10 a.m", "wednesday", "#ED6556");
        addTaskToTable(timetable, "Science", "10 a.m", "monday", "#ED6556");
        addTaskToTable(timetable, "Bio 2025", "11 a.m", "monday", "#ED6556");
        addTaskToTable(timetable, "food 3004", "11 a.m", "thursday", "#ED6556");

        return timetable;
    }
    public void loadHomeActivity(){
        LinearLayout timetable = getTimetable();
        LinearLayout homecontainer = (LinearLayout) findViewById(R.id.home_container);

        //If no tasks exists show welcome banner else remove banner and show timetable
        homecontainer.removeViewAt(0);
        homecontainer.addView(timetable,0);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void resetTabs(Button curTab){
        LinearLayout nav_bar = (LinearLayout) findViewById(R.id.nav_bar);
        for(int i=0; i< nav_bar.getChildCount(); i++){
            Button tab= (Button) nav_bar.getChildAt(i);
            tab.setBackgroundColor(Color.parseColor("#323031"));
        }
        curTab.setBackgroundResource(R.drawable.bordebtm);
    }
    public class addTaskListener implements View.OnClickListener
    {
        public void onClick(View v){
            setContentView(R.layout.activity_new_task);


        }
    }
    public class TimetableViewListener implements View.OnClickListener{
        public void onClick(View v){
            setContentView(R.layout.activity_main);
            Button curTab = (Button) v;

            //setContentView(R.layout.activity_main);
            timeTableTab = (Button) findViewById(R.id.timetable_tab);
            timeTableTab.setOnClickListener(new TimetableViewListener());

            tasksViewTab = (Button) findViewById(R.id.tasks_tab);
            tasksViewTab.setOnClickListener(new TasksViewListener());
            resetTabs(timeTableTab);
            loadHomeActivity();
        }

    }
    public class TasksViewListener implements View.OnClickListener{
        public void onClick(View v){
            setContentView(R.layout.tasks_view);
            Button curTab = (Button) v;
            tasksViewTab = (Button) findViewById(R.id.tasks_tab);
            resetTabs(tasksViewTab);

            timeTableTab = (Button) findViewById(R.id.timetable_tab);
            timeTableTab.setOnClickListener(new TimetableViewListener());
            loadTasksActivity();


        }

    }
}
