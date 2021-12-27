<div id="portalMenu_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" data-params="portalMenu.instance()">
	<#if user.login?has_content>
	<!-- Menu estatico -->
 	<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
  	<#else>
  	<!-- Menu fixo -->
  	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	</#if>
	
		<!-- We use the fluid option here to avoid overriding the fixed width of a normal container within the narrow content columns. -->
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-6">
				<span class="sr-only">${i18n.getTranslation('menu.toggle')}</span> 
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/portal/1/portal">${i18n.getTranslation('menu.title')}</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/portal/1/portal">${i18n.getTranslation('menu.home')}</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>
</div>

