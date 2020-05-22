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
import com.kerryprops.mp.mask.PrivilegeMask;
import com.kerryprops.mp.bean.Privilege;

@Component("privilegeMapper")
public interface PrivilegeMapper {
  @Select("SELECT COUNT(*)>0 FROM V0095 WHERE PRID=#{prid}")
  boolean exists(@Param("prid") String prid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.prid or mask.prname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.prid'>PRID, </if><if test='mask.prname'>PRNAME, </if></trim>FROM V0095 WHERE PRID=#{prid}</if></when><otherwise>SELECTPRID,PRNAME FROM V0095 WHERE PRID=#{prid}</otherwise></choose></script>")
  Privilege get(@Param("prid") String prid, @Param("mask") PrivilegeMask mask);

  @Insert(
      "INSERT INTO F0095 (PRID,PRNAME) VALUES (#{bean.prid,jdbcType=VARCHAR},#{bean.prname,jdbcType=VARCHAR})")
  void add(@Param("bean") Privilege bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.prname'>UPDATE F0095 <set><if test='mask.prname'>PRNAME=#{bean.prname,jdbcType=VARCHAR}, </if></set>WHERE PRID=#{bean.prid,jdbcType=VARCHAR}</if></when><otherwise>UPDATE F0095 SET PRNAME=#{bean.prname,jdbcType=VARCHAR} WHERE PRID=#{bean.prid,jdbcType=VARCHAR}</otherwise></choose></script>")
  void update(@Param("bean") Privilege bean, @Param("mask") PrivilegeMask mask);

  @Delete("DELETE FROM F0095 WHERE PRID=#{prid}")
  boolean delete(String prid);

  @Delete(
      "<script>DELETE FROM F0095<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.prid'>prid,</if><if test='mask.prname'>prname,</if></trim></when><otherwise>SELECT prid, prname</otherwise></choose> FROM V0095<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Privilege> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") PrivilegeMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V0095<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}
