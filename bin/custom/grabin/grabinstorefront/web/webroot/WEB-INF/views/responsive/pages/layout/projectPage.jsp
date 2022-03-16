<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

projectTableInclude
    <table id="projectTable" class="orderhistory-list-table responsive-table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Approved</th>
            <th scope="col">Ready</th>
            <th scope="col">To be deleted</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projects}" var="project">
            <tr>
                <td class="">${project.projectId}</td>
                <td class="">${project.projectName}</td>
                <td class="">${project.approved}</td>
                <td class="">${project.ready}</td>
                <td class="">${project.toBeDeleted}</td>
                <td>
                    <button id="approve-${project.projectId}" data-id="${project.projectId}" type="button" class="btn btn-success js-project-approve">Approve</button>
                    <button id="ready-${project.projectId}" data-id="${project.projectId}" type="button" class="btn btn-primary js-project-ready">Ready</button>
                    <button id="delete-${project.projectId}" data-id="${project.projectId}" class="btn btn-danger  js-project-delete">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
/projectTableInclude