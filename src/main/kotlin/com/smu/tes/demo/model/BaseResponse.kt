package com.smu.tes.demo.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class BaseResponse(
    var message: String = "",
    var data: Any? = null
)