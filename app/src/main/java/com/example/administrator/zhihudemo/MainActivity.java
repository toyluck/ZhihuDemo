package com.example.administrator.zhihudemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.zhihudemo.Model.MenuModel;
import com.example.administrator.zhihudemo.Protocol.GsonRequest;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final String MENU_URI = "http://news-at.zhihu.com/api/4/themes";
  private DrawerLayout mDrawerLayout;
  private NavigationView mNavigationView;
  private boolean mIsFirstOpen;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  private void initView() {
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
    mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
      @Override public void onDrawerOpened(View drawerView) {
        if (!mIsFirstOpen) {
          mIsFirstOpen = true;
          RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
          GsonRequest<MenuModel> gsonRequest =
              new GsonRequest<>(MENU_URI, MenuModel.class, new Response.Listener() {
                @Override public void onResponse(Object o) {
                  if (o instanceof MenuModel) {
                    initMenuView((MenuModel) o);
                  }
                }
              }, new Response.ErrorListener() {
                @Override public void onErrorResponse(VolleyError volleyError) {
                  System.out.println("-------onErrorResponse---------" + volleyError.getMessage()
                      + "---------------");
                }
              });
          requestQueue.add(gsonRequest);
          requestQueue.start();
        }
      }
    });
    initNavigationView();
    initActionBar();
    initFloatingActionButton();
  }

  private void initMenuView(MenuModel model) {
    Menu menu = mNavigationView.getMenu();
    List<MenuModel.T_other> others = model.others;
    for (MenuModel.T_other o : others) {
      menu.add(o.name);
      MenuItem item = menu.getItem(0);
    }
  }

  private void initNavigationView() {
    mNavigationView = (NavigationView) findViewById(R.id.navi_view);
    mNavigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
            Toast.makeText(MainActivity.this, "你在看么?", Toast.LENGTH_SHORT).show();
            mDrawerLayout.closeDrawers();
            return true;
          }
        });


   /*
    menu.addSubMenu(1, 1, Menu.NONE, "我是条目1");
    menu.addSubMenu(1, 2, Menu.NONE, "我是条目1");
    menu.addSubMenu(1, 3, Menu.NONE, "我是条目1");
    menu.addSubMenu(1, Menu.NONE, Menu.NONE, "我是条目1");
    menu.addSubMenu(1, Menu.NONE, Menu.NONE, "我是条目1");
    //menu.setQwertyMode(); //设置排序模式是否以英文字母的顺序
    menu.addSubMenu(2, Menu.NONE, Menu.NONE, "我是条目2");
    menu.addSubMenu(2, Menu.NONE, Menu.NONE, "我是条目3");
    menu.addSubMenu(2, Menu.NONE, Menu.NONE, "我是条目4");
    menu.addSubMenu(2, Menu.NONE, Menu.NONE, "我是条目%");
    menu.addSubMenu(3, Menu.NONE, Menu.NONE, "我是条6");*/

  }


  private void initFloatingActionButton() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });
  }

  private void initActionBar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    switch (id) {
      case R.id.action_settings:
        return true;
      case android.R.id.home:
        mDrawerLayout.openDrawer(GravityCompat.START);
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
