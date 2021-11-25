package com.liferay.simplify.configuration.api.sample.configuration;

import aQute.bnd.annotation.metatype.Meta;

/*
 * This interface is where we describe our configuration. 
 * Here is where we declare our configuration fields and their respective types.
 * 
 * You can see what each annotation attribute means by inspecting the 
 * aQute.bnd.annotation.metatype.Meta interface. 
 */
@Meta.OCD(
    id = "com.liferay.simplify.configuration.api.sample.configuration.WelcomeCardPortletConfiguration",
    localization = "content/Language", 
    name = "welcome-card-portlet-configuration"
)
public interface WelcomeCardPortletConfiguration {

	/*
	 * Each method describes a configuration field. The method return type
	 * indicates the field type. In this case, it is a String.
	 */
    @Meta.AD(
        deflt = "welcome",
        required = false
    )
    public String title();
    
    @Meta.AD(
        deflt = "blue",
        required = false
    )
    public String backgroundColor();
	
    @Meta.AD(
        deflt = "medium", 	
        required = false
    )
    public String size();
}
