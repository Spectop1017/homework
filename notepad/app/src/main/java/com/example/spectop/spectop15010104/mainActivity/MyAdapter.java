package com.example.spectop.spectop15010104.mainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.spectop.spectop15010104.R;
import com.example.spectop.spectop15010104.database.DataBaseHelper;
import com.example.spectop.spectop15010104.database.ProjectNameDao;

import java.util.List;
import java.util.Map;

/**
 * Created by spectop on 15-1-2.
 */
public class MyAdapter extends BaseAdapter{

    ProjectNameDao projectNameDao;
    String idstr;

    int id;


    private Context context;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context) {
        this.context = context;
        layoutInflater = layoutInflater.from(context);
    }

    @Override
    public int getCount() {

        load();

        int id;

        id = Integer.parseInt(idstr);

        return id;
    }

    private void load(){
        List<Map<String, String>> list = projectNameDao.loadNote();

        Map<String, String> map = list.get(0);

        //textView.setText(map.get("title"));
        idstr = map.get(DataBaseHelper.KEY_NOTE_ID);
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null){
            view = layoutInflater.inflate(R.layout.main_list_item , null);
            viewHolder = new ViewHolder();
            viewHolder.tittle = (TextView) view.findViewById(R.id.list_note_tittle);
            viewHolder.content = (TextView) view.findViewById(R.id.list_note_content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tittle.setText("tittle" + i);
        viewHolder.content.setText("add content");

        return view;
    }
}

class ViewHolder {
    TextView tittle;
    TextView content;
}
