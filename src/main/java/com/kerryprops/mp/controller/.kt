package com.kerryprops.mp.controller

import com.kerryprops.mp.FilterLogicExpr
import com.kerryprops.mp.bean.RolePrivilege
import com.kerryprops.mp.bean.User
import com.kerryprops.mp.bean.UserRole
import com.kerryprops.mp.mask.UserMask
import com.kerryprops.mp.service.RolePrivilegeService
import com.kerryprops.mp.service.UserService
import javax.servlet.http.HttpSession

val HttpSession.isSignedIn: Boolean get() = usid != null
var HttpSession.usid: Int?
    get() = getAttribute("usid") as? Int?
    set(value) = setAttribute("usid", value)

operator fun UserService.get(from: String, alias: String, mask: UserMask): User? = when (from) {
    "an8" -> query(FilterLogicExpr().equalsString(User.FIELD_USAN8, alias), null, 0, 1, mask).firstOrNull()
    "cell" -> query(FilterLogicExpr().equalsString(User.FIELD_USCELL, alias), null, 0, 1, mask).firstOrNull()
    "mail" -> query(FilterLogicExpr().equalsString(User.FIELD_USMAIL, alias), null, 0, 1, mask).firstOrNull()
    else -> null
}

fun RolePrivilegeService.check(usid: Int, prid: String): Boolean = count(
        FilterLogicExpr().equalsString(RolePrivilege.FIELD_RPPRID, prid).existsObject(
                UserRole.TABLE, FilterLogicExpr().equalsField(UserRole.FIELD_URROID, RolePrivilege.FIELD_RPROID).equalsNumber(UserRole.FIELD_URUSID, usid))) > 0
