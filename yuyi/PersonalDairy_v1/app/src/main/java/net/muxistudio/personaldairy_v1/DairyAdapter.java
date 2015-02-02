package net.muxistudio.personaldairy_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by root on 15-2-2.
 */
public class DairyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    public DairyAdapter(Context context) {
        this.context = context;
        layoutInflater = layoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
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

        viewHolder.title.setText("This is the No." + i + " message!");
        viewHolder.content.setText("I just want to write something here! \n Nothing important, " +
                "really \n Then just finish it.");

        return view;
    }

    class ViewHolder {
        TextView title;
        TextView content;
    }
}