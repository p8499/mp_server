package com.kerryprops.mp.controller

import com.kerryprops.mp.FilterExpr
import com.kerryprops.mp.OrderByListExpr
import com.kerryprops.mp.bean.User
import com.kerryprops.mp.controller.base.UserControllerBase
import com.kerryprops.mp.mask.UserMask
import com.kerryprops.mp.serviceEx.UserServiceEx
import org.apache.commons.codec.digest.DigestUtils
import org.apache.tika.Tika
import org.apache.tika.mime.MimeTypes
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.io.File
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class UserController : UserControllerBase() {
    override fun onGet(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int, mask: UserMask): User? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //无此对象，返回404
        //统一不输出密码
        val bean = userService[usid, mask.setUspswd(false)] ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return bean
    }

    override fun onAdd(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, bean: User?): User? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //添加失败，返回500
        val added = userService.add(bean.setUspswd(DigestUtils.md5Hex(defaultPassword))) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return added
    }

    override fun onUpdate(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int, bean: User?, mask: UserMask): User? {
        //缺bean，返回400
        bean ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //修改失败，返回500
        //统一不修改密码
        val updated = userService.update(bean, mask.setUspswd(false)) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        //处理
        return updated
    }

    override fun onDelete(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int) {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //删除失败，返回204
        userServiceEx.delete(usid).takeIf { it } ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
        File("$attachmentFolder/${User.NAME}/$usid").delete()
    }

    override fun onCount(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?): Long? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //处理
        return userService.count(filter)
    }

    override fun onQuery(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, filter: FilterExpr?, orderByList: OrderByListExpr?, start: Long, count: Long, mask: UserMask): MutableList<User> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        //统一不输出密码
        return userService.query(filter, orderByList, start, count, mask.setUspswd(false))
    }

    override fun onReadAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int, name: String?): ByteArray? {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //文件不存在，返回404
        val file = File("$attachmentFolder/${User.NAME}/$usid").takeIf { it.exists() }?.listFiles()?.takeIf { it.isNotEmpty() }?.get(0) ?: run { response.status = HttpServletResponse.SC_NOT_FOUND; return null }
        //处理
        return file.readBytes()
    }

    override fun onWriteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int, name: String?, bytes: ByteArray) {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //内容不符，返回406
        val contentType = Tika().detect(bytes).takeIf { it.startsWith("image/") } ?: run { response.status = HttpServletResponse.SC_NOT_ACCEPTABLE; return }
        //处理
        File("$attachmentFolder/${User.NAME}/$usid/portrait${MimeTypes.getDefaultMimeTypes().forName(contentType).extension}")
                .also { if (!it.parentFile.exists()) it.parentFile.mkdirs() }
                .also { it.parentFile.listFiles().forEach { it.delete() } }
                .writeBytes(bytes)
    }

    override fun onDeleteAttachment(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int, name: String?) {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //文件不存在，返回204
        val file = File("$attachmentFolder/${User.NAME}/$usid").takeIf { it.exists() }?.listFiles()?.takeIf { it.isNotEmpty() }?.get(0) ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
        //处理
        file.delete()
    }

    override fun onListAttachments(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int): MutableList<String> {
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return mutableListOf() }
        //处理
        return File("$attachmentFolder/${User.NAME}/$usid").takeIf { it.exists() }?.list()?.toMutableList() ?: mutableListOf()
    }

    @CrossOrigin(origins = [html], allowCredentials = "true")
    @RequestMapping(value = [(path + "_status")], method = [(RequestMethod.GET)], produces = ["application/json;charset=UTF-8"])
    fun status(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse): String? {
        //没有登录，返回204
        val usid = session.usid ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return null }
        //错误的usid，返回500
        val user = userService[usid, UserMask().setUsid(true)] ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return null }
        // 处理
        return jackson.writeValueAsString(user)
    }

    @CrossOrigin(origins = [html], allowCredentials = "true")
    @RequestMapping(value = [(path + "_signin")], method = [(RequestMethod.POST)], produces = ["application/json;charset=UTF-8"])
    fun signin(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @RequestParam from: String?, @RequestParam alias: String?, @RequestParam pswd: String?): String? {
        //缺from，返回400
        from ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //缺alias，返回400
        alias ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //缺pswd，返回400
        pswd ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return null }
        //无此用户，返回403
        val user = userService[from, alias, UserMask().all(true)] ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        //密码错，返回403
        user.uspswd.takeIf { it == DigestUtils.md5Hex(pswd) } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return null }
        // 处理
        return jackson.writeValueAsString(user.setUspswd(null).also { session.usid = it.usid })
    }

    @CrossOrigin(origins = [html], allowCredentials = "true")
    @RequestMapping(value = [(path + "_password")], method = [(RequestMethod.POST)], produces = ["application/json;charset=UTF-8"])
    fun password(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, @RequestParam old: String?, @RequestParam new: String?) {
        //缺old，返回400
        old ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        //缺new，返回400
        new ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //无此用户，返回403
        val user = userService[session.usid, UserMask().setUspswd(true)] ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //原密码错，返回403
        user.uspswd.takeIf { it == DigestUtils.md5Hex(old) } ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //修改失败，返回500
        user.uspswd = DigestUtils.md5Hex(new)
        userService.update(user, UserMask().setUspswd(true)) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return }
    }

    @CrossOrigin(origins = [html], allowCredentials = "true")
    @RequestMapping(value = [(path + "_reset")], method = [(RequestMethod.POST)], produces = ["application/json;charset=UTF-8"])
    fun reset(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, usid: Int?) {
        //缺usid，返回400
        usid ?: run { response.status = HttpServletResponse.SC_BAD_REQUEST; return }
        //未登录，返回403
        session.usid ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //无此用户，返回403
        val user = userService[session.usid, UserMask().setUspswd(true)] ?: run { response.status = HttpServletResponse.SC_FORBIDDEN; return }
        //修改失败，返回500
        user.uspswd = DigestUtils.md5Hex(defaultPassword)
        userService.update(user, UserMask().setUspswd(true)) ?: run { response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; return }
    }

    @CrossOrigin(origins = [html], allowCredentials = "true")
    @RequestMapping(value = [(path + "_signout")], method = [(RequestMethod.POST)], produces = ["application/json;charset=UTF-8"])
    fun signout(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse) {
        //未登录，返回204
        session.usid ?: run { response.status = HttpServletResponse.SC_NO_CONTENT; return }
        //处理
        session.removeAttribute("usid")
    }

    @Value(value = "\${app.defaultPassword}")
    lateinit var defaultPassword: String

    @Value(value = "\${app.attachmentFolder}")
    lateinit var attachmentFolder: String

    @Value(value = "#{userServiceEx}")
    lateinit var userServiceEx: UserServiceEx
}