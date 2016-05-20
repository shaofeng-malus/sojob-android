package com.malus.pushshow.adapter.job;

import android.content.Context;

import com.malus.pushshow.R;
import com.malus.pushshow.adapter.CommonAdapter;
import com.malus.pushshow.adapter.ViewHolder;
import com.malus.pushshow.bean.ProvinceItemForm51;

import java.util.List;

/********************
 * 作者：malus
 * 日期：16/3/10
 * 时间：下午2:28
 * 注释：
 ********************/
public class ProvinceListAdapter extends CommonAdapter<ProvinceItemForm51> {
    public ProvinceListAdapter(Context context, List<ProvinceItemForm51> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void convert(ViewHolder holder, ProvinceItemForm51 provinceItemForm51) {
        holder.setText(R.id.item_province_list_name, provinceItemForm51.getValue());
        if(provinceItemForm51.getHassub()!=""&&provinceItemForm51.getHassub().equals("1")){
            holder.setVisibility(R.id.item_province_list_hassub,true);
        }else{
            holder.setVisibility(R.id.item_province_list_hassub,false);
        }
    }
}
