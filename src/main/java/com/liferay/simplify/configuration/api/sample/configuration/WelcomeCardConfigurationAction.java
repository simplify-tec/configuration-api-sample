package com.liferay.simplify.configuration.api.sample.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.simplify.configuration.api.sample.constants.WelcomeCardPortletKeys;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/*
 * Class to include parameters in the configuration JSP and handle 
 * the form submission
 */
@Component(
	// Add this line to use the configuration
	configurationPid = "com.liferay.simplify.configuration.api.sample.configuration.WelcomeCardPortletConfiguration",
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
		
		// pass the configuration to configuration.jsp
		httpServletRequest.setAttribute(
        		WelcomeCardPortletConfiguration.class.getName(),
        		_welcomeCardPortletConfiguration);
		
		super.include(portletConfig, httpServletRequest, httpServletResponse);
	}
	
	/*
	 * The method used to instantiate our configuration field 
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
