<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : orders.xsl
    Created on : June 12, 2013, 10:08 PM
    Author     : Quaikiet
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
xmlns:tns="http://xml.netbeans.org/schema/orders"
>
    <xsl:param name="searchValue"/>
    <xsl:output method="html"/>
    

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
      
       
        <html>
            <body>
                <xsl:apply-templates select="tns:orders"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="tns:orders">
        
        <table border="1">
            <thead>
                <tr>
                    <th>OrderId</th>
                    <th>UserName</th>
                    <th>OrderDate</th>
                    <th>Invoice</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <xsl:choose>
                    <xsl:when test="not ($searchValue='')">
                        <xsl:for-each select="tns:Order[contains(tns:UserName, $searchValue)]">
                            <tr>
                                <td>
                                    <xsl:value-of select="tns:OrderId"/>
                                </td>
                                <td>
                                    <xsl:value-of select="tns:UserName"/>
                                </td>
                                <td>
                                    <xsl:call-template name="FormatDateType">
                                        <xsl:with-param name="dateString" select="tns:OrderDate"/>
                                    </xsl:call-template>
                                </td>
                                <td>

                                    <xsl:value-of select="tns:Invoice"/>$
                                </td>
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="@Delivered='false'">
                                            <input>
                                                <xsl:attribute name="name">btnStatus</xsl:attribute>
                                                <xsl:attribute name="type">button</xsl:attribute>
                                                <xsl:attribute name="value">UnDelivered</xsl:attribute>
                                                <xsl:attribute name="onClick">changeBtnStatus(<xsl:value-of select="tns:OrderId"/>)</xsl:attribute>
                                                <xsl:attribute name="id">btnStatusID<xsl:value-of select="tns:OrderId"/></xsl:attribute>
                                            
                                            </input>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <input>
                                                <xsl:attribute name="name">btnStatus</xsl:attribute>
                                                <xsl:attribute name="type">button</xsl:attribute>
                                                <xsl:attribute name="value">Delivered</xsl:attribute>
                                                <xsl:attribute name="onClick">changeBtnStatus(<xsl:value-of select="tns:OrderId"/>)</xsl:attribute>
                                                <xsl:attribute name="id">btnStatusID<xsl:value-of select="tns:OrderId"/></xsl:attribute>
                                            
                                            </input>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:for-each select="tns:Order">
                            <tr>
                                <td>
                                    <xsl:value-of select="tns:OrderId"/>
                                </td>
                                <td>
                                    <xsl:value-of select="tns:UserName"/>
                                </td>
                                <td>
                                    <xsl:call-template name="FormatDateType">
                                        <xsl:with-param name="dateString" select="tns:OrderDate"/>
                                    </xsl:call-template>
                                </td>
                                <td>

                                    <xsl:value-of select="tns:Invoice"/>$
                                </td>
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="@Delivered='false'">
                                            <input>
                                                <xsl:attribute name="name">btnStatus</xsl:attribute>
                                                <xsl:attribute name="type">button</xsl:attribute>
                                                <xsl:attribute name="value">UnDelivered</xsl:attribute>
                                                <xsl:attribute name="onClick">changeBtnStatus(<xsl:value-of select="tns:OrderId"/>)</xsl:attribute>
                                                <xsl:attribute name="id">btnStatusID<xsl:value-of select="tns:OrderId"/></xsl:attribute>

                                            </input>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <input>
                                                <xsl:attribute name="name">btnStatus</xsl:attribute>
                                                <xsl:attribute name="type">button</xsl:attribute>
                                                <xsl:attribute name="value">Delivered</xsl:attribute>
                                                <xsl:attribute name="onClick">changeBtnStatus(<xsl:value-of select="tns:OrderId"/>)</xsl:attribute>
                                                <xsl:attribute name="id">btnStatusID<xsl:value-of select="tns:OrderId"/></xsl:attribute>

                                            </input>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </xsl:otherwise>
                </xsl:choose>
            </tbody>
        </table>
    </xsl:template>

    <xsl:template name="FormatDateType">
        <!-- input format yyyy-mm-ddThh:mm:ss.mls+gmt -->
     <!--2012-12-12T00:00:00.000+07:00-->
     <!-- output format dd/mm/yyyy ss:mm:hh -->
        <xsl:param name="dateString"/>
        <xsl:variable name="yyyy">
            <xsl:value-of select="substring($dateString,1,4)"/>
        </xsl:variable>
        <xsl:variable name="mm">
            <xsl:value-of select="substring($dateString, 6, 2)"/>
        </xsl:variable>
        <xsl:variable name="dd">
            <xsl:value-of select="substring($dateString, 9, 2)"/>
        </xsl:variable>
        <xsl:variable name="hh">
            <xsl:value-of select="substring($dateString, 12, 2)"/>
        </xsl:variable>
        <xsl:variable name="mimi">
            <xsl:value-of select="substring($dateString, 15, 2)"/>
        </xsl:variable>
        <xsl:variable name="ss">
            <xsl:value-of select="substring($dateString, 18, 2)"/>
        </xsl:variable>
        <xsl:value-of select="$dd"/>
        <xsl:value-of select="'/'"/>
        <xsl:value-of select="$mm"/>
        <xsl:value-of select="'/'"/>
        <xsl:value-of select="$yyyy"/>
        <xsl:value-of select="' '"/>
        <xsl:value-of select="$hh"/>
        <xsl:value-of select="':'"/>
        <xsl:value-of select="$mimi"/>
        <xsl:value-of select="':'"/>
        <xsl:value-of select="$ss"/>
    </xsl:template>

</xsl:stylesheet>
