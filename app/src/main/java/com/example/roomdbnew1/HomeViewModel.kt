package com.example.roomdbnew1

import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var count = 0
    fun incCount()
    {
        count++
    }

}