<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="custom.css">
</head>
<body>
<div class="container">
    <br>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">JobSearchPRO</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


    </nav>
    <br>

    <div class="bd-example">
        <form action="/search" method="post">
            <div class="form-group row">
                <label for="form-control1" class="col-sm-2 col-form-label col-form-label-sm">Keywords</label>
                <div class="col">
                    <input type="text" class="form-control form-control-sm" name="keyword" value="${searchQuery.keywords[0]}" id="form-control1" placeholder="Keyword1">
                </div>
                <div class="col">
                    <input type="text" class="form-control form-control-sm" name="keyword2" value="${searchQuery.keywords[1]}" placeholder="Keyword2">
                </div>
                <div class="col">
                    <input type="text" class="form-control form-control-sm" name="keyword3" value="${searchQuery.keywords[2]}" placeholder="Keyword3">
                </div>
            </div>

            <div class="form-group row">
                <label for="location" class="col-sm-2 col-form-label col-form-label-sm">Location</label>
                <div class="col">
                    <input type="text" class="form-control form-control-sm" name="location" value="${searchQuery.location}" id="location" placeholder="Location">
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label col-form-label-sm" for="inlineFormCustomSelect">Predefined</label>
                <div class="col">
                    <select class="form-control form-control-sm" name="predefinedSelect" id="inlineFormCustomSelect">
                        <option selected></option>
                        <option value="1">Junior Java, Młodszy Programista</option>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <div class="col">
                    <button type="submit" class="btn btn-primary btn-sm">Search</button>
                </div>
            </div>
        </form>
    </div>

    <br>

    <ul class="list-group">


        <div class="row">
            <div class="col">
                <p>Results: ${fn:length(searchResult.jobPostingList)}</p>

                <c:forEach items="${searchResult.jobPostingList}" var="jobPosting">

                    <h3><a href="${jobPosting.URL}">${jobPosting.title}</a></h3>
                    <p class="job_provider"><span class="job_date">${jobPosting.dateString}</span>
                        <small> - ${jobPosting.provider}</small>
                    </p>
                    <p>${jobPosting.description}</p>

                </c:forEach>


            </div>


        </div>
        <%@ include file="footer.htm" %>

