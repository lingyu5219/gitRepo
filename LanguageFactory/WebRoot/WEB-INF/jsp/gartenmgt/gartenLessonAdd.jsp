<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box">
	<!-- <div class="box-header with-border">
		<form id="queryAddForm" class="form-horizontal no-padding"
			role="form" method="post">
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
							<select class="form-control lessonTypeIdSelect2" name="lessonTypeId" style="width: 100%">
					    		<option selected="selected" value="0">请选择课程类型</option>
							</select>
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
							<input type="text" class="form-control" id="lessonName"
								name="lessonName" placeholder="请输入课程名称">
					</div>
				</div>
				搜索
				<div class="col-xs-6 col-md-2">
					<button type="button" id="btn-query2"
						class="btn btn-block btn-primary">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</div>
		</form>
	</div> -->
	<div class="box-body">
		<table id="dataListAddTable" class="table table-condensed table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>课程类型</th>
					<th>课程名称</th>
					<th>描述</th>
					<!-- <th>创建人</th>
					<th>创建时间</th> -->
					<th>操作</th>
				</tr>
			</thead>
		</table>
		<div class="row">
			<div class="col-sm-5">
				<div class="col-md-8 col-md-offset-1">
					<button type="button" class="btn btn-flat btn-block btn-primary btn-save">增加</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- <form id="addForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">课程类型</label>
				<div class="col-md-9">
					<select class="form-control lessonTypeIdSelect2" name="lessonTypeId" style="width: 100%">
			    		<option selected="selected"></option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">课程</label>
				<div class="col-md-9">
					<input type="text" class="form-control" id="lessonName" name="lessonName"
						placeholder="请输入课程类型" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">描述</label>
				<div class="col-md-9">
					<textarea class="form-control" rows="3" name="description" placeholder="请输入描述"></textarea>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea class="form-control" rows="3" name="remark"
						placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="col-md-9 col-md-offset-3">
				<button type="button" class="btn btn-flat btn-block btn-primary btn-save">保存</button>
			</div>
		</div>
	</div>
</form> -->