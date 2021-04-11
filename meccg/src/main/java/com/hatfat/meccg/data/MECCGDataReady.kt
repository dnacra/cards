package com.hatfat.meccg.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.hatfat.cards.data.DataReady
import com.hatfat.meccg.repo.MECCGCardsRepository
import com.hatfat.meccg.repo.MECCGMetaDataRepository
import javax.inject.Inject

class MECCGDataReady @Inject constructor(
    cardRepository: MECCGCardsRepository,
//    setRepository: SWCCGSetRepository,
    metaDataRepository: MECCGMetaDataRepository
) : DataReady {

    private val mediatorLiveData: MediatorLiveData<Boolean> = MediatorLiveData()

    init {
        val onChangedListener = Observer<Boolean> {
            mediatorLiveData.value =
                (cardRepository.loaded.value == true)
//                        && (setRepository.loaded.value == true)
                        && (metaDataRepository.loaded.value == true)
        }

        mediatorLiveData.addSource(cardRepository.loaded, onChangedListener)
//        mediatorLiveData.addSource(setRepository.loaded, onChangedListener)
        mediatorLiveData.addSource(metaDataRepository.loaded, onChangedListener)
    }

    override val isDataReady: LiveData<Boolean>
        get() = mediatorLiveData
}
