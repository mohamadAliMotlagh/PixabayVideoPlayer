package com.motlagh.feature.search.domain

sealed class SearchError : Throwable() {
    data object Network : SearchError()

    data object EmptyResult : SearchError()

    data object General : SearchError()
}