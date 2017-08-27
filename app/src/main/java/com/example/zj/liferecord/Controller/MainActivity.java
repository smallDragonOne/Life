package com.example.zj.liferecord.Controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.example.zj.liferecord.DataBase.IOperateDataBase;
import com.example.zj.liferecord.DataBase.OperateDataBase;
import com.example.zj.liferecord.Model.RecordBean;
import com.example.zj.liferecord.R;
import com.example.zj.liferecord.Views.NavInfoManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //String[] list = new String[]{"1","2","3"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);

        final List<RecordBean> list = new ArrayList<>();
        getData(list);

        final List<String> contentList = new ArrayList<>();
        for (RecordBean bean: list){
            contentList.add(bean.getContent());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,contentList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,CreateOrEditActivity.class);
                intent.putExtra("content",contentList.get(position));

                startActivity(intent);
            }
        });

    }

    private  void  init(){
        listView = (ListView) findViewById(R.id.list);

        NavInfoManager nav = new NavInfoManager(this, NavInfoManager.NavType.justRight);
        nav.SetTitle("LifeRecord");
        nav.setRightText("添加");

        nav.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreateOrEditActivity.class);

                startActivity(intent);
            }
        });

    }

    private void getData(List<RecordBean> list){
        IOperateDataBase db = new OperateDataBase(this);
        SQLiteDatabase database = db.getWriteDataBase();
        Cursor cursor = database.query("Record",new String[]{"id","Title","Content"},null,null,null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("Title"));
            String content = cursor.getString(cursor.getColumnIndex("Content"));

            list.add(new RecordBean(id,title,content,null));
            //System.out.println("-->"+id+"::::::::::"+name);
        }

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
}
