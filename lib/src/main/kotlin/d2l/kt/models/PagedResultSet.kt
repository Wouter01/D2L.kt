package d2l.kt.models

data class PagedResultSet<T>(val pagingInfo: PagingInfo, val items: List<T>)

data class PagingInfo(val bookmark: String, val hasMoreItems: Boolean)
