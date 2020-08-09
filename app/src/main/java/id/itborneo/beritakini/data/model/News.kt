package id.itborneo.beritakini.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    var title: String?,
    var sourceName: String?,
    var author: String?,
    var url: String?,
    var urlImage: String?
):Parcelable