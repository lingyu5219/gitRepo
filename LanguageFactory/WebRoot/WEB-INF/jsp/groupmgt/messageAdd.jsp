<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="addForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">消息标题</label>
				<div class="col-md-10">
					<input type="text" class="form-control" id="msgTitle" name="msgTitle"
						placeholder="请输入消息标题" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">消息类型</label>
				<div class="col-md-8">
					<select class="form-control msgTypeSelect2" name="msgType" style="width: 100%">
			    		<option selected="selected" value="0">请选择消息类型</option>
			    		<option value="1">新闻</option>
			    		<option value="2">活动</option>
			    		<option value="3">公告</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-xs-12 col-md-3 control-label">集团消息</label>
				<div class="col-xs-3 col-md-3">
					<div class="switch" data-on-label="是" data-off-label="否">
						<input type="checkbox" name="isGroupMsg"/>
					</div>
				</div>
				<div class="col-xs-9 col-md-6">
					<select class="form-control msgGartenIdSelect2" name="msgGartenId" style="width: 100%">
			    		<!-- <option selected="selected"></option> -->
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">消息格式</label>
				<div class="col-md-8">
					<select class="form-control msgPatternSelect2" name="msgPattern" style="width: 100%">
			    		<option selected="selected" value="0">请选择消息格式</option>
			    		<option value="1">图文</option>
			    		<option value="2">视频</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div id="msgVideoChosser" class="form-group hidden">
				<label class="col-md-3 control-label">选择视频 </label>
				<div class="col-md-9">
					<input type="hidden" name="msgVideoId"/>
					<input type="button" class="form-control btn btn-primary" value="选择视频"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row msgContent">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label" for="msgContent">内容</label>
				<div class="col-md-10">
					<textarea id="msgContent" name="msgContent">请输入内容</textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row msgVideo hidden">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label" for="msgVideo">视频</label>
				<div class="col-md-10">
					<div class="embed-responsive embed-responsive-16by9">
						<video id="msgVideo" class="embed-responsive-item hidden" controls>
						    <source src="" type="video/mp4">
						          您的浏览器不支持 HTML5 video 标签。
						</video>
					</div>
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