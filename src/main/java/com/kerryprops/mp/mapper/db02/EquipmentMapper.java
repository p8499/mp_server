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
import com.kerryprops.mp.mask.EquipmentMask;
import com.kerryprops.mp.bean.Equipment;

@Component("equipmentMapper")
public interface EquipmentMapper {
  @Select("SELECT COUNT(*)>0 FROM V1201 WHERE EQID=#{eqid}")
  boolean exists(@Param("eqid") Integer eqid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.eqid or mask.eqserial or mask.eqnumb or mask.eqname or mask.eqcwid or mask.eqcwname or mask.eqwcmcu or mask.eqwcdl01'><trim prefix='SELECT' suffixOverrides=','><if test='mask.eqid'>EQID, </if><if test='mask.eqserial'>EQSERIAL, </if><if test='mask.eqnumb'>EQNUMB, </if><if test='mask.eqname'>EQNAME, </if><if test='mask.eqcwid'>EQCWID, </if><if test='mask.eqcwname'>EQCWNAME, </if><if test='mask.eqwcmcu'>EQWCMCU, </if><if test='mask.eqwcdl01'>EQWCDL01, </if></trim>FROM V1201 WHERE EQID=#{eqid}</if></when><otherwise>SELECTEQID,EQSERIAL,EQNUMB,EQNAME,EQCWID,EQCWNAME,EQWCMCU,EQWCDL01 FROM V1201 WHERE EQID=#{eqid}</otherwise></choose></script>")
  Equipment get(@Param("eqid") Integer eqid, @Param("mask") EquipmentMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F1201_EQID.nextval AS eqid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "eqid",
    keyProperty = "bean.eqid"
  )
  @Insert(
      "INSERT INTO F1201 (EQID,EQSERIAL,EQNUMB,EQNAME,EQCWID) VALUES (#{bean.eqid,jdbcType=INTEGER},#{bean.eqserial,jdbcType=VARCHAR},#{bean.eqnumb,jdbcType=INTEGER},#{bean.eqname,jdbcType=VARCHAR},#{bean.eqcwid,jdbcType=INTEGER})")
  void add(@Param("bean") Equipment bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.eqserial or mask.eqnumb or mask.eqname or mask.eqcwid'>UPDATE F1201 <set><if test='mask.eqserial'>EQSERIAL=#{bean.eqserial,jdbcType=VARCHAR}, </if><if test='mask.eqnumb'>EQNUMB=#{bean.eqnumb,jdbcType=INTEGER}, </if><if test='mask.eqname'>EQNAME=#{bean.eqname,jdbcType=VARCHAR}, </if><if test='mask.eqcwid'>EQCWID=#{bean.eqcwid,jdbcType=INTEGER}, </if></set>WHERE EQID=#{bean.eqid,jdbcType=INTEGER}</if></when><otherwise>UPDATE F1201 SET EQSERIAL=#{bean.eqserial,jdbcType=VARCHAR}, EQNUMB=#{bean.eqnumb,jdbcType=INTEGER}, EQNAME=#{bean.eqname,jdbcType=VARCHAR}, EQCWID=#{bean.eqcwid,jdbcType=INTEGER} WHERE EQID=#{bean.eqid,jdbcType=INTEGER}</otherwise></choose></script>")
  void update(@Param("bean") Equipment bean, @Param("mask") EquipmentMask mask);

  @Delete("DELETE FROM F1201 WHERE EQID=#{eqid}")
  boolean delete(Integer eqid);

  @Delete(
      "<script>DELETE FROM F1201<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.eqid'>eqid,</if><if test='mask.eqserial'>eqserial,</if><if test='mask.eqnumb'>eqnumb,</if><if test='mask.eqname'>eqname,</if><if test='mask.eqcwid'>eqcwid,</if><if test='mask.eqcwname'>eqcwname,</if><if test='mask.eqwcmcu'>eqwcmcu,</if><if test='mask.eqwcdl01'>eqwcdl01,</if></trim></when><otherwise>SELECT eqid, eqserial, eqnumb, eqname, eqcwid, eqcwname, eqwcmcu, eqwcdl01</otherwise></choose> FROM V1201<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Equipment> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") EquipmentMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V1201<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(EQID),NULL,${defaultValue}) FROM V1201<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minEqid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(EQID),NULL,${defaultValue}) FROM V1201<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxEqid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(EQNUMB),NULL,${defaultValue}) FROM V1201<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minEqnumb(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(EQNUMB),NULL,${defaultValue}) FROM V1201<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxEqnumb(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(EQCWID),NULL,${defaultValue}) FROM V1201<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minEqcwid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(EQCWID),NULL,${defaultValue}) FROM V1201<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxEqcwid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}
