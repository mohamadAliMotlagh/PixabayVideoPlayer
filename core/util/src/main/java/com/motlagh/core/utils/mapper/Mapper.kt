package com.motlagh.core.utils.mapper

fun interface Mapper<I, O> : (I) -> O
