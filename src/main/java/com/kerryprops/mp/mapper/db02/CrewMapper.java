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
import com.kerryprops.mp.mask.CrewMask;
import com.kerryprops.mp.bean.Crew;

@Component("crewMapper")
public interface CrewMapper {
  @Select("SELECT COUNT(*)>0 FROM V3003 WHERE CWID=#{cwid}")
  boolean exists(@Param("cwid") Integer cwid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.cwid or mask.cwname or mask.cwan8 or mask.cwwcmcu or mask.cwwcdl01'><trim prefix='SELECT' suffixOverrides=','><if test='mask.cwid'>CWID, </if><if test='mask.cwname'>CWNAME, </if><if test='mask.cwan8'>CWAN8, </if><if test='mask.cwwcmcu'>CWWCMCU, </if><if test='mask.cwwcdl01'>CWWCDL01, </if></trim>FROM V3003 WHERE CWID=#{cwid}</if></when><otherwise>SELECTCWID,CWNAME,CWAN8,CWWCMCU,CWWCDL01 FROM V3003 WHERE CWID=#{cwid}</otherwise></choose></script>")
  Crew get(@Param("cwid") Integer cwid, @Param("mask") CrewMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F3003_CWID.nextval AS cwid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "cwid",
    keyProperty = "bean.cwid"
  )
  @Insert(
      "INSERT INTO F3003 (CWID,CWNAME,CWAN8,CWWCMCU) VALUES (#{bean.cwid,jdbcType=INTEGER},#{bean.cwname,jdbcType=VARCHAR},#{bean.cwan8,jdbcType=INTEGER},#{bean.cwwcmcu,jdbcType=VARCHAR})")
  void add(@Param("bean") Crew bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.cwname or mask.cwan8 or mask.cwwcmcu'>UPDATE F3003 <set><if test='mask.cwname'>CWNAME=#{bean.cwname,jdbcType=VARCHAR}, </if><if test='mask.cwan8'>CWAN8=#{bean.cwan8,jdbcType=INTEGER}, </if><if test='mask.cwwcmcu'>CWWCMCU=#{bean.cwwcmcu,jdbcType=VARCHAR}, </if></set>WHERE CWID=#{bean.cwid,jdbcType=INTEGER}</if></when><otherwise>UPDATE F3003 SET CWNAME=#{bean.cwname,jdbcType=VARCHAR}, CWAN8=#{bean.cwan8,jdbcType=INTEGER}, CWWCMCU=#{bean.cwwcmcu,jdbcType=VARCHAR} WHERE CWID=#{bean.cwid,jdbcType=INTEGER}</otherwise></choose></script>")
  void update(@Param("bean") Crew bean, @Param("mask") CrewMask mask);

  @Delete("DELETE FROM F3003 WHERE CWID=#{cwid}")
  boolean delete(Integer cwid);

  @Delete(
      "<script>DELETE FROM F3003<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.cwid'>cwid,</if><if test='mask.cwname'>cwname,</if><if test='mask.cwan8'>cwan8,</if><if test='mask.cwwcmcu'>cwwcmcu,</if><if test='mask.cwwcdl01'>cwwcdl01,</if></trim></when><otherwise>SELECT cwid, cwname, cwan8, cwwcmcu, cwwcdl01</otherwise></choose> FROM V3003<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Crew> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") CrewMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V3003<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(CWID),NULL,${defaultValue}) FROM V3003<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minCwid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(CWID),NULL,${defaultValue}) FROM V3003<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxCwid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(CWAN8),NULL,${defaultValue}) FROM V3003<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minCwan8(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(CWAN8),NULL,${defaultValue}) FROM V3003<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxCwan8(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}
