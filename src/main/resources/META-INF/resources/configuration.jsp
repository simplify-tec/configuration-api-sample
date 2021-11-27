<%@ include file="./init.jsp" %>
<%@page import="com.liferay.portal.kernel.util.Constants"%>

<!-- Our configuration action URL -->
<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<!-- URL to redirect to after the form is submitted -->
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form 
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group markupView="lexicon">
			<!-- 
				Configuration values inputs
				Notice that the input names must be the same as those
				used to retrieve its values on the configuration action 
			-->
			<aui:input name="title" value="<%= title %>" />
			<aui:select label="background-color" name="backgroundColor">
				<aui:option label="select-a-background-color" selected="true" value="" />
		        <aui:option label="white" selected="<%= "white".equals(backgroundColor) %>" value="white" />
		        <aui:option label="blue" selected="<%= "blue".equals(backgroundColor) %>" value="blue" />
		        <aui:option label="red" selected="<%= "red".equals(backgroundColor) %>" value="red" />
			</aui:select>
			<aui:select label="size" name="size">
				<aui:option label="select-a-size" selected="true" value="" />
		        <aui:option label="small" selected="<%= "small".equals(size) %>" value="small" />
		        <aui:option label="medium" selected="<%= "medium".equals(size) %>" value="medium" />
		        <aui:option label="large" selected="<%= "large".equals(size) %>" value="large" />
			</aui:select>
		
			<!-- Hidden inputs used internally by Liferay -->
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>"/>
			<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
	
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>
		
	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />
		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>		
</liferay-frontend:edit-form>