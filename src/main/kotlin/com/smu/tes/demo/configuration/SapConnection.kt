package com.smu.tes.demo.configuration

data class SapConnection(
    override var connectionId: String = "",
    override var type: String = "",
    override var sapClient: String  = "",
    override var userID: String = "",
    override var password: String = "",
    override var language: String = "",
    override var host: String = "",
    override var systemNumber: String = "",
    override var r3name: String = "",
    override var minPool: String? = "",
    override var maxPool: String? = ""
) : IConnection