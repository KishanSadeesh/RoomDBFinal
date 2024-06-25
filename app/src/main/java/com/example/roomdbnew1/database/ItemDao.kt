package com.example.roomdbnew1.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    //crud

    @Insert (onConflict = OnConflictStrategy.REPLACE)//insert into item(.name,price,)
    suspend fun insert(groceryItem: Item)

    @Update
    suspend   fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item WHERE id = :itemId")
    fun getItem(itemId: Int): Flow<Item>

    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>


}