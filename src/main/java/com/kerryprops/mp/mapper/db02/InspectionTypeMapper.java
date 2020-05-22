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
import com.kerryprops.mp.mask.InspectionTypeMask;
import com.kerryprops.mp.bean.InspectionType;

@Component("inspectionTypeMapper")
public interface InspectionTypeMapper {
  @Select("SELECT COUNT(*)>0 FROM V1700 WHERE ITID=#{itid}")
  boolean exists(@Param("itid") String itid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.itid or mask.itname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.itid'>ITID, </if><if test='mask.itname'>ITNAME, </if></trim>FROM V1700 WHERE ITID=#{itid}</if></when><otherwise>SELECTITID,ITNAME FROM V1700 WHERE ITID=#{itid}</otherwise></choose></script>")
  InspectionType get(@Param("itid") String itid, @Param("mask") InspectionTypeMask mask);

  @Insert(
      "INSERT INTO F1700 (ITID,ITNAME) VALUES (#{bean.itid,jdbcType=VARCHAR},#{bean.itname,jdbcType=VARCHAR})")
  void add(@Param("bean") InspectionType bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.itname'>UPDATE F1700 <set><if test='mask.itname'>ITNAME=#{bean.itname,jdbcType=VARCHAR}, </if></set>WHERE ITID=#{bean.itid,jdbcType=VARCHAR}</if></when><otherwise>UPDATE F1700 SET ITNAME=#{bean.itname,jdbcType=VARCHAR} WHERE ITID=#{bean.itid,jdbcType=VARCHAR}</otherwise></choose></script>")
  void update(@Param("bean") InspectionType bean, @Param("mask") InspectionTypeMask mask);

  @Delete("DELETE FROM F1700 WHERE ITID=#{itid}")
  boolean delete(String itid);

  @Delete(
      "<script>DELETE FROM F1700<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.itid'>itid,</if><if test='mask.itname'>itname,</if></trim></when><otherwise>SELECT itid, itname</otherwise></choose> FROM V1700<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<InspectionType> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") InspectionTypeMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V1700<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}
