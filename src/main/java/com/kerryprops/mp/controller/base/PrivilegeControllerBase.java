package com.kerryprops.mp.controller.base;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tika.Tika;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kerryprops.mp.*;
import com.kerryprops.mp.bean.Privilege;
import com.kerryprops.mp.mask.PrivilegeMask;
import com.kerryprops.mp.service.PrivilegeService;

public abstract class PrivilegeControllerBase {
  protected static final String path = "api/privilege";
  protected static final String listPath = "api/privilege_list";
  protected static final String attachmentPath = "api/privilege_attachment";
  protected static final String pathKey = "/{prid}";
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
      @PathVariable String prid,
      @RequestParam(required = false) String mask)
      throws Exception {
    PrivilegeMask maskObj =
        mask == null || mask.equals("")
            ? new PrivilegeMask().all(true)
            : new PrivilegeMask(Long.valueOf(mask));
    Privilege bean = onGet(session, request, response, prid, maskObj);
    return jackson.writeValueAsString(bean);
  }

  @Nullable
  protected abstract Privilege onGet(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String prid,
      @Nonnull PrivilegeMask mask)
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
      @PathVariable String prid,
      @RequestBody Privilege bean)
      throws Exception {
    onAdd(session, request, response, prid, bean);
    return jackson.writeValueAsString(bean);
  }

  @Nullable
  protected abstract Privilege onAdd(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String prid,
      @Nullable Privilege bean)
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
      @PathVariable String prid,
      @RequestBody Privilege bean,
      @RequestParam(required = false) String mask)
      throws Exception {
    PrivilegeMask maskObj =
        mask == null || mask.equals("")
            ? new PrivilegeMask().all(true)
            : new PrivilegeMask(Long.valueOf(mask));
    onUpdate(session, request, response, prid, bean, maskObj);
    return jackson.writeValueAsString(bean);
  }

  @Nullable
  protected abstract Privilege onUpdate(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String prid,
      @Nullable Privilege bean,
      @Nonnull PrivilegeMask mask)
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
      @PathVariable String prid)
      throws Exception {
    onDelete(session, request, response, prid);
  }

  protected abstract void onDelete(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String prid)
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
    PrivilegeMask maskObj =
        mask == null || mask.equals("")
            ? new PrivilegeMask().all(true)
            : new PrivilegeMask(Long.valueOf(mask));
    Long total = onCount(session, request, response, filterObj);
    if (total == null) return null;
    long start = rangeObj.getStart(total);
    long count = rangeObj.getCount(total);
    List<Privilege> results =
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
  protected abstract List<Privilege> onQuery(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nullable FilterExpr filter,
      @Nullable OrderByListExpr orderByList,
      long start,
      long count,
      @Nonnull PrivilegeMask mask)
      throws Exception;

  @CrossOrigin(origins = html, allowCredentials = "true", exposedHeaders = "Content-Disposition")
  @RequestMapping(value = attachmentPath + pathKey, method = RequestMethod.GET)
  public void downloadAttachment(
      HttpSession session,
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable String prid,
      @RequestParam(required = false) String name)
      throws Exception {
    byte[] bytes = onReadAttachment(session, request, response, prid, name);
    if (bytes != null) {
      String contentType = new Tika().detect(bytes);
      response.setContentType(contentType);
      String extension = MimeTypes.getDefaultMimeTypes().forName(contentType).getExtension();
      String fileName =
          name == null
              ? "download"
              : name.lastIndexOf('.') > -1 ? name.substring(0, name.lastIndexOf('.')) : name;
      response.setHeader(
          "Content-Disposition", String.format("fileName=%s%s", fileName, extension));
      response.setHeader("Pragma", "No-cache");
      response.setHeader("Cache-Control", "No-cache");
      response.setDateHeader("Expires", 0);
      StreamUtils.copy(bytes, response.getOutputStream());
      response.getOutputStream().close();
    }
    response.getOutputStream().close();
  }

  @Nullable
  protected abstract byte[] onReadAttachment(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String prid,
      @Nullable String name)
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
      @PathVariable String prid,
      @RequestParam(required = false) String name)
      throws Exception {
    byte[] bytes = StreamUtils.copyToByteArray(request.getInputStream());
    request.getInputStream().close();
    onWriteAttachment(session, request, response, prid, name, bytes);
  }

  protected abstract void onWriteAttachment(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String prid,
      @Nullable String name,
      @Nonnull byte[] bytes)
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
      @PathVariable String prid,
      @RequestParam(required = false) String name)
      throws Exception {
    onDeleteAttachment(session, request, response, prid, name);
  }

  protected abstract void onDeleteAttachment(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String prid,
      @Nullable String name)
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
      @PathVariable String prid)
      throws Exception {
    List<String> result = onListAttachments(session, request, response, prid);
    return jackson.writeValueAsString(result);
  }

  @Nonnull
  protected abstract List<String> onListAttachments(
      @Nonnull HttpSession session,
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull String prid)
      throws Exception;

  @Value(value = "#{jackson}")
  protected ObjectMapper jackson;

  @Value(value = "#{privilegeService}")
  protected PrivilegeService privilegeService;
}
