package adapter;

/**
 * Created by dell on 2019/9/9.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.linxixiangxin.passcet.R;

public class shengci extends BaseAdapter {
    private Context context;
    private List <Map <String, Object>> datas;
    private  LayoutInflater listContainer;
    public Map<Integer,Boolean>cbxFlag = null;

    //自定义空间集
    public class ViewHolder{
        public EditText word;
        public EditText detail;

    }


    public shengci(Context context, List <Map <String, Object>> datas){
        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.datas=datas;
        cbxFlag = new HashMap <Integer, Boolean>();
        init();
    }

    private void init() {
        int i;
        for (i = 0; i < datas.size(); i++) ;
        cbxFlag.put(i, false);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean hasChecked(int position) {
        return  cbxFlag.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final int selecketID = position;
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = listContainer.inflate(R.layout.activity_shengci_item, null);
            holder.word = convertView.findViewById(R.id.word);
            holder.detail = convertView.findViewById(R.id.detail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.word.setText((String) datas.get(position).get("word"));
        holder.detail.setText((String) datas.get(position).get("detail"));

        return convertView;
    }
}
