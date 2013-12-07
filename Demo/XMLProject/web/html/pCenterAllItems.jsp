<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

 

  <div id="listItem">
        <c:import var="gamesXML" url="/XML/games.xml" charEncoding="UTF-8"/>
        <c:import var="gamesXSL" url="/WEB-INF/games.xsl" charEncoding="UTF-8"/>
        <x:transform xml="${gamesXML}" xslt="${gamesXSL}"/>
  </div>
  