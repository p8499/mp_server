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
import com.kerryprops.mp.mask.CrewUserMask;
import com.kerryprops.mp.bean.CrewUser;

@Component("crewUserMapper")
public interface CrewUserMapper {
  @Select("SELECT COUNT(*)>0 FROM V30031 WHERE CUID=#{cuid}")
  boolean exists(@Param("cuid") Integer cuid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.cuid or mask.cucwid or mask.cucwname or mask.cuwcmcu or mask.cuwcdl01 or mask.cuusid or mask.cuusname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.cuid'>CUID, </if><if test='mask.cucwid'>CUCWID, </if><if test='mask.cucwname'>CUCWNAME, </if><if test='mask.cuwcmcu'>CUWCMCU, </if><if test='mask.cuwcdl01'>CUWCDL01, </if><if test='mask.cuusid'>CUUSID, </if><if test='mask.cuusname'>CUUSNAME, </if></trim>FROM V30031 WHERE CUID=#{cuid}</if></when><otherwise>SELECTCUID,CUCWID,CUCWNAME,CUWCMCU,CUWCDL01,CUUSID,CUUSNAME FROM V30031 WHERE CUID=#{cuid}</otherwise></choose></script>")
  CrewUser get(@Param("cuid") Integer cuid, @Param("mask") CrewUserMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F30031_CUID.nextval AS cuid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "cuid",
    keyProperty = "bean.cuid"
  )
  @Insert(
      "INSERT INTO F30031 (CUID,CUCWID,CUUSID) VALUES (#{bean.cuid,jdbcType=INTEGER},#{bean.cucwid,jdbcType=INTEGER},#{bean.cuusid,jdbcType=INTEGER})")
  void add(@Param("bean") CrewUser bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.cucwid or mask.cuusid'>UPDATE F30031 <set><if test='mask.cucwid'>CUCWID=#{bean.cucwid,jdbcType=INTEGER}, </if><if test='mask.cuusid'>CUUSID=#{bean.cuusid,jdbcType=INTEGER}, </if></set>WHERE CUID=#{bean.cuid,jdbcType=INTEGER}</if></when><otherwise>UPDATE F30031 SET CUCWID=#{bean.cucwid,jdbcType=INTEGER}, CUUSID=#{bean.cuusid,jdbcType=INTEGER} WHERE CUID=#{bean.cuid,jdbcType=INTEGER}</otherwise></choose></script>")
  void update(@Param("bean") CrewUser bean, @Param("mask") CrewUserMask mask);

  @Delete("DELETE FROM F30031 WHERE CUID=#{cuid}")
  boolean delete(Integer cuid);

  @Delete(
      "<script>DELETE FROM F30031<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.cuid'>cuid,</if><if test='mask.cucwid'>cucwid,</if><if test='mask.cucwname'>cucwname,</if><if test='mask.cuwcmcu'>cuwcmcu,</if><if test='mask.cuwcdl01'>cuwcdl01,</if><if test='mask.cuusid'>cuusid,</if><if test='mask.cuusname'>cuusname,</if></trim></when><otherwise>SELECT cuid, cucwid, cucwname, cuwcmcu, cuwcdl01, cuusid, cuusname</otherwise></choose> FROM V30031<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<CrewUser> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") CrewUserMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V30031<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(CUID),NULL,${defaultValue}) FROM V30031<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minCuid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(CUID),NULL,${defaultValue}) FROM V30031<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxCuid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(CUCWID),NULL,${defaultValue}) FROM V30031<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minCucwid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(CUCWID),NULL,${defaultValue}) FROM V30031<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxCucwid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(CUUSID),NULL,${defaultValue}) FROM V30031<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minCuusid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(CUUSID),NULL,${defaultValue}) FROM V30031<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxCuusid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}
