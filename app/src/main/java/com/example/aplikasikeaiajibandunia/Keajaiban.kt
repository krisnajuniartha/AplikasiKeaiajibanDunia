package com.example.aplikasikeaiajibandunia

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler

@Parcelize
data class Keajaiban(
    val name: String?,
    val description: String?,
    val photo: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    companion object : Parceler<Keajaiban> {

        override fun Keajaiban.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(description)
            parcel.writeString(photo)
        }

        override fun create(parcel: Parcel): Keajaiban {
            return Keajaiban(parcel)
        }
    }
}
