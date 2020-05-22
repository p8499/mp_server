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
import com.kerryprops.mp.mask.WorkCenterMask;
import com.kerryprops.mp.bean.WorkCenter;

@Component("workCenterMapper")
public interface WorkCenterMapper {
  @Select("SELECT COUNT(*)>0 FROM V3000 WHERE WCMCU=#{wcmcu}")
  boolean exists(@Param("wcmcu") String wcmcu);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.wcmcu or mask.wcdl01'><trim prefix='SELECT' suffixOverrides=','><if test='mask.wcmcu'>WCMCU, </if><if test='mask.wcdl01'>WCDL01, </if></trim>FROM V3000 WHERE WCMCU=#{wcmcu}</if></when><otherwise>SELECTWCMCU,WCDL01 FROM V3000 WHERE WCMCU=#{wcmcu}</otherwise></choose></script>")
  WorkCenter get(@Param("wcmcu") String wcmcu, @Param("mask") WorkCenterMask mask);

  @Insert(
      "INSERT INTO F3000 (WCMCU,WCDL01) VALUES (#{bean.wcmcu,jdbcType=VARCHAR},#{bean.wcdl01,jdbcType=VARCHAR})")
  void add(@Param("bean") WorkCenter bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.wcdl01'>UPDATE F3000 <set><if test='mask.wcdl01'>WCDL01=#{bean.wcdl01,jdbcType=VARCHAR}, </if></set>WHERE WCMCU=#{bean.wcmcu,jdbcType=VARCHAR}</if></when><otherwise>UPDATE F3000 SET WCDL01=#{bean.wcdl01,jdbcType=VARCHAR} WHERE WCMCU=#{bean.wcmcu,jdbcType=VARCHAR}</otherwise></choose></script>")
  void update(@Param("bean") WorkCenter bean, @Param("mask") WorkCenterMask mask);

  @Delete("DELETE FROM F3000 WHERE WCMCU=#{wcmcu}")
  boolean delete(String wcmcu);

  @Delete(
      "<script>DELETE FROM F3000<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.wcmcu'>wcmcu,</if><if test='mask.wcdl01'>wcdl01,</if></trim></when><otherwise>SELECT wcmcu, wcdl01</otherwise></choose> FROM V3000<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<WorkCenter> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") WorkCenterMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V3000<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}
