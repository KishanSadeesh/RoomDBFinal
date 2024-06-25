package com.example.roomdbnew1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.roomdbnew1.database.Item
import com.example.roomdbnew1.database.ItemDao
import com.example.roomdbnew1.database.ItemRoomDatabase
import com.example.roomdbnew1.databinding.ActivityHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    var TAG = HomeActivity::class.java.simpleName

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    lateinit var dao: ItemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var database = ItemRoomDatabase.getDatabase(this)
        dao = database.itemDao()
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.txtCount.setText(""+viewModel.count)
        binding.btndb.setOnClickListener {
            insertDataDb()
        }
        binding.btnFind.setOnClickListener {
            FindItemDB(21)
        }

        binding.countbtn.setOnClickListener {
            viewModel.incCount()
            binding.txtCount.setText("" + viewModel.count)
        }
    }

    private fun FindItemDB(id: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            var item = dao.getItem(id).first()
            binding.tvdb.setText(item.itemName)
        }
    }

        private fun insertDataDb() {
            GlobalScope.launch {
                var item = Item(21, "fruits", 11.11, 11)
                dao.insert(item)
            }
        }
    }
