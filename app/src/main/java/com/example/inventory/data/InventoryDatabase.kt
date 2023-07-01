package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
	entities = [Item::class],
	version = 1,
	exportSchema = false
)
abstract class InventoryDatabase : RoomDatabase() {
	abstract fun itemDAO(): ItemDao

	companion object {
		@Volatile
		private var Instance: InventoryDatabase? = null

		fun getDatabase(ctx: Context): InventoryDatabase {
			return Instance ?: synchronized(this) {
				Room.databaseBuilder(ctx, InventoryDatabase::class.java, "item_db")
					.fallbackToDestructiveMigration()
					.build()
					.also {
						Instance = it
					}
			}
		}
	}
}