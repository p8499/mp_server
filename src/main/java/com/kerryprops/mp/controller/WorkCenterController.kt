package com.kerryprops.mp.controller

import com.kerryprops.mp.FilterExpr
import com.kerryprops.mp.OrderByListExpr
import com.kerryprops.mp.bean.WorkCenter
import com.kerryprops.mp.controller.base.WorkCenterControllerBase
import com.kerryprops.mp.mask.WorkCenterMask
import org.springframework.web.bind.annotation.RestController
import java.io.InputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class WorkCenterController : WorkCenterControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, wcmcu: String, mask: WorkCenterMask): WorkCenter? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //无此对象，返回404
        val bean = workCenterService[wcmcu, mask] ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return bean
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, wcmcu: String, bean: WorkCenter?): WorkCenter? {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, wcmcu: String, bean: WorkCenter?, mask: WorkCenterMask): WorkCenter? {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, wcmcu: String) {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return }
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //处理
        return workCenterService.count(filter)
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: WorkCenterMask): MutableList<WorkCenter> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        return workCenterService.query(filter, orderByList, start, count, mask)
    }

    override fun onReadAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, wcmcu: String, name: String?): ByteArray? {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun onWriteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, wcmcu: String, name: String?, bytes: ByteArray) {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return }
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, wcmcu: String, name: String?) {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return }
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, wcmcu: String): MutableList<String> {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return mutableListOf() }
    }
}