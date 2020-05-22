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
import com.kerryprops.mp.mask.EnumTypeMask;
import com.kerryprops.mp.bean.EnumType;

@Component("enumTypeMapper")
public interface EnumTypeMapper {
  @Select("SELECT COUNT(*)>0 FROM V0004 WHERE ETID=#{etid}")
  boolean exists(@Param("etid") String etid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.etid or mask.etname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.etid'>ETID, </if><if test='mask.etname'>ETNAME, </if></trim>FROM V0004 WHERE ETID=#{etid}</if></when><otherwise>SELECTETID,ETNAME FROM V0004 WHERE ETID=#{etid}</otherwise></choose></script>")
  EnumType get(@Param("etid") String etid, @Param("mask") EnumTypeMask mask);

  @Insert(
      "INSERT INTO F0004 (ETID,ETNAME) VALUES (#{bean.etid,jdbcType=VARCHAR},#{bean.etname,jdbcType=VARCHAR})")
  void add(@Param("bean") EnumType bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.etname'>UPDATE F0004 <set><if test='mask.etname'>ETNAME=#{bean.etname,jdbcType=VARCHAR}, </if></set>WHERE ETID=#{bean.etid,jdbcType=VARCHAR}</if></when><otherwise>UPDATE F0004 SET ETNAME=#{bean.etname,jdbcType=VARCHAR} WHERE ETID=#{bean.etid,jdbcType=VARCHAR}</otherwise></choose></script>")
  void update(@Param("bean") EnumType bean, @Param("mask") EnumTypeMask mask);

  @Delete("DELETE FROM F0004 WHERE ETID=#{etid}")
  boolean delete(String etid);

  @Delete(
      "<script>DELETE FROM F0004<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.etid'>etid,</if><if test='mask.etname'>etname,</if></trim></when><otherwise>SELECT etid, etname</otherwise></choose> FROM V0004<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<EnumType> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") EnumTypeMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V0004<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}
