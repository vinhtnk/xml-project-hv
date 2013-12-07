<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : orders.xsl
    Created on : June 12, 2013, 10:08 PM
    Author     : Quaikiet
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
xmlns:tns="http://xml.netbeans.org/schema/orders">
   
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <>
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
                    <th>UserId</th>
                    <th>OrderDate</th>
                    <th>Invoice</th>
                    <th>Delivered</th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="tns:Order">
                    <tr>
                        <td>
                            <xsl:value-of select="tns:OrderId"/>
                        </td>
                        <td>
                            <xsl:value-of select="tns:UserId"/>
                        </td>
                        <td>
                            <xsl:value-of select="tns:OrderDate"/>
                        </td>
                        <td>

                            <xsl:value-of select="tns:Invoice"/>$
                        </td>
                        <td>
                            <xsl:if test="@Delivered='false'">
                             No
                            </xsl:if>
                        </td>
                    </tr>
                </xsl:for-each>
            </tbody>
        </table>
    </xsl:template>
</xsl:stylesheet>
