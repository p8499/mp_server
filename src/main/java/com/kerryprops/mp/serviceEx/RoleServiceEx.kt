package com.kerryprops.mp.serviceEx

import com.kerryprops.mp.FilterLogicExpr
import com.kerryprops.mp.bean.RolePrivilege
import com.kerryprops.mp.service.RolePrivilegeService
import com.kerryprops.mp.service.RoleService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("roleServiceEx")
open class RoleServiceEx : RoleService() {
    @Transactional
    override fun delete(roid: String): Boolean {
        rolePrivilegeService.delete(FilterLogicExpr().equalsString(RolePrivilege.FIELD_RPROID, roid))
        return super.delete(roid)
    }

    @Value(value = "#{rolePrivilegeService}")
    lateinit var rolePrivilegeService: RolePrivilegeService
}
