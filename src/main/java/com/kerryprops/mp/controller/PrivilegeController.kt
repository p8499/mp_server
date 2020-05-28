package com.kerryprops.mp.controller

import com.kerryprops.mp.FilterExpr
import com.kerryprops.mp.OrderByListExpr
import com.kerryprops.mp.bean.Privilege
import com.kerryprops.mp.controller.base.PrivilegeControllerBase
import com.kerryprops.mp.mask.PrivilegeMask
import org.springframework.web.bind.annotation.RestController
import java.io.InputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class PrivilegeController : PrivilegeControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, prid: String, mask: PrivilegeMask): Privilege? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //无此对象，返回404
        val bean = privilegeService[prid, mask] ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return bean
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, prid: String, bean: Privilege?): Privilege? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //添加失败，返回500
        val added = privilegeService.add(bean) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return added
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, prid: String, bean: Privilege?, mask: PrivilegeMask): Privilege? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //修改失败，返回500
        val updated = privilegeService.update(bean, mask) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return updated
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, prid: String) {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //删除失败，返回204
        privilegeService.delete(prid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //处理
        return privilegeService.count(filter)
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: PrivilegeMask): MutableList<Privilege> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        return privilegeService.query(filter, orderByList, start, count, mask)
    }

    override fun onReadAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, prid: String, name: String?): ByteArray? {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
    }

    override fun onWriteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, prid: String, name: String?, bytes: ByteArray) {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return }
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, prid: String, name: String?) {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return }
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, prid: String): MutableList<String> {
        //不提供此方法，返回404
        kotlin.run { response.status = HttpServletResponse.SC_NOT_FOUND; return mutableListOf() }
    }
}