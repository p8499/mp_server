package com.kerryprops.mp.controller

import com.kerryprops.mp.FilterExpr
import com.kerryprops.mp.OrderByListExpr
import com.kerryprops.mp.bean.Equipment
import com.kerryprops.mp.controller.base.EquipmentControllerBase
import com.kerryprops.mp.mask.EquipmentMask
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.InputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class EquipmentController : EquipmentControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, eqid: Int, mask: EquipmentMask): Equipment? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //无此对象，返回404
        val bean = equipmentService[eqid, mask] ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return bean
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, bean: Equipment?): Equipment? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //添加失败，返回500
        val added = equipmentService.add(bean) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return added
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, eqid: Int, bean: Equipment?, mask: EquipmentMask): Equipment? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //修改失败，返回500
        val updated = equipmentService.update(bean, mask) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return updated
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, eqid: Int) {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //删除失败，返回204
        equipmentService.delete(eqid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
        File("$attachmentFolder/${Equipment.NAME}/$eqid").delete()
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //处理
        return equipmentService.count(filter)
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: EquipmentMask): MutableList<Equipment> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        return equipmentService.query(filter, orderByList, start, count, mask)
    }

    override fun onReadAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, eqid: Int, name: String?): ByteArray? {
        //缺name，返回400
        name ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //文件不存在，返回404
        val file = File("$attachmentFolder/${Equipment.NAME}/$eqid/$name").takeIf { it.exists() } ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return file.readBytes()
    }

    override fun onWriteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, eqid: Int, name: String?, bytes: ByteArray) {
        //缺name，返回400
        name ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //文件已存在，返回409
        val file = File("$attachmentFolder/${Equipment.NAME}/$eqid/$name").also { if (!it.parentFile.exists()) it.parentFile.mkdirs() }.takeUnless { it.exists() } ?: run { response.status = HttpServletResponse.SC_CONFLICT; return }
        //处理
        file.writeBytes(bytes)
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, eqid: Int, name: String?) {
        //缺name，返回400
        name ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //文件不存在，返回204
        val file = File("$attachmentFolder/${Equipment.NAME}/$eqid/$name").takeIf { it.exists() } ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
        //处理
        file.delete()
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, eqid: Int): MutableList<String> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        return File("$attachmentFolder/${Equipment.NAME}/$eqid").takeIf { it.exists() }?.list()?.toMutableList() ?: mutableListOf()
    }

    @Value(value = "\${app.attachmentFolder}")
    lateinit var attachmentFolder: String
}