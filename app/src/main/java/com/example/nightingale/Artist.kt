package com.example.nightingale

import android.os.Parcel
import android.os.Parcelable

data class Artist(
    val id: Int,
    val link: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val tracklist: String,
    val type: String
): Parcelable {
    // Constructor to read from Parcel
    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        link = parcel.readString() ?: "",
        name = parcel.readString() ?: "",
        picture = parcel.readString() ?: "",
        picture_big = parcel.readString() ?: "",
        picture_medium = parcel.readString() ?: "",
        picture_small = parcel.readString() ?: "",
        picture_xl = parcel.readString() ?: "",
        tracklist = parcel.readString() ?: "",
        type = parcel.readString() ?: ""
    )

    // Method to write object properties into Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(link)
        parcel.writeString(name)
        parcel.writeString(picture)
        parcel.writeString(picture_big)
        parcel.writeString(picture_medium)
        parcel.writeString(picture_small)
        parcel.writeString(picture_xl)
        parcel.writeString(tracklist)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    // Companion object to generate instances from a Parcel
    companion object CREATOR : Parcelable.Creator<Artist> {
        override fun createFromParcel(parcel: Parcel): Artist {
            return Artist(parcel)
        }

        override fun newArray(size: Int): Array<Artist?> {
            return arrayOfNulls(size)
        }
    }
}