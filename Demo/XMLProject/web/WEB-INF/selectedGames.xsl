<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : games.xsl
    Created on : June 7, 2013, 10:46 PM
    Author     : Quaikiet
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
  xmlns:tns="http://xml.netbeans.org/schema/games" >

    <xsl:output method="html"/>

    <!-- TODO customize transformation rules
         syntax recommendation http://www.w3.org/TR/xslt
    -->
    <xsl:template match="/" >
                <xsl:apply-templates select="tns:games"/>
    </xsl:template>

<xsl:template match="tns:games">
    <div></div>
            <xsl:for-each select="tns:Game[tns:GenreId='1']">
            <div class="main-item">
            <img>
                <xsl:attribute name="src">./img/games/<xsl:value-of select='tns:Image_URL' /></xsl:attribute>
                <xsl:attribute name="title"><xsl:value-of select="tns:Pinboard"/></xsl:attribute>
                </img>

            <div>
                    <xsl:attribute name="id">itemNo<xsl:value-of select="tns:GameId"/></xsl:attribute>
                    <xsl:attribute name="class">itemText</xsl:attribute>
                    <div>
                        <xsl:attribute name="class">gameName</xsl:attribute><xsl:value-of select="tns:Name" />
                    </div>
                    <div>
                        <xsl:attribute name="class">gamePrice</xsl:attribute><xsl:value-of select="tns:Price" />
                    </div>
                    <div class="GameDesc">
                        <xsl:value-of select="tns:Description" />
                        </div>
                </div>
            </div>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
