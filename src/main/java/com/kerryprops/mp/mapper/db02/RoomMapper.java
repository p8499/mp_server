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
import com.kerryprops.mp.mask.RoomMask;
import com.kerryprops.mp.bean.Room;

@Component("roomMapper")
public interface RoomMapper {
  @Select("SELECT COUNT(*)>0 FROM V1200 WHERE RMID=#{rmid}")
  boolean exists(@Param("rmid") Integer rmid);

  @Select(
      "<script><choose><when test='mask!=null'><if test='mask.rmid or mask.rmserial or mask.rman8 or mask.rmname or mask.rmcwid or mask.rmcwname or mask.rmwcmcu or mask.rmwcdl01'><trim prefix='SELECT' suffixOverrides=','><if test='mask.rmid'>RMID, </if><if test='mask.rmserial'>RMSERIAL, </if><if test='mask.rman8'>RMAN8, </if><if test='mask.rmname'>RMNAME, </if><if test='mask.rmcwid'>RMCWID, </if><if test='mask.rmcwname'>RMCWNAME, </if><if test='mask.rmwcmcu'>RMWCMCU, </if><if test='mask.rmwcdl01'>RMWCDL01, </if></trim>FROM V1200 WHERE RMID=#{rmid}</if></when><otherwise>SELECTRMID,RMSERIAL,RMAN8,RMNAME,RMCWID,RMCWNAME,RMWCMCU,RMWCDL01 FROM V1200 WHERE RMID=#{rmid}</otherwise></choose></script>")
  Room get(@Param("rmid") Integer rmid, @Param("mask") RoomMask mask);

  @org.apache.ibatis.annotations.SelectKey(
    statement = "SELECT F1200_RMID.nextval AS rmid FROM DUAL",
    before = true,
    resultType = Integer.class,
    keyColumn = "rmid",
    keyProperty = "bean.rmid"
  )
  @Insert(
      "INSERT INTO F1200 (RMID,RMSERIAL,RMAN8,RMNAME,RMCWID) VALUES (#{bean.rmid,jdbcType=INTEGER},#{bean.rmserial,jdbcType=VARCHAR},#{bean.rman8,jdbcType=INTEGER},#{bean.rmname,jdbcType=VARCHAR},#{bean.rmcwid,jdbcType=INTEGER})")
  void add(@Param("bean") Room bean);

  @Update(
      "<script><choose><when test='mask!=null'><if test='mask.rmserial or mask.rman8 or mask.rmname or mask.rmcwid'>UPDATE F1200 <set><if test='mask.rmserial'>RMSERIAL=#{bean.rmserial,jdbcType=VARCHAR}, </if><if test='mask.rman8'>RMAN8=#{bean.rman8,jdbcType=INTEGER}, </if><if test='mask.rmname'>RMNAME=#{bean.rmname,jdbcType=VARCHAR}, </if><if test='mask.rmcwid'>RMCWID=#{bean.rmcwid,jdbcType=INTEGER}, </if></set>WHERE RMID=#{bean.rmid,jdbcType=INTEGER}</if></when><otherwise>UPDATE F1200 SET RMSERIAL=#{bean.rmserial,jdbcType=VARCHAR}, RMAN8=#{bean.rman8,jdbcType=INTEGER}, RMNAME=#{bean.rmname,jdbcType=VARCHAR}, RMCWID=#{bean.rmcwid,jdbcType=INTEGER} WHERE RMID=#{bean.rmid,jdbcType=INTEGER}</otherwise></choose></script>")
  void update(@Param("bean") Room bean, @Param("mask") RoomMask mask);

  @Delete("DELETE FROM F1200 WHERE RMID=#{rmid}")
  boolean delete(Integer rmid);

  @Delete(
      "<script>DELETE FROM F1200<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  void deleteWhere(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT A.* FROM (SELECT B.*, ROWNUM B_ROWNUM FROM (<choose><when test='mask!=null'><trim prefix='SELECT' suffixOverrides=','><if test='mask.rmid'>rmid,</if><if test='mask.rmserial'>rmserial,</if><if test='mask.rman8'>rman8,</if><if test='mask.rmname'>rmname,</if><if test='mask.rmcwid'>rmcwid,</if><if test='mask.rmcwname'>rmcwname,</if><if test='mask.rmwcmcu'>rmwcmcu,</if><if test='mask.rmwcdl01'>rmwcdl01,</if></trim></when><otherwise>SELECT rmid, rmserial, rman8, rmname, rmcwid, rmcwname, rmwcmcu, rmwcdl01</otherwise></choose> FROM V1200<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if><if test='order!=null'> ORDER BY ${order.toString()}</if>) B WHERE ROWNUM &lt;=#{start}+#{count}) A WHERE B_ROWNUM &gt;=#{start}+1</script>")
  List<Room> query(
      @Param("filter") FilterExpr filter,
      @Param("order") OrderByListExpr order,
      @Param("start") long start,
      @Param("count") long count,
      @Param("mask") RoomMask mask);

  @Select(
      "<script>SELECT COUNT(*) FROM V1200<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  long count(@Param("filter") FilterExpr filter);

  @Select(
      "<script>SELECT DECODE(MIN(RMID),NULL,${defaultValue}) FROM V1200<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minRmid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(RMID),NULL,${defaultValue}) FROM V1200<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxRmid(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(RMAN8),NULL,${defaultValue}) FROM V1200<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minRman8(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(RMAN8),NULL,${defaultValue}) FROM V1200<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxRman8(@Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MIN(RMCWID),NULL,${defaultValue}) FROM V1200<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer minRmcwid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);

  @Select(
      "<script>SELECT DECODE(MAX(RMCWID),NULL,${defaultValue}) FROM V1200<if test='filter!=null'> WHERE ${filter.toStringOracle()}</if></script>")
  Integer maxRmcwid(
      @Param("filter") FilterExpr filter, @Param("defaultValue") Integer defaultValue);
}
