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
import com.kerryprops.mp.mask.EnumTypeValueMask;
import com.kerryprops.mp.bean.EnumTypeValue;

@Component("enumTypeValueMapper")
public interface EnumTypeValueMapper {
  @Select("SELECT COUNT(*)>0 FROM V0005 WHERE EVID=#{evid}")
  boolean exists(@Param("evid") Integer evid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.evid or mask.evetid or mask.evval or mask.evdescription or mask.evetname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.evid'>EVID, </if><if test='mask.evetid'>EVETID, </if><if test='mask.evval'>EVVAL, </if><if test='mask.evdescription'>EVDESCRIPTION, </if><if test='mask.evetname'>EVETNAME, </if></trim>FROM V0005 WHERE EVID=#{evid}</if></when><otherwise>SELECTEVID,EVETID,EVVAL,EVDESCRIPTION,EVETNAME FROM V0005 WHERE EVID=#{evid}</otherwise></choose></script>")
  EnumTypeValue get(@Param("evid") Integer evid, @Param("mask") EnumTypeValueMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F0005_EVID.nextval AS evid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "evid",
    keyProperty = "bean.evid"
  )
  @Insert(
      "INSERT INTO F0005 (EVID,EVETID,EVVAL,EVDESCRIPTION) VALUES (#{bean.evid,jdbcType=INTEGER},#{bean.evetid,jdbcType=VARCHAR},#{bean.evval,jdbcType=SMALLINT},#{bean.evdescription,jdbcType=VARCHAR})")
  void add(@Param("bean") EnumTypeValue bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.evetid or mask.evval or mask.evdescription'>UPDATE F0005 <set><if test='mask.evetid'>EVETID=#{bean.evetid,jdbcType=VARCHAR}, </if><if test='mask.evval'>EVVAL=#{bean.evval,jdbcType=SMALLINT}, </if><if test='mask.evdescription'>EVDESCRIPTION=#{bean.evdescription,jdbcType=VARCHAR}, </if></set>WHERE EVID=#{bean.evid,jdbcType=INTEGER}</if></when><otherwise>UPDATE F0005 SET EVETID=#{bean.evetid,jdbcType=VARCHAR}, EVVAL=#{bean.evval,jdbcType=SMALLINT}, EVDESCRIPTION=#{bean.evdescription,jdbcType=VARCHAR} WHERE EVID=#{bean.evid,jdbcType=INTEGER}</otherwise></choose></script>")
  void update(@Param("bean") EnumTypeValue bean, @Param("mask") EnumTypeValueMask mask);

  @Delete("DELETE FROM F0005 WHERE EVID=#{evid}")
  boolean delete(Integer evid);

  @Delete(
      "<script>DELETE FROM F0005<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.evid'>evid,</if><if test='mask.evetid'>evetid,</if><if test='mask.evval'>evval,</if><if test='mask.evdescription'>evdescription,</if><if test='mask.evetname'>evetname,</if></trim></when><otherwise>SELECT evid, evetid, evval, evdescription, evetname</otherwise></choose> FROM V0005<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<EnumTypeValue> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") EnumTypeValueMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V0005<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(EVID),NULL,${defaultValue}) FROM V0005<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minEvid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(EVID),NULL,${defaultValue}) FROM V0005<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxEvid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(EVVAL),NULL,${defaultValue}) FROM V0005<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minEvval(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(EVVAL),NULL,${defaultValue}) FROM V0005<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxEvval(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}
