<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">模块名</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="moduleName" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">菜单名</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="menuName" />
				</div>
			</div>
		</div>
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">父菜单</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="parentMenuName" />
				</div>
			</div>
		</div> -->
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">URL</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="location" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">节点类型</label>
				<div class="col-md-9">
					<select name="isFork" class="form-control" readonly style="width: 100%">
						<option value="0">叶子</option>
						<option value="1">枝干</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建人</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="userName">
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建时间</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="createTime">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea class="form-control" readonly rows="3" name="remark"></textarea>
				</div>
			</div>
		</div>
	</div>
</form>
