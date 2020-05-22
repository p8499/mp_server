package com.kerryprops.mp.mapper.db02;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.RolePrivilegeMask;
import com.kerryprops.mp.bean.RolePrivilege;

@Component("rolePrivilegeMapper")
public interface RolePrivilegeMapper {
  @Select("SELECT COUNT(*)>0 FROM V00941 WHERE RPID=#{rpid}")
  boolean exists(@Param("rpid") Integer rpid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.rpid or mask.rproid or mask.rpprid or mask.rproname or mask.rpprname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.rpid'>RPID, </if><if test='mask.rproid'>RPROID, </if><if test='mask.rpprid'>RPPRID, </if><if test='mask.rproname'>RPRONAME, </if><if test='mask.rpprname'>RPPRNAME, </if></trim>FROM V00941 WHERE RPID=#{rpid}</if></when><otherwise>SELECTRPID,RPROID,RPPRID,RPRONAME,RPPRNAME FROM V00941 WHERE RPID=#{rpid}</otherwise></choose></script>")
  RolePrivilege get(@Param("rpid") Integer rpid, @Param("mask") RolePrivilegeMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F00941_RPID.nextval AS rpid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "rpid",
    keyProperty = "bean.rpid"
  )
  @Insert(
      "INSERT INTO F00941 (RPID,RPROID,RPPRID) VALUES (#{bean.rpid,jdbcType=INTEGER},#{bean.rproid,jdbcType=VARCHAR},#{bean.rpprid,jdbcType=VARCHAR})")
  void add(@Param("bean") RolePrivilege bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.rproid or mask.rpprid'>UPDATE F00941 <set><if test='mask.rproid'>RPROID=#{bean.rproid,jdbcType=VARCHAR}, </if><if test='mask.rpprid'>RPPRID=#{bean.rpprid,jdbcType=VARCHAR}, </if></set>WHERE RPID=#{bean.rpid,jdbcType=INTEGER}</if></when><otherwise>UPDATE F00941 SET RPROID=#{bean.rproid,jdbcType=VARCHAR}, RPPRID=#{bean.rpprid,jdbcType=VARCHAR} WHERE RPID=#{bean.rpid,jdbcType=INTEGER}</otherwise></choose></script>")
  void update(@Param("bean") RolePrivilege bean, @Param("mask") RolePrivilegeMask mask);

  @Delete("DELETE FROM F00941 WHERE RPID=#{rpid}")
  boolean delete(Integer rpid);

  @Delete(
      "<script>DELETE FROM F00941<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.rpid'>rpid,</if><if test='mask.rproid'>rproid,</if><if test='mask.rpprid'>rpprid,</if><if test='mask.rproname'>rproname,</if><if test='mask.rpprname'>rpprname,</if></trim></when><otherwise>SELECT rpid, rproid, rpprid, rproname, rpprname</otherwise></choose> FROM V00941<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<RolePrivilege> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") RolePrivilegeMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V00941<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(RPID),NULL,${defaultValue}) FROM V00941<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minRpid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(RPID),NULL,${defaultValue}) FROM V00941<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxRpid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}
