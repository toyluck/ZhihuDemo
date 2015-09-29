package com.example.administrator.zhihudemo.Widge;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * ProjectName :ZhihuDemo
 * Created by Administrator on 2015/9/29  17:03.
 */
public class MenuItemView extends TextView {
  public MenuItemView(Context context) {
    this(context,null);
  }

  public MenuItemView(Context context, AttributeSet attrs) {
    this(context, attrs,0);
  }

  public MenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

  }
}
