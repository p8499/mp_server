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
import com.kerryprops.mp.mask.MaintenanceTypeMask;
import com.kerryprops.mp.bean.MaintenanceType;

@Component("maintenanceTypeMapper")
public interface MaintenanceTypeMapper {
  @Select("SELECT COUNT(*)>0 FROM V1701 WHERE MTID=#{mtid}")
  boolean exists(@Param("mtid") String mtid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.mtid or mask.mtname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.mtid'>MTID, </if><if test='mask.mtname'>MTNAME, </if></trim>FROM V1701 WHERE MTID=#{mtid}</if></when><otherwise>SELECTMTID,MTNAME FROM V1701 WHERE MTID=#{mtid}</otherwise></choose></script>")
  MaintenanceType get(@Param("mtid") String mtid, @Param("mask") MaintenanceTypeMask mask);

  @Insert(
      "INSERT INTO F1701 (MTID,MTNAME) VALUES (#{bean.mtid,jdbcType=VARCHAR},#{bean.mtname,jdbcType=VARCHAR})")
  void add(@Param("bean") MaintenanceType bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.mtname'>UPDATE F1701 <set><if test='mask.mtname'>MTNAME=#{bean.mtname,jdbcType=VARCHAR}, </if></set>WHERE MTID=#{bean.mtid,jdbcType=VARCHAR}</if></when><otherwise>UPDATE F1701 SET MTNAME=#{bean.mtname,jdbcType=VARCHAR} WHERE MTID=#{bean.mtid,jdbcType=VARCHAR}</otherwise></choose></script>")
  void update(@Param("bean") MaintenanceType bean, @Param("mask") MaintenanceTypeMask mask);

  @Delete("DELETE FROM F1701 WHERE MTID=#{mtid}")
  boolean delete(String mtid);

  @Delete(
      "<script>DELETE FROM F1701<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.mtid'>mtid,</if><if test='mask.mtname'>mtname,</if></trim></when><otherwise>SELECT mtid, mtname</otherwise></choose> FROM V1701<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<MaintenanceType> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") MaintenanceTypeMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V1701<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}
