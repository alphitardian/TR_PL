<%--
    Document   : borrowed
    Created on : Dec 9, 2020, 11:59:35 AM
    Author     : Kelvin
--%>
<%@page import="models.User"%>
<%@page import="daos.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="daos.BookDao"%>
<%@page import="models.Book"%>
<%@page import="java.util.List"%>
<%
    if (session.isNew()) {
        response.sendRedirect("login.jsp");
    } else if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<%
    UserDao userDao = new UserDao();

    User user = userDao.getUserById(session.getId());
%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Books - PerpusApp</title>

        <!-- Custom fonts for this template-->
        <link href="../assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="../assets/css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="../assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@ include file="components/navigation.jspf" %>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <%@ include file="components/topbar.jspf" %>

                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Books</h1>
                            <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm btn-add" data-toggle="modal" data-target="#bookModal"><i class="fas fa-plus fa-sm text-white-50"></i> Add Book</a>
                        </div>

                        <!-- Content Row -->

                        <div class="row">

                            <!-- Area Chart -->
                            <div class="col-xl-12 col-lg-12">
                                <div class="card shadow mb-4">
                                    <!-- Card Header - Dropdown -->
                                    <div
                                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <h6 class="m-0 font-weight-bold text-primary">Book Overview</h6>
                                        <div class="dropdown no-arrow">
                                            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                                 aria-labelledby="dropdownMenuLink">
                                                <div class="dropdown-header">Dropdown Header:</div>
                                                <a class="dropdown-item" href="#">Action</a>
                                                <a class="dropdown-item" href="#">Another action</a>
                                                <div class="dropdown-divider"></div>
                                                <a class="dropdown-item" href="#">Something else here</a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Card Body -->
                                    <div class="card-body">
                                        <table class="table table-bordered dataTable-enable" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Book</th>
                                                    <th>Due Date</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Book</th>
                                                    <th>Due Date</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <%
                                                    for (int i = 0; i < user.getBorrowedList().size(); i++) {
                                                %>
                                                <tr>
                                                    <td><%= user.getBorrowedList().get(i).getId()%></td>
                                                    <td><%= user.getBorrowedList().get(i).getBook().getTitle()%></td>
                                                    <td><%= user.getBorrowedList().get(i).getDueDate()%></td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2020</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <%@ include file="components/logoutModal.jspf" %>


        <!-- Bootstrap core JavaScript-->
        <script src="../assets/vendor/jquery/jquery.min.js"></script>
        <script src="../assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="../assets/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="../assets/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="../assets/vendor/chart.js/Chart.min.js"></script>

        <!-- Page level plugins -->
        <script src="../assets/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="../assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <script>
            $('.dataTable-enable').DataTable();

            $(document).on("click", ".btn-edit", function () {
                $('#formEditBook').attr('action', "UpdateBook");

                var id = $(this).data('id');
                var isbn = $(this).data('isbn');
                var title = $(this).data('title');
                var author = $(this).data('author');
                var publisher = $(this).data('publisher');
                var availability = $(this).data('availability');

                $(".modal-body #id").val(id);
                $(".modal-body #isbn").val(isbn);
                $(".modal-body #title").val(title);
                $(".modal-body #author").val(author);
                $(".modal-body #publisher").val(publisher);
                $(".modal-body #availability").val(availability);
            });

            $(document).on("click", ".btn-add", function () {

                $('#formEditBook').attr('action', "AddBook");

                $(".modal-body #id").val("");
                $(".modal-body #isbn").val("");
                $(".modal-body #title").val("");
                $(".modal-body #author").val("");
                $(".modal-body #publisher").val("");
                $(".modal-body #availability").val("");
            });
        </script>

    </body>

</html>