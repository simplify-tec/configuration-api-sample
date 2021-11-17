# API de Configuração
Um projeto de exemplo ilustrando diferentes tipos de aplicação da API de Configuração do Liferay.

A API de configuração permite que você crie um grupo de variáveis modificáveis, que podem ser modificadas por usuários do portal sem a necessidade de mexer em código Java, ou fazer o redeploy de algum módulo. Essas variáveis podem ser utilizadas por qualquer componente. Além disso, você pode escopar essas configurações por sistema, instância virtual (company), site (group) ou instância de portlet, caso seu componente seja um portlet.

## Como criar uma configuração
Nesse projeto utilizaremos como exemplo um módulo com um portlet. Será um card de boas vindas com o título, a cor de background e o seu tamanhao configuráveis. Iremos ter configurações no escopo de instância virtual e de instância de portlet.
O primeiro passo é criar uma interface que especifique as variáveis que compoẽm nossa configuração e quais são os seus respectivos tipos. Essa interface pode ser criada em qualquer lugar do módulo, mas para seguir o mesmo padrão da Liferay, criamos esse arquivo na pasta *configuration* com o nome *[nome do portlet]Configuration*.
