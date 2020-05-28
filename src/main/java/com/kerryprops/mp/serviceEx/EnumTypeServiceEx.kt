package com.kerryprops.mp.serviceEx

import com.kerryprops.mp.FilterLogicExpr
import com.kerryprops.mp.bean.EnumTypeValue
import com.kerryprops.mp.service.EnumTypeService
import com.kerryprops.mp.service.EnumTypeValueService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("enumTypeServiceEx")
open class EnumTypeServiceEx : EnumTypeService() {
    @Transactional
    override fun delete(etid: String?): Boolean {
        enumTypeValueService.delete(FilterLogicExpr().equalsString(EnumTypeValue.FIELD_EVETID, etid))
        return super.delete(etid)
    }

    @Value(value = "#{enumTypeValueService}")
    lateinit var enumTypeValueService: EnumTypeValueService
}