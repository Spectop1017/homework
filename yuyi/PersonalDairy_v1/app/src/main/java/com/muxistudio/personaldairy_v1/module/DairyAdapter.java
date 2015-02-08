package com.muxistudio.personaldairy_v1.module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.muxistudio.personaldairy_v1.R;
import com.muxistudio.personaldairy_v1.database.DataBaseHelper;
import com.muxistudio.personaldairy_v1.database.MyDairyDao;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 15-2-2.
 * BaseAdapter
 * Including:
 * refreshList(); to reload the list.
 * getIndex(List); to get index.
 * getTitle(List,int) to get the specified TITLE by the index.
 * getContent(List,int) to get the specified CONTENT by the index.
 */
public class DairyAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    MyDairyDao myDairyDao;
    int bound;

    List<Map<String, String>> list;

    public DairyAdapter(Context context) {
        myDairyDao = new MyDairyDao(context);
        list = myDairyDao.loadDairy();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return getIndex(list);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.main_dairy_item, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.item_title);
            viewHolder.content = (TextView) view.findViewById(R.id.item_content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.title.setText(getTitle(list, bound-i-1));
        viewHolder.content.setText(getContent(list, bound-i-1));

        return view;
    }

    public void refreshList() {
        list.clear();
        list.addAll(myDairyDao.loadDairy());
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView title;
        TextView content;
    }

    protected int getIndex(List<Map<String, String>> list) {
        bound = list.size();
        return list.size();
    }  //返回list长度

    protected String getTitle(List<Map<String, String>> list, int i) {
        String title;
        Map<String, String> map = list.get(i);
        title = map.get(DataBaseHelper.KEY_DAIRY_TITLE);
        return title;
    }  //返回list(i)的title

    protected String getContent(List<Map<String, String>> list, int i) {
        String content;
        Map<String, String> map = list.get(i);
        content = map.get(DataBaseHelper.KEY_DAIRY_CONTENT);
        return content;
    }  //返回list(i)的content
}