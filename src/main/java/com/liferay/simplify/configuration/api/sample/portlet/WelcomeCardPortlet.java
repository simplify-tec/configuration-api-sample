package com.liferay.simplify.configuration.api.sample.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.simplify.configuration.api.sample.configuration.WelcomeCardPortletConfiguration;
import com.liferay.simplify.configuration.api.sample.constants.WelcomeCardPortletKeys;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author pedro
 */
@Component(
	configurationPid = "com.liferay.simplify.configuration.api.sample.configuration.WelcomeCardPortletConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=WelcomeCard",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + WelcomeCardPortletKeys.WELCOMECARD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class WelcomeCardPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		// pass the configuration to view.jsp
		renderRequest.setAttribute(
        		WelcomeCardPortletConfiguration.class.getName(),
        		_welcomeCardPortletConfiguration);
		
		super.render(renderRequest, renderResponse);
	}
	
	/*
	 * The method used to intantiate our configuration field 
	 */
    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
    	_welcomeCardPortletConfiguration = ConfigurableUtil.createConfigurable(
            		WelcomeCardPortletConfiguration.class, properties);
    }

    /*
     * Field used to access configuration values
     */
    private volatile WelcomeCardPortletConfiguration _welcomeCardPortletConfiguration;
}