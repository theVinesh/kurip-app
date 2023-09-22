package model

import TimeUtil

data class KuripModel(
    val id: Long = 0,
    val title: String = "",
    val body: String = "",
    val lastModified: Long = TimeUtil.now(),
)
