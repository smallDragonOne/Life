package com.example.zj.liferecord.Controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.zj.liferecord.DataBase.IOperateDataBase;
import com.example.zj.liferecord.DataBase.OperateDataBase;
import com.example.zj.liferecord.Model.RecordBean;
import com.example.zj.liferecord.MyApplication;
import com.example.zj.liferecord.R;
import com.example.zj.liferecord.Utils.MyAdapter;
import com.example.zj.liferecord.Utils.ViewHolder;
import com.example.zj.liferecord.Views.NavInfoManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        System.out.println("base111Activity");
        init();
        super.onCreate(savedInstanceState);
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

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,contentList);

        listView.setAdapter(new MyAdapter<RecordBean>(list,this, R.layout.main_list_item) {
            @Override
            protected void Covert(ViewHolder holder, RecordBean data) {
                TextView text = holder.getViews(R.id.text);
                text.setText(data.getContent());
            }
        });

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

        NavInfoManager nav = new NavInfoManager(this, NavInfoManager.NavType.NoLeft);
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

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK){
            System.out.println(MyApplication.getMyApplication().getActivityCount());
            exit();
        }
        return true;
    }
    private long mTime = 0;
    private void exit(){
        if (System.currentTimeMillis() - mTime > 2000){

            mTime = System.currentTimeMillis();
            ShowToast("再点一次退出应用");
        }
        else {
            System.out.println("123445");
            removeAllActivity();
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
