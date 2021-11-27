<%@page import="com.liferay.simplify.configuration.api.sample.configuration.WelcomeCardPortletConfiguration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>


<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
WelcomeCardPortletConfiguration _welcomeCardPortletConfiguration = 
	(WelcomeCardPortletConfiguration) request.getAttribute(WelcomeCardPortletConfiguration.class.getName());

String title = "", backgroundColor = "", size = "";

if (_welcomeCardPortletConfiguration != null) {
	// getting current preference values or defaults
	String defaultTitle = _welcomeCardPortletConfiguration.title();
	title = portletPreferences.getValue("title", defaultTitle);
	
	String defaultBackgroundColor =  _welcomeCardPortletConfiguration.backgroundColor();
	backgroundColor = portletPreferences.getValue("backgroundColor", defaultBackgroundColor);
	
	String defaultSize =  _welcomeCardPortletConfiguration.size();
	size = portletPreferences.getValue("size", defaultSize);
}
%>