<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<security:authorize access="!isAuthenticated()">
	<h3>You must log in to access the latest news.</h3>
</security:authorize>

<security:authorize access="isAuthenticated()">
	<h1>Latest news: </h1>
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Date</th>
				<th>Item</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item">
				<tr>
					<td>
						<c:out value="${item.publishedDate}" />
						<br/>
						<c:out value="${item.blog.name}" />
					</td>
					
					<td>
						<strong> <a href="<c:out value="${item.link}" />"
							target="blank"> <c:out value="${item.title}"></c:out>
						</a>
						</strong> 
						<br /> ${item.description}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</security:authorize>