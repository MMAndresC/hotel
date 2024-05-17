<%@ page import="dao.Database" %>
<%@ page import="dao.BicycleDao" %>
<%@ page import="domain.Bicycle" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@include file="includes/header.jsp"%>

    <main class="container">
        <section class="mb-5">

            <%
                try{
                    Database database = new Database();
                    BicycleDao bicycleDao = new BicycleDao(database.getConnection());
                    List<Bicycle> bicycleList = bicycleDao.showAll();
                    for (Bicycle bicycle : bicycleList) {
            %>
            <div class="row mb-1">
                <div class="col ">
                    <div class="card shadow-sm">
                        <img src="../hotel-data/<%=bicycle.getImage()%>" class="bd-placeholder-img card-img-top"/>
                        <div>
                            <p class="text-center mt-2"><%=bicycle.getBrand()%></p>
                            <p class="text-center mt-2"><%=bicycle.getModel()%></p>
                            <p class="text-center mt-2"><%=bicycle.getType()%></p>
                            <p class="text-center mt-2"><%=bicycle.getSize()%></p>
                        </div>
                        <div class="col-12 col-md-2 ms-md-2 text-truncate mt-2 d-flex justify-content-center">
                            <button type="button" class="btn me-3">
                                <a href="details-bicycle.jsp?cod=<%=bicycle.getCod()%>">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-pen" viewBox="0 0 16 16">
                                        <path
                                                d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z">
                                        </path>
                                    </svg>
                                    <span>Modify</span>
                                </a>
                            </button>

                            <button type="submit" class="btn btn-outline-danger">
                                <a href="delete-bicycle?cod=<%= bicycle.getCod() %>">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-x-octagon-fill" viewBox="0 0 16 16">
                                        <path
                                                d="M11.46.146A.5.5 0 0 0 11.107 0H4.893a.5.5 0 0 0-.353.146L.146 4.54A.5.5 0 0 0 0 4.893v6.214a.5.5 0 0 0 .146.353l4.394 4.394a.5.5 0 0 0 .353.146h6.214a.5.5 0 0 0 .353-.146l4.394-4.394a.5.5 0 0 0 .146-.353V4.893a.5.5 0 0 0-.146-.353L11.46.146zm-6.106 4.5L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z">
                                        </path>
                                    </svg>
                                    <span>Delete</span>
                                </a>
                            </button>
                        </div>
                    </div>

                </div>
            </div>
            <%
                }
                database.close();
            }catch(SQLException sqle){}

            %>

        </section>

        <section class="border border-1 p-5 rounded">
            <form id="formNewBicycle" action="add-bicycle" method="post">
                <div class="row mb-3">
                    <label for="brand" class="col-sm-2 col-form-label">Brand</label>
                    <div class="col-sm-10">
                        <input name="brand" type="text" class="form-control w-25" id="brand" />
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="model" class="col-sm-2 col-form-label">Model</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="model" name="model" />
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="type" class="col-sm-2 col-form-label">Type</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="type" name="type" />
                    </div>
                </div>
                <div class="row mb-3">
                    <span class="col-sm-2 col-form-label">Size</span>
                    <div class="col-sm-10">
                        <select class="form-select" aria-label="Default select example" name="size">
                            <option selected disabled>Select size</option>
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                        </select>
                    </div>
                </div>
                <div class="col-6 d-flex justify-content-center">
                    <img src="../assets/default_bicycle.jpg" alt="default bicycle" id="img" class="img-fluid "/>
                </div>
                <div class="col-6 d-flex flex-column justify-content-center">
                    <label for="image" class="form-label">Image</label>
                    <input type="file" class="form-control w-60" id="image" name="image" accept="image/jpg, image/png, image/jpeg"
                           onchange="document.getElementById('img').src = window.URL.createObjectURL(this.files[0])">
                </div>
                <button type="submit" class="btn btn-primary">Register bicycle</button>
            </form>
        </section>
        <div id="result"></div>
    </main>

<%@include file="includes/footer.jsp"%>


<script >
    $(document).ready(function() {
        var options = {
                target: '#result',
                success: showResponse,
                clearForm: true,
                resetForm: true,
        }
        $('#formNewBicycle').ajaxForm(options);
    });

     function showResponse(responseText, statusText) {
        $("#img").attr("src", "../hotel-data/default_bicycle.jpg");
        if($.trim(responseText) == "success"){
            window.location.href = "/hotel";
        }
     };
</script>