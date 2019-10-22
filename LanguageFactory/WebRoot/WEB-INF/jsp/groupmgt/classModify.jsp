<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="updateForm" class="form-horizontal no-padding" role="form" method="post">
	<input type="hidden" name="classId" />
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">所属幼儿园</label>
				<div class="col-xs-9 col-md-8">
					<select class="form-control staffGartenIdSelect2" name="gartenId" style="width: 100%"></select>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">班级类型</label>
				<div class="col-md-8">
					<select class="form-control classTypeIdSelect2" name="classTypeId" style="width: 100%"></select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">班级名称</label>
				<div class="col-md-8">
					<input type="text" class="form-control" id="className" name="className" placeholder="请输入班级名称" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">入学年份</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control datepicker" id="year" name="year" placeholder="请输入入学年份" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">限制名额</label>
				<div class="col-md-8">
					<input type="text" class="form-control" id="capacity" name="capacity" placeholder="请输入限制名额" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">现有人数</label>
				<div class="col-md-8">
					<input type="text" class="form-control" id="currentQuantity" name="currentQuantity" placeholder="请输入限制名额" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建人</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" name="createByName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建时间</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" name="createTime"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">描述</label>
				<div class="col-md-8">
					<textarea class="form-control" rows="3" name="description" placeholder="请输入描述"></textarea>
				</div>
			</div>
		</div>
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">备注</label>
				<div class="col-md-8">
					<textarea class="form-control" rows="3" name="remark" placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div> -->
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="col-md-9 col-md-offset-3">
				<button type="button" class="btn btn-flat btn-block btn-primary btn-update">保存</button>
			</div>
		</div>
	</div>
</form>