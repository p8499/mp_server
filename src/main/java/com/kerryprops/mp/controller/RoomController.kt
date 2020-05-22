package com.kerryprops.mp.controller

import com.kerryprops.mp.FilterExpr
import com.kerryprops.mp.OrderByListExpr
import com.kerryprops.mp.bean.Room
import com.kerryprops.mp.controller.base.RoomControllerBase
import com.kerryprops.mp.mask.RoomMask
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class RoomController : RoomControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, rmid: Int, mask: RoomMask): Room? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //无此对象，返回404
        val bean = roomService[rmid, mask] ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return bean
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, bean: Room?): Room? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //添加失败，返回500
        val added = roomService.add(bean) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return added
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, rmid: Int, bean: Room?, mask: RoomMask): Room? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //修改失败，返回500
        val updated = roomService.update(bean, mask) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return updated
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, rmid: Int) {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //删除失败，返回204
        roomService.delete(rmid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
        File(session.servletContext.getRealPath("$attachmentFolder/${Room.NAME}/$rmid")).delete()
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //处理
        return roomService.count(filter)
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: RoomMask): MutableList<Room> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        return roomService.query(filter, orderByList, start, count, mask)
    }

    override fun inputStream(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, rmid: Int, name: String): InputStream? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //文件不存在，返回404
        val file = File(session.servletContext.getRealPath("$attachmentFolder/${Room.NAME}/$rmid/$name")).takeIf { it.exists() } ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return file.inputStream()
    }

    override fun outputStream(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, rmid: Int, name: String): OutputStream? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //文件已存在，返回409
        val file = File(session.servletContext.getRealPath("$attachmentFolder/${Room.NAME}/$rmid/$name")).also { if (!it.parentFile.exists()) it.parentFile.mkdirs() }.takeUnless { it.exists() } ?: run { response.status = HttpServletResponse.SC_CONFLICT; return null }
        //处理
        return file.outputStream()
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, rmid: Int, name: String) {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //文件不存在，返回204
        val file = File(session.servletContext.getRealPath("$attachmentFolder/${Room.NAME}/$rmid/$name")).takeIf { it.exists() } ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
        //处理
        file.delete()
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, rmid: Int): MutableList<String> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        return File(session.servletContext.getRealPath("$attachmentFolder/${Room.NAME}/$rmid")).takeIf { it.exists() }?.list()?.toMutableList() ?: mutableListOf()
    }

    @Value(value = "\${app.attachmentFolder}")
    lateinit var attachmentFolder: String
}