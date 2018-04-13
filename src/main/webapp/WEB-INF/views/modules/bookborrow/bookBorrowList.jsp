<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图书借阅表管理</title>
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
		<li class="active"><a href="${ctx}/bookborrow/bookBorrow/">图书借阅表列表</a></li>
		<shiro:hasPermission name="bookborrow:bookBorrow:edit"><li><a href="${ctx}/bookborrow/bookBorrow/form">图书借阅表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bookBorrow" action="${ctx}/bookborrow/bookBorrow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>图书编号：</label>
				<form:input path="bookId.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>用户编号：</label>
				<sys:treeselect id="userId" name="userId.id" value="${bookBorrow.userId.id}" labelName="userId.name"
								labelValue="${bookBorrow.userId.name}" title="用户" url="/sys/user/treeData"
								cssClass="required" allowClear="true"/>
			</li>
			<li><label>图书状态（归还，未还，损坏，遗失)：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>续借次数（默认续借一个月）：</label>
				<form:input path="num" htmlEscape="false" maxlength="5" class="input-medium"/>
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
				<th>图书状态（归还，未还，损坏，遗失)</th>
				<th>续借次数（默认续借一个月）</th>
				<th>评论IP</th>
				<th>借出时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="bookborrow:bookBorrow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bookBorrow">
			<tr>
				<td><a href="${ctx}/bookborrow/bookBorrow/form?id=${bookBorrow.id}">
					${bookBorrow.bookId.id}
				</a></td>
				<td>
					${bookBorrow.userId.id}
				</td>
				<td>
					${bookBorrow.status}
				</td>
				<td>
					${bookBorrow.num}
				</td>
				<td>
					${bookBorrow.ip}
				</td>
				<td>
					<fmt:formatDate value="${bookBorrow.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bookBorrow.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bookborrow:bookBorrow:edit"><td>
    				<a href="${ctx}/bookborrow/bookBorrow/form?id=${bookBorrow.id}">修改</a>
					<a href="${ctx}/bookborrow/bookBorrow/delete?id=${bookBorrow.id}" onclick="return confirmx('确认要删除该图书借阅表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>