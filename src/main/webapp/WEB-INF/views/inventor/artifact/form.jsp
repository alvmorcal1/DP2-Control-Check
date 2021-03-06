<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-select code="inventor.artifact.form.label.artifact_type" path="artifactType">
		<acme:input-option code="Component" value="COMPONENT" selected="${artifactType == 'COMPONENT'}"/>
		<acme:input-option code="Tool" value="TOOL" selected="${artifactType == 'TOOL' }"/>
	</acme:input-select>
	<acme:input-textbox code="inventor.artifact.form.label.name" path="name"/>		
	<acme:input-textbox code="inventor.artifact.form.label.code" path="code"/>	
	<acme:input-textbox code="inventor.artifact.form.label.technology" path="technology"/>	
	<acme:input-textbox code="inventor.artifact.form.label.description" path="description"/>	
	<acme:input-money code="inventor.artifact.form.label.retail_price" path="retailPrice"/>

	<acme:input-textbox code="inventor.artifact.form.label.link" path="link"/>	
	<jstl:choose>		
		<jstl:when test="${command == 'show' && isPublic}">
				<acme:input-textbox code="inventor.artifact.form.label.inventor.username" path="inventor.userAccount.username" readonly="true"/>
				<acme:input-money code="inventor.artifact.form.label.retail_priceExchange" path="budgetExchange" readonly="true"/>
				<acme:input-moment code="inventor.artifact.form.label.retail_priceExchangeDate" path="budgetExchangeDate" readonly="true"/>	
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && !isPublic}">
				<acme:input-money code="inventor.artifact.form.label.retail_priceExchange" path="budgetExchange" readonly="true"/>
				<acme:input-moment code="inventor.artifact.form.label.retail_priceExchangeDate" path="budgetExchangeDate" readonly="true"/>	
				<acme:submit code="inventor.artifact.form.button.update" action="/inventor/artifact/update"/>
				<acme:submit code="inventor.artifact.form.button.delete" action="/inventor/artifact/delete"/>
				<acme:submit code="inventor.artifact.form.button.publish" action="/inventor/artifact/publish"/>
				
		</jstl:when>
		<jstl:when test="${command == 'create'}">
				<acme:submit code="inventor.artifact.form.button.create" action="/inventor/artifact/create"/>
		</jstl:when>
	</jstl:choose>
	
	<jstl:if test="${artifactType == 'COMPONENT'}">
		<acme:button code="inventor.artifact.list.button.diskol" action="/inventor/diskol/list?masterId=${id}"/>
	</jstl:if>
	
</acme:form>