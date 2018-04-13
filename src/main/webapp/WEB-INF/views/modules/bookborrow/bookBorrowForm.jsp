<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图书借阅表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bookborrow/bookBorrow/">图书借阅表列表</a></li>
		<li class="active"><a href="${ctx}/bookborrow/bookBorrow/form?id=${bookBorrow.id}">图书借阅表<shiro:hasPermission name="bookborrow:bookBorrow:edit">${not empty bookBorrow.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bookborrow:bookBorrow:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bookBorrow" action="${ctx}/bookborrow/bookBorrow/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">图书编号：</label>
			<div class="controls">
				<form:input path="bookId.id" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户编号：</label>
			<div class="controls">
				<sys:treeselect id="userId" name="userId.id" value="${bookBorrow.userId.id}" labelName="userId.name"
								labelValue="${bookBorrow.userId.name}" title="用户" url="/sys/user/treeData"
								cssClass="required" allowClear="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图书状态（归还，未还，损坏，遗失)：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">续借次数（默认续借一个月）：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="5" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论IP：</label>
			<div class="controls">
				<form:input path="ip" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bookborrow:bookBorrow:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>