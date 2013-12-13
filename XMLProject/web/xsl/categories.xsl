<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : categories.xsl
    Created on : December 8, 2013, 11:07 PM
    Author     : Hoang
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:apply-templates select="categories"/>
    </xsl:template>
    <xsl:template match="categories">
        <xsl:for-each select="category">
            <li>
                <a>
                    <xsl:attribute name="href">/selectCategoryOfProduct.jsp?Condition=categoryID&amp;Val=
                        <xsl:value-of select="categoryID"/>
                    </xsl:attribute>
                    <xsl:attribute name="id">
                        <xsl:value-of select="categoryID"/>
                    </xsl:attribute>
                    <div>
                    <xsl:value-of select="categoryName"/></div>
                </a>

            </li>
           
        </xsl:for-each>

    </xsl:template>

</xsl:stylesheet>
