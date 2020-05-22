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
import com.kerryprops.mp.mask.UserRoleMask;
import com.kerryprops.mp.bean.UserRole;

@Component("userRoleMapper")
public interface UserRoleMapper {
  @Select("SELECT COUNT(*)>0 FROM V00921 WHERE URID=#{urid}")
  boolean exists(@Param("urid") Integer urid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.urid or mask.urusid or mask.urroid or mask.urusname or mask.urroname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.urid'>URID, </if><if test='mask.urusid'>URUSID, </if><if test='mask.urroid'>URROID, </if><if test='mask.urusname'>URUSNAME, </if><if test='mask.urroname'>URRONAME, </if></trim>FROM V00921 WHERE URID=#{urid}</if></when><otherwise>SELECTURID,URUSID,URROID,URUSNAME,URRONAME FROM V00921 WHERE URID=#{urid}</otherwise></choose></script>")
  UserRole get(@Param("urid") Integer urid, @Param("mask") UserRoleMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F00921_URID.nextval AS urid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "urid",
    keyProperty = "bean.urid"
  )
  @Insert(
      "INSERT INTO F00921 (URID,URUSID,URROID) VALUES (#{bean.urid,jdbcType=INTEGER},#{bean.urusid,jdbcType=INTEGER},#{bean.urroid,jdbcType=VARCHAR})")
  void add(@Param("bean") UserRole bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.urusid or mask.urroid'>UPDATE F00921 <set><if test='mask.urusid'>URUSID=#{bean.urusid,jdbcType=INTEGER}, </if><if test='mask.urroid'>URROID=#{bean.urroid,jdbcType=VARCHAR}, </if></set>WHERE URID=#{bean.urid,jdbcType=INTEGER}</if></when><otherwise>UPDATE F00921 SET URUSID=#{bean.urusid,jdbcType=INTEGER}, URROID=#{bean.urroid,jdbcType=VARCHAR} WHERE URID=#{bean.urid,jdbcType=INTEGER}</otherwise></choose></script>")
  void update(@Param("bean") UserRole bean, @Param("mask") UserRoleMask mask);

  @Delete("DELETE FROM F00921 WHERE URID=#{urid}")
  boolean delete(Integer urid);

  @Delete(
      "<script>DELETE FROM F00921<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.urid'>urid,</if><if test='mask.urusid'>urusid,</if><if test='mask.urroid'>urroid,</if><if test='mask.urusname'>urusname,</if><if test='mask.urroname'>urroname,</if></trim></when><otherwise>SELECT urid, urusid, urroid, urusname, urroname</otherwise></choose> FROM V00921<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<UserRole> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") UserRoleMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V00921<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(URID),NULL,${defaultValue}) FROM V00921<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minUrid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(URID),NULL,${defaultValue}) FROM V00921<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxUrid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(URUSID),NULL,${defaultValue}) FROM V00921<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minUrusid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(URUSID),NULL,${defaultValue}) FROM V00921<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxUrusid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}
