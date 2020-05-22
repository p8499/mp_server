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
import com.kerryprops.mp.mask.RoleMask;
import com.kerryprops.mp.bean.Role;

@Component("roleMapper")
public interface RoleMapper {
  @Select("SELECT COUNT(*)>0 FROM V0094 WHERE ROID=#{roid}")
  boolean exists(@Param("roid") String roid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.roid or mask.roname'><trim prefix='SELECT' suffixOverrides=','><if test='mask.roid'>ROID, </if><if test='mask.roname'>RONAME, </if></trim>FROM V0094 WHERE ROID=#{roid}</if></when><otherwise>SELECTROID,RONAME FROM V0094 WHERE ROID=#{roid}</otherwise></choose></script>")
  Role get(@Param("roid") String roid, @Param("mask") RoleMask mask);

  @Insert(
      "INSERT INTO F0094 (ROID,RONAME) VALUES (#{bean.roid,jdbcType=VARCHAR},#{bean.roname,jdbcType=VARCHAR})")
  void add(@Param("bean") Role bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.roname'>UPDATE F0094 <set><if test='mask.roname'>RONAME=#{bean.roname,jdbcType=VARCHAR}, </if></set>WHERE ROID=#{bean.roid,jdbcType=VARCHAR}</if></when><otherwise>UPDATE F0094 SET RONAME=#{bean.roname,jdbcType=VARCHAR} WHERE ROID=#{bean.roid,jdbcType=VARCHAR}</otherwise></choose></script>")
  void update(@Param("bean") Role bean, @Param("mask") RoleMask mask);

  @Delete("DELETE FROM F0094 WHERE ROID=#{roid}")
  boolean delete(String roid);

  @Delete(
      "<script>DELETE FROM F0094<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.roid'>roid,</if><if test='mask.roname'>roname,</if></trim></when><otherwise>SELECT roid, roname</otherwise></choose> FROM V0094<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Role> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") RoleMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V0094<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);
}
