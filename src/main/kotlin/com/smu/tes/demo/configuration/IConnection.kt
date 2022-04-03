package com.smu.tes.demo.configuration

interface IConnection {
    var connectionId: String
    var type: String
    var sapClient: String
    var userID: String
    var password: String
    var language: String
    var host: String
    var systemNumber: String
    var r3name: String
    var minPool: String?
    var maxPool: String?
}