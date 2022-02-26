package com.project.myapplicationj.database.appdb;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.myapplicationj.Utils;
import com.project.myapplicationj.database.daos.EmployEntityDao;
import com.project.myapplicationj.database.entities.EmployEntity;


@Database(version = 5,entities = {EmployEntity.class})
public abstract  class Appdb extends RoomDatabase {


  private static Appdb db;

  public abstract EmployEntityDao getEmployEntityDao();



  public static synchronized Appdb getDb_instance(Context context)
  {

    if(db==null)
    {
      db = Room.databaseBuilder(context.getApplicationContext(),
              Appdb.class, Utils.dbName)
              .fallbackToDestructiveMigration()
              .allowMainThreadQueries()
              .build();



    }


    return db;

  }



}
