<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="searchForm" class="form-horizontal no-padding" role="form"
	method="post">
	<input type="hidden" name="mediaPattern"/>
	<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
				<label class="col-md-4 control-label" for="mediaTitle">资源名</label>
                <div class="col-md-8">
                	<input type="text" class="form-control" id="mediaTitle" name="mediaTitle" placeholder="请输入资源名称">
                </div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
	            <label class="col-xs-12 col-md-3 control-label">所属集团</label>
				<div class="col-xs-3 col-md-3">
					<div class="switch" data-on-label="是" data-off-label="否">
						<input type="checkbox" name="isGroupMedia"/>
					</div>
				</div>
				<div class="col-xs-9 col-md-6">
					<select class="form-control mediaGartenIdSelect2" name="mediaGartenId" style="width: 100%">
			    		<option selected="selected" value="-1">请选择所属校园</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-2">
			<button type="button" id="btn-query" class="btn btn-block btn-primary"><i class="fa fa-search"></i></button>
		</div>
	</div>
</form>
<%-- <section class="content" style="padding:15px 0px;">
	<div class="col-xs-6 col-md-4 mediaItem">
	    <div class="thumbnail">
	        <img src="${basePath}static/upload/file/media/pic/20180622/4db9c37241b547f08a8f3aabad2d7b88.png">
	        <div class="caption">
		        Thumbnail label
	        </div>
	    </div>
	</div>
</section> --%>
<div class="box-body">
	<table id="searchListTable" class="table table-condensed table-hover">
		<thead>
		<tr>
			<th>#</th>
			<th>资源名称</th>
			<th>缩略图</th>
			<th>操作</th>
		</tr>
		</thead>
	</table>
</div>
