package com.example.android.trackmysleepquality.sleepdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight

class SleepDetailViewModel(
        sleepNightKey: Long = 0L,
        dataSource: SleepDatabaseDao) : ViewModel() {

    val database = dataSource

    private val night: LiveData<SleepNight>

    fun getNight() = night

    init {
        night = database.getNightWithId(sleepNightKey)
    }

    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()
    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    fun onClose() {
        _navigateToSleepTracker.value = true
    }

}