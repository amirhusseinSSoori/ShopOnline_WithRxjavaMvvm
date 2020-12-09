package project.android.shoponlineproject.dataBase;

import android.telecom.Call;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import project.android.shoponlineproject.model.Detail;

@Dao
public interface UserDao {
    @Query("SELECT * FROM details")
    List<Detail> getAll();

//    @Query("SELECT * FROM DETAILS WHERE uid IN (:userIds)")
//    List<Detail> loadAllByIds(int[] userIds);

//    @Query("SELECT * FROM Detail WHERE code LIKE code AND " +
//            "color LIKE color AND color LIKE color AND description LIKE description AND image LIKE image AND name LIKE name LIMIT 1")
//    Detail findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Detail> details);


    @Insert()
    void insertAllOrders(ArrayList<Detail> order);

    @Delete
    void delete(List<Detail> details);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void Updata(ArrayList<Detail> details);


    @Update()
    void Updatalist(ArrayList<Detail> order);

    @Query("DELETE FROM details")
     void dropTable();


}
