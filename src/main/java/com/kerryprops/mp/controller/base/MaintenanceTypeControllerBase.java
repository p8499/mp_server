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
import com.kerryprops.mp.bean.MaintenanceType;
import com.kerryprops.mp.mask.MaintenanceTypeMask;
import com.kerryprops.mp.service.MaintenanceTypeService;

public abstract class MaintenanceTypeControllerBase {
  protected static final String path = "api/maintenancetype";
  protected static final String listPath = "api/maintenancetype_list";
  protected static final String attachmentPath = "api/maintenancetype_attachment";
  protected static final String pathKey = "/{mtid}";
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
      @PathVariable String mtid,
      @RequestParam(required = false) String mask)
      throws Exception {
    MaintenanceTypeMask maskObj =
        mask == null || mask.equals("")
            ? new MaintenanceTypeMask().all(true)
            : new MaintenanceTypeMask(Long.valueOf(mask));
    MaintenanceType bean = onGet(session, request, response, mtid, maskObj);
    return jackson.writeValueAsString(bean);
  }

  @Nullable
  protected abstract MaintenanceType onGet(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mtid,
      @Nonnull MaintenanceTypeMask mask)
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
      @PathVariable String mtid,
      @RequestBody MaintenanceType bean)
      throws Exception {
    onAdd(session, request, response, mtid, bean);
    return jackson.writeValueAsString(bean);
  }

  @Nullable
  protected abstract MaintenanceType onAdd(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mtid,
      @Nullable MaintenanceType bean)
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
      @PathVariable String mtid,
      @RequestBody MaintenanceType bean,
      @RequestParam(required = false) String mask)
      throws Exception {
    MaintenanceTypeMask maskObj =
        mask == null || mask.equals("")
            ? new MaintenanceTypeMask().all(true)
            : new MaintenanceTypeMask(Long.valueOf(mask));
    onUpdate(session, request, response, mtid, bean, maskObj);
    return jackson.writeValueAsString(bean);
  }

  @Nullable
  protected abstract MaintenanceType onUpdate(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mtid,
      @Nullable MaintenanceType bean,
      @Nonnull MaintenanceTypeMask mask)
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
      @PathVariable String mtid)
      throws Exception {
    onDelete(session, request, response, mtid);
  }

  protected abstract void onDelete(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mtid)
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
    MaintenanceTypeMask maskObj =
        mask == null || mask.equals("")
            ? new MaintenanceTypeMask().all(true)
            : new MaintenanceTypeMask(Long.valueOf(mask));
    Long total = onCount(session, request, response, filterObj);
    if (total == null) return null;
    long start = rangeObj.getStart(total);
    long count = rangeObj.getCount(total);
    List<MaintenanceType> results =
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
  protected abstract List<MaintenanceType> onQuery(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nullable FilterExpr filter,
      @Nullable OrderByListExpr orderByList,
      long start,
      long count,
      @Nonnull MaintenanceTypeMask mask)
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
      @PathVariable String mtid,
      @RequestParam(required = true) String name)
      throws Exception {
    InputStream input = inputStream(session, request, response, mtid, name);
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
      @Nonnull String mtid,
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
      @PathVariable String mtid,
      @RequestParam(required = true) String name)
      throws Exception {
    OutputStream output = outputStream(session, request, response, mtid, name);
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
      @Nonnull String mtid,
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
      @PathVariable String mtid,
      @RequestParam(required = true) String name)
      throws Exception {
    onDeleteAttachment(session, request, response, mtid, name);
  }

  protected abstract void onDeleteAttachment(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mtid,
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
      @PathVariable String mtid)
      throws Exception {
    List<String> result = onListAttachments(session, request, response, mtid);
    return jackson.writeValueAsString(result);
  }

  @Nonnull
  protected abstract List<String> onListAttachments(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String mtid)
      throws Exception;

  @Value(value = "#{jackson}")
  protected ObjectMapper jackson;

  @Value(value = "#{maintenanceTypeService}")
  protected MaintenanceTypeService maintenanceTypeService;
}