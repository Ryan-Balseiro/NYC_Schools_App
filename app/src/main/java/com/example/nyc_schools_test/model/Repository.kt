package com.example.nyc_schools_test.model

import android.os.health.UidHealthStats
import com.example.nyc_schools_test.model.remote.SchoolListResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun useCaseSchoolList(): Flow<UIState>
    fun useCaseSchoolSatbyDBN(dbn: String): Flow<UIState>
}//end Repository