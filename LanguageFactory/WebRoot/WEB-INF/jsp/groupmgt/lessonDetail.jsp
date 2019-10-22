<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">课程类型</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="lessonTypeName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">课程名称</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="lessonName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">描述</label>
				<div class="col-md-9">
					<!-- <textarea class="form-control" readonly rows="8" name="description" placeholder="请输入描述"></textarea> -->
					<div id="description" style="background:#eee; border:1px solid #D2D6DE; padding:6px 12px;">
						
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">创建人</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="createByName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">创建时间</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="createTime"/>
				</div>
			</div>
		</div>
	</div>
</form>