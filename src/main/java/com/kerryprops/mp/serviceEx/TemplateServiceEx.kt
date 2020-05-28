package com.kerryprops.mp.serviceEx

import com.kerryprops.mp.FilterLogicExpr
import com.kerryprops.mp.bean.TemplateProcedure
import com.kerryprops.mp.mask.TemplateProcedureMask
import com.kerryprops.mp.service.TemplateService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("templateServiceEx")
open class TemplateServiceEx : TemplateService() {
    @Transactional
    override fun delete(ttid: Int?): Boolean {
        templateProcedureServiceEx
                .query(FilterLogicExpr().equalsNumber(TemplateProcedure.FIELD_TPTTID, ttid), null, 0, Long.MAX_VALUE, TemplateProcedureMask().keys(true))
                .forEach { templateProcedureServiceEx.delete(it.tpid) }
        return super.delete(ttid)
    }

    @Value(value = "#{templateProcedureServiceEx}")
    lateinit var templateProcedureServiceEx: TemplateProcedureServiceEx
}