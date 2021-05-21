package com.example.refill_project.models

class Products {
    var id: Int
    var name: String
    var description : String
    var price: Int

    constructor(id: Int, name:String, description:String, price:Int){
        this.id = id
        this.name = name
        this.description = description
        this.price = price
    }
}