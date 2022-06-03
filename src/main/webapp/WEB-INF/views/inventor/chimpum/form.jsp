<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"/>
	<jstl:if test="${command == 'show'}">
		<acme:input-moment code="inventor.chimpum.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.chimpum.form.label.description" path="description"/>
	<acme:input-moment code="inventor.chimpum.form.label.startDate" path="startDate"/>
	<acme:input-moment code="inventor.chimpum.form.label.finishDate" path="finishDate"/>
	<acme:input-money code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-textbox code="inventor.chimpum.form.label.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create?masterId=${masterId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>