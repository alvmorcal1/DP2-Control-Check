<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.diskol.list.label.code" path="code" width="20%"/>
	<acme:list-column code="inventor.diskol.list.label.creationMoment" path="creationMoment" width="20%"/>
	<acme:list-column code="inventor.diskol.list.label.theme" path="theme" width="20%"/>
	<acme:list-column code="inventor.diskol.list.label.summary" path="summary" width="20%"/>
	<acme:list-column code="inventor.diskol.list.label.quota" path="quota" width="20%"/>
</acme:list>

<acme:button code="inventor.diskol.list.button.create" action="/inventor/diskol/create?masterId=${masterId}"/>