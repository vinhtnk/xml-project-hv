<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : games.xsl
    Created on : June 17, 2013, 12:55 AM
    Author     : Quaikiet
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
xmlns:tns="http://xml.netbeans.org/schema/games"
>
    <xsl:output method="html"/>
    <xsl:param name="searchValue"/>
    <xsl:param name="fromPrice"/>
    <xsl:param name="toPrice"/>
    <xsl:param name="sortName"/>
    <xsl:param name="sortOrder"/>
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <body>
                <xsl:apply-templates select="tns:games"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="tns:games">
        <table border="1" width="2px" class="pure-table pure-table-horizontal adminTbl">
            <thead>
                <tr>
                    <th>GameId</th>
                    <th>
                        <input value="Name" name= "btnSortName"  type="button">
                            <xsl:attribute name="onclick">applyXslTemplateWithXmlGames('<xsl:value-of select="$searchValue"/>', '<xsl:value-of select="$fromPrice"/>', '<xsl:value-of select="$toPrice"/>', 'Name', '<xsl:value-of select="$sortOrder"/>')</xsl:attribute>
                        </input>
                    </th>
                    <th>
                        <input value="Price" name= "btnSortPrice" type="button">
                            <xsl:attribute name="onclick">applyXslTemplateWithXmlGames('<xsl:value-of select="$searchValue"/>', '<xsl:value-of select="$fromPrice"/>', '<xsl:value-of select="$toPrice"/>', 'Price', '<xsl:value-of select="$sortOrder"/>')
                            </xsl:attribute>
                        </input>
                    </th>
                    <th>PinBoard</th>
                    <th>Description</th>
                    <th>asdasdasdasd</th>
                    <th>asdasdasdasdasdasd</th>
                </tr>
            </thead>
            <tbody>
                  
                        <xsl:choose>
                            <xsl:when test="$sortName='Price'">
                                <xsl:for-each select="tns:Game[(contains(tns:Name, $searchValue)) and (( tns:Price >= $fromPrice) and ( $toPrice >= tns:Price ))]">
                                    <xsl:sort select="*[name()=$sortName]" order="{$sortOrder}" data-type="number"/>
                                      <tr>
                                         <td>
                                            <xsl:value-of select="tns:GameId"/>
                                        </td>
                                       <td>
                                         <input name="updateName" type="text">
                                              <xsl:attribute name="id">btnUpdateGameName<xsl:value-of select="tns:GameId"/></xsl:attribute>
                                          <xsl:attribute name="value"><xsl:value-of select="tns:Name"/></xsl:attribute>
                                          <xsl:attribute name="onchange">updateItemByGameIdAndFieldName('<xsl:value-of select="tns:GameId"/>', 'Name')</xsl:attribute></input>
                                        </td>
                                       <td>
                                         <input name="updatePrice" type="text">
                                              <xsl:attribute name="id">btnUpdateGamePrice<xsl:value-of select="tns:GameId"/></xsl:attribute>
                                          <xsl:attribute name="value"><xsl:value-of select="tns:Price"/></xsl:attribute>
                                          <xsl:attribute name="onchange">updateItemByGameIdAndFieldName('<xsl:value-of select="tns:GameId"/>', 'Price')</xsl:attribute></input>
                                        </td>
                                        <td>
                                         <input name="updatePinboard" type="text">
                                              <xsl:attribute name="id">btnUpdateGamePinboard<xsl:value-of select="tns:GameId"/></xsl:attribute>
                                          <xsl:attribute name="value"><xsl:value-of select="tns:Pinboard"/></xsl:attribute>
                                          <xsl:attribute name="onchange">updateItemByGameIdAndFieldName('<xsl:value-of select="tns:GameId"/>', 'Pinboard')</xsl:attribute></input>
                                        </td>
                                        <td>
                                         <input name="updateDescription" type="text">
                                              <xsl:attribute name="id">btnUpdateGameDescription<xsl:value-of select="tns:GameId"/></xsl:attribute>
                                          <xsl:attribute name="value"><xsl:value-of select="tns:Description"/></xsl:attribute>
                                          <xsl:attribute name="onchange">updateItemByGameIdAndFieldName('<xsl:value-of select="tns:GameId"/>', 'Description')</xsl:attribute></input>
                                        </td>
                                        
                                    </tr>
                                </xsl:for-each>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:for-each select="tns:Game[(contains(tns:Name, $searchValue)) and (( tns:Price >= $fromPrice) and ( $toPrice >= tns:Price ))]">
                                    <xsl:sort select="*[name()=$sortName]" order="{$sortOrder}" data-type="text"/>
                                    <tr>
                                         <td>
                                            <xsl:value-of select="tns:GameId"/>
                                        </td>
                                       <td>
                                         <input name="updateName" type="text">
                                              <xsl:attribute name="id">btnUpdateGameName<xsl:value-of select="tns:GameId"/></xsl:attribute>
                                          <xsl:attribute name="value"><xsl:value-of select="tns:Name"/></xsl:attribute>
                                          <xsl:attribute name="onchange">updateItemByGameIdAndFieldName('<xsl:value-of select="tns:GameId"/>', 'Name')</xsl:attribute></input>
                                        </td>
                                       <td>
                                         <input name="updatePrice" type="text">
                                              <xsl:attribute name="id">btnUpdateGamePrice<xsl:value-of select="tns:GameId"/></xsl:attribute>
                                          <xsl:attribute name="value"><xsl:value-of select="tns:Price"/></xsl:attribute>
                                          <xsl:attribute name="onchange">updateItemByGameIdAndFieldName('<xsl:value-of select="tns:GameId"/>', 'Price')</xsl:attribute></input>
                                        </td>
                                        <td>
                                         <input name="updatePinboard" type="text">
                                              <xsl:attribute name="id">btnUpdateGamePinboard<xsl:value-of select="tns:GameId"/></xsl:attribute>
                                          <xsl:attribute name="value"><xsl:value-of select="tns:Pinboard"/></xsl:attribute>
                                          <xsl:attribute name="onchange">updateItemByGameIdAndFieldName('<xsl:value-of select="tns:GameId"/>', 'Pinboard')</xsl:attribute></input>
                                        </td>
                                   <td>
                                         <input name="updateDescription" type="text">
                                              <xsl:attribute name="id">btnUpdateGameDescription<xsl:value-of select="tns:GameId"/></xsl:attribute>
                                          <xsl:attribute name="value"><xsl:value-of select="tns:Description"/></xsl:attribute>
                                          <xsl:attribute name="onchange">updateItemByGameIdAndFieldName('<xsl:value-of select="tns:GameId"/>', 'Description')</xsl:attribute></input>
                                        </td>
                                        
                                    </tr>
                                </xsl:for-each>
                            </xsl:otherwise>
                        </xsl:choose>
                              
                 
            </tbody>
        </table>
    </xsl:template>

</xsl:stylesheet>
