package com.example.kiesslingpaul_uebung05


data class Person(val gender: String, val name: NameInfo, val location: LocationInfo, val picture: PictureURL)

data class NameInfo(val title: String, val first: String, val last: String)
data class LocationInfo(val street: String, val city: String, val state: String, val postcode: String )
data class PictureURL(val large: String, val medium: String, val thumbnail: String)