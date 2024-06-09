package com.hatfat.fab.search

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.hatfat.cards.search.CardSearchOptionsProvider
import com.hatfat.cards.search.filter.SpinnerFilter
import com.hatfat.cards.search.filter.TextFilter
import com.hatfat.cards.search.filter.advanced.AdvancedFilter
import com.hatfat.fab.R
import com.hatfat.fab.repo.FabMetaDataRepository
import com.hatfat.fab.repo.FabSetRepository
import com.hatfat.fab.search.filter.keyword.FabKeywordFilter
import com.hatfat.fab.search.filter.keyword.FabKeywordOption
import com.hatfat.fab.search.filter.set.FabSetFilter
import com.hatfat.fab.search.filter.set.FabSetOption
import com.hatfat.fab.search.filter.text.FabTextFilterMode
import com.hatfat.fab.search.filter.type.FabTypeFilter
import com.hatfat.fab.search.filter.type.FabTypeOption
import com.hatfat.swccg.search.filter.advanced.FabAdvancedFilter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FabCardSearchOptionsProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val metaDataRepository: FabMetaDataRepository,
    private val setRepository: FabSetRepository,
//    private val formatRepository: SWCCGFormatRepository
) : CardSearchOptionsProvider {
    override fun getTextSearchOptions(): List<TextFilter> {
        return listOf(
            TextFilter(
                FabTextFilterMode.NAME.toString(),
                FabTextFilterMode.NAME,
                context.getString(R.string.text_search_option_name),
                true
            ),
            TextFilter(
                FabTextFilterMode.GAMETEXT.toString(),
                FabTextFilterMode.GAMETEXT,
                context.getString(R.string.text_search_option_gametext),
                false
            ),
        )
    }

    override fun getDropdownFilterLiveData(savedStateHandle: SavedStateHandle): List<MutableLiveData<SpinnerFilter>> {
        return listOf(
            setLiveData(savedStateHandle),
            typesLiveData(savedStateHandle),
            keywordsLiveData(savedStateHandle)
        )
    }

    override fun hasAdvancedFilters(): Boolean {
        return true
    }

    override fun getNewAdvancedFilter(): AdvancedFilter {
//        return SWCCGAdvancedFilter(
//            SWCCGField.entries.map { SWCCGAdvancedFilterField(it) }.sortedBy { it.displayName },
//            AdvancedFilterMode.entries.toList()
//        )
        return FabAdvancedFilter(emptyList(), emptyList())
    }

    private fun typesLiveData(savedStateHandle: SavedStateHandle): MutableLiveData<SpinnerFilter> {
        val initialList = listOf(FabTypeOption(context.getString(R.string.fab_any_type)))
        val defaultValue = FabTypeFilter(
            initialList,
            initialList[0]
        )

        val persistedLiveData = savedStateHandle.getLiveData(
            "typeKey",
            defaultValue
        )

        val mediatorLiveData = MediatorLiveData<SpinnerFilter>()
        mediatorLiveData.value = persistedLiveData.value

        val onChangedListener = Observer<Any> {
            metaDataRepository.types.value?.takeIf { it.isNotEmpty() }?.let { types ->
                val persistedData = persistedLiveData.value ?: defaultValue
                val newOptions = initialList.toMutableList()

                val options = types.map { FabTypeOption(it) }.sortedBy { it.displayName }
                newOptions.addAll(options)

                if (newOptions != persistedData.options) {
                    persistedData.options = newOptions

                    if (!newOptions.contains(persistedData.selectedOption)) {
                        persistedData.selectedOption = newOptions[0]
                    }

                    persistedLiveData.value = persistedData
                    mediatorLiveData.value = persistedData
                }
            }
        }

        mediatorLiveData.addSource(persistedLiveData, onChangedListener)
        mediatorLiveData.addSource(metaDataRepository.types, onChangedListener)

        return mediatorLiveData
    }

    private fun keywordsLiveData(savedStateHandle: SavedStateHandle): MutableLiveData<SpinnerFilter> {
        val initialList = listOf(FabKeywordOption(context.getString(R.string.fab_any_keyword)))
        val defaultValue = FabKeywordFilter(
            initialList,
            initialList[0]
        )

        val persistedLiveData = savedStateHandle.getLiveData(
            "keywordKey",
            defaultValue
        )

        val mediatorLiveData = MediatorLiveData<SpinnerFilter>()
        mediatorLiveData.value = persistedLiveData.value

        val onChangedListener = Observer<Any> {
            metaDataRepository.keywords.value?.takeIf { it.isNotEmpty() }?.let { keywords ->
                val persistedData = persistedLiveData.value ?: defaultValue
                val newOptions = initialList.toMutableList()

                val options = keywords.map { FabKeywordOption(it) }.sortedBy { it.displayName }
                newOptions.addAll(options)

                if (newOptions != persistedData.options) {
                    persistedData.options = newOptions

                    if (!newOptions.contains(persistedData.selectedOption)) {
                        persistedData.selectedOption = newOptions[0]
                    }

                    persistedLiveData.value = persistedData
                    mediatorLiveData.value = persistedData
                }
            }
        }

        mediatorLiveData.addSource(persistedLiveData, onChangedListener)
        mediatorLiveData.addSource(metaDataRepository.keywords, onChangedListener)

        return mediatorLiveData
    }

    private fun setLiveData(savedStateHandle: SavedStateHandle): MutableLiveData<SpinnerFilter> {
        val initialList = listOf(FabSetOption(context.getString(R.string.fab_any_set), "0"))
        val defaultValue = FabSetFilter(
            initialList,
            initialList[0],
        )

        val persistedLiveData = savedStateHandle.getLiveData(
            "setKey",
            defaultValue
        )

        val mediatorLiveData = MediatorLiveData<SpinnerFilter>()
        mediatorLiveData.value = persistedLiveData.value

        val onChangedListener = Observer<Any> {
            setRepository.setList.value?.let { sets ->
                val persistedData = persistedLiveData.value ?: defaultValue
                val newOptions = initialList.toMutableList()

                val options = sets.map {
                    FabSetOption(it.name, it.id)
                }

                options.let {
                    newOptions.addAll((it))
                }

                if (newOptions != persistedData.options) {
                    persistedData.options = newOptions

                    if (!newOptions.contains(persistedData.selectedOption)) {
                        persistedData.selectedOption = newOptions[0]
                    }

                    persistedLiveData.value = persistedData
                    mediatorLiveData.value = persistedData
                }
            }
        }

        mediatorLiveData.addSource(persistedLiveData, onChangedListener)
        mediatorLiveData.addSource(setRepository.setList, onChangedListener)

        return mediatorLiveData
    }
//
//    private fun formatLiveData(savedStateHandle: SavedStateHandle): MutableLiveData<SpinnerFilter> {
//        val initialList = listOf(
//            SWCCGFormatOption(
//                SWCCGFormat(
//                    context.getString(R.string.swccg_non_legacy),
//                    "non_legacy",
//                    emptyList(),
//                    emptyList(),
//                    emptyList(),
//                    listOf("601"),
//                    emptyList(),
//                )
//            ),
//            SWCCGFormatOption(
//                SWCCGFormat(
//                    context.getString(R.string.swccg_any_format),
//                    "any_format",
//                    emptyList(),
//                    emptyList(),
//                    emptyList(),
//                    emptyList(),
//                    emptyList(),
//                )
//            )
//        )
//        val defaultValue = SWCCGFormatFilter(
//            initialList,
//            initialList[1],
//            initialList[0],
//        )
//
//        val persistedLiveData = savedStateHandle.getLiveData(
//            "formatKey",
//            defaultValue
//        )
//
//        val mediatorLiveData = MediatorLiveData<SpinnerFilter>()
//        mediatorLiveData.value = persistedLiveData.value
//
//        val onChangedListener = Observer<Any> {
//            formatRepository.formatsList.value?.takeIf {
//                it.isNotEmpty()
//            }?.let { formats ->
//                val persistedData = persistedLiveData.value ?: defaultValue
//                val newOptions = initialList.toMutableList()
//
//                val options = formats.map { SWCCGFormatOption(it) }
//
//                newOptions.addAll(options)
//
//                if (newOptions != persistedData.options) {
//                    persistedData.options = newOptions
//
//                    if (!newOptions.contains(persistedData.selectedOption)) {
//                        persistedData.selectedOption = newOptions[0]
//                    }
//
//                    persistedLiveData.value = persistedData
//                    mediatorLiveData.value = persistedData
//                }
//            }
//        }
//
//        mediatorLiveData.addSource(persistedLiveData, onChangedListener)
//        mediatorLiveData.addSource(formatRepository.formatsList, onChangedListener)
//
//        return mediatorLiveData
//    }
}