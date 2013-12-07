<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<form class="pure-skin-xml">
    <div class="pure-menu pure-menu-open">
        <a class="pure-menu-heading">Kind of Game</a>
        <ul id="leftPanel">
            <c:import var="genresXML" url="/XML/genres.xml" charEncoding="UTF-8"/>
            <c:import var="genresXSL" url="/WEB-INF/genres.xsl" charEncoding="UTF-8"/>
            <x:transform xml="${genresXML}" xslt="${genresXSL}"/>

        </ul>
    </div>
</form>