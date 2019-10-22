<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="addForm" class="form-horizontal no-padding" role="form"
	method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">模块图标</label>
				<div class="col-md-10">
					<input type="hidden" name="moduleIcon" value="fa-gear"/>
					<div id="moduleIcon">
						<i class="fa fa-users icon-large icon-default" data-class="fa-users"></i>
						<i class="fa fa-gear icon-large icon-default" data-class="fa-gear"></i>
						<i class="fa fa-file-text icon-large icon-default" data-class="fa-file-text"></i>
						<i class="fa fa-envelope icon-large icon-default" data-class="fa-envelope"></i>
						<i class="fa fa-sitemap icon-large icon-default" data-class="fa-sitemap"></i>
						<i class="fa fa-commenting icon-large icon-default" data-class="fa-commenting"></i>
						<i class="fa fa-bar-chart icon-large icon-default" data-class="fa-bar-chart"></i>
						<i class="fa fa-heart icon-large icon-default" data-class="fa-heart"></i>
						<i class="fa fa-photo icon-large icon-default" data-class="fa-photo"></i>
						<i class="fa fa-object-group icon-large icon-default" data-class="fa-object-group"></i>
						<i class="fa fa-graduation-cap icon-large icon-default" data-class="fa-graduation-cap"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">模块名</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="moduleName"
						placeholder="请输入模块名" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">备注</label>
				<div class="col-md-10">
					<textarea class="form-control" rows="3" name="remark"
						placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="col-md-10 col-md-offset-2">
				<button type="button"
					class="btn btn-flat btn-block btn-primary btn-save">保存</button>
			</div>
		</div>
	</div>
</form>
