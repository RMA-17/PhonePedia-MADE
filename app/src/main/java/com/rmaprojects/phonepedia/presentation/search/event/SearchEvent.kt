package com.rmaprojects.phonepedia.presentation.search.event

sealed class SearchEvent {
    data class TypingQueries(val query: String): SearchEvent()
    data class ChangeCategory(val category: FilterCategory): SearchEvent()
    object BeginSearch: SearchEvent()
}

enum class FilterCategory(val filterValue: String) {
    SMARTPHONE("Smartphones"),
    TABLETS("Tablets"),
    LAPTOPS("Laptops"),
    SMARTWATCHES("Smartwatches")
}