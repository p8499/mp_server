package com.kerryprops.mp.service.ex

import com.kerryprops.mp.FilterLogicExpr
import com.kerryprops.mp.bean.CrewUser
import com.kerryprops.mp.bean.UserRole
import com.kerryprops.mp.service.CrewUserService
import com.kerryprops.mp.service.UserRoleService
import com.kerryprops.mp.service.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("userServiceEx")
open class UserServiceEx : UserService() {
    @Transactional
    override fun delete(usid: Int): Boolean {
        userRoleService.delete(FilterLogicExpr().equalsNumber(UserRole.FIELD_URUSID, usid))
        crewUserService.delete(FilterLogicExpr().equalsNumber(CrewUser.FIELD_CUUSID, usid))
        return super.delete(usid)
    }

    @Value(value = "#{userRoleService}")
    lateinit var userRoleService: UserRoleService

    @Value(value = "#{crewUserService}")
    lateinit var crewUserService: CrewUserService
}
