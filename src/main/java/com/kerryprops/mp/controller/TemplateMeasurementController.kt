package com.kerryprops.mp.controller

import com.kerryprops.mp.FilterExpr
import com.kerryprops.mp.OrderByListExpr
import com.kerryprops.mp.bean.TemplateMeasurement
import com.kerryprops.mp.controller.base.TemplateMeasurementControllerBase
import com.kerryprops.mp.mask.TemplateMeasurementMask
import org.springframework.web.bind.annotation.RestController
import java.io.InputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class TemplateMeasurementController : TemplateMeasurementControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tsid: Int, mask: TemplateMeasurementMask): TemplateMeasurement? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //无此对象，返回404
        val bean = templateMeasurementService[tsid, mask] ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return bean
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, bean: TemplateMeasurement?): TemplateMeasurement? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //添加失败，返回500
        val added = templateMeasurementService.add(bean) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return added
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tsid: Int, bean: TemplateMeasurement?, mask: TemplateMeasurementMask): TemplateMeasurement? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //修改失败，返回500
        val updated = templateMeasurementService.update(bean, mask) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return updated
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tsid: Int) {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //删除失败，返回204
        templateMeasurementService.delete(tsid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //处理
        return templateMeasurementService.count(filter)
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: TemplateMeasurementMask): MutableList<TemplateMeasurement> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        return templateMeasurementService.query(filter, orderByList, start, count, mask)
    }

    override fun onReadAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tsid: Int, name: String?): ByteArray? {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun onWriteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tsid: Int, name: String?, bytes: ByteArray) {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return }
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tsid: Int, name: String?) {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return }
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tsid: Int): MutableList<String> {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return mutableListOf() }
    }
}