package com.example.kiesslingpaul_uebung05


// data classes for the response data
data class Results(val results: Array<Person>)
data class Person(val gender: String, val name: NameInfo, val location: LocationInfo, val picture: PictureURL)

data class NameInfo(val title: String, val first: String, val last: String)
data class LocationInfo(val street: Street, val city: String, val state: String, val postcode: String, val country: String)
data class Street(val number: String, val name: String)
data class PictureURL(val large: String, val medium: String, val thumbnail: String)
