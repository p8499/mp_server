package com.kerryprops.mp.controller.base;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kerryprops.mp.*;
import com.kerryprops.mp.bean.Building;
import com.kerryprops.mp.mask.BuildingMask;
import com.kerryprops.mp.service.BuildingService;

public abstract class BuildingControllerBase {
  protected static final String path = "api/building";
  protected static final String listPath = "api/building_list";
  protected static final String attachmentPath = "api/building_attachment";
  protected static final String pathKey = "/{mcmcu}";
  protected static final String html = "http://192.168.100.43:8080";

  @CrossOrigin(origins = html, allowCredentials = "true")
  @RequestMapping(
    value = path + pathKey,
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public String get(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String mcmcu,
      @RequestParam(required = false) String mask)
      throws Exception {
    BuildingMask maskObj =
        mask == null || mask.equals("")
            ? new BuildingMask().all(true)
            : new BuildingMask(Long.valueOf(mask));
    Building bean = onGet(session, request, response, mcmcu, maskObj);
    return jackson.writeValueAsString(bean);
  }

  @Nullable
  protected abstract Building onGet(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mcmcu,
      @Nonnull BuildingMask mask)
      throws Exception;

  @CrossOrigin(origins = html, allowCredentials = "true")
  @RequestMapping(
    value = path + pathKey,
    method = RequestMethod.POST,
    produces = "application/json;charset=UTF-8"
  )
  public String add(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String mcmcu,
      @RequestBody Building bean)
      throws Exception {
    onAdd(session, request, response, mcmcu, bean);
    return jackson.writeValueAsString(bean);
  }

  @Nullable
  protected abstract Building onAdd(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mcmcu,
      @Nullable Building bean)
      throws Exception;

  @CrossOrigin(origins = html, allowCredentials = "true")
  @RequestMapping(
    value = path + pathKey,
    method = RequestMethod.PUT,
    produces = "application/json;charset=UTF-8"
  )
  public String update(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String mcmcu,
      @RequestBody Building bean,
      @RequestParam(required = false) String mask)
      throws Exception {
    BuildingMask maskObj =
        mask == null || mask.equals("")
            ? new BuildingMask().all(true)
            : new BuildingMask(Long.valueOf(mask));
    onUpdate(session, request, response, mcmcu, bean, maskObj);
    return jackson.writeValueAsString(bean);
  }

  @Nullable
  protected abstract Building onUpdate(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mcmcu,
      @Nullable Building bean,
      @Nonnull BuildingMask mask)
      throws Exception;

  @CrossOrigin(origins = html, allowCredentials = "true")
  @RequestMapping(
    value = path + pathKey,
    method = RequestMethod.DELETE,
    produces = "application/json;charset=UTF-8"
  )
  public void delete(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String mcmcu)
      throws Exception {
    onDelete(session, request, response, mcmcu);
  }

  protected abstract void onDelete(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mcmcu)
      throws Exception;

  @CrossOrigin(origins = html, allowCredentials = "true", exposedHeaders = "Content-Range")
  @RequestMapping(
    value = listPath,
    method = {RequestMethod.GET, RequestMethod.POST},
    produces = "application/json;charset=UTF-8"
  )
  public String query(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @RequestParam(required = false, name = "filter") String paramFilter,
      @RequestBody(required = false) String bodyFilter,
      @RequestParam(required = false) String orderBy,
      @RequestHeader(required = false, name = "Range", defaultValue = "items=0-9") String range,
      @RequestParam(required = false) String mask)
      throws Exception {
    String filter = paramFilter == null || paramFilter.equals("") ? bodyFilter : paramFilter;
    FilterExpr filterObj =
        filter == null || filter.equals("") ? null : jackson.readValue(filter, FilterExpr.class);
    OrderByListExpr orderByListObj =
        orderBy == null || orderBy.equals("") ? null : OrderByListExpr.fromQuery(orderBy);
    RangeExpr rangeObj = RangeExpr.fromQuery(range);
    BuildingMask maskObj =
        mask == null || mask.equals("")
            ? new BuildingMask().all(true)
            : new BuildingMask(Long.valueOf(mask));
    Long total = onCount(session, request, response, filterObj);
    if (total == null) return null;
    long start = rangeObj.getStart(total);
    long count = rangeObj.getCount(total);
    List<Building> results =
        onQuery(session, request, response, filterObj, orderByListObj, start, count, maskObj);
    response.setHeader(
        "Content-Range", RangeListExpr.getContentRange(start, results.size(), total));
    return jackson.writeValueAsString(results);
  }

  @Nullable
  protected abstract Long onCount(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nullable FilterExpr filter)
      throws Exception;

  @Nonnull
  protected abstract List<Building> onQuery(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nullable FilterExpr filter,
      @Nullable OrderByListExpr orderByList,
      long start,
      long count,
      @Nonnull BuildingMask mask)
      throws Exception;

  @CrossOrigin(origins = html, allowCredentials = "true", exposedHeaders = "Content-Disposition")
  @RequestMapping(
    value = attachmentPath + pathKey,
    method = RequestMethod.GET,
    produces = "application/octet-stream"
  )
  public void downloadAttachment(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String mcmcu,
      @RequestParam(required = true) String name)
      throws Exception {
    InputStream input = inputStream(session, request, response, mcmcu, name);
    if (input == null) return;
    String contentType = URLConnection.guessContentTypeFromName(name);
    response.setContentType(contentType == null ? "application/octet-stream" : contentType);
    response.setHeader("Content-Disposition", "attachment;fileName=" + name);
    StreamUtils.copy(input, response.getOutputStream());
    input.close();
    response.getOutputStream().close();
  }

  @Nullable
  protected abstract InputStream inputStream(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mcmcu,
      @Nonnull String name)
      throws Exception;

  @CrossOrigin(origins = html, allowCredentials = "true")
  @RequestMapping(
    value = attachmentPath + pathKey,
    method = RequestMethod.PUT,
    produces = "application/json;charset=UTF-8"
  )
  public void uploadAttachment(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String mcmcu,
      @RequestParam(required = true) String name)
      throws Exception {
    OutputStream output = outputStream(session, request, response, mcmcu, name);
    if (output == null) return;
    StreamUtils.copy(request.getInputStream(), output);
    request.getInputStream().close();
    output.close();
  }

  @Nullable
  protected abstract OutputStream outputStream(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mcmcu,
      @Nonnull String name)
      throws Exception;

  @CrossOrigin(origins = html, allowCredentials = "true")
  @RequestMapping(
    value = attachmentPath + pathKey,
    method = RequestMethod.DELETE,
    produces = "application/json;charset=UTF-8"
  )
  public void deleteAttachment(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String mcmcu,
      @RequestParam(required = true) String name)
      throws Exception {
    onDeleteAttachment(session, request, response, mcmcu, name);
  }

  protected abstract void onDeleteAttachment(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mcmcu,
      @Nonnull String name)
      throws Exception;

  @CrossOrigin(origins = html, allowCredentials = "true")
  @RequestMapping(
    value = attachmentPath + pathKey,
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public String listAttachments(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String mcmcu)
      throws Exception {
    List<String> result = onListAttachments(session, request, response, mcmcu);
    return jackson.writeValueAsString(result);
  }

  @Nonnull
  protected abstract List<String> onListAttachments(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mcmcu)
      throws Exception;

  @Value(value = "#{jackson}")
  protected ObjectMapper jackson;

  @Value(value = "#{buildingService}")
  protected BuildingService buildingService;
}
