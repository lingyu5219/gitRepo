<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<form id="addForm" class="form-horizontal no-padding" role="form" method="post">
			<div class="row">
	            <div class="col-sm-6">
	             	<div class="form-group">
	                    <label class="col-md-3 control-label">部门名称</label>
	                   	<div class="col-md-9">
					    	<input type="text" class="form-control" name="deptName" placeholder="请输入部门名称" />
						</div>
					</div>
	            </div>
	            <div class="col-sm-6">
	             	<div class="form-group">
	                    <label class="col-md-3 control-label">部门地址</label>
	                   	<div class="col-md-9">
					    	<input type="text" class="form-control" name="deptAddress" placeholder="请输入部门地址" />
						</div>
					</div>
	            </div>
            </div>
            <div class="row">
	            <div class="col-sm-6">
	             	<div class="form-group">
	                    <label class="col-md-3 control-label">所属中心ID</label>
	                   	<div class="col-md-9">
					    	<input type="text" class="form-control" name="centerId" placeholder="请输入中心ID" />
						</div>
					</div>
	            </div>
	            <div class="col-sm-6">
	             	<div class="form-group">
	                    <label class="col-md-3 control-label">备注</label>
	                   	<div class="col-md-9">
					    	<input type="text" class="form-control" name="remark" placeholder="请输入备注" />
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
		</form>
		