package com.malus.pushshow.adapter.job;

import android.content.Context;

import com.malus.pushshow.R;
import com.malus.pushshow.adapter.CommonAdapter;
import com.malus.pushshow.adapter.ViewHolder;
import com.malus.pushshow.bean.job51.JobItemForm51;

import java.util.List;

/********************
 * 作者：malus
 * 日期：16/2/23
 * 时间：下午3:19
 * 注释：
 ********************/
public class JobListAdapter extends CommonAdapter<JobItemForm51> {
    public JobListAdapter(Context context, List<JobItemForm51> datas, int layoutId) {
        super(context, datas, layoutId);
    }
    @Override
    protected void convert(ViewHolder holder, JobItemForm51 jobItemForm51) {
        holder.setText(R.id.job_list_item_salary,jobItemForm51.getProvidesalary())
                .setText(R.id.job_list_item_job,jobItemForm51.getJobname())
                .setText(R.id.job_list_item_company,jobItemForm51.getConame())
                .setText(R.id.job_list_item_edu,jobItemForm51.getDegree())
                .setText(R.id.job_list_item_time,jobItemForm51.getIssuedate())
                .setText(R.id.job_list_item_address,jobItemForm51.getJobarea());
//                .setImageResource(R.id.job_list_item_icon,jobItemForm51.getCompanyLogo());
    }
}
