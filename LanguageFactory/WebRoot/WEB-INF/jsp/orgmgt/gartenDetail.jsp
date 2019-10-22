<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form"
	method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">校园名称</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" name="gartenName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
				<label class="col-md-6 control-label">邮编</label>
				<div class="col-md-6">
					<input readonly type="text" class="form-control" name="gartenZipCode"/>
				</div>
				
			</div>
		</div>
		<div class="col-sm-4">
			<div class="form-group">
				<label class="col-md-3 control-label">邮箱</label>
				<div class="col-md-9">
					<input readonly type="text" class="form-control" name="gartenEmail"/>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="form-group">
				<label class="col-md-3 control-label">电话</label>
				<div class="col-md-9">
					<input readonly type="text" class="form-control" name="gartenPhone"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">地址</label>
				<div class="col-sm-8 input-group" style="padding-left:15px; padding-right:15px;">
					<span class="input-group-addon"><i class="glyphicon glyphicon-map-marker text-primary"></i></span>
					<input readonly class="form-control" name="gartenProvinceName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<div class="col-sm-12 input-group" style="padding-left:15px; padding-right:15px;">
					<span class="input-group-addon"><i class="glyphicon glyphicon-tree-deciduous text-primary"></i></span>
					<input readonly class="form-control" name="gartenCityName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<div class="col-sm-12 input-group" style="padding-left:15px; padding-right:15px;">
					<span class="input-group-addon"><i class="glyphicon glyphicon-grain text-primary"></i></span>
					<input readonly class="form-control" name="gartenDistrictName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">	
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">详细地址</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" name="gartenAddress"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">地图</label>
				<div class="col-md-10">
					<div id="addressMap" style="width: 100%;height:300px;overflow: hidden;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">校园简介</label>
				<div class="col-md-10">
					<div id="gartenDesc" style="word-wrap:break-word;background:#eee; border:1px solid #D2D6DE; padding:6px 12px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">园长致辞</label>
				<div class="col-md-10">
					<div id="gartenSpeech" style="word-wrap:break-word;background:#eee; border:1px solid #D2D6DE; padding:6px 12px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">参观时间</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" name="gartenVisitTime"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建者</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" id="createByName"
						name="createByName" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建时间</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" id="createTime"
						name="createTime" />
				</div>
			</div>
		</div>
	</div>
</form>
