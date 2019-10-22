<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">留言标题</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" name="commentsTitle" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">留言人</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="commentsBy" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">联系电话</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="commentsPhone" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">电子邮箱</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="commentsEmail" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">联系地址</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="commentsAddress" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">留言内容</label>
				<div class="col-md-10">
					<textarea readonly class="form-control" rows="3" name="commentsContent" ></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建时间</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="createTime" />
				</div>
			</div>
		</div>
	</div>
</form>
