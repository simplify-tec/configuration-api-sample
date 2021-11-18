# Configuration API Sample

Sample project showing an application of Liferay Configuration API.

## Configuration API

If you are a back-end developer, you will probably make a lot of components, like portlets or gogo commands. Sometimes you'll want some characteristics of a component to be configurable by a portal user, in a way he doesn't have to go into Java code to configure it. That's when Configuration API comes into play. With it, you can create a set of configurable values to be used in any component you want. You can also scope it by system (throughout the system), virtual instance (company), site (group), or portlet instance (if your component is a portlet). [talk more about scopes (?)]

## The project

In this project, we will create a module with a portlet as an example. It will be a welcome card with a configurable title, background color, and size. This configurations values will have the scope of system and portlet instance.
[show two different photos of the portlet]

In practice, the configuration values scoped by system will serve only as default values for the portlet, as you can change them by setting portlet instance configurations per each instance.

## How to create a configuration

The first step is to create an interface that specifies all the configuration variables, their respective types and their scope. The default scope is system.

This interface can be created anywhere in the module, but we've followed Liferay's pattern and put it on a folder called *configuration* with the name *[portlet name]Configuration*, in this case, it is *WelcomeCardPortletConfiguration*.
[talk about categorization (?)]. You must use annotations from *Meta* class to indicate OSGI this interface is a specifying a configuration. To use this class, insert this line on your module's *build.gradle*

    compile group: "biz.aQute.bnd", name: "biz.aQute.bndlib"
    compileOnly group: "com.liferay", name: "com.liferay.portal.configuration.metatype.api"


You also need to add this line to *bnb.bnb* so bnd knows your module is using configurations.

    -metatype: *

### The annotations

**Meta.OCD**:

```java
@Meta.OCD(
    id = "com.liferay.simplify.configuration.api.sample.configuration.WelcomeCardPortletConfiguration",
    localization = "content/Language", 
    name = "welcome-card-portlet-configuration"
)
public interface WelcomeCardPortletConfiguration {
```

Where you set some properties of your configuration in general. The most important one is **id**. It is used by OSGI to identify your configuration. You should always use the interface's *package* plus its *name* preceded by a dot as your **id**.

**Meta.AD**:

```java
@Meta.AD(
    deflt = "blue",
    description = "cards-background-color",
    name = "background-color", 
    required = false
)
public String backgroundColor();
```

Specifies the properties of a configuration field. The interface method specifies the field itself, being its type the same as the return type of the method. In our case, we have the configuration fields of *String* type. *deflt* specifies the default value.
You can see what each annotation attribute means by inspecting the `aQute.bnd.annotation.metatype.Meta` interface.

Now that you've created your interface, Liferay will automatically generate an UI where you can set these configuration variables, you only need to deploy this module. After deploying it, go to **Control Panel > System Settings > Third Party > welcome-card-portlet-configuration**. [talk about language.properties (?)].

![Welcome Card System Configuration](images/system-settings-configuration.png)

Here you can configure variables to be used throughout the system. But as we need portlet scoped variables, these system values will be used only as default values for newly created portlets.

## Calling the configuration on the portlet

Using the configuration inside the portlet is very easy. You just need to indicate OSGI which configuration your component is using, by adding `configurationPid = "<configuration id>",` to the *@Component* annotation, like this

```java
@Component(
    configurationPid = "com.liferay.simplify.configuration.api.sample.configuration.WelcomeCardPortletConfiguration",
    ...
}
```

then you need to create a configuration instance when the component is activating. Add this method and field to the class

```java
    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
    	_welcomeCardPortletConfiguration = ConfigurableUtil.createConfigurable(
            		WelcomeCardPortletConfiguration.class, properties);
    }

    private volatile WelcomeCardPortletConfiguration _welcomeCardPortletConfiguration;
```

The `@Activate` annotation indicates this method will run when the component is starting. The `@Modified` makes this method be called each time any configuration value changes, so your component will always be up to date. `_welcomeCardPortletConfiguration` must be *volatile* to avoid some multithreading related problems.

**Notice that these steps can be applied to any class with a *component* annotation (an OSGI component), not just a portlet.**

To test if we can use our configuration values inside the portlet, paste this method inside it

```java
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		System.out.println(_welcomeCardPortletConfiguration.backgroundColor());
		System.out.println(_welcomeCardPortletConfiguration.size());
		System.out.println(_welcomeCardPortletConfiguration.title());
		
		super.render(renderRequest, renderResponse);
	}
```

and see if it logs our default configuration values

```
blue
medium
welcome
```

Our configuration values are ready to be passed to the portlet's view.jsp. Explaining how the *welcome card* view.jsp works it outside the scope of this project.

## Creating portlet configuration

Now we need to create a configuration UI that is accessible by any portlet. We first need to create a class that extends `DefaultConfigurationAction` and reference our portlet and our configuration. This class is responsible for making the connection between the portlet and the configuration, creating the configuration UI and handling its form submission.