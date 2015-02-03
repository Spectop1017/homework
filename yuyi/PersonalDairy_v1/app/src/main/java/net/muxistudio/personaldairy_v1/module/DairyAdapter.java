package net.muxistudio.personaldairy_v1.module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.muxistudio.personaldairy_v1.R;
import net.muxistudio.personaldairy_v1.database.DataBaseHelper;
import net.muxistudio.personaldairy_v1.database.MyDairyDao;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 15-2-2.
 */
public class DairyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    MyDairyDao myDairyDao = new MyDairyDao(context);


    List<Map<String ,String >> list = myDairyDao.loadDairy();

    public DairyAdapter(Context context) {
        this.context = context;
        layoutInflater = layoutInflater.from(context);
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

        viewHolder.title.setText(getTitle(list,i));
        viewHolder.content.setText(getContent(list,i));

        return view;
    }

    class ViewHolder {
        TextView title;
        TextView content;
    }

    protected int getIndex(List list) {
        return list.size();
    }  //返回list长度

    protected String getTitle(List list, int i) {
        String title;
        Map<String ,String > map = (Map<String, String>) list.get(i);
        title = map.get(DataBaseHelper.KEY_DAIRY_TITLE);
        return title;
    }  //返回list(i)的title

    protected String getContent(List list,int i) {
        String content;
        Map<String ,String > map = (Map<String, String>) list.get(i);
        content = map.get(DataBaseHelper.KEY_DAIRY_CONTENT);
        return content;
    }  //返回list(i)的content
}