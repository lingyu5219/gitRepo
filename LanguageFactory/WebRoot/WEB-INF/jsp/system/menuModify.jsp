<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="updateForm" class="form-horizontal no-padding" role="form" method="post">
	<input type="hidden" name="menuId" />
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">模块</label>
				<div class="col-md-9">
					<select class="form-control moduleIdSelect2" name="moduleId" style="width: 100%">
			    		<!-- <option selected="selected"></option> -->
					</select>
				</div>
			</div>
		</div>
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">父菜单</label>
				<div class="col-md-9">
					<select class="form-control parentIdSelect2" name="parentId">
			    		<option selected="selected"></option>
					</select>
				</div>
			</div>
		</div> -->
	
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">菜单名</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="menuName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">URL</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="location"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">节点类型</label>
				<div class="col-md-9">
					<select name="isFork" class="form-control" style="width: 100%">
						<option value="1">枝干</option>
						<option value="2">叶子</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea class="form-control" rows="3" name="remark" ></textarea>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="col-md-9 col-md-offset-3">
				<button type="button" class="btn btn-flat btn-block btn-primary btn-update">保存</button>
			</div>
		</div>
	</div>
</form>
