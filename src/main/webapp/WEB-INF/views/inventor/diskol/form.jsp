<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.diskol.form.label.code" path="code"/>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:input-moment code="inventor.diskol.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="inventor.diskol.form.label.theme" path="theme"/>
	<acme:input-textbox code="inventor.diskol.form.label.summary" path="summary"/>
	<acme:input-moment code="inventor.diskol.form.label.startDate" path="startDate"/>
	<acme:input-moment code="inventor.diskol.form.label.finishDate" path="finishDate"/>
	<acme:input-money code="inventor.diskol.form.label.quota" path="quota"/>
	<acme:input-textbox code="inventor.diskol.form.label.additionalInfo" path="additionalInfo"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="inventor.diskol.form.button.update" action="/inventor/diskol/update"/>
			<acme:submit code="inventor.diskol.form.button.delete" action="/inventor/diskol/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.diskol.form.button.create" action="/inventor/diskol/create?masterId=${masterId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>