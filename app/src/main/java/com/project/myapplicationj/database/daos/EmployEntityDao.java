package com.project.myapplicationj.database.daos;



import androidx.room.Dao;
import androidx.room.Insert;

import androidx.room.Query;


import com.project.myapplicationj.database.entities.EmployEntity;

import java.util.List;


@Dao
public interface EmployEntityDao {

    @Query(" Select count(*)  from employ_details")
    public int get_count();


    @Query(" Select * from employ_details")
    public List<EmployEntity> get_all_datas();


    @Query(" Select * from employ_details  where emp_id=:emp_id")
    public EmployEntity get_datas_specif_employ(Long emp_id);


    @Query(" Select * from employ_details  where   name like :ser ")
    public List<EmployEntity> get_datas_similar(String ser);

    @Query(" Delete from employ_details")
    public int del_all();


    @Insert
    public Long insert_employ_details(EmployEntity tbl);

}
