<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>
<body>
<div class="container">
    <form class="form-horizontal" method="post" enctype="multipart/form-data">
        <fieldset>

            <!-- Form Name -->
            <legend>Delete user:</legend>

            <!-- User Info -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="login">Login</label>
                <div class="col-md-4">
                    <input
                            id="login"
                            name="login"
                            type="text"
                            value="${requestScope.user.login}"
                            class="form-control input-md"
                            readonly>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="role">Role</label>
                <div class="col-md-4">
                    <input
                            id="role"
                            name="role"
                            type="text"
                            value="${requestScope.user.role}"
                            class="form-control input-md"
                            readonly>
                </div>
            </div>

            <!-- Button (Delete) -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="delete">Operation</label>
                <div class="col-md-8">
                    <button id="delete" name="delete" class="btn btn-danger">Delete</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
<%@include file="parts/footer.jsp" %>
