<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图书表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        $(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/book/book/export");
                        $("#searchForm").submit();
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
            $("#btnImport").click(function(){
                $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
                    bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
            });
        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
	</script>
	<style>
		.table th, .table td {
			text-align: center;
			vertical-align: middle!important;
		}
	</style>
</head>
<body>
<div id="importBox" class="hide">
	<form id="importForm" action="${ctx}/book/book/import"
		  method="post" enctype="multipart/form-data" class="form-search"
		  style="padding-left: 20px; text-align: center;"
		  onsubmit="loading('正在导入，请稍等...');">
		<br />
		<input id="uploadFile" name="file" type="file"
			   style="width: 330px" />
		<br /> <br />
		<input id="btnImportSubmit"
			   class="btn btn-primary" type="submit" value="   导    入   " /> <a
			href="${ctx}/book/book/import/template">下载模板</a>
	</form>
</div>

<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/book/book/">图书表列表</a></li>
	<shiro:hasPermission name="book:book:edit"><li><a href="${ctx}/book/book/form">图书表添加</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="book" action="${ctx}/book/book/" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<ul class="ul-form">
		<li><label>图书地址：</label>
			<sys:treeselect id="library" name="library.id" value="${book.library.id}"
							labelName="library.name" labelValue="${book.library.name}" title="图书地址"
							url="/library/selfLibrary/treeData" cssClass="input-small" allowClear="true"/>
		</li>
		<li><label>书名：</label>
			<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
		</li>
		<li><label>图书类别：</label>
			<form:select path="category" class="input-medium required">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('book_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</li>
		<li><label>作者：</label>
			<form:input path="author" htmlEscape="false" maxlength="100" class="input-medium"/>
		</li>
		<li class="clearfix"></li>
		<li class="btns">
			<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>--%>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查s询" onclick="return page();"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
		</li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>图片</th>
		<th>图书存放地址</th>
		<th>书名</th>
		<th>出版社名称</th>
		<th>总数量</th>
		<th>不在库数量</th>
		<th>图书类别</th>
		<th>作者</th>
		<th>单价</th>
		<th>收藏数</th>
		<th>借阅数</th>
		<th>续借数</th>
		<%--<th>出版日期</th>--%>
		<%--<th>入库时间</th>--%>
		<%--<th>更新日期</th>--%>
		<shiro:hasPermission name="book:book:edit"><th>操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="book" varStatus="status">
		<tr>
            <td nowrap>
                <input type="hidden" id="nameImage_${status.count}" value="${book.photo}" htmlEscape="false"
					   maxlength="255" class="input-xlarge"/>
                <sys:ckfinder input="nameImage_${status.count}" type="images" uploadPath="/photo"
							  selectMultiple="false" maxWidth="100" maxHeight="100" readonly="true"/>
            </td>
			<td nowrap>
                <a href="${ctx}/book/book/form?id=${book.id}">
					${book.library.name}
			    </a>
            </td>
			<td>
					${book.name}
			</td>
			<td>
					${book.pressName}
			</td>

			<td nowrap>
					${book.total}
			</td>
			<td nowrap>
					${book.num}
			</td>
			<td nowrap>
               <c:forEach items="${fns:getDictList('book_category')}" var="test">
                     <c:if test="${test.value eq book.category}">
                         ${test.label}
                     </c:if>
               </c:forEach>

			</td>
			<td nowrap>
					${book.author}
			</td>
			<td nowrap>
					${book.price}
			</td>
			<td nowrap>
					${book.collect}
			</td>
			<td nowrap>
					${book.borrow}
			</td>
			<td nowrap>
					${book.renewal}
			</td>
			<%--<td nowrap>--%>
				<%--<fmt:formatDate value="${book.pressTime}" pattern="yyyy-MM-dd"/>--%>
			<%--</td>--%>
			<%--<td nowrap>--%>
				<%--<fmt:formatDate value="${book.storageDate}" pattern="yyyy-MM-dd"/>--%>
			<%--</td>--%>
			<%--<td nowrap>--%>
				<%--<fmt:formatDate value="${book.updateDate}" pattern="yyyy-MM-dd"/>--%>
			<%--</td>--%>
			<shiro:hasPermission name="book:book:edit"><td nowrap>
				<a href="${ctx}/book/book/form?id=${book.id}&library.name=${book.library.name}">修改</a>
				<a href="${ctx}/book/book/delete?id=${book.id}" onclick="return confirmx('确认要删除该图书表吗？', this.href)">删除</a>
			</td></shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>