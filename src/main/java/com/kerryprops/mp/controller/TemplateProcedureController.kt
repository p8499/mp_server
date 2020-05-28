package com.kerryprops.mp.controller

import com.kerryprops.mp.FilterExpr
import com.kerryprops.mp.OrderByListExpr
import com.kerryprops.mp.bean.TemplateProcedure
import com.kerryprops.mp.controller.base.TemplateProcedureControllerBase
import com.kerryprops.mp.mask.TemplateProcedureMask
import com.kerryprops.mp.serviceEx.TemplateProcedureServiceEx
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import java.io.InputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class TemplateProcedureController : TemplateProcedureControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tpid: Int, mask: TemplateProcedureMask): TemplateProcedure? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //无此对象，返回404
        val bean = templateProcedureService[tpid, mask] ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return bean
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, bean: TemplateProcedure?): TemplateProcedure? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //添加失败，返回500
        val added = templateProcedureService.add(bean) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return added
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tpid: Int, bean: TemplateProcedure?, mask: TemplateProcedureMask): TemplateProcedure? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //修改失败，返回500
        val updated = templateProcedureService.update(bean, mask) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return updated
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tpid: Int) {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //删除失败，返回204
        templateProcedureServiceEx.delete(tpid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //处理
        return templateProcedureService.count(filter)
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: TemplateProcedureMask): MutableList<TemplateProcedure> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        return templateProcedureService.query(filter, orderByList, start, count, mask)
    }

    override fun onReadAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tpid: Int, name: String?): ByteArray? {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun onWriteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tpid: Int, name: String?, bytes: ByteArray) {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return }
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tpid: Int, name: String?) {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return }
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, tpid: Int): MutableList<String> {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return mutableListOf() }
    }

    @Value(value = "#{templateProcedureServiceEx}")
    lateinit var templateProcedureServiceEx: TemplateProcedureServiceEx
}