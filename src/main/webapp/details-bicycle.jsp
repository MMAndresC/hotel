<%@ page import="dao.Database" %>
<%@ page import="dao.BicycleDao" %>
<%@ page import="domain.Bicycle" %>
<%@ page import="java.sql.SQLException" %>
<%@include file="includes/header.jsp"%>

<main>
    <%
        int cod = Integer.parseInt(request.getParameter("cod"));
        Database database = new Database();
        BicycleDao bicycleDao = new BicycleDao(database.getConnection());

        try{
            Bicycle bicycle = bicycleDao.findByCod(cod);
            if(bicycle.getCod() != -1){
    %>
    <img src="../hotel-data/<%  request.getRequestURL(); %>" alt="bicycle image"  class="img-fluid "/>
    <section class="border border-1 p-5 rounded">
        <form id="formModifyBicycle" action="modify-bicycle" method="post">
            <input name="cod" type="hidden" class="form-control w-25" id="cod"  value="<% bicycle.getCod(); %>"/>
            <div class="row mb-3">
                <label for="brand" class="col-sm-2 col-form-label">Brand</label>
                <div class="col-sm-10">
                    <input name="brand" type="text" class="form-control w-25" id="brand" value="<% bicycle.getBrand(); %>"/>
                </div>
            </div>
            <div class="row mb-3">
                <label for="model" class="col-sm-2 col-form-label">Model</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="model" name="model" value="<% bicycle.getModel(); %>"/>
                </div>
            </div>
            <div class="row mb-3">
                <label for="type" class="col-sm-2 col-form-label">Type</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="type" name="type" value="<% bicycle.getType(); %>"/>
                </div>
            </div>
            <div class="row mb-3">
                <input type="hidden" class="form-control" id="init-size" name="init-size" value="<% bicycle.getImage(); %>"/>
                <span class="col-sm-2 col-form-label">Size</span>
                <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example" name="size" id="size">
                        <option value="S"> S</option>
                        <option value="M">M</option>
                        <option value="L">L</option>
                    </select>
                </div>
            </div>
            <div class="col-6 d-flex justify-content-center">
                <img src="../hotel-data/<% bicycle.getImage(); %>" alt="bicycle image" id="img" class="img-fluid "/>
            </div>
            <input type="hidden" class="form-control" id="image" name="image" value="<% bicycle.getImage(); %>"/>
            <div class="col-6 d-flex flex-column justify-content-center">
                <label for="newImg" class="form-label">Image</label>
                <input type="file" class="form-control w-60" id="newImg" name="newImg" accept="image/jpg, image/png, image/jpeg"
                       onchange="document.getElementById('img').src = window.URL.createObjectURL(this.files[0])">
            </div>
            <button type="submit" class="btn btn-primary">Save changes</button>
        </form>
    </section>
    <%
            }else{
                response.sendRedirect("index.jsp");
            }
        }catch(SQLException sqle){
            response.sendRedirect("/index.jsp");
        }
    %>
    <div id="result"></div>
</main>

<script >
    $(document).ready(function() {
        var options = {
                target: '#result',
                success: showResponse,
                clearForm: true,
                resetForm: true,
        }
        $('#formModifyBicycle').ajaxForm(options);
        let initSize = $('#init-size').val();
        $('#size').val(initSize);
    });

     function showResponse(responseText, statusText) {
        $("#img").attr("src", "../assets/default_bicycle.jpg");
        if($.trim(responseText) == "success"){
            window.location.href = "/hotel";
        }
     };
</script>