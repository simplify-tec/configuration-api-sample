# Configuration API Sample
A sample project showing the different applications of Liferay Configuration API.
## Configuration API
If you are a back end developer, you will probably make a lot of components, like portlets or gogo commands. Sometimes you will want some characteristics of a component to be configurable by a portal user, in a way he doesn't have to go into Java code to configure it. That's when Configuration API comes into play. With it, you can create a set of configurable values to be used in any component you want. You can also scope it by virtual instance (company), site (group) or portlet instance (if your component is a portlet).

## Como criar uma configuração
In this project we will use a module with a portlet as example. It will be a welcome card with configurable title, background cpçpr and size. This configurations will have the scope of virtual instance and portlet instance. 

The first step is to create an interface that specifies all the configuration variables and its respectives types.
This interface can be created anywhere in the module, but we'll follow Liferay's pattern and put it on a folder called *configuration* with the name *[portlet name]Configuration*.