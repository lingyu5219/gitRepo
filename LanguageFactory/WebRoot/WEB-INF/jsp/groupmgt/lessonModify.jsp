<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="updateForm" class="form-horizontal no-padding" role="form" method="post">
	<input type="hidden" name="lessonId" />
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">课程类型</label>
				<div class="col-md-9">
					<select class="form-control lessonTypeIdSelect2" name="lessonTypeId" style="width: 100%">
			    		<!-- <option selected="selected" value="0">请选择课程类型</option> -->
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">课程名称</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="lessonName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">描述</label>
				<div class="col-md-9">
					<!-- <textarea class="form-control" rows="6" name="description" placeholder="请输入描述"></textarea> -->
					<textarea id="description" name="description">请输入描述</textarea>
				</div>
			</div>
		</div>
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建人</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="createByName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建时间</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="createTime"/>
				</div>
			</div>
		</div> -->
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea class="form-control" rows="3" name="remark"
						placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div> -->
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="col-md-9 col-md-offset-3">
				<button type="button" class="btn btn-flat btn-block btn-primary btn-update">保存</button>
			</div>
		</div>
	</div>
</form>