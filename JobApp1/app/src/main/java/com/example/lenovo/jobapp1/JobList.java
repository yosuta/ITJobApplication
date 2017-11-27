package com.example.lenovo.jobapp1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class JobList extends ArrayAdapter<Job>{
    private Activity context;
    private List<Job> jobList;
    public JobList(Activity context,List<Job> jobList){
        super(context,R.layout.list_layout,jobList);
        this.context=context;
        this.jobList=jobList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View listViewItem=layoutInflater.inflate(R.layout.list_layout,null,true);
        TextView textTitl=(TextView)listViewItem.findViewById(R.id.txttitle);
        TextView textCompanyname=(TextView)listViewItem.findViewById(R.id.txtcompanyname);
        Job job=jobList.get(position);
        textTitl.setText(job.getTitle());
        textCompanyname.setText(job.getCompanyName());
        return listViewItem;
    }
}
