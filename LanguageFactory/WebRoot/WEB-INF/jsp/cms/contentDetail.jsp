<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label" for="contentColumnName">所属栏目</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" name="contentColumnName" placeholder="请选择所属栏目" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">内容类型</label>
				<div class="col-md-8">
					<select readonly class="form-control" name="contentType" style="width: 100%">
			    		<option value="1">本站内容</option>
			    		<option value="2">外部链接</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">标题</label>
				<div class="col-md-10">
					<input type="text" readonly class="form-control" name="contentTitle"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">副标题</label>
				<div class="col-md-10">
					<input type="text" readonly class="form-control" name="contentSubTitle"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row hidden">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">外部链接</label>
				<div class="col-md-10">
					<a id="contentUrl" href="#" target="_blank"></a>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">内容</label>
				<div class="col-md-10">
					<div id="contentBody" style="background:#eee; border:1px solid #D2D6DE; padding:6px 12px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">备注</label>
				<div class="col-md-10">
					<textarea readonly class="form-control" rows="3" name="remark"></textarea>
				</div>
			</div>
		</div>
	</div>
</form>
