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
import com.kerryprops.mp.mask.BranchMask;
import com.kerryprops.mp.bean.Branch;

@Component("branchMapper")
public interface BranchMapper {
  @Select("SELECT COUNT(*)>0 FROM V3001 WHERE BPMCU=#{bpmcu}")
  boolean exists(@Param("bpmcu") String bpmcu);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.bpmcu or mask.bpdl01 or mask.bpwcmcu or mask.bpwcdl01'><trim prefix='SELECT' suffixOverrides=','><if test='mask.bpmcu'>BPMCU, </if><if test='mask.bpdl01'>BPDL01, </if><if test='mask.bpwcmcu'>BPWCMCU, </if><if test='mask.bpwcdl01'>BPWCDL01, </if></trim>FROM V3001 WHERE BPMCU=#{bpmcu}</if></when><otherwise>SELECTBPMCU,BPDL01,BPWCMCU,BPWCDL01 FROM V3001 WHERE BPMCU=#{bpmcu}</otherwise></choose></script>")
  Branch get(@Param("bpmcu") String bpmcu, @Param("mask") BranchMask mask);

  @Insert(
      "INSERT INTO F3001 (BPMCU,BPDL01,BPWCMCU) VALUES (#{bean.bpmcu,jdbcType=VARCHAR},#{bean.bpdl01,jdbcType=VARCHAR},#{bean.bpwcmcu,jdbcType=VARCHAR})")
  void add(@Param("bean") Branch bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.bpdl01 or mask.bpwcmcu'>UPDATE F3001 <set><if test='mask.bpdl01'>BPDL01=#{bean.bpdl01,jdbcType=VARCHAR}, </if><if test='mask.bpwcmcu'>BPWCMCU=#{bean.bpwcmcu,jdbcType=VARCHAR}, </if></set>WHERE BPMCU=#{bean.bpmcu,jdbcType=VARCHAR}</if></when><otherwise>UPDATE F3001 SET BPDL01=#{bean.bpdl01,jdbcType=VARCHAR}, BPWCMCU=#{bean.bpwcmcu,jdbcType=VARCHAR} WHERE BPMCU=#{bean.bpmcu,jdbcType=VARCHAR}</otherwise></choose></script>")
  void update(@Param("bean") Branch bean, @Param("mask") BranchMask mask);

  @Delete("DELETE FROM F3001 WHERE BPMCU=#{bpmcu}")
  boolean delete(String bpmcu);

  @Delete(
      "<script>DELETE FROM F3001<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.bpmcu'>bpmcu,</if><if test='mask.bpdl01'>bpdl01,</if><if test='mask.bpwcmcu'>bpwcmcu,</if><if test='mask.bpwcdl01'>bpwcdl01,</if></trim></when><otherwise>SELECT bpmcu, bpdl01, bpwcmcu, bpwcdl01</otherwise></choose> FROM V3001<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Branch> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") BranchMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V3001<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}
