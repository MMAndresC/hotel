<%@ page import="dao.Database" %>
<%@ page import="dao.GuestDao" %>
<%@ page import="domain.Guest" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@include file="includes/header2.jsp"%>

<main class="container">
    <section class="mb-5">
        <div class="row border-bottom border-2">
            <div class="col-3 col-md-2">DNI</div>
            <div class="col-6 col-md-2">Name</div>
            <div class="col-3 col-md-2">Phone</div>
        </div>
        <%
            Database database = new Database();
            GuestDao guestDao = new GuestDao(database.getConnection());
            try{
                List<Guest> guestList = guestDao.showAll();
                for (Guest guest : guestList) {
        %>
        <div class="row mb-1">

            <div class="col-3 col-md-2 text-truncate"><%= guest.getId() %></div>
            <div class="col-6 col-md-2 text-truncate"><%= guest.getName() %></div>
            <div class="col-6 col-md-2 text-truncate"><%= guest.getRoom() %></div>
            <div class="col-3 col-md-2 text-truncate"><%= guest.getPhone() %></div>
            <div class="col-12 col-md-2 ms-md-2 text-truncate mt-2 d-flex justify-content-center">

                <button type="submit" class="btn btn-outline-danger">
                    <a href="delete-guest?id=<%= guest.getId() %>">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-x-octagon-fill" viewBox="0 0 16 16">
                            <path
                                    d="M11.46.146A.5.5 0 0 0 11.107 0H4.893a.5.5 0 0 0-.353.146L.146 4.54A.5.5 0 0 0 0 4.893v6.214a.5.5 0 0 0 .146.353l4.394 4.394a.5.5 0 0 0 .353.146h6.214a.5.5 0 0 0 .353-.146l4.394-4.394a.5.5 0 0 0 .146-.353V4.893a.5.5 0 0 0-.146-.353L11.46.146zm-6.106 4.5L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z">
                            </path>
                        </svg>
                    </a>
                </button>
            </div>
        </div>
        <%
        }
        }catch(SQLException sqle){}

        %>
    </section>
    <section>
        <form>
            <div class="row mb-3">
                <label for="id" class="col-sm-2 col-form-label">Personal Id</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="id" name="id" />
                </div>
            </div>
            <div class="row mb-3">
                <label for="name" class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name" />
                </div>
            </div>
            <div class="row mb-3">
                <label for="room" class="col-sm-2 col-form-label">Room</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="room" name="room"/>
                </div>
            </div>
            <div class="row mb-3">
                <label for="phone" class="col-sm-2 col-form-label">Phone</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="phone" name="phone"/>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Register Guest</button>
        </form>
    </section>
</main>

<script type="text/javascript">
        $(document).ready(function() {
            $("form").on("submit", function(event) {
                event.preventDefault();
                var formValue = $(this).serialize();
                $.post("add-guest", formValue, function(data) {
                    $("#result").html(data);
                });
                window.location.href=window.location.href;
            });
        });
    </script>