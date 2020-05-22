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
import com.kerryprops.mp.mask.UserMask;
import com.kerryprops.mp.bean.User;

@Component("userMapper")
public interface UserMapper {
  @Select("SELECT COUNT(*)>0 FROM V0092 WHERE USID=#{usid}")
  boolean exists(@Param("usid") Integer usid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.usid or mask.usan8 or mask.uscell or mask.usmail or mask.usname or mask.uspswd or mask.usstatus'><trim prefix='SELECT' suffixOverrides=','><if test='mask.usid'>USID, </if><if test='mask.usan8'>USAN8, </if><if test='mask.uscell'>USCELL, </if><if test='mask.usmail'>USMAIL, </if><if test='mask.usname'>USNAME, </if><if test='mask.uspswd'>USPSWD, </if><if test='mask.usstatus'>USSTATUS, </if></trim>FROM V0092 WHERE USID=#{usid}</if></when><otherwise>SELECTUSID,USAN8,USCELL,USMAIL,USNAME,USPSWD,USSTATUS FROM V0092 WHERE USID=#{usid}</otherwise></choose></script>")
  User get(@Param("usid") Integer usid, @Param("mask") UserMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F0092_USID.nextval AS usid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "usid",
    keyProperty = "bean.usid"
  )
  @Insert(
      "INSERT INTO F0092 (USID,USAN8,USCELL,USMAIL,USNAME,USPSWD,USSTATUS) VALUES (#{bean.usid,jdbcType=INTEGER},#{bean.usan8,jdbcType=INTEGER},#{bean.uscell,jdbcType=VARCHAR},#{bean.usmail,jdbcType=VARCHAR},#{bean.usname,jdbcType=VARCHAR},#{bean.uspswd,jdbcType=VARCHAR},#{bean.usstatus,jdbcType=SMALLINT})")
  void add(@Param("bean") User bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.usan8 or mask.uscell or mask.usmail or mask.usname or mask.uspswd or mask.usstatus'>UPDATE F0092 <set><if test='mask.usan8'>USAN8=#{bean.usan8,jdbcType=INTEGER}, </if><if test='mask.uscell'>USCELL=#{bean.uscell,jdbcType=VARCHAR}, </if><if test='mask.usmail'>USMAIL=#{bean.usmail,jdbcType=VARCHAR}, </if><if test='mask.usname'>USNAME=#{bean.usname,jdbcType=VARCHAR}, </if><if test='mask.uspswd'>USPSWD=#{bean.uspswd,jdbcType=VARCHAR}, </if><if test='mask.usstatus'>USSTATUS=#{bean.usstatus,jdbcType=SMALLINT}, </if></set>WHERE USID=#{bean.usid,jdbcType=INTEGER}</if></when><otherwise>UPDATE F0092 SET USAN8=#{bean.usan8,jdbcType=INTEGER}, USCELL=#{bean.uscell,jdbcType=VARCHAR}, USMAIL=#{bean.usmail,jdbcType=VARCHAR}, USNAME=#{bean.usname,jdbcType=VARCHAR}, USPSWD=#{bean.uspswd,jdbcType=VARCHAR}, USSTATUS=#{bean.usstatus,jdbcType=SMALLINT} WHERE USID=#{bean.usid,jdbcType=INTEGER}</otherwise></choose></script>")
  void update(@Param("bean") User bean, @Param("mask") UserMask mask);

  @Delete("DELETE FROM F0092 WHERE USID=#{usid}")
  boolean delete(Integer usid);

  @Delete(
      "<script>DELETE FROM F0092<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.usid'>usid,</if><if test='mask.usan8'>usan8,</if><if test='mask.uscell'>uscell,</if><if test='mask.usmail'>usmail,</if><if test='mask.usname'>usname,</if><if test='mask.uspswd'>uspswd,</if><if test='mask.usstatus'>usstatus,</if></trim></when><otherwise>SELECT usid, usan8, uscell, usmail, usname, uspswd, usstatus</otherwise></choose> FROM V0092<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<User> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") UserMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V0092<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(USID),NULL,${defaultValue}) FROM V0092<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minUsid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(USID),NULL,${defaultValue}) FROM V0092<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxUsid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(USAN8),NULL,${defaultValue}) FROM V0092<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minUsan8(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(USAN8),NULL,${defaultValue}) FROM V0092<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxUsan8(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(USSTATUS),NULL,${defaultValue}) FROM V0092<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minUsstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(USSTATUS),NULL,${defaultValue}) FROM V0092<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxUsstatus(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}
