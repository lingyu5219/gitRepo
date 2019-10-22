<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="updateForm" class="form-horizontal no-padding" role="form" method="post">
	<input type="hidden" name="contentId"/>
	<input type="hidden" name="contentState"/>
	<input type="hidden" name="contentColumnId"/>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label" for="contentColumnName">所属栏目</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control tree-select" name="contentColumnName" placeholder="请选择所属栏目" />
					<div class="tree-menu tree-menu-hide">
                   		<div id="columnTree"></div>
                   	</div>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">内容类型</label>
				<div class="col-md-8">
					<select class="form-control" name="contentType" style="width: 100%">
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
					<input type="text" class="form-control" name="contentTitle" placeholder="请输入标题" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">副标题</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="contentSubTitle" placeholder="请输入副标题" />
				</div>
			</div>
		</div>
	</div>
	<div class="row hidden">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">外部链接</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="contentUrl" placeholder="请输入外部链接URL" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">内容</label>
				<div class="col-md-10">
					<textarea id="contentBody" name="contentBody">请输入内容</textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">备注</label>
				<div class="col-md-10">
					<textarea class="form-control" rows="3" name="remark" placeholder="请输入备注"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="col-md-10 col-md-offset-2">
				<button type="button"
					class="btn btn-flat btn-block btn-primary btn-update">保存</button>
			</div>
		</div>
	</div>
</form>
