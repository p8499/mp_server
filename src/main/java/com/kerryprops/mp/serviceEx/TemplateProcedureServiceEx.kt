package com.kerryprops.mp.serviceEx

import com.kerryprops.mp.FilterLogicExpr
import com.kerryprops.mp.bean.TemplateMeasurement
import com.kerryprops.mp.service.TemplateMeasurementService
import com.kerryprops.mp.service.TemplateProcedureService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("templateProcedureServiceEx")
open class TemplateProcedureServiceEx : TemplateProcedureService() {
    @Transactional
    override fun delete(tpid: Int?): Boolean {
        templateMeasurementService.delete(FilterLogicExpr().equalsNumber(TemplateMeasurement.FIELD_TSTPID, tpid));
        return super.delete(tpid);
    }

    @Value(value = "#{templateMeasurementService}")
    lateinit var templateMeasurementService: TemplateMeasurementService
}