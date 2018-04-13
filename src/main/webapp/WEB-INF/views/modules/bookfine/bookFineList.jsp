<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图书罚款表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bookfine/bookFine/">图书罚款表列表</a></li>
		<shiro:hasPermission name="bookfine:bookFine:edit"><li><a href="${ctx}/bookfine/bookFine/form">图书罚款表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bookFine" action="${ctx}/bookfine/bookFine/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>图书编号：</label>
				<form:input path="bookId.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>用户编号1：</label>
				<sys:treeselect id="userId" name="userId.id" value="${bookFine.userId.id}" labelName="userId.name"
					labelValue="${bookFine.userId.name}" title="用户" url="/sys/user/treeData?officeId=2" cssClass="input-small"
					allowClear="true" />
			</li>
			<li><label>罚款原因：</label>
				<form:input path="reason" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>图书编号</th>
				<th>用户编号</th>
				<th>罚款原因</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="bookfine:bookFine:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bookFine">
			<tr>
				<td><a href="${ctx}/bookfine/bookFine/form?id=${bookFine.id}">
					${bookFine.bookId.id}
				</a></td>
				<td>
					${bookFine.userId.id}
				</td>
				<td>
					${bookFine.reason}
				</td>
				<td>
					${bookFine.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${bookFine.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bookFine.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bookfine:bookFine:edit"><td>
    				<a href="${ctx}/bookfine/bookFine/form?id=${bookFine.id}">修改</a>
					<a href="${ctx}/bookfine/bookFine/delete?id=${bookFine.id}" onclick="return confirmx('确认要删除该图书罚款表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>