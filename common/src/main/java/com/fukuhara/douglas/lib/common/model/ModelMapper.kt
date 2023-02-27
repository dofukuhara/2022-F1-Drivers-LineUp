package com.fukuhara.douglas.lib.common.model

interface ModelMapper<IN, OUT> {
    // IN: DTO ---[transform]---> OUT: Domain Model
    fun transform(dtoData: IN, skipElementIfFailedToParseDriver: Boolean = false): OUT
}
