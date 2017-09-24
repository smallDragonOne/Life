package com.example.zj.liferecord.Controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.zj.liferecord.DataBase.IOperateDataBase;
import com.example.zj.liferecord.DataBase.OperateDataBase;
import com.example.zj.liferecord.Model.RecordBean;
import com.example.zj.liferecord.MyApplication;
import com.example.zj.liferecord.R;
import com.example.zj.liferecord.Utils.Common;
import com.example.zj.liferecord.Utils.MyAdapter;
import com.example.zj.liferecord.Utils.ViewHolder;
import com.example.zj.liferecord.Views.NavInfoManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {

    //private ListView listView;

    private SwipeMenuListView listView;

    final private List<RecordBean> listData = new ArrayList<>();

    private MyAdapter<RecordBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.main_activity);
        init();

        initSwipeListView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        listData.clear();
        //String[] list = new String[]{"1","2","3"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);

        //final List<RecordBean> list = new ArrayList<>();
        getData(listData);

        /*
        final List<String> contentList = new ArrayList<>();
        for (RecordBean bean: listData){
            contentList.add(bean.getContent());
        }*/

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,contentList);

        adapter = new MyAdapter<RecordBean>(listData,this, R.layout.main_list_item) {
            @Override
            protected void Covert(ViewHolder holder, RecordBean data) {
                TextView text = holder.getViews(R.id.text);
                text.setText(data.getContent());
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,CreateOrEditActivity.class);
                intent.putExtra("content",listData.get(position).getContent());
                intent.putExtra("id",listData.get(position).getId());
                startActivity(intent);
            }
        });

    }

    private  void  init(){
       // listView = (ListView) findViewById(R.id.list);
        listView = (SwipeMenuListView)findViewById(R.id.listView);

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

    private void initSwipeListView(){

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem overItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                overItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                overItem.setWidth(90);
                // set item title
                overItem.setTitle("置顶");
                // set item title fontsize
                overItem.setTitleSize(18);
                // set item title font color
                overItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(overItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);

                deleteItem.setTitle("删除");

                deleteItem.setTitleSize(18);

                deleteItem.setTitleColor(Color.WHITE);
                // set a icon
                //deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);


        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:

                        RecordBean bean = listData.get(position);
                        IOperateDataBase db = new OperateDataBase(MainActivity.this);
                        int number = Common.getCurrentMaxNumber(db);
                        number += 1;
                        SQLiteDatabase database = db.getWriteDataBase();
                        database.execSQL("update Record set number = "+number+" where id = "+bean.getId()+"");
                        database.close();
                        ShowToast("置顶成功");

                        listData.remove(position);
                        bean.setmNumber(number);
                        listData.add(0,bean);
                        adapter.notifyUpdate(listData);
                        break;
                    case 1:
                        RecordBean bean1 = listData.get(position);
                        IOperateDataBase db1 = new OperateDataBase(MainActivity.this);
                        SQLiteDatabase database1 = db1.getWriteDataBase();
                        database1.execSQL("delete from Record where id = "+bean1.getId()+"");
                        database1.close();
                        listData.remove(position);
                        adapter.notifyUpdate(listData);
                        ShowToast("删除成功");
                        break;
                    default:
                        break;
                }
                return  false;
            }
        });
    }

    private void getData(List<RecordBean> list){
        IOperateDataBase db = new OperateDataBase(this);
        SQLiteDatabase database = db.getWriteDataBase();
        Cursor cursor = database.query("Record",new String[]{"id","Title","Content","number"},null,null,null,null,"number desc");
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("Title"));
            String content = cursor.getString(cursor.getColumnIndex("Content"));
            Integer number = cursor.getInt(cursor.getColumnIndex("number"));

            list.add(new RecordBean(id,title,content,null,number));

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

}
