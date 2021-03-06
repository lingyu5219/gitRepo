<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="addForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">所属幼儿园</label>
				<div class="col-xs-9 col-md-8">
					<select class="form-control staffGartenIdSelect2" name="gartenId" style="width: 100%">
			    		<option selected="selected"></option>
					</select>
				</div>
			</div>
		</div> -->
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">项目名称</label>
				<div class="col-md-9">
					<input type="text" class="form-control" id="eduProjectName" name="eduProjectName"
						placeholder="请输入教育项目名称" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">描述</label>
				<div class="col-md-9">
					<!-- <textarea class="form-control" rows="8" name="description" placeholder="请输入描述"></textarea> -->
					<textarea id="description" name="description">请输入描述</textarea>
				</div>
			</div>
		</div>
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">备注</label>
				<div class="col-md-8">
					<textarea class="form-control" rows="3" name="remark"
						placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div> -->
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="col-md-9 col-md-offset-3">
				<button type="button" class="btn btn-flat btn-block btn-primary btn-save">保存</button>
			</div>
		</div>
	</div>
</form>