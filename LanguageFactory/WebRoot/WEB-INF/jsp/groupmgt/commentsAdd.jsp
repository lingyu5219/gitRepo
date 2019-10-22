<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="addForm" class="form-horizontal no-padding" role="form"
	method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">留言标题</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="commentsTitle"
						placeholder="请输入留言标题" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">留言人</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="commentsBy"
						placeholder="请输入留言人姓名" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">联系电话</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="commentsPhone"
						placeholder="请输入联系电话" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">电子邮箱</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="commentsEmail"
						placeholder="请输入电子邮箱" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">联系地址</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="commentsAddress"
						placeholder="请输入联系地址" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">留言内容</label>
				<div class="col-md-10">
					<textarea class="form-control" rows="3" name="commentsContent"
						placeholder="请输入内容 ..."></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">验证码</label>
				<div class="col-md-5">
					<input type="text" class="form-control" name="verifyCode" placeholder="请输入验证码" />
				</div>
				<div class="col-md-3">
					<img src="${ctx}kaptcha" id="kaptchaImage" title="看不清，点击换一张">
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<button type="button" class="btn btn-flat btn-block btn-primary btn-save">保存</button>
		</div>
	</div>
</form>
