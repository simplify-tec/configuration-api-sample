package com.liferay.simplify.configuration.api.sample.configuration;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.simplify.configuration.api.sample.constants.WelcomeCardPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/*
 * Class to include parameters in the configuration JSP and handle 
 * the form submission
 */
@Component(
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
    property = {
    	// makes the connection between this action and the portlet
        "javax.portlet.name=" + WelcomeCardPortletKeys.WELCOMECARD
    },
    service = ConfigurationAction.class
)
public class WelcomeCardConfigurationAction extends DefaultConfigurationAction {

    @Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
        throws Exception {

    	// first you get the values from the request
        String title = ParamUtil.getString(actionRequest, "title");
        String backgroundColor = ParamUtil.getString(actionRequest, "backgroundColor");
        String size = ParamUtil.getString(actionRequest, "size");
        
        // then you save them
        setPreference(actionRequest, "title", title);
        setPreference(actionRequest, "backgroundColor", backgroundColor);
        setPreference(actionRequest, "size", size);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }
	
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {
		super.include(portletConfig, httpServletRequest, httpServletResponse);
	}
	
}
