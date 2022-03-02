<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Debug footer. Not for production. Outputs in an HTML comment --%>

<c:if test="${showStorefrontDebugInfo}">

    pk=${fn:escapeXml(cmsPage.pk)} <br>
    cmsPageName=${fn:escapeXml(cmsPage.name)} <br>



    Content slots=${fn:escapeXml(cmsPage.contentSlots)} <br>

    <c:forEach var="listVar" items="${cmsPage.contentSlots}">
        Content slots=${fn:escapeXml(listVar.uid)} <br>
    </c:forEach>


    Template=${fn:escapeXml(cmsPage.masterTemplate.name)} <br>
    cmsSiteUid=${fn:escapeXml(cmsSite.uid)} <br>
    secure=${fn:escapeXml(request.secure)} <br>
    contextPath=${fn:escapeXml(request.contextPath)}
    siteRootPath=<c:url value="/"/>

    siteResourcePath=${fn:escapeXml(siteResourcePath)} <br>
    themeResourcePath=${fn:escapeXml(themeResourcePath)} <br>
    commonResourcePath=${fn:escapeXml(commonResourcePath)} <br>
    requestURI=${fn:escapeXml(request.requestURI)} <br>


    Jalo Session details:

    ${fn:escapeXml(storefrontDebugJaloSessionAttributes)} <br>


    UiExperienceLevel:

    uiExperienceLevel=${fn:escapeXml(uiExperienceLevel)} <br>
    uiExperienceOverride=${fn:escapeXml(uiExperienceOverride)} <br>
    detectedUiExperienceCode=${fn:escapeXml(detectedUiExperienceCode)} <br>
    overrideUiExperienceCode=${fn:escapeXml(overrideUiExperienceCode)} <br>


    Detected Browser:

    detectedDeviceId=${fn:escapeXml(detectedDevice.id)} <br>
    detectedDeviceUserAgent=${fn:escapeXml(detectedDevice.userAgent)} <br>
    detectedDeviceCapabilities=${fn:escapeXml(detectedDevice.capabilities)} <br>

</c:if>
