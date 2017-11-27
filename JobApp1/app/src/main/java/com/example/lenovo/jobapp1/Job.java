package com.example.lenovo.jobapp1;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;

public class Job {
    public String jobId;
    public String companyName;
    public String address;
    public String title;
    public String about;
    public Job(){

    }
    public Job(String jobId,String companyName, String address, String title, String about){
        this.companyName=companyName;
        this.address=address;
        this.title=title;
        this.about=about;
        this.jobId=jobId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
