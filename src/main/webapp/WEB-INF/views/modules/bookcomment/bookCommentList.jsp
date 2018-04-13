<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图书评论表管理</title>
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
		<li class="active"><a href="${ctx}/bookcomment/bookComment/">图书评论表列表</a></li>
		<shiro:hasPermission name="bookcomment:bookComment:edit"><li><a href="${ctx}/bookcomment/bookComment/form">图书评论表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bookComment" action="${ctx}/bookcomment/bookComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>图书编号：</label>
				<form:input path="bookId.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>用户编号：</label>
				<sys:treeselect id="userId" name="userId.id" value="${bookComment.userId.id}" labelName="userId.name"
								labelValue="${bookComment.userId.name}" title="用户" url="/sys/user/treeData"
								cssClass="required" allowClear="true"/>

			</li>
			<li><label>评论内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>评论内容</th>
				<th>评论IP</th>
				<th>评论时间</th>
				<shiro:hasPermission name="bookcomment:bookComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bookComment">
			<tr>
				<td><a href="${ctx}/bookcomment/bookComment/form?id=${bookComment.id}">
					${bookComment.bookId.id}
				</a></td>
				<td>
					${bookComment.userId.id}
				</td>
				<td>
					${bookComment.content}
				</td>
				<td>
					${bookComment.ip}
				</td>
				<td>
					<fmt:formatDate value="${bookComment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bookcomment:bookComment:edit"><td>
    				<a href="${ctx}/bookcomment/bookComment/form?id=${bookComment.id}">修改</a>
					<a href="${ctx}/bookcomment/bookComment/delete?id=${bookComment.id}" onclick="return confirmx('确认要删除该图书评论表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>