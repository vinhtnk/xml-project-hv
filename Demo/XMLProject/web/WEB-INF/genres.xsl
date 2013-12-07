<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : genres.xsl
    Created on : June 10, 2013, 3:31 PM
    Author     : burningk
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
xmlns:tns="http://xml.netbeans.org/schema/genres" exclude-result-prefixes="tns">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">  
                <xsl:apply-templates select="tns:genres"/>
    </xsl:template>
    <xsl:template match="tns:genres">
        <xsl:for-each select="tns:genre">
            <li>
                <a>
                    <xsl:attribute name="href">/selectKindOfGame.jsp?Condition=GenreId&amp;Val=<xsl:value-of select="tns:GenreId"/></xsl:attribute>
                    <xsl:attribute name="id"><xsl:value-of select="tns:GenreId"/></xsl:attribute>
                    <xsl:value-of select="tns:GenreName"/>
                </a>

            </li>
        </xsl:for-each>
        
    </xsl:template>

</xsl:stylesheet>
