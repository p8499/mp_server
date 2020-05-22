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
import com.kerryprops.mp.mask.BuildingMask;
import com.kerryprops.mp.bean.Building;

@Component("buildingMapper")
public interface BuildingMapper {
  @Select("SELECT COUNT(*)>0 FROM V3002 WHERE MCMCU=#{mcmcu}")
  boolean exists(@Param("mcmcu") String mcmcu);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.mcmcu or mask.mcdl01 or mask.mcbpmcu or mask.mcbpdl01 or mask.mcwcmcu or mask.mcwcdl01'><trim prefix='SELECT' suffixOverrides=','><if test='mask.mcmcu'>MCMCU, </if><if test='mask.mcdl01'>MCDL01, </if><if test='mask.mcbpmcu'>MCBPMCU, </if><if test='mask.mcbpdl01'>MCBPDL01, </if><if test='mask.mcwcmcu'>MCWCMCU, </if><if test='mask.mcwcdl01'>MCWCDL01, </if></trim>FROM V3002 WHERE MCMCU=#{mcmcu}</if></when><otherwise>SELECTMCMCU,MCDL01,MCBPMCU,MCBPDL01,MCWCMCU,MCWCDL01 FROM V3002 WHERE MCMCU=#{mcmcu}</otherwise></choose></script>")
  Building get(@Param("mcmcu") String mcmcu, @Param("mask") BuildingMask mask);

  @Insert(
      "INSERT INTO F3002 (MCMCU,MCDL01,MCBPMCU) VALUES (#{bean.mcmcu,jdbcType=VARCHAR},#{bean.mcdl01,jdbcType=VARCHAR},#{bean.mcbpmcu,jdbcType=VARCHAR})")
  void add(@Param("bean") Building bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.mcdl01 or mask.mcbpmcu'>UPDATE F3002 <set><if test='mask.mcdl01'>MCDL01=#{bean.mcdl01,jdbcType=VARCHAR}, </if><if test='mask.mcbpmcu'>MCBPMCU=#{bean.mcbpmcu,jdbcType=VARCHAR}, </if></set>WHERE MCMCU=#{bean.mcmcu,jdbcType=VARCHAR}</if></when><otherwise>UPDATE F3002 SET MCDL01=#{bean.mcdl01,jdbcType=VARCHAR}, MCBPMCU=#{bean.mcbpmcu,jdbcType=VARCHAR} WHERE MCMCU=#{bean.mcmcu,jdbcType=VARCHAR}</otherwise></choose></script>")
  void update(@Param("bean") Building bean, @Param("mask") BuildingMask mask);

  @Delete("DELETE FROM F3002 WHERE MCMCU=#{mcmcu}")
  boolean delete(String mcmcu);

  @Delete(
      "<script>DELETE FROM F3002<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.mcmcu'>mcmcu,</if><if test='mask.mcdl01'>mcdl01,</if><if test='mask.mcbpmcu'>mcbpmcu,</if><if test='mask.mcbpdl01'>mcbpdl01,</if><if test='mask.mcwcmcu'>mcwcmcu,</if><if test='mask.mcwcdl01'>mcwcdl01,</if></trim></when><otherwise>SELECT mcmcu, mcdl01, mcbpmcu, mcbpdl01, mcwcmcu, mcwcdl01</otherwise></choose> FROM V3002<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Building> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") BuildingMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V3002<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}
