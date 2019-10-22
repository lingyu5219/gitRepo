<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">消息标题</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" id="msgTitle" name="msgTitle"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">消息类型</label>
				<div class="col-md-8">
					<select readonly class="form-control msgTypeSelect2" name="msgType" style="width: 100%">
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
					<input readonly type="text" class="form-control" name="msgGartenName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">消息格式</label>
				<div class="col-md-8">
					<select readonly class="form-control msgPatternSelect2" name="msgPattern" style="width: 100%">
			    		<option value="1">图文</option>
			    		<option value="2">视频</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建者</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" id="createByName" name="createByName" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建时间</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" id="createTime" name="createTime" />
				</div>
			</div>
		</div>
	</div>
	<div class="row msgContent hidden">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label" for="msgContent">内容</label>
				<div class="col-md-10">
					<div id="msgContent" style="background:#eee; border:1px solid #D2D6DE; padding:6px 12px;"></div>
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
						<video id="msgVideo" class="embed-responsive-item" controls>
						    <source src="" type="video/mp4">
						          您的浏览器不支持 HTML5 video 标签。
						</video>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>